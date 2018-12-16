package org.radekbor.domains.images;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ImageDeleteServiceTest {

    @InjectMocks
    private ImageDeleteService imageDeleteService;

    @Mock
    private ImageDetailsRepository imageDetailsRepository;

    private static final long USER_ID = 100L;

    @Test
    public void shouldDeleteImageWhenInvokedByOwner() throws IllegalAccessException {
        long imageID = 1L;
        ImageDetails imageDetails = mock(ImageDetails.class);
        when(imageDetails.getUserId()).thenReturn(USER_ID);
        when(imageDetailsRepository.findById(imageID)).thenReturn(Optional.of(imageDetails));

        imageDeleteService.deleteImage(imageID, USER_ID);

        verify(imageDetailsRepository).delete(imageDetails);
    }

    @Test(expected = IllegalAccessException.class)
    public void shouldNotDeleteImageWhenNotInvokedByOwner() throws IllegalAccessException {
        long imageID = 1L;
        ImageDetails imageDetails = mock(ImageDetails.class);
        when(imageDetails.getUserId()).thenReturn(USER_ID);
        when(imageDetailsRepository.findById(imageID)).thenReturn(Optional.of(imageDetails));

        imageDeleteService.deleteImage(imageID, USER_ID + 1);
    }
}