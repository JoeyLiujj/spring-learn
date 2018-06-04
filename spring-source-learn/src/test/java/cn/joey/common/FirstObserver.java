package cn.joey.common;

import java.util.Observable;
import java.util.Observer;

public class FirstObserver implements Observer{

	@Override
	public void update(Observable obj, Object arg) {
		System.out.println("��ϴ������ûϴ���ܳ���");
	}

}
