package me.soyeon207.tutorial.config;

import me.soyeon207.tutorial.jwt.JwtAccessDeniedHandler;
import me.soyeon207.tutorial.jwt.JwtAuthenticationEntryPoint;
import me.soyeon207.tutorial.jwt.JwtSecurityConfig;
import me.soyeon207.tutorial.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // @PreAuthorize 어노테이션을 메소드단위로 추가
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecurityConfig(TokenProvider tokenProvider, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,JwtAccessDeniedHandler jwtAccessDeniedHandler) {
        this.tokenProvider = tokenProvider;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // /h2-console/ 하위 모든 요청과 파비콘은 무시
        web
            .ignoring()
            .antMatchers("/h2-console/**","/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // 우린 토큰을 사용하니깐 csrf 는 비활성

            .exceptionHandling() // 만든 Exception 들 등록
            .authenticationEntryPoint(jwtAuthenticationEntryPoint) // 401 UNAUTHORIZED
            .accessDeniedHandler(jwtAccessDeniedHandler) // 403 FORBIDDEN

            .and() // h2 console 설정들
            .headers()
            .frameOptions()
            .sameOrigin()

            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용 X

            .and()
            .authorizeRequests() // HttpServletRequest 를 사용하는 요청들에 대한 접근 제한 설정
            .antMatchers("/api/hello").permitAll() // /api/hello 에 대한 요청은 인증 없이 접근 허용
            .antMatchers("/api/authenticate").permitAll()
            .antMatchers("/api/signup").permitAll() // 로그인 및 회원가입은 토큰이 없이 요청하기 때문에 접근 허용
            .anyRequest().authenticated() // 나머지 요청들은 모두 인증되어야 한다

            .and()
            .apply(new JwtSecurityConfig(tokenProvider));
    }
}
