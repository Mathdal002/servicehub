
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

        if (request == null) {
            throw new IllegalArgumentException(
                "Os dados da empresa são obrigatórios."
            );
        }

        String name = request.name() == null
                ? ""
                : request.name().trim();

        if (name.isBlank()) {
            throw new IllegalArgumentException(
                "O nome da empresa é obrigatório."
            );
        }

        String document = normalize(request.document());
        String email = normalize(request.email());
        String phone = normalize(request.phone());

        if (document != null &&
                companyRepository.existsByDocument(document)) {

            throw new IllegalArgumentException(
                "Já existe uma empresa cadastrada com este documento."
            );
        }

        Company company = new Company();

        company.setName(name);
        company.setDocument(document);
        company.setEmail(email);
        company.setPhone(phone);
        company.setActive(true);

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

    // Método auxiliar para normalizar os campos
    private String normalize(String value) {

        if (value == null || value.isBlank()) {
            return null;
        }

        return value.trim();
    }
}
