package cn.joey.common;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Outer {
	final String a = "a"; 
	String b="b";
	static String c = "c";
	static final String d="d";
	
	public Inner dosome(final String a,final String b){//防止内部类修改
		return new Inner(){

			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getProvince() {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
	}
	public static void main(String[] args) {
		Outer outer=new Outer();
		String a ="10";
		String b = "20";
		outer.dosome(a,b);
		System.out.println(outer.method(2));
	}
	
	public int method(int a) {
		try{
			int d=8/0;
			
		}catch(Exception e){
			
			return 10;
		}finally{
			System.out.println(10+"----");
		}
		return 12;
	}
}

interface Inner{
	String getName();
	String getProvince();
}
