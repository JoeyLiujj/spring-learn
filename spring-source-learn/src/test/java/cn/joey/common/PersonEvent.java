package cn.joey.common;

public class PersonEvent {
	private PersonSource person;
	public PersonEvent(PersonSource person){
		this.person = person;
	}
	public PersonSource getSource(){
		return person;
	}
}
