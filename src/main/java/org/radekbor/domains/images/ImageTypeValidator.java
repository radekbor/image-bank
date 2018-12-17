package org.radekbor.domains.images;

import org.apache.commons.lang.ArrayUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Component
public class ImageTypeValidator {

    private String[] IMAGE_MIMES = {
            "image/bmp", "image/gif",
            "image/jpeg", "image/tiff", "image/png"};


    private Tika tika;

    @Autowired
    ImageTypeValidator() {
        this(new Tika());
    }

    ImageTypeValidator(Tika tika) {
        this.tika = tika;
    }

    boolean isImage(MultipartFile file) throws IOException {
        String detected = tika.detect(file.getBytes());
        return ArrayUtils.contains(IMAGE_MIMES, detected);
    }

    Optional<MediaType> getType(byte[] bytes) {
        String detected = tika.detect(bytes);
        if (ArrayUtils.contains(IMAGE_MIMES, detected)) {
            return Optional.of(MediaType.valueOf(detected));
        } else {
            return Optional.empty();
        }

    }
}
