package br.com.servicehub.company.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CompanyRequest(
		@NotBlank(message = "O nome da empresa é obrigatório")
	    @Size(max = 150)
	    String name,

	    @Size(max = 18)
	    String document,

	    @Email(message = "E-mail inválido")
	    @Size(max = 150)
	    String email,

	    @Size(max = 20)
	    String phone) {}
