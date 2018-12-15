package org.radekbor.domains.images;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImageSaveService {

    private ImageDetailsRepository imageDetailsRepository;

    @Autowired
    private ImageSaveService(ImageDetailsRepository imageDetailsRepository) {
        this.imageDetailsRepository = imageDetailsRepository;
    }

    public ImageDetails save(ImageDetails imageDetails, byte[] bytes) {
        imageDetails.addImage(new Image(imageDetails, bytes, ImageType.FULL));
        return this.imageDetailsRepository.save(imageDetails);
    }
}
