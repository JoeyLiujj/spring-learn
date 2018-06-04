package cn.joey.common;

public class UnCloneA implements Cloneable{
	private int i;
	public UnCloneA(int ii){
		i=ii;
	}
	public void doublevalue(){
		i*=2;
	}
	public String toString(){
		return Integer.toString(i);
	}
	@Override
	protected Object clone() {
		UnCloneA o= null;
		try{
			o = (UnCloneA)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return o;
	}
}
class CloneB implements Cloneable{
	public int aInt;
	public UnCloneA unCA = new UnCloneA(111);
	public Object clone(){
		CloneB o =null;
		try{
			o =(CloneB) super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		o.unCA =(UnCloneA)unCA.clone();
		return o;
	}
}

class CloneC implements Cloneable{
	public String str;
	public StringBuffer strBuff;
	public Object clone(){
		CloneC o=null;
		try{
			o = (CloneC)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return o;
	}
	public void chageStr(String b){
		b="10000";
	}
}