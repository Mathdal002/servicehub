package br.com.servicehub.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.servicehub.company.dto.CompanyRequest;
import br.com.servicehub.company.dto.CompanyResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/companies")
@Validated
public class CompanyController {
	private final CompanyService companyService;
	
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CompanyResponse> create(@Valid @RequestBody CompanyRequest request) {
		CompanyResponse response = companyService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
	
}
