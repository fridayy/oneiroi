package ninja.harmless.user.exception

/**
 * @author bnjm@harmless.ninja - 9/15/16.
 */
class InvalidCredentialsException extends RuntimeException {
    InvalidCredentialsException(String message) {
        super(message)
    }
}
