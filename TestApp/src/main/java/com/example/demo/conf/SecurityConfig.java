package com.example.demo.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.app.DatabaseUserDetailsService;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private DatabaseUserDetailsService userDetailsService;
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		// ここに設定を書く
		
		http.formLogin(login -> login			// フォーム認証の設定記述開始
				.loginProcessingUrl("/login")	// ユーザー名・パスワードの送信先URL
				.loginPage("/login")			// ログイン画面のURL
				.defaultSuccessUrl("/list")		// ログイン成功後のリダイレクト先URL
				.failureUrl("/login?error")		// ログイン失敗後のリダイレクト先URL
				.permitAll()
		).logout(logout -> logout				// ログアウトの設定記述開始
				.logoutUrl("/user/logout") 
				.logoutSuccessUrl("/login")			//　ログイン後成功後のリダイレクト先URL 
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
		).authorizeHttpRequests(authz -> authz
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations())
					.permitAll()				// "/css/**"などはログインなしでもアクセス可能
				.mvcMatchers("/resources/**", "/resource/**", "/css/**", "/js/**", "/images/**", "/vendor/**", "/fonts/**")
					.permitAll()
				.mvcMatchers("/list")				
					.permitAll()				// "/"はログインなしでもアクセス可能
				.mvcMatchers("/signup")				
					.permitAll()
				.mvcMatchers("/registration")				
					.permitAll()
					.mvcMatchers("/signup_error")				
					.permitAll()
//				.mvcMatchers("/general")
//					.hasRole("GENERAL")			// "/general"はROLE_GENERALのみアクセス可能
//				.mvcMatchers("/admin")
//					.hasRole("Admin")			// "/admin"はROLE_ADMINのみアクセス可能
					.anyRequest()
					.authenticated());		//他のURLはログイン後のみアクセス可能
        
        return http.build();
    }
	
}
