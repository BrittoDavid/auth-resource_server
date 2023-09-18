package co.com.lucasian.auth.britto.cloud.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;




/**
 *
 * @author DavidBritto
 */

@Configuration
public class SecurityConfig {
    
    private static final String AUTH_WRITE = "write";
    private static final String AUTH_READ = "read"; 
    private static final String ROOT_PATTERN = "/**";
    
    
    @Bean
    SecurityFilterChain clientSecurityFilterChain(HttpSecurity http) throws Exception{
                
        http.formLogin(withDefaults()).csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, ROOT_PATTERN).hasAnyAuthority(AUTH_READ)
                .requestMatchers(HttpMethod.POST, ROOT_PATTERN).hasAnyAuthority(AUTH_WRITE)
                .requestMatchers(HttpMethod.PATCH, ROOT_PATTERN).hasAnyAuthority(AUTH_WRITE)
                .requestMatchers(HttpMethod.PUT, ROOT_PATTERN).hasAnyAuthority(AUTH_WRITE)
                .requestMatchers(HttpMethod.DELETE, ROOT_PATTERN).hasAnyAuthority(AUTH_WRITE)
                .anyRequest().permitAll());
        http.oauth2ResourceServer(oauth -> oauth.jwt(
                configJwt -> configJwt.decoder(JwtDecoders.fromIssuerLocation("http://localhost:9000"))));
        
        return http.build();
        
    } 
    
    @Bean
    JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter(){
        var converter = new JwtGrantedAuthoritiesConverter();
        converter.setAuthorityPrefix("");
        return converter;
    }
    
    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter(JwtGrantedAuthoritiesConverter settings){
        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(settings);
        return converter;
    }
}
