package kosaka.vagas.modules.candidates.useCases;

import kosaka.vagas.modules.candidates.dto.ProfileCandidateResponseDTO;
import kosaka.vagas.modules.candidates.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.UUID;

public class ProfileCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID id) {
        var candidate = this.candidateRepository.findById(id)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User not found");
                });

        return ProfileCandidateResponseDTO.builder()
                .description(candidate.getDescription())
                .username(candidate.getUsername())
                .email(candidate.getEmail())
                .id(candidate.getId())
                .name(candidate.getName())
                .build();
    }
}
