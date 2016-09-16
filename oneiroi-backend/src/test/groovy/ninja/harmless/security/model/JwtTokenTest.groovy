package ninja.harmless.security.model

import spock.lang.Specification

/**
 * @author bnjm@harmless.ninja - 9/15/16.
 */
class JwtTokenTest extends Specification {

    JwtToken classUnderTest

    def setup() {
        classUnderTest = new JwtToken(header: "a", payload: "b", signature: "c")
    }

    def "test corrrect string representation"() {
        expect:
            classUnderTest.toString() == "a.b.c"
    }
}
