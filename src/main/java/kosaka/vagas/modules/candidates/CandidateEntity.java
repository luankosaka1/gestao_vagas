package kosaka.vagas.modules.candidates;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateEntity {
    private UUID id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String description;
    private String curriculum;
}
