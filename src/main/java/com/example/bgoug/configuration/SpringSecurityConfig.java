package com.example.bgoug.configuration;

import com.example.bgoug.member.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MemberService memberService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.memberService).passwordEncoder(getBCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
		/*
		 * CharacterEncodingFilter filter = new CharacterEncodingFilter();
		 * filter.setEncoding("UTF-8"); filter.setForceEncoding(true);
		 */
        
        http
                .authorizeRequests()
                .antMatchers("/", "/fragments/**", "/members/register", "/img/*", "/members/login", "/css/**", "/bootstrap/**", "/jquery/**", "/assets/**", "/images/**").permitAll()
                .antMatchers("/events/*", "/companies/*").hasRole("ADMIN")
                .antMatchers("/members/all").access("hasRole('ADMIN')")
                .antMatchers("/members/membersOfCompany").access("hasRole('USER') or hasRole('ADMIN')")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/members/login").permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .rememberMe()
                .rememberMeCookieName("rememberMeForBgOug")
                .rememberMeParameter("remember")
                .key("Secret")
                .tokenValiditySeconds(1000000)
                .and()
                .logout().logoutSuccessUrl("/").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/unauthorized")
                .and()
                .csrf().disable();
       
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    

}
