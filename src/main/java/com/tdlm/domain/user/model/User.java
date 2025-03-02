package com.tdlm.domain.user.model;

import com.tdlm.domain.todo.model.ToDo;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private Boolean isAdmin;

    @OneToMany(mappedBy = "user")
    private ArrayList<ToDo> listOfToDo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        if(isAdmin) authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return authorities;
    }
}
