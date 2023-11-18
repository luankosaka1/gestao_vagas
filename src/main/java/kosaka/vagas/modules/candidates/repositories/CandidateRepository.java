package kosaka.vagas.modules.candidates.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kosaka.vagas.modules.candidates.entities.CandidateEntity;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    public Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);
    public Optional<CandidateEntity> findByUsername(String username);
}
