package kosaka.vagas.modules.candidates.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthCandidateResponseDTO {
    private String access_token;
}
