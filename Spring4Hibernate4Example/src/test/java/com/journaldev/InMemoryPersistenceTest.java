package com.journaldev;

import java.util.List;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.journaldev.config.Config;
import com.journaldev.dao.PersonDao;
import com.journaldev.model.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Config.class })
@ActiveProfiles("testMemory")
public class InMemoryPersistenceTest {
	@Autowired
	private PersonDao personDao;

	@Test
	public void testSave() {
		Person person = new Person("Martin", "Argentina");
		personDao.save(person);

		List<Person> people = personDao.list();
		assertEquals(1, people.size());

		System.out.println(person);
	}
}
