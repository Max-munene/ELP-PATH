package com.example.emtechelppathbackend.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import java.util.Arrays;

import static com.example.emtechelppathbackend.security.entities.Permissions.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {


        httpSecurity.csrf(csrf -> csrf.disable())//disabling csfr
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests


                        .requestMatchers("/auth/**", "/users/**", "/career/**", "/scholar/**",
                                "socials/**", "/profile/**", "/image/**", "/api/scholars/**").permitAll()
                        .requestMatchers("/auth/register", "/bio/**", "/education/**", "/scholar/education/**",
                                "/scholar/expenses/**", "/application-pdf/**", "/applications/**").permitAll()
                        .requestMatchers("/roles/**", "/hubs/**", "/feeds/**", "/swagger/**").permitAll()

                        .requestMatchers("/auth/**", "/users/**", "/career/**", "/scholar/**", "socials/**", "/profile/**", "/image/**", "/api/scholars/**").permitAll()
                        .requestMatchers("/auth/register", "/bio/**", "/education/**", "/scholar/education/**", "/scholar/expenses/**").permitAll()
                        .requestMatchers("/roles/**", "/hubs/**", "/feeds/**", "/events/**").permitAll()

                        .requestMatchers("/send-email").permitAll()
                        .requestMatchers(GET, "/profile/**").permitAll()//all apis are not authenticated
                        //secure the branch champion- retricting access to branch champion and admin
                        .requestMatchers("/branch-champion/**").
                        hasAnyRole("ADMIN", "BRANCH_CHAMPION")

                        //signing permissions and securing operations/endpoints one by one for the Branch champion endpoints
                        .requestMatchers(GET, "/branch-champion/**").hasAnyAuthority(ADMIN_READ.name(), BRANCH_CHAMPION_READ.name())
                        .requestMatchers(PUT, "/branch-champion/**").hasAnyAuthority(ADMIN_UPDATE.name(), BRANCH_CHAMPION_UPDATE.name())
                        .requestMatchers(POST, "/branch-champion/**").hasAnyAuthority(ADMIN_CREATE.name(), BRANCH_CHAMPION_CREATE.name())
                        .requestMatchers(DELETE, "/branch-champion/**").hasAnyAuthority(ADMIN_DELETE.name(), BRANCH_CHAMPION_DELETE.name())

                        //secure the branch champion- restricting access only admin
                        .requestMatchers("/admin/**").
                        hasRole("ADMIN")

                        //signing permissions and securing operations/endpoints one by one for the admin endpoints
                        .requestMatchers(GET, "/admin/**").hasAuthority(ADMIN_READ.name())
                        .requestMatchers(PUT, "/admin/**").hasAuthority(ADMIN_UPDATE.name())
                        .requestMatchers(POST, "/admin/**").hasAuthority(ADMIN_CREATE.name())
                        .requestMatchers(DELETE, "/admin/**").hasAuthority(ADMIN_DELETE.name())
                        .anyRequest().authenticated()// Require authentication for other APIs

                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


        return httpSecurity.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**");
    }


    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Bean
    public CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
