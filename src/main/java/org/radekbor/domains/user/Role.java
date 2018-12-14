package org.radekbor.domains.user;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    @SequenceGenerator(name="roles_id_gen", sequenceName="ROLES_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_id_gen")
    private Long id;

    private String name;

    private Role() {
      // for Hiberante
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
