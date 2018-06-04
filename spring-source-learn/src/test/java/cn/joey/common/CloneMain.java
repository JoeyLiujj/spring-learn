package cn.joey.common;

public class CloneMain {
	public static void main(String[] args) {
		CloneB b1=new CloneB();
		b1.aInt=11;
		System.out.println("before clone,b1.aInt="+b1.aInt);
		System.out.println("before clone,b1.unCA="+b1.unCA);
		
		CloneB b2=(CloneB)b1.clone();
		b2.aInt=22;
//		b2.unCA.doublevalue();
		b1.aInt=33;
		b1.unCA.doublevalue();
		
		System.out.println("===============================");
		System.out.println("after clone,b1.aInt="+b1.aInt);
		System.out.println("after clone,b1.unCA="+b1.unCA);
		
		System.out.println("===============================");
		System.out.println("after clone,b2.aInt="+b2.aInt);
		System.out.println("after clone,b2.unCA="+b2.unCA);
		
		
		CloneC c1=new CloneC();
		c1.str = new String("initialzeStr");
		c1.strBuff=new StringBuffer("initializeStrBuff");
		System.out.println("before clone,c1.str="+c1.str);
		System.out.println("before clone,c1.strBuff="+c1.strBuff);
		
		CloneC c2 =(CloneC) c1.clone();
		c2.strBuff = c2.strBuff.append("change strBuff clone");
		System.out.println("===============================");
		System.out.println("after clone,c1.str="+c1.str);
		System.out.println("after clone,c1.strBuff="+c1.strBuff);
		
		System.out.println("===============================");
		System.out.println("after clone,c2.str="+c2.str);
		System.out.println("after clone,c2.strBuff="+c2.strBuff);
		
		
		CloneC c = new CloneC();
		c.str="1000";
		c.chageStr(c.str);
		System.err.println(c.str);
	}
}
