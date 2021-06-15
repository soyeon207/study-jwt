package me.soyeon207.tutorial.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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
            .authorizeRequests() // HttpServletRequest 를 사용하는 요청들에 대한 접근 제한 설정
            .antMatchers("/api/hello").permitAll() // /api/hello 에 대한 요청은 인증 없이 접근 허용
            .anyRequest().authenticated(); // 나머지 요청들은 모두 인증되어야 한다
    }
}
