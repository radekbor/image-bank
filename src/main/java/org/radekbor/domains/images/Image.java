package org.radekbor.domains.images;

import javax.persistence.*;

@Entity
public class Image {

    @Id
    @SequenceGenerator(name = "image_id_gen", sequenceName = "image_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_id_gen")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imageDetails")
    private ImageDetails imageDetails;

    private byte[] bytes;

    @Enumerated(value = EnumType.STRING)
    private ImageType imageType;

    private Image() {
        // FOR HIBERNATE
    }

    public Image(ImageDetails imageDetails, byte[] bytes, ImageType imageType) {
        this.imageDetails = imageDetails;
        this.bytes = bytes;
        this.imageType = imageType;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public ImageDetails getImageDetails() {
        return imageDetails;
    }
}
