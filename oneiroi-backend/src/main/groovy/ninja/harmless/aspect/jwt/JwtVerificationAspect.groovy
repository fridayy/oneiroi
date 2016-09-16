package ninja.harmless.aspect.jwt

import ninja.harmless.security.JwtService
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

/**
 * @author bnjm@harmless.ninja - 9/16/16.
 */
@Aspect
@Component
class JwtVerificationAspect {

    @Autowired
    JwtService service

    @Around("@annotation(ninja.harmless.aspect.jwt.VerifyJWT) && execution(* * (..))")
    public Object verifyTokenAspect(ProceedingJoinPoint pjp) throws Throwable {
        Object returnObject = null
        try {
            if(service.verifyJWT(stripBearer(pjp))) {
                returnObject = pjp.proceed()
            } else {
                returnObject = new ResponseEntity<?>(HttpStatus.FORBIDDEN)
            }
        } catch (Throwable t) {
            throw t
        }
        return returnObject
    }

    private String stripBearer(ProceedingJoinPoint pjp) {
        String token = null
        Object[] methodArgs = pjp.getArgs()
        if(Objects.isNull(methodArgs) || methodArgs.length == 0) {
            throw new InvalidMethodOrderException("RequestParams argument must be the first argument in the signature and contain a string starting with 'Bearer'.")
        }
        String argument = methodArgs[0] as String
        if(argument.startsWith("Bearer")) {
            String[] splitted = argument.split(" ")
            token = splitted[1]
            return token
        }
        throw new InvalidMethodOrderException("RequestParams argument must be the first argument in the signature and contain a string starting with 'Bearer'.")
    }
}
