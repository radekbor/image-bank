package org.radekbor.domains.images;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageSaveServiceIT {

    @Autowired
    private ImageSaveService imageSaveService;

    @Autowired
    private ImageFetchService imageFetchService;

    @Test
    public void shouldStoreImageInDb() {

        ImageDetails imageDetails = new ImageDetails(1L, "name");

        imageSaveService.save(imageDetails, new byte[]{1, 2, 3});

        Page<ImageInformation> userImages = imageFetchService.getUserImages(1l, PageRequest.of(0, 20));

        assertThat(userImages.getContent()).isNotEmpty();
        ImageInformation savedImage = userImages.getContent().get(0);
        assertThat(savedImage.getImages()).isNotEmpty();
    }

}