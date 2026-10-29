package br.com.servicehub.company;

import org.springframework.stereotype.Service;

import br.com.servicehub.company.dto.CompanyRequest;
import br.com.servicehub.company.dto.CompanyResponse;
import jakarta.transaction.Transactional;

@Service
public class CompanyService {
	
	private final CompanyRepository companyRepository;
	
	public CompanyService(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}
	
	@Transactional
	public CompanyResponse create(CompanyRequest request) {
		
		Company company = new Company();

        company.setName(request.name().trim());
        company.setDocument(request.document());
        company.setEmail(request.email());
        company.setPhone(request.phone());

        Company savedCompany = companyRepository.save(company);

        return new CompanyResponse(
            savedCompany.getId(),
            savedCompany.getName(),
            savedCompany.getDocument(),
            savedCompany.getEmail(),
            savedCompany.getPhone(),
            savedCompany.getActive(),
            savedCompany.getCreatedAt()
        );
	}
}
