package cn.joey.visitor;

public class MyVisitor implements Visitor{

	@Override
	public void visit(Subject sub) {
		System.out.println("visit this subject:"+ sub.getSubject());
	}

}
