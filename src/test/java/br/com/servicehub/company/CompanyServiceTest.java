package br.com.servicehub.company;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.servicehub.company.dto.CompanyRequest;
import br.com.servicehub.company.dto.CompanyResponse;

class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    private CompanyService companyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        companyService = new CompanyService(companyRepository);
    }

    @Test
    void deveCadastrarEmpresaComSucesso() {

        CompanyRequest request = new CompanyRequest(
            "Empresa Teste",
            null,
            "contato@empresa.com",
            "62999999999"
        );

        when(companyRepository.save(any(Company.class)))
            .thenAnswer(invocation -> {
                Company company = invocation.getArgument(0);
                company.setId(1L);
                company.prePersist();
                return company;
            });

        CompanyResponse response = companyService.create(request);

        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("Empresa Teste", response.name());
        assertEquals("contato@empresa.com", response.email());
        assertTrue(response.active());
        assertNotNull(response.createdAt());

        verify(companyRepository, times(1)).save(any(Company.class));
    }
}
