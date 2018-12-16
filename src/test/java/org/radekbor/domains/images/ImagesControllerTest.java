package org.radekbor.domains.images;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.radekbor.config.CustomControllerAdvice;
import org.radekbor.domains.user.account.CustomUserDetails;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ImagesControllerTest {

    private MockMvc mockMvc;
    private ImageSaveService imageSaveService = mock(ImageSaveService.class);
    private ImageFetchService imageFetchService = mock(ImageFetchService.class);
    private ImageDeleteService imageDeleteService = mock(ImageDeleteService.class);
    private ImageTypeValidator imageTypeValidator = mock(ImageTypeValidator.class);
    private ImagesController controller = new ImagesController(
            imageSaveService,
            imageFetchService,
            imageDeleteService,
            imageTypeValidator
    );

    private static final long USER_ID = 1L;

    @Before
    public void setUp() {
        Authentication authentication = mock(Authentication.class);
        CustomUserDetails customUserDetails = mock(CustomUserDetails.class);
        when(customUserDetails.getId()).thenReturn(USER_ID);
        when(authentication.getPrincipal()).thenReturn(customUserDetails);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(CustomControllerAdvice.class).build();
    }

    @Test
    public void shouldStoreFile() throws Exception {

        MockMultipartFile firstFile = new MockMultipartFile("file", "filename.png", "image/png", "xyz".getBytes());

        ImageDetails imageDetails = mock(ImageDetails.class);
        when(imageDetails.getId()).thenReturn(1L);
        when(imageSaveService.save(any(ImageDetails.class), any())).thenReturn(imageDetails);
        when(imageTypeValidator.isImage(any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/images/upload")
                .file(firstFile))
                .andExpect(status().is(200))
                .andExpect(content().string("1"));

    }

    @Test
    public void shouldNotStoreFileWhenNotImage() throws Exception {

        MockMultipartFile firstFile = new MockMultipartFile("file", "filename.txt", "text/plain", "some plain text".getBytes());

        when(imageTypeValidator.isImage(any())).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/images/upload")
                .file(firstFile))
                .andExpect(status().is5xxServerError());

    }
}