package org.radekbor.domains.images;

import java.util.List;

public class ImageInformation {

    private Long id;
    private String name;
    private List<Long> images;

    public ImageInformation(Long id, String name, List<Long> images) {
        this.id = id;
        this.name = name;
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public List<Long> getImages() {
        return images;
    }
}
