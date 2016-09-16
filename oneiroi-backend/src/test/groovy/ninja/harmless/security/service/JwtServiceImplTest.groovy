package ninja.harmless.security.service

import ninja.harmless.security.JwtService
import ninja.harmless.user.model.BasicUserInformation
import ninja.harmless.user.model.User
import spock.lang.Specification
/**
 * @author bnjm@harmless.ninja - 9/14/16.
 */
class JwtServiceImplTest extends Specification {

    JwtService classUnderTest
    User user

    def setup() {
        classUnderTest = new JwtServiceImpl("0b487743a90eea78d71271e87f52849575d87fa0ac91795f88485248f334a67d")
        user = new User(basic: new BasicUserInformation(username: "John Doe"))
    }

    def "test jwt generation"() {
        given:
            def expected = "eyJhbGciOiJIUzI1NiJ9.eyJhZG1pbiI6dHJ1ZX0.RhXTkMa2-7_UCx0_aplXSf5yMpKkb9Tba13VrNyblto"
        when:
            def result = classUnderTest.generateJWT(user)
        then:
            result.toString() == expected
    }

    def "test jwt splitted in correct parts"() {
        given:
            def header = "eyJhbGciOiJIUzI1NiJ9"
            def payload = "eyJhZG1pbiI6dHJ1ZX0"
            def signature = "RhXTkMa2-7_UCx0_aplXSf5yMpKkb9Tba13VrNyblto"
        when:
            def token = classUnderTest.generateJWT(user)
        then:
            token.header == header
            token.payload == payload
            token.signature == signature
    }

    def "test verification"() {
        expect:
            def token = classUnderTest.generateJWT(user)
            classUnderTest.verifyJWT(token)
    }
}
