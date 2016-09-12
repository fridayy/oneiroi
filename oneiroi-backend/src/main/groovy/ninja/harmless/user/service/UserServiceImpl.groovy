package ninja.harmless.user.service

import ninja.harmless.user.UserService
import ninja.harmless.user.model.User
import ninja.harmless.user.repository.UserRepository
import org.apache.tomcat.util.codec.binary.Base64
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import java.security.InvalidKeyException

/**
 * @author bnjm@harmless.ninja - 9/12/16.
 */
@Service
class UserServiceImpl implements UserService {

    UserRepository userRepository

    @Autowired
    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository
    }

    @Override
    boolean auth(User user) {
        User u = userRepository.findByUsernameIgnoreCase(user.username)
        if (u.password.equals(user.password)) {
            return true
        }
        return false
    }

    public String generateJwt() {
        String key = "abc"
        String header = "{ \"alg\" : \"HS256\", \"typ\" : \"jwt\"}"

        String payload = "{ \"sub\": \"1234567890\", \"name\": \"bnjm\", \"admin\": true }"


        def headerEncoded = base64UrlEncode(header)
        def payloadEncoded = base64UrlEncode(payload)
        def hash = hmacSHA256(key, headerEncoded + "." + payloadEncoded)
        def signature = hash.encodeBase64().toString()

        return headerEncoded + "." + payloadEncoded + "." + signature
    }

    public byte[] hmacSHA256(String secret, String data) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256")
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256")
            mac.init(secretKeySpec)
            byte[] digest = mac.doFinal(data.getBytes("UTF-8"))
            return digest
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Invalid key exception while converting to HMac SHA256")
        }
    }

    public String base64UrlEncode(String message) {
        Base64 encoder = new Base64(true)
        byte[] encodedBytes = encoder.encode(message.getBytes())

        return new String(encodedBytes).trim()
    }
}
