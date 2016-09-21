package ninja.harmless.aspect.jwt;

/**
 * @author bnjm@harmless.ninja - 9/17/16.
 */
public class InvalidMethodOrderException extends RuntimeException {
    public InvalidMethodOrderException(String message) {
        super(message);
    }
}
