package kosaka.vagas.modules.candidates.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosaka.vagas.exceptions.UserFoundException;
import kosaka.vagas.modules.candidates.Entities.CandidateEntity;
import kosaka.vagas.modules.candidates.repositories.CandidateRepository;

@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository repository;

    public CandidateEntity execute(CandidateEntity entity) {
        this.repository.findByUsernameOrEmail(entity.getUsername(), entity.getEmail())
            .ifPresent((user) -> {
                throw new UserFoundException();
            });

        return this.repository.save(entity);
    }
}
