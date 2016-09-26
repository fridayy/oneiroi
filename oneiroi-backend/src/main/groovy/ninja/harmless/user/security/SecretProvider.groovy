package ninja.harmless.user.security

/**
 * @author bnjm@harmless.ninja - 9/26/16.
 */
interface SecretProvider {
    /**
     * Returns the secret specificed within the application.properties.
     * If they are not present a secret key will be generated.
     * @return
     */
    String getSecret()
}