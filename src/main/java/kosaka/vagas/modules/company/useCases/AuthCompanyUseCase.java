package kosaka.vagas.modules.company.useCases;

import com.auth0.jwt.algorithms.Algorithm;
import kosaka.vagas.modules.company.dto.AuthCompanyDTO;
import kosaka.vagas.modules.company.dto.AuthCompanyResponseDTO;
import kosaka.vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security.token.secret}")
    private String secretKey;

    public AuthCompanyResponseDTO execute(AuthCompanyDTO dto) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(dto.username())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Company not found");
                });

        var matched = this.passwordEncoder.matches(dto.password(), company.getPassword());

        if (!matched) {
            throw new AuthenticationException();
        }

        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        var jwt = JWT.create().withIssuer("api")
                .withSubject(company.getId().toString())
                .withExpiresAt(expiresIn)
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(Algorithm.HMAC256(secretKey));

        return AuthCompanyResponseDTO.builder()
                .access_token(jwt)
                .expires_in(expiresIn.toEpochMilli())
                .build();

    }
}
