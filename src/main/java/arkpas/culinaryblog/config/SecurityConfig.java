package arkpas.culinaryblog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private DataSource dataSource;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig (DataSource dataSource) {
        super();
        this.dataSource = dataSource;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    @Override
    public void configure (HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/przepis/dodaj", "/kategoria/dodaj")
                .authenticated();
        httpSecurity
                .authorizeRequests()
                .anyRequest()
                .permitAll();
        httpSecurity
                .authorizeRequests()
                .antMatchers("/h2-console/**")
                .permitAll();
        httpSecurity
                .csrf()
                .disable();
        httpSecurity
                .headers()
                .frameOptions()
                .disable();

        httpSecurity
                .formLogin()
                .loginPage("/login")
                .failureUrl("/loginfail")
                .defaultSuccessUrl("/loginsuccess");
    }

    @Autowired
    public void users (AuthenticationManagerBuilder authManBuilder) throws Exception {
        authManBuilder
                .jdbcAuthentication()
                .passwordEncoder(passwordEncoder)
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, active FROM User WHERE username = ?")
                .authoritiesByUsernameQuery("SELECT username, name FROM Authority WHERE username = ?");
    }



}
