package org.radekbor.domains.images;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional// TODO remove it
public class ImageSaveServiceIT {

    @Autowired
    private ImageSaveService imageSaveService;

    @Autowired
    private ImageDetailsRepository imageDetailsRepository;

    @Test
    public void shouldStoreImageInDb() {

        ImageDetails imageDetails = new ImageDetails(1L, "name");
        Long id = imageSaveService.save(imageDetails, new byte[]{1, 2, 3}).getId();

        List<ImageDetails> all = imageDetailsRepository.findAll();
        assertThat(all).isNotEmpty();
        ImageDetails savedImage = all.get(0);
        assertThat(savedImage.getImages()).isNotEmpty();
    }
}