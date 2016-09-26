package ninja.harmless

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.scheduling.annotation.EnableScheduling

/**
 * @author bnjm@harmless.ninja - 9/11/16.
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableScheduling
class BootstrapOneiroi {
    static void main(String[] args) {
        SpringApplication.run(BootstrapOneiroi, args)
    }
}
