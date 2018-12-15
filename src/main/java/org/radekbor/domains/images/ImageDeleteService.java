package org.radekbor.domains.images;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ImageDeleteService {

    private ImageDetailsRepository imageDetailsRepository;

    @Autowired
    private ImageDeleteService(ImageDetailsRepository imageDetailsRepository) {
        this.imageDetailsRepository = imageDetailsRepository;
    }


    public void deleteImage(long id, long userId) throws IllegalAccessException {
        Optional<ImageDetails> imageDetailsOptional = imageDetailsRepository.findById(id);
        if (imageDetailsOptional.isPresent()) {
            ImageDetails imageDetails = imageDetailsOptional.get();
            if (imageDetails.getUserId() != userId) {
                throw new IllegalAccessException("Forbidden");
            }
            imageDetailsRepository.delete(imageDetails);
        }
    }
}
