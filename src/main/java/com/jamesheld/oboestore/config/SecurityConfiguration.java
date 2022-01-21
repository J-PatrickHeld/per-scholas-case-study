package com.jamesheld.oboestore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.jamesheld.oboestore.services.UserService;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

   @Autowired
   private UserService userService;

   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http.httpBasic().and()
       .authorizeRequests()
           .antMatchers(
                   "/registration*",
                   "/js/**",
                   "/css/**",
                   "/images/**",
                   "/files/**",
                   "/",
                   "/about",
                   "/contact").permitAll()
           .antMatchers(
        		   "/orders/new/**",
        		   "/orders/save/**").hasAnyRole("USER", "ADMIN")
           .antMatchers(
        		   "/orders/**", 
        		   "/api/**").hasRole("ADMIN")
           .anyRequest().authenticated() 
       .and()
       		.headers().frameOptions().sameOrigin() //allowing rendering inside html iframes (allows iframes within the same domain)
       .and()
           .formLogin()
               .loginPage("/login")
               .usernameParameter("email")
               .loginProcessingUrl("/login")
               .defaultSuccessUrl("/orders/new", true)
               .permitAll()
       .and()
           .logout()
               .invalidateHttpSession(true)
               .clearAuthentication(true)
               .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
               .logoutSuccessUrl("/login?logout")
               .permitAll()
       .and()
       		.csrf().ignoringAntMatchers("/api/**") //allow post method
       .and()
       		.exceptionHandling().accessDeniedPage("/403");
   }

   @Bean
   public BCryptPasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
   }

   @Bean
   public DaoAuthenticationProvider authenticationProvider(){
       DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
       auth.setUserDetailsService(userService);
       auth.setPasswordEncoder(passwordEncoder());
       return auth;
   }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.authenticationProvider(authenticationProvider());
   }

}

