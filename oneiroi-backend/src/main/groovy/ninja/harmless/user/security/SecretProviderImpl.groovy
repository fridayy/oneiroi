package ninja.harmless.user.security

import org.apache.tomcat.util.codec.binary.Base64
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
/**
 * @author bnjm@harmless.ninja - 9/26/16.
 */
@Service
class SecretProviderImpl implements SecretProvider {

    @Value('${secret}')
    private String secret

    @Override
    String getSecret() {
        return secret ?: generateKey()
    }


    private String generateKey() {
        long currentTime = System.nanoTime();
        return Base64.encodeBase64URLSafeString(String.valueOf(currentTime).bytes);
    }
}
