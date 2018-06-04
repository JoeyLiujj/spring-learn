package cn.joey.common;

import java.util.Arrays;

public class FirstDayAtSchool {
	public String[] prepareMYBag(){
		String[] schoolMsg = {"Books","NoteBooks","Pens"};
		System.out.println("My school bag contains"+Arrays.toString(schoolMsg));
		return schoolMsg;
	}
	public String[] addPencils(){
		String[] schoolbag = {"Books","NoteBooks","Pens","Pencils"};
		System.out.println("Now my school bag contains:"+Arrays.toString(schoolbag));
		return schoolbag;
	}
}
