package ninja.harmless.security.jwt;

/**
 * @author bnjm@harmless.ninja - 9/17/16.
 */
public class JsonWebToken {
    private String header;
    private String payload;
    private String signature;

    public JsonWebToken(String header, String payload, String signature) {
        this.header = header;
        this.payload = payload;
        this.signature = signature;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return header + "." + payload + "." + signature;
    }
}
