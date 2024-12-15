package com.cmmurielo.model;

import com.cmmurielo.utils.Constants;
import com.cmmurielo.utils.Utilities;

import java.time.LocalDateTime;

public class Task {
	private int id;
	private String description;
	private boolean isCompleted;
	private String created;
	private boolean active;

	public Task(String description) {
		Utilities utilities = new Utilities();
		this.description = description;
		this.created = utilities.parseLocalDataTimeToString(LocalDateTime.now(),
				Constants.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSSSSS);
		this.isCompleted = false;
		this.active = true;
	}

	public Task(int id, String description, boolean isCompleted, String created, boolean active) {
		this.id = id;
		this.description = description;
		this.isCompleted = isCompleted;
		this.created = created;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated() {
		Utilities utilities = new Utilities();
		this.created = utilities.parseLocalDataTimeToString(LocalDateTime.now(),
				Constants.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSSSSS);
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return String.format("Task ID: %d%nDescription: %s%nCompleted: %s%nCreated: %s%n", id, description,
				isCompleted ? "Completada" : "Sin completar", created);
	}
}
