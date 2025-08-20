package com.edogawa.dtos;

import com.edogawa.enums.Priority;
import com.edogawa.enums.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TicketRequestDto(

	@NotBlank
	String title,
	@NotNull
	Status status,
	@NotNull
	Priority priority,
	@NotBlank
	String customerId,
	@NotBlank
	String assignedTo
	
) {}
