package com.tdlm.domain.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tdlm.domain.listener.AuditListener;
import com.tdlm.domain.listener.Auditable;
import com.tdlm.domain.todo.model.ToDo;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditListener.class)
@Builder
public class User implements UserDetails, Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private Date createdAt;

    private Date updatedAt;

    private Boolean isAdmin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ToDo> listOfToDo = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        if(isAdmin) authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return authorities;
    }
}
