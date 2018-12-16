package org.radekbor.domains.images;


import org.apache.tika.Tika;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class ImageTypeValidatorTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"image/bmp", true},
                {"image/gif", true},
                {"image/jpeg", true},
                {"image/tiff", true},
                {"image/png", true},
                {"image/xxxx", false},
                {"application/pdf", false},
        });
    }

    private String input;

    private boolean expected;

    public ImageTypeValidatorTest(String input, boolean expected) {
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void shouldReturnProperValue() throws IOException {
        Tika tika = mock(Tika.class);
        MultipartFile multipartFile = mock(MultipartFile.class);

        ImageTypeValidator imageTypeValidator = new ImageTypeValidator(tika);
        when(tika.detect(any(byte[].class))).thenReturn(input);

        boolean result = imageTypeValidator.isImage(multipartFile);

        assertThat(result).isEqualTo(expected);
    }

}