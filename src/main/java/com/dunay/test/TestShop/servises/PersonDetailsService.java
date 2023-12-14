package com.dunay.test.TestShop.servises;

import com.dunay.test.TestShop.DTO.PersonDetails;
import com.dunay.test.TestShop.models.Person;
import com.dunay.test.TestShop.repositories.PersonsRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class PersonDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final PersonsRepository personsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personsRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found!"));
        return new PersonDetails(person);
    }

    public PersonDetails getPersonDetailsById(int id) {
        return new PersonDetails(personsRepository.findById(id).orElseThrow());
    }

    @Transactional
    public void savePerson(Person person) {
        personsRepository.save(person);
    }

    @Transactional
    public void editPersonDetails(Authentication authentication,
                                  MultiValueMap<String, String> formData) {
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        Person person = getPersonDetailsById(personDetails.getId()).getPerson();
        String name = formData.getFirst("name");
        String email = formData.getFirst("email");
        person.setName(name);
        person.setEmail(email);
        savePerson(person);
    }


    @Transactional
    public void registerPerson(Person person) {
        String password = passwordEncoder.encode(person.getPassword());
        person.setPassword(password);
        person.addRole("ROLE_USER");
        savePerson(person);
    }


    @Transactional
    public void deletePerson(Authentication authentication) {
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        personsRepository.deleteById(personDetails.getId());
    }

    @Transactional
    public void setAdminRole(Authentication authentication) {
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        Person person = personDetails.getPerson();
        person.addRole("ROLE_ADMIN");
        personsRepository.save(person);
    }
}
