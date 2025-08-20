package com.edogawa.dtos;

import java.time.Instant;

import com.edogawa.enums.Priority;
import com.edogawa.enums.Status;

public record TicketResponseDto( 

	Long id,
    String title,
    Status status,
    Priority priority,
    String customerId,
    String assignedTo,
    Instant createdAt,
    Instant updatedAt

) {}
