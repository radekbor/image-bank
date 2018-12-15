package org.radekbor.domains.images;

import org.radekbor.domains.user.account.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.ArrayList;


@RestController
public class ImagesController {


    @Autowired
    ImagesController(ImageSaveService imageSaveService) {
        this.imageSaveService = imageSaveService;
    }

    private ImageSaveService imageSaveService;

    private ImageFetchService imageFetchService;

    @PostMapping(value = "/images/upload")
    public Long addImage(@RequestParam("file") MultipartFile file) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails details = (CustomUserDetails) authentication.getDetails();

        ImageDetails imageDetails = new ImageDetails(details.getId(), file.getName());
        ImageDetails saved = imageSaveService.save(imageDetails, file.getBytes());
        return saved.getId();
    }

    @GetMapping(value = "/images")
    public Page<ImageInformation> getImages(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails details = (CustomUserDetails) authentication.getDetails();
        return imageFetchService.getUserImages(details.getId(), pageable);
    }

    @GetMapping(value = "/images/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") long id) throws IllegalAccessException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails details = (CustomUserDetails) authentication.getDetails();

        Image image = imageFetchService.getImage(id);

        if (image.getImageDetails().getUserId() != details.getId()) {
            throw new IllegalAccessException("FORBIDDEN");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());

        return new ResponseEntity<>(image.getBytes(), headers, HttpStatus.OK);
    }
}
