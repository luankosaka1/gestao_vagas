package kosaka.vagas.modules.candidates.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProfileCandidateResponseDTO {
    private UUID id;
    private String description;
    private String username;
    private String email;
    private String name;
}
