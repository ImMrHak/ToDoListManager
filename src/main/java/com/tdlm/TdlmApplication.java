package com.tdlm;

import com.tdlm.domain.permission.model.Privilege;
import com.tdlm.domain.permission.repository.PrivilegeDomainRepository;
import com.tdlm.domain.role.model.Role;
import com.tdlm.domain.role.repository.RoleDomainRepository;
import com.tdlm.domain.task.enumeration.TaskStatus;
import com.tdlm.domain.task.model.Task;
import com.tdlm.domain.task.repository.TaskDomainRepository;
import com.tdlm.domain.todo.model.ToDo;
import com.tdlm.domain.todo.repository.ToDoDomainRepository;
import com.tdlm.domain.user.model.User;
import com.tdlm.domain.user.projection.UserProjection;
import com.tdlm.domain.user.repository.UserDomainRepository;
import com.tdlm.kernel.utils.DefaultRoles;
import com.tdlm.kernel.utils.DefaultRolePrivileges;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TdlmApplication {
    public static void main(String[] args) {
        SpringApplication.run(TdlmApplication.class, args);
    }

    @Bean
    CommandLineRunner seedDatabase(UserDomainRepository userDomainRepository, PrivilegeDomainRepository privilegeDomainRepository, RoleDomainRepository roleDomainRepository, ToDoDomainRepository toDoDomainRepository, TaskDomainRepository taskDomainRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Insert roles if they don't already exist
            for (DefaultRoles defaultRoles : DefaultRoles.values()) {
                    List<Privilege> privileges = new ArrayList<>();

                    for(DefaultRolePrivileges defaultRolePrivileges : defaultRoles.defaultRolePrivileges) {
                        Privilege priv = privilegeDomainRepository.save(Privilege.builder().authority(defaultRolePrivileges.authority).build());
                        privileges.add(priv);

                    }

                    Role savedRole = roleDomainRepository.save(Role.builder()
                            .authority(defaultRoles.name())
                            .privileges(privileges)
                            .build());
                };


            // Create the user immrhak
            User user = userDomainRepository.save(User.builder()
                    .email("admin@tdlm.com")
                    .firstName("ImMr")
                    .lastName("Hak")
                    .username("immrhak")
                    .password(passwordEncoder.encode("test123P"))
                            .roles(roleDomainRepository.findById(1L).stream().toList())
                    .build());

            // EXAMPLE PROJECTION WORKING PERFECTLY
            UserProjection newUser = userDomainRepository.findUserByUsername(user.getUsername());

            user.getAuthorities().forEach(a -> System.out.println(a.getAuthority()));

            // Create 50 ToDo entries for the user immrhak
            for (int i = 1; i <= 15000; i++) {
                ToDo toDo = ToDo.builder()
                        .title("ToDo " + i)
                        .description("Description for ToDo " + i)
                        .owner(user).build();
                toDoDomainRepository.save(toDo); // Save ToDo
                Task task = Task.builder()
                        .taskName("Task " + i)
                        .taskStatus(TaskStatus.LOW_PRIORITY)
                        .completed(false)
                        .toDo(toDo).build();
                Task task2 = Task.builder()
                        .taskName("Task2 " + i)
                        .taskStatus(TaskStatus.MEDIUM_PRIORITY)
                        .completed(false)
                        .toDo(toDo).build();
                taskDomainRepository.save(task);
                taskDomainRepository.save(task2);
            }
        };
    }

}
