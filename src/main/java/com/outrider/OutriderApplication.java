package com.outrider;

import com.outrider.domain.entity.PointOfSale;
import com.outrider.domain.entity.Role;
import com.outrider.domain.entity.User;
import com.outrider.domain.repository.PointOfSaleRepository;
import com.outrider.domain.repository.RoleRepository;
import com.outrider.domain.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
public class OutriderApplication {

	private static final Logger log = LoggerFactory.getLogger(OutriderApplication.class);
    private Role role;

	public static void main(String[] args) {
		SpringApplication.run(OutriderApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadPosData(PointOfSaleRepository rep) {
		return (args) -> {
			rep.save(new PointOfSale("SomePOS","(11) 9999-9999","someAddress","08:00-17:00"));
			rep.save(new PointOfSale("AnotherPOS","(11) 8888-8888","someAddressTo","08:00-19:00"));
		};
	}

	@Bean
	public CommandLineRunner loadRolesData(RoleRepository rep) {
		return (args) -> {
			role = new Role();
			role.setAuthority("ADMIN");
            rep.save(role);
		};
	}

	@Bean
	public CommandLineRunner loadUserData(UserRepository rep) {
		return (args) -> {

			User user = new User();
			user.setAccountNonExpired(true);
			user.setAccountNonLocked(true);
			user.setCredentialsNonExpired(true);
			user.setEnabled(true);
			user.setPasswordEncoder(new BCryptPasswordEncoder());
            user.setPassword(new BCryptPasswordEncoder().encode("s0m3d4y"));
			user.setUserName("administrator");
			user.getAuthorities().add(role);
			rep.save(user);
		};
	}
}
