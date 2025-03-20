package com.tdlm.domain.role.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tdlm.domain.permission.model.Privilege;
import com.tdlm.domain.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;

    private String authority;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Privilege> privileges = new ArrayList<>();

    @ManyToMany(mappedBy = "roles") @JsonIgnore
    private List<User> users = new ArrayList<>();
}
