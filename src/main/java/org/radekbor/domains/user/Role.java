package org.radekbor.domains.user;

import javax.persistence.Entity;

@Entity
public class Role extends BaseEntity {

    private String name;

    private Role() {
      // for Hiberante
    }

    public Role(String name) {
        super();
        this.name = name;
    }

    public Role(long id) {
        super(id);
    }

    public String getName() {
        return name;
    }
}
