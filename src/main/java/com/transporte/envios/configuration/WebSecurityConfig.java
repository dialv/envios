package com.transporte.envios.configuration;


import com.transporte.envios.security.JWTAuthenticationFilter;
import com.transporte.envios.security.JWTAuthorizationFilter;
import com.transporte.envios.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final UserDetailsServiceImpl userDetailsService;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = (UserDetailsServiceImpl) userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

       http
               .cors()   //permitimos cors, comunicacion entre dominios
               .and()
               .csrf().disable()  // lo deshabilitamos xq si se permite comunicacion entre dominios
               .authorizeRequests()  // a todas las url que machen con eliminar deben estar autorizadas
               .antMatchers("/api/**")
               .hasAnyRole("ADMIN")
               .anyRequest().permitAll()  // lo demas lo permito
               .and()
               .addFilter(jwtAuthenticationFilter()) //esto es necesario para implementar json webtoken, mediante filtros
               .addFilter(jwtAuthorizationFilter())
               .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // rest es sin estado

        http.headers().frameOptions().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");
        //podemos agregar otros validarores por si falla
        return jwtAuthenticationFilter;
    }

    @Bean
    public JWTAuthorizationFilter jwtAuthorizationFilter() throws Exception {
        return new JWTAuthorizationFilter(authenticationManager());
    }
}
