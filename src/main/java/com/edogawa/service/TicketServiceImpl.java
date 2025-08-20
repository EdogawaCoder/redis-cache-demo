package com.edogawa.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.edogawa.entities.Ticket;
import com.edogawa.enums.Status;

@Service
public class TicketServiceImpl {

	private final com.edogawa.repositories.FakeTicketRepository ticketRepository;

	public TicketServiceImpl(com.edogawa.repositories.FakeTicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}

	@Cacheable(value = "ticket", key = "#id")
	public Ticket getById(Long id) {
		return ticketRepository.findById(id);
	}

	@Cacheable(value = "ticketList", key = "#status")
	public List<Ticket> listByStatus(Status status) {
		return ticketRepository.findByStatus(status);
	}

	@Caching(put = { 
			@CachePut(value = "ticket", key = "#ticket.id") }, evict = {
			@CacheEvict(value = "ticket", allEntries = true) })
	public Ticket update(Ticket ticket) {
		return ticketRepository.save(ticket);
	}

	@Caching(evict = { 
			@CacheEvict(value = "ticket", key = "#id"),
			@CacheEvict(value = "ticketList", allEntries = true) })
	public boolean delete(Long id) {
		Ticket existingTicket = ticketRepository.findById(id);
		if (existingTicket == null) return false;
		ticketRepository.deleteById(id);				
		return true;
}
	
	
}
