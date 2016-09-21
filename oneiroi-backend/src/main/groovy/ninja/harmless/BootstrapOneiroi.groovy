package ninja.harmless

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy
/**
 * @author bnjm@harmless.ninja - 9/11/16.
 */
@SpringBootApplication
@EnableAspectJAutoProxy
class BootstrapOneiroi {
    static void main(String[] args) {
        SpringApplication.run(BootstrapOneiroi, args)
    }
}

//@Configuration
//class OneriroiConfig {
//
//    @Bean
//    public JwtService<User> getJwtBean() {
//        return new JwtServiceImpl<User>();
//    }
//}
