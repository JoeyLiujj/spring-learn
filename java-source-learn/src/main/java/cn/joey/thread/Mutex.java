package cn.joey.thread;

import java.io.Serializable;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @auther liujiji
 * @date 2018/11/14 10:49
 */
public class Mutex implements Serializable {

    private static class Sync extends AbstractQueuedSynchronizer{

        @Override
        protected boolean isHeldExclusively() {
            return getState()==1;
        }

        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if(getState()==0) throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

    }
    private final Sync sync = new Sync();

    public void lock(){
        sync.acquire(1);
    }
    public boolean tryLock(){
        return sync.tryAcquire(1);
    }
    public void unlock(){
        sync.release(1);
    }
    public boolean isLocked(){
        return sync.isHeldExclusively();
    }
}
