package cn.joey.common;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.commons.lang3.time.DateUtils;


public class Test<T extends Number> {
	private T e;
	public void caculate(T a,T b){
		System.out.println();
	}
	public static <T> void print(T[] e){
		
	}
	public static void main(String[] args) throws ParseException {
		String[] split = "1".split(",");
		System.out.println(split.length);
	}
	public static int hash(int h){
		h^=(h>>>20)^(h>>>12);
		return h^(h>>>7)^(h>>>4);
	}
}
