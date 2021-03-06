package org.radekbor.domains.images;

import org.radekbor.domains.user.account.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@RestController
public class ImagesController {


    @Autowired
    ImagesController(ImageSaveService imageSaveService,
                     ImageFetchService imageFetchService,
                     ImageDeleteService imageDeleteService,
                     ImageTypeValidator imageTypeValidator) {
        this.imageSaveService = imageSaveService;
        this.imageFetchService = imageFetchService;
        this.imageDeleteService = imageDeleteService;
        this.imageTypeValidator = imageTypeValidator;
    }

    private ImageSaveService imageSaveService;

    private ImageFetchService imageFetchService;

    private ImageDeleteService imageDeleteService;
    private ImageTypeValidator imageTypeValidator;

    // TODO extract to another bean
    private CustomUserDetails getDetails() {
        return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping(value = "/images/upload")
    public Long addImage(@RequestParam("file") MultipartFile file) throws IOException {
        CustomUserDetails details = getDetails();
        if (!imageTypeValidator.isImage(file)) {
            throw new IllegalArgumentException();
        }
        ImageDetails imageDetails = new ImageDetails(details.getId(), file.getOriginalFilename());
        ImageDetails saved = imageSaveService.save(imageDetails, file.getBytes());
        return saved.getId();
    }

    @GetMapping(value = "/images")
    public Page<ImageInformation> getImages(@RequestParam(value = "name", required = false) String name, Pageable pageable) {
        CustomUserDetails details = getDetails();
        if (name == null) {
            return imageFetchService.getUserImages(details.getId(), pageable);
        } else {
            return imageFetchService.getUserImages(details.getId(), name, pageable);
        }
    }


    @GetMapping(value = "/images/download/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") long id) throws IllegalAccessException {
        CustomUserDetails details = getDetails();
        Image image = imageFetchService.getImage(id);
        if (image.getImageDetails().getUserId() != details.getId()) {
            throw new IllegalAccessException("FORBIDDEN");
        }

        byte[] bytes = image.getBytes();
        Optional<MediaType> mediaType = imageTypeValidator.getType(bytes);
        if (mediaType.isPresent()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(mediaType.get());
            headers.setCacheControl(CacheControl.noCache().getHeaderValue());
            return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
        } else {
            throw new IllegalStateException();
        }
    }

    @DeleteMapping(value = "/images/{imageDetailsId}")
    public void deleteImage(@PathVariable("imageDetailsId") long imageDetailsId) throws IllegalAccessException {
        CustomUserDetails details = getDetails();

        imageDeleteService.deleteImage(imageDetailsId, details.getId());
    }
}