package cn.joey.aop;

import java.io.IOException;

public interface UserService {
    public void add() throws IOException;
    public void update(int a);
    public void delete();
    public void search();
}
