package org.radekbor.domains.user;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Getter;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    public BaseEntity(long id) {
        this.id = id;
    }

    public BaseEntity() {

    }

    public Long getId() {
        return id;
    }
}