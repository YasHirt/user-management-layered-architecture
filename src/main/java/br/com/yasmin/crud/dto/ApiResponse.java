package br.com.yasmin.crud.dto;

public record ApiResponse(UserResponseDTO userResponse, String status, int code) {}
