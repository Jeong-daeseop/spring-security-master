package io.security.springsecuritymaster.security.configs;

import io.security.springsecuritymaster.security.dsl.RestApiDsl;
import io.security.springsecuritymaster.security.entrypoint.RestAuthenticationEntryPoint;
import io.security.springsecuritymaster.security.handler.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final AuthenticationProvider restAuthenticationProvider;
    private final AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;
    private final FormAuthenticationSuccessHandler successHandler;
    private final FormAuthenticationFailureHandler failureHandler;
    private final RestAuthenticationSuccessHandler restSuccessHandler;
    private final RestAuthenticationFailureHandler restFailureHandler;
    private final AuthorizationManager<RequestAuthorizationContext> authorizationManager;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        /*
        http
                .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/user"). hasAuthority("USER") //엔드 포인트와 권한 설정, 요청이 /user 엔드포인트 요청인 경우 USER 권한을 필요로 한다
                .requestMatchers( "/mypage/**")
                .requestMatchers(RegexRequestMatcher.regexMatcher("/resource/[A-Za-z0-9]+")).hasAuthority("USER") //정규 표현식을 사용할 수 있다
                .requestMatchers(HttpMethod.GET, "/**).hasAuthority("read") //HTTP METHOD 를 옵션으로 설정할 수 있다
                .requestMatchers(HttpMethod.POST).hasAuthority("write") // POST 방식의 모든 엔드포인트 요청은 write 권한을 필요로 한다
                .requestMatchers(new AntPathRequestMatcher("/manager/**"))
                .requestMatchers("/admin/**")
                .hasAuthority("USER") //Ant 패턴을 사용할 수 있다. 요청이 /mypage 또는 하위 경로인 경우 USER 권한을 필요로 한다
                .hasAuthority("MANAGER") //원하는 RequestMatcher 를 직접 사용할 수 있다
                .hasAnyAuthority("ADMIN","MANAGER") /admin/ 이하의 모든 요청은 ADMIN 과 MANAGER 권한을 필요로 한다
                .anyRequest().authenticated()); 위에서 정의한 규칙 외 모든 엔드포인트 요청은 인증을 필요로 한다
        */
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().access(authorizationManager))

                .formLogin(form -> form
                        .loginPage("/login")
                        .authenticationDetailsSource(authenticationDetailsSource)
                        .successHandler(successHandler)
                        .failureHandler(failureHandler)
                        .permitAll())
                .authenticationProvider(authenticationProvider)
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(new FormAccessDeniedHandler("/denied"))
                )
        ;
        return http.build();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain restSecurityFilterChain(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(restAuthenticationProvider);
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();            // build() 는 최초 한번 만 호출해야 한다

        http
                .securityMatcher("/api/**")
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/images/**", "/js/**", "/favicon.*", "/*/icon-*").permitAll()
                        .requestMatchers("/api","/api/login").permitAll()
                        .requestMatchers("/api/user").hasAuthority("ROLE_USER")
                        .requestMatchers("/api/manager").hasAuthority("ROLE_MANAGER")
                        .requestMatchers("/api/admin").hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated())
//                .csrf(AbstractHttpConfigurer::disable)
                .authenticationManager(authenticationManager)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                        .accessDeniedHandler(new RestAccessDeniedHandler()))
                .with(new RestApiDsl<>(), restDsl -> restDsl
                                            .restSuccessHandler(restSuccessHandler)
                                            .restFailureHandler(restFailureHandler)
                                            .loginPage("/api/login")
                                            .loginProcessingUrl("/api/login"))
        ;

        return http.build();
    }
}