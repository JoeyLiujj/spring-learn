package cn.joey.observer;

public interface Subject {
	public void add(Observer server);
	public void del(Observer server);
	public void notifyObservers();
	public void operation();
}
