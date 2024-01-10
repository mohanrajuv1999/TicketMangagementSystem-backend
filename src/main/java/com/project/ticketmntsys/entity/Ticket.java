package com.project.ticketmntsys.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Size(min = 1, max = 50, message = "Title too long (max 50 characters)")
	private String title;

	@NotNull
	@Size(min = 1, max = 500, message = "Description too long (max 500 characters)")
	private String description;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOpened;

	private Date dateClosed;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdated;

	private String createdBy;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "ticket_status", joinColumns = @JoinColumn(name = "ticket_id"), inverseJoinColumns = @JoinColumn(name = "status_id"))
	private List<Status> updates;

	private String assigned_to;

	private String stage;
	
	private String priority;

	public Ticket(String title, String description, String createdBy) {
		this.title = title;
		this.description = description;
		this.createdBy = createdBy;
	}

	public Ticket() {
	}
	
	public Ticket(String assigned_to)
	{
		this.assigned_to=assigned_to;
	}

	
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Ticket(int id,
			@NotNull @Size(min = 1, max = 50, message = "Title too long (max 50 characters)") String title,
			@NotNull @Size(min = 1, max = 500, message = "Description too long (max 500 characters)") String description,
			Date dateOpened, Date dateClosed, Date lastUpdated, String createdBy, List<Status> updates,
			String assigned_to, String stage, String priority) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.dateOpened = dateOpened;
		this.dateClosed = dateClosed;
		this.lastUpdated = lastUpdated;
		this.createdBy = createdBy;
		this.updates = updates;
		this.assigned_to = assigned_to;
		this.stage = stage;
		this.priority = priority;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateOpened() {
		return dateOpened;
	}

	public void setDateOpened(Date dateOpened) {
		this.dateOpened = dateOpened;
	}

	public Date getDateClosed() {
		return dateClosed;
	}

	public void setDateClosed(Date dateClosed) {
		this.dateClosed = dateClosed;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public List<Status> getUpdates() {
		return updates;
	}

	public void setUpdates(List<Status> updates) {
		this.updates = updates;
	}
	public void addUpdate(Status status) {
		this.updates.add(status);
	}

	public String getassigned_to() {
		return assigned_to;
	}

	public void setassigned_to(String assigned_to) {
		this.assigned_to = assigned_to;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}
}