package cn.joey.chain;

public class MyHandler extends AbstractHandler implements Handler{

	private String name;
	
	public MyHandler(String name){
		this.name=name;
	}
	
	@Override
	public void operateor() {
		System.out.println(name + "deal!");
		//此处决定是否继续向下传递
		//为空 则不向下传递，不为空 则向下传递
		if(getHandler()!=null){
			getHandler().operateor();
		}
	}
}
