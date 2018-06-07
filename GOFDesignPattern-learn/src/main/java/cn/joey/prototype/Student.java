package cn.joey.prototype;

public class Student implements Cloneable{
	public String name;
	public int age;
	private Professor professor;
	public Student (String name,int age,Professor professor){
		this.name=name;
		this.age = age;
		this.professor = professor;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Object object =null;
		object = (Student)super.clone();
		return object;
	}
	public static void main(String[] args) {
		try {
			Professor professor = new Professor("zhangsan1");
			Student student = new Student("zhangsan", 20,professor);
			Student copy =(Student) student.clone();
			copy.age =20;
			copy.name = "lisi";
			copy.setProfessor(new Professor("lisi1"));
			System.out.println(student.getAge()+student.getName()+student.getProfessor().getName());
			
		} catch (CloneNotSupportedException e) {
			
		}
		
	}
}
