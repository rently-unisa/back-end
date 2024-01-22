package it.unisa.c02.rently.rently_application.config;

import it.unisa.c02.rently.rently_application.data.dao.GestioneAutenticazioneDAO;
import it.unisa.c02.rently.rently_application.security.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig implements WebMvcConfigurer {

    @Autowired
    private GestioneAutenticazioneDAO userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).addFilterBefore(
                        new AuthorizationFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)),
                                this.userRepository), UsernamePasswordAuthenticationFilter.class
                )
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/api/autenticazione/login").permitAll()
                                .requestMatchers("/api/ricerca/premium").permitAll()
                                .requestMatchers("/api/ricerca/all").permitAll()
                                .requestMatchers("/api/annuncio/visualizza-annuncio").permitAll()
                                .requestMatchers("/api/autenticazione/signup").permitAll()
                                .requestMatchers("/api/valutazione/visualizza-valutazioni-annuncio").permitAll()
                                .requestMatchers("/api/valutazione/visualizza-valutazioni-utente").permitAll()
                                .requestMatchers("/api/area-personale/profilo-utente").permitAll()
                                .requestMatchers("/api/annuncio/visualizza-annunci-utente").permitAll()
                                .requestMatchers("/annunci/**").permitAll()  // Consentire l'accesso alle immagini sotto /annunci/
                                .requestMatchers("/static/**").permitAll()  // Consentire l'accesso ai file sotto /static
                                .anyRequest().authenticated())
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }



}
