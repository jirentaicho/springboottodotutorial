package com.volkruss.mytodo.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="todo")
@Setter
@Getter
public class Todo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="todo_id")
	private String todoId;
	
	@Column(name="todo_title")
	private String todoTitle;
	
	@Column(name="finished")
	private boolean finished;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;
}

