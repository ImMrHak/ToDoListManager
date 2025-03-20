package com.tdlm.domain.permission.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tdlm.domain.role.model.Role;
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
public class Privilege implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long privilegeId;

    private String authority;

    @ManyToMany(mappedBy = "privileges") @JsonIgnore
    private List<Role> roles = new ArrayList<>();
}
