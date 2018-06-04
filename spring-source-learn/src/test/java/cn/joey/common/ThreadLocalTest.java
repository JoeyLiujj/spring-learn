package cn.joey.common;

import java.util.Random;

public class ThreadLocalTest implements Runnable{

	ThreadLocal<Student> studentThreadLocal = new ThreadLocal<Student>();
	
	@Override
	public void run() {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println(currentThreadName+" is running ... ");
		Random random = new Random();
		int age = random.nextInt();
		System.out.println(currentThreadName+" is set age: "+age);
		//通过这个方法，为每一个线程都独立的new一个student对象，每个线程的student对象都可以设置不同的值。
		Student student = getStudent();
		student.setAge(age);
		System.out.println(currentThreadName+" is the first get age:"+student.getAge());
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(currentThreadName+" is second get age: " + student.getAge());
	}

	private Student getStudent() {
		Student student = studentThreadLocal.get();
		if(student==null){
			student = new Student();
			studentThreadLocal.set(student);
		}
		return student;
	}
	
	public static void main(String[] args) {
		ThreadLocalTest test1 = new ThreadLocalTest();
		Thread t1 = new Thread(test1, "thread1");
		Thread t2 = new Thread(test1, "thread2");
		t1.start();
		t2.start();
	}
}

class Student{
	int age;
	public int getAge(){
		return age;
	}
	public void setAge(int age){
		this.age=age;
	}
}