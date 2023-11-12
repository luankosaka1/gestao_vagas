package kosaka.vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosaka.vagas.exceptions.UserFoundException;
import kosaka.vagas.modules.company.entities.CompanyEntity;
import kosaka.vagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {
    @Autowired
    CompanyRepository repository;

    public CompanyEntity execute(CompanyEntity entity) {
        this.repository.findByUsernameOrEmail(entity.getUsername(), entity.getEmail())
            .ifPresent((company) -> {
                throw new UserFoundException();
            });
            
        return this.repository.save(entity);
    }
}
