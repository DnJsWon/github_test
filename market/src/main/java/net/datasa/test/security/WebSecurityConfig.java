package net.datasa.test.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 시큐리티 환경설정
 */
@Configuration //환경설정 처리
@EnableWebSecurity//security 기능활성화
public class WebSecurityConfig {
    //로그인 없이 접근 가능 경로
    private static final String[] PUBLIC_URLS = {
            "/"                     //root
            , "/images/**"          //이미지 경로
            , "/css/**"             //CSS파일들
            , "/js/**"              //JavaSCript 파일들
            , "/member/join"        //회원가입
    };

    @Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(author -> author
                .requestMatchers(PUBLIC_URLS).permitAll()//접근허용url
                .anyRequest().authenticated()//접근허용url외는 인증거쳐야함
            )
            .httpBasic(Customizer.withDefaults())
            .formLogin(formLogin -> formLogin
                    .loginPage("/member/loginForm")//로그인 폼페이지
                    .usernameParameter("id")
                    .passwordParameter("password")
                    .loginProcessingUrl("/member/login")//로그인 처리페이지
                    .defaultSuccessUrl("/", true)//로그인성공 후 이동할 페이지 
                    .permitAll()
            )
            .logout(logout -> logout
                    .logoutUrl("/member/logout")//로그 아웃처리 url
                    .logoutSuccessUrl("/")//로그아웃 성공후 이동할 페이지
            );

        http
            .cors(AbstractHttpConfigurer::disable)//스프링시큐리티와 같이못씀
            .csrf(AbstractHttpConfigurer::disable);//스프링시큐리티와 같이못씀

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
