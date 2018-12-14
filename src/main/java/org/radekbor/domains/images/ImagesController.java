package org.radekbor.domains.images;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ImagesController {

    @GetMapping(value = "/images")
    public List<String> getUrls(@AuthenticationPrincipal User activeUser) {
        // TODO return list of all images urls
        return new ArrayList<>();
    }
}
