package hibernateexamples.journaldev.dao;

import hibernateexamples.journaldev.model.Person;

import java.util.List;

public interface PersonDao {

	void save(Person person);

	List<Person> list();

}