package com.edogawa.repositories;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.edogawa.entities.Ticket;
import com.edogawa.enums.Priority;
import com.edogawa.enums.Status;

@Repository
public class FakeTicketRepository {

	private final Map<Long, Ticket> db = new ConcurrentHashMap<>();
	private final java.util.concurrent.atomic.AtomicLong seq = new java.util.concurrent.atomic.AtomicLong();

	// fake seeds

	public FakeTicketRepository() {

		putSeed(seed(1L, "Ticket 1", Status.Opened, Priority.Low, "customer1", "agent1"));
		putSeed(seed(2L, "Ticket 2", Status.Resolved, Priority.Medium, "customer2", "agent2"));
		seq.set(2L);
	}

	public void putSeed(Ticket ticket) {

		db.put(ticket.getId(), ticket);
	}

	private Ticket seed(Long id, String title, com.edogawa.enums.Status status, com.edogawa.enums.Priority priority,
			String customerId, String assignedTo) {

		Ticket ticket = new Ticket();
		ticket.setId(id);
		ticket.setTitle(title);
		ticket.setStatus(status);
		ticket.setPriority(priority);
		ticket.setCustomerId(customerId);
		ticket.setAssignedTo(assignedTo);
		ticket.setCreatedAt(java.time.Instant.now());
		ticket.setUpdatedAt(java.time.Instant.now());

		return ticket;
	}

	public Ticket findById(Long id) {
		simulateLatency();
		return db.get(id);
	}

	public List<Ticket> findByStatus(Status status) {
		simulateLatency();
		return db.values().stream().filter(ticket -> ticket.getStatus() == status)
				.sorted(Comparator.comparing(Ticket::getUpdatedAt).reversed()).collect(Collectors.toList());
	}

	public Ticket save(Ticket ticket) {
		if (ticket.getCreatedAt() == null)
			ticket.setCreatedAt(java.time.Instant.now());
		db.put(ticket.getId(), ticket);
		return ticket;

	}

	public void deleteById(Long id) {

		db.remove(id);
	}

	private void simulateLatency() {
		try {
			// Simulate a delay of 1200 milliseconds in data base.

			Thread.sleep(1200);

		} catch (InterruptedException e) {
			// Restore interrupted status

			Thread.currentThread().interrupt();
		}
	}
}
