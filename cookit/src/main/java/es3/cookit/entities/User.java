package es3.cookit.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.oidc.OidcTenantConfig.Authentication;
import io.quarkus.resteasy.runtime.SecurityContextFilter;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.persistence.Entity;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;

@Entity
public class User extends PanacheEntity {

    private String name;

    private String lastName;

    private Integer age;

    private String email;

    public User(String name, String lastName, Integer age, String email) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }



}
