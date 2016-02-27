package com.journaldev.dao;

import java.util.List;

import com.journaldev.model.Person;

public interface PersonDao {

	void save(Person person);

	List<Person> list();

}