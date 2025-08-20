package com.edogawa.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edogawa.dtos.TicketMapper;
import com.edogawa.dtos.TicketRequestDto;
import com.edogawa.dtos.TicketResponseDto;
import com.edogawa.entities.Ticket;
import com.edogawa.enums.Status;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

	private final com.edogawa.service.TicketServiceImpl ticketService;
	
	public TicketController(com.edogawa.service.TicketServiceImpl ticketService) {
		this.ticketService = ticketService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TicketResponseDto> getTicketById(@PathVariable Long id) {
		Ticket ticket = ticketService.getById(id);
		if (ticket ==  null) {
			return ResponseEntity.notFound().build();}
		
		return ResponseEntity.ok(TicketMapper.toTicketResponseDto(ticket));
	}
	
		
		
	@GetMapping
	public ResponseEntity<List<TicketResponseDto>> listByStatus(@RequestParam Status status){
		List<TicketResponseDto> tickets = ticketService.listByStatus(status)
				.stream()
				.map(TicketMapper::toTicketResponseDto)
				.toList();
		return ResponseEntity.ok(tickets);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TicketResponseDto> updateTicket(
			@PathVariable Long id, 
			@Valid @RequestBody TicketRequestDto ticketRequestDto
			) {
		
		Ticket ticket = TicketMapper.toTicket(ticketRequestDto);
		
		ticket.setId(id);
		
		Ticket updatedTicket = ticketService.update(ticket);
		
		if (updatedTicket == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(TicketMapper.toTicketResponseDto(updatedTicket));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
		boolean deleted = ticketService.delete(id);
		if (!deleted) {
			return ResponseEntity.notFound().build();
		}
			return ResponseEntity.noContent().build();
		}
}

