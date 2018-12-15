package org.radekbor.domains.images;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ImageFetchService {

    private ImageDetailsRepository imageDetailsRepository;

    private ImageRepository imageRepository;

    @Autowired
    ImageFetchService(ImageDetailsRepository imageDetailsRepository, ImageRepository imageRepository) {
        this.imageDetailsRepository = imageDetailsRepository;
    }

    public Page<ImageInformation> getUserImages(long id, Pageable pageable) {
        Page<ImageDetails> allByUserId = imageDetailsRepository.findAllByUserId(id, pageable);

        return allByUserId.map(this::convert);
    }

    private ImageInformation convert(ImageDetails imageDetails) {
        return new ImageInformation(imageDetails.getId(), imageDetails.getName());
    }

    public Image getImage(long id) {
        return imageRepository.getOne(id);
    }
}
