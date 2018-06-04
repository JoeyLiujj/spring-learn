package cn.joey;

public class HelloWorld {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public void init(){
		System.out.println("该句在Bean初始化过程中被执行");
	}
}
