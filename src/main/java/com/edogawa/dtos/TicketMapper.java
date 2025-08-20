package com.edogawa.dtos;

import com.edogawa.entities.Ticket;

public class TicketMapper {

	public static Ticket toTicket(TicketRequestDto ticketRequestDto) {

		Ticket ticket = new Ticket();
		ticket.setTitle(ticketRequestDto.title());
		ticket.setStatus(ticketRequestDto.status());
		ticket.setPriority(ticketRequestDto.priority());
		ticket.setCustomerId(ticketRequestDto.customerId());
		ticket.setAssignedTo(ticketRequestDto.assignedTo());
		ticket.setCreatedAt(java.time.Instant.now());
		ticket.setUpdatedAt(java.time.Instant.now());
		return ticket;

	}

	public static TicketResponseDto toTicketResponseDto(Ticket ticket) {
		TicketResponseDto ticketResponseDto = new TicketResponseDto
				(ticket.getId(), 
				ticket.getTitle(),
				ticket.getStatus(),
				ticket.getPriority(),
				ticket.getCustomerId(),
				ticket.getAssignedTo(),
				ticket.getCreatedAt(),
				ticket.getUpdatedAt());

		return ticketResponseDto;

	}
}
