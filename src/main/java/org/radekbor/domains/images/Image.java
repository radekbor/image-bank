package org.radekbor.domains.images;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
public class Image {

    public Image() {
        // FOR HIBERNATE
        // TODO public due issue with hibernate.bytecode.use_reflection_optimize
    }

    @Id
    @SequenceGenerator(name = "image_id_gen", sequenceName = "image_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_id_gen")
    private Long id;

    @ManyToOne
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "image_details")
    private ImageDetails imageDetails;

    @Column(name = "bytes", columnDefinition = "BLOB")
    private byte[] bytes;

    @Enumerated(value = EnumType.STRING)
    private ImageType imageType;

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

    public Long getId() {
        return id;
    }
}
