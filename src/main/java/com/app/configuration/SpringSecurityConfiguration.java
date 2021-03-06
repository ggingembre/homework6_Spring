package com.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Spring security configuration with Basic login form.
 */
@Configuration
@EnableWebSecurity
@ComponentScan({"com.app.services"})
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                .antMatchers("/user/show").hasAnyRole("ADMIN")
                .antMatchers("/").hasAnyRole("USER", "ADMIN")
                .antMatchers("/product/showAll").hasAnyRole("USER", "ADMIN")
                .antMatchers("/product/{productId}").hasAnyRole("USER", "ADMIN")
                .antMatchers("/product/search").hasAnyRole("USER", "ADMIN")
                .antMatchers("/user/**").hasRole("ADMIN")
                .antMatchers("/product/**").hasRole("ADMIN")
                .antMatchers("/**").hasRole("ADMIN")
                .anyRequest().denyAll()
                .and().formLogin()
                .and().csrf().disable();

    }

/* In case you want to save users in memory / issue with normal login
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER")
                .and().withUser("admin").password("admin").roles("ADMIN");
    }

*/
}
