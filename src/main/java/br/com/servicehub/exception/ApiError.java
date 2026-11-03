package br.com.servicehub.exception;

public record ApiError(
		int status,
		String error,
		String message) {}
