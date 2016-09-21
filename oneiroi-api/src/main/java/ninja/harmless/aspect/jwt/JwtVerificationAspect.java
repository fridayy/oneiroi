package ninja.harmless.aspect.jwt;

import ninja.harmless.security.jwt.JwtService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author bnjm@harmless.ninja - 9/17/16.
 */
@Aspect
@Component
public class JwtVerificationAspect {

    @Autowired
    private JwtService<?> service;

    @Value("${secret}")
    private String secret;

    @Around("@annotation(ninja.harmless.aspect.jwt.VerifyJWT) && execution(* * (..))")
    public Object verifyTokenAspect(ProceedingJoinPoint pjp) throws Throwable {
        Object returnObject = null;

        try {
            String token = stripBearer(pjp);
            if (service.verifyJWT(token, secret)) {
                returnObject = pjp.proceed();
            } else {
                returnObject = new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (Throwable t) {
            throw t;
        }

        return returnObject;
    }

    private String stripBearer(ProceedingJoinPoint pjp) {
        String token = null;
        Object[] methodArgs = pjp.getArgs();
        checkArgumentOrder(methodArgs);
        String argument = (String) methodArgs[0];

        if(argument.startsWith("Bearer")) {
            String[] splitted = argument.split(" ");
            token = splitted[1];
            return token;
        }
        throw new InvalidMethodOrderException("RequestParams argument must be the first argument in the signature and contain a string starting with 'Bearer'.");
    }

    private void checkArgumentOrder(Object[] args) {
        if(Objects.isNull(args) || args.length < 1) {
            throw new InvalidMethodOrderException("RequestParams argument must be the first argument in the signature and contain a string starting with 'Bearer'.");
        }
    }

}
