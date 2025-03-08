// package com.example.xephangnguoidung.application.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import com.example.xephangnguoidung.application.service.CustomUserDetailsService;
// import com.example.xephangnguoidung.application.service.NguoiDungService;

// @Configuration
// @EnableMethodSecurity(securedEnabled = true)

// public class SecurityConfiguration {
//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public UserDetailsService userDetailsService(NguoiDungService nguoiDungService) {
//         return new CustomUserDetailsService(nguoiDungService);
//     }

//     @Bean
//     public AuthenticationManager authenticationManager(HttpSecurity http,
//             PasswordEncoder passwordEncoder,
//             UserDetailsService userDetailsService) throws Exception {
//         AuthenticationManagerBuilder authenticationManagerBuilder = http
//                 .getSharedObject(AuthenticationManagerBuilder.class);
//         authenticationManagerBuilder
//                 .userDetailsService(userDetailsService)
//                 .passwordEncoder(passwordEncoder);
//         return authenticationManagerBuilder.build();
//     }
// }
