package cn.joey.common;

import static org.junit.Assert.*;
import org.junit.Test;

public class PrepareMybagTest {
	FirstDayAtSchool school = new FirstDayAtSchool();
	String[] bag={"Books","NoteBooks","Pens"};
	@Test
	public void testPrepareMyBag(){
		System.out.println("Inside testPrepareMyBag");
		assertArrayEquals(bag, school.prepareMYBag());
	}
}
