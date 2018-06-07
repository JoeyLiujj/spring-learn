package cn.joey.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author Joey
 *
 */
public class Prototype implements Cloneable,Serializable{

	private static final long serialVersionUID = 1L;
	private String str;
	private int i;
	private SerializableObj obj;
	
	public Object clone() throws CloneNotSupportedException{
		Prototype proto=(Prototype)super.clone();
		return proto;
	}
	
	public Object deepClone() throws IOException, ClassNotFoundException{
		/* 写入当前对象的二进制流 */  
		ByteArrayOutputStream bos = new ByteArrayOutputStream();  
		ObjectOutputStream oos = new ObjectOutputStream(bos);  
		oos.writeObject(this);  
		
		/* 读出二进制流产生的新对象 */  
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());  
		ObjectInputStream ois = new ObjectInputStream(bis);  
		return ois.readObject();  
	}
	
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public SerializableObj getObj() {
		return obj;
	}
	public void setObj(SerializableObj obj) {
		this.obj = obj;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
	
	
}
class SerializableObj implements Serializable{

	private static final long serialVersionUID = 1L;
	
}