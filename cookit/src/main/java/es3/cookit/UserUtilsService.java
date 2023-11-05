package es3.cookit;

import java.security.Principal;

import io.quarkus.runtime.Startup;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@Startup
@ApplicationScoped
public class UserUtilsService {

    @Inject
    private SecurityIdentity securityIdentity;

    private static SecurityIdentity instance;

    /**
     * Gets the custom user.
     *
     * @return the custom user
     */
    public static Principal getCustomUser() {
        return instance.getPrincipal();
    }
        /*
         * DefaultJWTCallerPrincipal{id='null', name='Jadson Romualdo Oliveira Da Costa', expiration=1698884548, notBefore=0, issuedAt=1698880948, issuer='https://accounts.google.com', audience=[578833402102-p5uorh9nqtuchhqq44j53ghu4p3s0cq7.apps.googleusercontent.com], subject='113842701008387909462', type='JWT', issuedFor='578833402102-p5uorh9nqtuchhqq44j53ghu4p3s0cq7.apps.googleusercontent.com', authTime=0, givenName='Jadson', familyName='Romualdo Oliveira Da Costa', middleName='null', nickName='null', preferredUsername='null', email='2022003693@aluno.poa.ifrs.edu.br', emailVerified=true, allowedOrigins=null, updatedAt=0, acr='null', groups=[]}
         */
    public static String getUserName() {
        return instance.getPrincipal().getName();
    }

    @PostConstruct
    private void setUp() {
        instance = this.securityIdentity;
    }
}