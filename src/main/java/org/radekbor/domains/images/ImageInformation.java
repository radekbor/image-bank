package org.radekbor.domains.images;

public class ImageInformation {

    private Long id;
    private String name;

    public ImageInformation(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
