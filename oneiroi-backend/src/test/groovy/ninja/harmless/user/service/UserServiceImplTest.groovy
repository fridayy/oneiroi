package ninja.harmless.user.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import ninja.harmless.user.repository.UserRepository
import org.junit.Ignore
import spock.lang.Specification
/**
 * @author bnjm@harmless.ninja - 9/12/16.
 */
@Ignore
class UserServiceImplTest extends Specification {

    UserServiceImpl classUnderTest

    void setup() {
        classUnderTest = new UserServiceImpl(Mock(UserRepository))
    }


    def "test base 64"() {
        given:
            def expectation = "YWJj"
        when:
            def result = classUnderTest.base64UrlEncode("abc")
        then:
            result == expectation
    }

    def "test hmac sha256"() {
        given:
            def expectationB64 = "TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ="
            def expectationHex = "4c9540f793ab33b13670169bdf444c1eb1c37047f18e861981e14e34587b1e04"
        when:
            def hash = classUnderTest.hmacSHA256("secret", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9")
            def resultBase64 = hash.encodeBase64().toString()
            def resultHex = hash.encodeHex().toString()
        then:
            resultBase64 == expectationB64
            resultHex == expectationHex
    }

    def "testsig"() {
        expect:
            println(classUnderTest.generateJwt())
    }

    def "asdb"() {
        when:
            String payload = "{ \"sub\": \"1234567890\", \"name\": \"bnjm\", \"admin\": true }"
            String c = Jwts.builder().setPayload(payload).signWith(SignatureAlgorithm.HS256, "abc".getBytes()).compact()
            def result = classUnderTest.generateJwt()
            String d = Jwts.parser().setSigningKey("abc".getBytes()).parse(result)
        then:
            println(result)
        println(d)
    }
}
