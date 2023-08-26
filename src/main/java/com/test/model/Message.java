package com.test.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Message {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String text;
	private long senderId;
	private long receiverId;
	private Date dateTime;
}
