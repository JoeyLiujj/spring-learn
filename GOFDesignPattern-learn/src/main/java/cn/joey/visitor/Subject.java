package cn.joey.visitor;

public interface Subject {
	 void accept(Visitor visitor);
	 String getSubject();
}
