package com.tdlm;

import com.tdlm.domain.task.repository.TaskDomainRepository;
import com.tdlm.domain.todo.repository.ToDoDomainRepository;
import com.tdlm.domain.user.model.User;
import com.tdlm.domain.user.repository.UserDomainRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TdlmApplication {
    public static void main(String[] args) {
        SpringApplication.run(TdlmApplication.class, args);
    }

    @Bean
    CommandLineRunner seedDatabase(UserDomainRepository userDomainRepository, ToDoDomainRepository toDoDomainRepository, TaskDomainRepository taskDomainRepository, PasswordEncoder passwordEncoder){
        return args -> {
                userDomainRepository.save(User.builder().email("admin@tdlm.com").firstName("ImMr").lastName("Hak").username("immrhak").password(passwordEncoder.encode("test123P")).isAdmin(true).build());
        };
    }
}
