package ninja.harmless.user.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * @author bnjm@harmless.ninja - 9/26/16.
 */
@ContextConfiguration(classes = TestConfig.class)
class SecretProviderTest extends Specification {

    @Autowired
    SecretProvider classUnderTest

    void setup() {

    }

    void "test da shit"() {
        expect:
            classUnderTest.getSecret() == "test"
    }

    void cleanup() {

    }
}

@Configuration
class TestConfig {
    @Bean
    SecretProvider getProv() {
        return new SecretProviderImpl();
    }

    /**
     * Provides test properties
     */
    @Bean
    public PropertySourcesPlaceholderConfigurer props() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        Properties props = new Properties()
        props.setProperty("secret", "test")
        configurer.setProperties(props)
        return configurer
    }
}
