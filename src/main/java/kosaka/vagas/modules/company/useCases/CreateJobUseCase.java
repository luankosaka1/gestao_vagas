package kosaka.vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosaka.vagas.modules.company.entities.JobEntity;
import kosaka.vagas.modules.company.repositories.JobRepository;

@Service
public class CreateJobUseCase {
    @Autowired
    private JobRepository repository;

    public JobEntity execute(JobEntity entity) {            
        return this.repository.save(entity);
    }
}
