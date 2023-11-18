package kosaka.vagas.modules.candidates.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kosaka.vagas.exceptions.UserFoundException;
import kosaka.vagas.modules.candidates.entities.CandidateEntity;
import kosaka.vagas.modules.candidates.repositories.CandidateRepository;

@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity entity) {
        this.repository.findByUsernameOrEmail(entity.getUsername(), entity.getEmail())
            .ifPresent((user) -> {
                throw new UserFoundException();
            });

        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        return this.repository.save(entity);
    }
}
