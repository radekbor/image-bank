package org.radekbor.domains.images;


import org.apache.tika.Tika;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class ImageTypeToMediaTypeTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"image/bmp", Optional.of(MediaType.valueOf("image/bmp"))},
                {"image/gif", Optional.of(MediaType.IMAGE_GIF)},
                {"image/jpeg", Optional.of(MediaType.IMAGE_JPEG)},
                {"image/tiff", Optional.of(MediaType.valueOf("image/tiff"))},
                {"image/png", Optional.of(MediaType.IMAGE_PNG)},
                {"image/xxxx", Optional.empty()},
                {"application/pdf", Optional.empty()},
        });
    }

    private String input;

    private Optional<MediaType> expected;

    public ImageTypeToMediaTypeTest(String input, Optional<MediaType> optionalMediaType) {
        this.input = input;
        this.expected = optionalMediaType;
    }

    @Test
    public void shouldReturnProperValue() throws IOException {
        Tika tika = mock(Tika.class);

        ImageTypeValidator imageTypeValidator = new ImageTypeValidator(tika);
        when(tika.detect(any(byte[].class))).thenReturn(input);

        Optional<MediaType> mediaType = imageTypeValidator.getType(new byte[]{});

        assertThat(mediaType).isEqualTo(expected);
    }

}