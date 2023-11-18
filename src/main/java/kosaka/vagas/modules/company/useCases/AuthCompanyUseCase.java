package kosaka.vagas.modules.company.useCases;

import com.auth0.jwt.algorithms.Algorithm;
import kosaka.vagas.modules.company.dto.AuthCompanyDTO;
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

@Service
public class AuthCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security.token.secret}")
    private String secretKey;

    public String execute(AuthCompanyDTO dto) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(dto.username())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Company not found");
                });

        var matched = this.passwordEncoder.matches(dto.password(), company.getPassword());

        if (!matched) {
            throw new AuthenticationException();
        }

        return JWT.create().withIssuer("api")
                .withSubject(company.getId().toString())
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .sign(Algorithm.HMAC256(secretKey));

    }
}
