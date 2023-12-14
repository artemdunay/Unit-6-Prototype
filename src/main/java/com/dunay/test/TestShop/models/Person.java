package com.dunay.test.TestShop.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(min = 5, max = 20)
    @Column(unique = true)
    private String username;

    @NotBlank
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<GrantedAuthority> authorities = new ArrayList<>();

    @Size(min = 5, max = 50)
    private String name;

    @Email
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<TestOrder> orders;

    public void addRole(String role) {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
        if (!authorities.contains(authority)) {
            this.authorities.add(authority);
        }
    }
}
