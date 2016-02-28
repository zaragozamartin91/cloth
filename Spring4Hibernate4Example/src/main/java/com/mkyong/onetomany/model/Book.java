package com.mkyong.onetomany.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Book {
	private Integer id;
	private String title;
	private Author author;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(nullable = false, unique = true)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// joinColumn permite establecer explicitamente la columna de la tabla de
	// relacion entre entidades. es opcional en el caso OneToMany -> ManyToOne
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Author.class)
	@JoinColumn(name = "authorId", nullable = false)
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Book(String title, Author author) {
		super();
		this.title = title;
		this.author = author;
	}

	public Book() {
		super();
	}

}
