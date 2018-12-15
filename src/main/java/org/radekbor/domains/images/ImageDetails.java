package org.radekbor.domains.images;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class ImageDetails {

    @Id
    @SequenceGenerator(name = "image_details_id_gen", sequenceName = "image_details_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_details_id_gen")
    private Long id;

    private Long userId;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "imageDetails")
    private List<Image> images = new ArrayList<>();


    private ImageDetails() {
        // FOR HIBERNATE
    }

    public ImageDetails(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public List<Image> getImages() {
        return Collections.unmodifiableList(this.images);
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void addImage(Image image) {
        images.add(image);
    }

}
