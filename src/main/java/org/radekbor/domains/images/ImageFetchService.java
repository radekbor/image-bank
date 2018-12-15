package org.radekbor.domains.images;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class ImageFetchService {

    private ImageDetailsRepository imageDetailsRepository;

    private ImageRepository imageRepository;

    @Autowired
    ImageFetchService(ImageDetailsRepository imageDetailsRepository, ImageRepository imageRepository) {
        this.imageDetailsRepository = imageDetailsRepository;
        this.imageRepository = imageRepository;
    }

    public Page<ImageInformation> getUserImages(long id, Pageable pageable) {
        Page<ImageDetails> allByUserId = imageDetailsRepository.findAllByUserId(id, pageable);
        return allByUserId.map(this::convert);
    }

    private ImageInformation convert(ImageDetails imageDetails) {
        List<Long> images = imageDetails.getImages().stream().map(Image::getId).collect(Collectors.toList());
        return new ImageInformation(imageDetails.getId(), imageDetails.getName(), images);
    }

    public Image getImage(long id) {
        return imageRepository.getOne(id);
    }

    public Page<ImageInformation> getUserImages(long id, String name, Pageable pageable) {
        Page<ImageDetails> allByUserId = imageDetailsRepository.findAllByUserIdAndName(id, name, pageable);
        return allByUserId.map(this::convert);
    }
}
