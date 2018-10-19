package cn.joey.observer;

import java.util.Vector;

public class AbstractSubject implements Subject{

	private Vector<Observer> vector = new Vector<Observer>();
	
	@Override
	public void add(Observer server) {
		vector.add(server);
	}

	@Override
	public void del(Observer server) {
		vector.remove(server);
	}

	@Override
	public void notifyObservers() {
		for(Observer server:vector){
			server.update();
		}
	}

	@Override
	public void operation() {

	}

}
