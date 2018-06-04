package cn.joey.common;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class FirstDayAtSchoolTest {

	FirstDayAtSchool school = new FirstDayAtSchool();
	String[] bag1={"Books","NoteBooks","Pens"};
	String[] bag2={"Books","NoteBooks","Pens","Rulers"};
	@Test
	public void testPrepareMYBag() {
		String[] prepareMYBag = school.prepareMYBag();
		assertArrayEquals(bag1, prepareMYBag);
	}

	@Test
	public void testAddPencils() {
		String[] pencils = school.addPencils();
		assertArrayEquals(bag2, pencils);
	}

}
