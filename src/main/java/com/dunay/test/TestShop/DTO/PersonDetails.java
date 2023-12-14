package com.dunay.test.TestShop.DTO;

import com.dunay.test.TestShop.models.Person;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Getter
@EqualsAndHashCode
public class PersonDetails implements UserDetails {

    private final Person person;

    private final int id;

    @NotBlank
    private final String name;

    private final List<GrantedAuthority> authorities;

    @Email
    private final String email;

    public PersonDetails(Person person) {
        this.person = person;
        this.id = person.getId();
        this.name = person.getName();
        this.email =person.getEmail();
        this.authorities = person.getAuthorities();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.person.getPassword();
    }

    @Override
    public String getUsername() {
        return this.person.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
