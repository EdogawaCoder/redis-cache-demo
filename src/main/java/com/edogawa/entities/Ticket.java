package com.edogawa.entities;

import java.time.Instant;

import com.edogawa.enums.Priority;
import com.edogawa.enums.Status;

import lombok.Data;

@Data
public class Ticket implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String title;
	private Status status;
	private Priority priority;
	private String customerId;
	// Username of the person assigned to the ticket
	private String assignedTo;
	private	Instant createdAt;
	private Instant updatedAt;
	
	
	public Ticket(Long id, String title, Status status, Priority priority, String customerId, String assignedTo,
			Instant createdAt, Instant updatedAt) {
		super();
		this.id = id;
		this.title = title;
		this.status = status;
		this.priority = priority;
		this.customerId = customerId;
		this.assignedTo = assignedTo;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}


	public Ticket() {
		
	}
	
	
}
