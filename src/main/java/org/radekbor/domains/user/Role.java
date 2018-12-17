package org.radekbor.domains.user;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @SequenceGenerator(name="roles_id_gen", sequenceName="roles_id_seq")
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
