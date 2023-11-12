package kosaka.vagas.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kosaka.vagas.modules.company.entities.JobEntity;
import kosaka.vagas.modules.company.useCases.CreateJobUseCase;

@RestController
@RequestMapping("company/job")
public class JobController {
    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody JobEntity entity) {
        try {
            var response = this.createJobUseCase.execute(entity);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
