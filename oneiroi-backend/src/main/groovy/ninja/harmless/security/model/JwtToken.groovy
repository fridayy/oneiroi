package ninja.harmless.security.model

/**
 * @author bnjm@harmless.ninja - 9/15/16.
 */
class JwtToken {
    String header
    String payload
    String signature

    @Override
    String toString() {
        return "$header.$payload.$signature"
    }
}
