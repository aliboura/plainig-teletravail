package dz.djezzydevs.hrplaning.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


//  @Autowired
//  private JwtRequestFilter jwtRequestFilter;


  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {

    httpSecurity.cors().and().csrf().disable()
            .authorizeRequests()
            .antMatchers("/v2/api-docs",
                    "/configuration/ui",
                    "/swagger-resources/**",
                    "/configuration/security",
                    "/swagger-ui.html",
//                    "/admin/**",
                    "/webjars/**").permitAll()
            .antMatchers("/**").access("hasRole('ROLE_RH_PLANING')")

            .antMatchers("/home/**").access("hasRole('ROLE_RH_PLANING_ADMIN') or hasRole('ROLE_ADMIN')")

            .anyRequest().authenticated()
            .and()

            .addFilterAfter(new JwtRequestFilter(), UsernamePasswordAuthenticationFilter.class)

            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }



}