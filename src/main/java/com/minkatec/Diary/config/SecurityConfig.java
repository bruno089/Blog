package com.minkatec.Diary.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

// 1 Crear  clase que extiende de WebSecurityConfigurerAdapter
// 1 Create class, and it extends from WebSecurityConfigurerAdapter
// 2 Insertar estas anotaciones de abajo
// 2 Insert annotations below
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // 3 Inyectamos el gestor de usuarios
    // 3 Inyect our user manager
    @Autowired
    private UserDetailsService userDetailsService;
    // 4 Configurar estos tres metodos
    // 4 Config these three methods
        // 4.1 Como vamos a establecer la config, que tipo de seguridad yo quiero
        // 4.1 How we use , what kind of security type i want
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    //PARA REGISTER
            http.csrf().disable().authorizeRequests()
                    .antMatchers("/api/auth/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated();
    }
        // 4.2 Tenemos que configurar un constructor de autenticacion
        // 4.2
        // 4.3 Como vamos a codificar las claves
    // 5 Tendre que implementar mi gestor personalizado porque hare gestion centralizada












}
