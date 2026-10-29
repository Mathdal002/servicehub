package br.com.servicehub.company.dto;

import java.time.LocalDateTime;

public record CompanyResponse(
		Long id,
	    String name,
	    String document,
	    String email,
	    String phone,
	    Boolean active,
	    LocalDateTime createdAt) {}
