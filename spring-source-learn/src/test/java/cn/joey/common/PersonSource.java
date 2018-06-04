package cn.joey.common;

import java.util.List;
import java.util.Vector;

public class PersonSource {
	private List<PersonListener> listeners = new Vector<PersonListener>();
	public void eat(){
		for(PersonListener t:listeners){
			t.isCanEat(new PersonEvent(this));
		}
	}
	public void run(){
		
	}
	public void addPersonListener(PersonListener listener){
		listeners.add(listener);
	}
	public void removePersonListener(PersonListener listener){
		listeners.remove(listener);
	}
	public static void main(String[] args) {
		PersonSource person = new PersonSource();
		person.addPersonListener(new PersonListener() {
			
			@Override
			public void isCanEat(PersonEvent event) {
				PersonSource source = event.getSource();
				System.out.println(source.getClass());
			}
		});
		person.eat();
	}
}
