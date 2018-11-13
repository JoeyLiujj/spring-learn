package cn.joey.thread;

/**
 * @auther liujiji
 * @date 2018/10/31 15:11
 */
public class VolatileObjectTest implements Runnable {
    private volatile ObjectA a;
    public VolatileObjectTest(ObjectA a){
        this.a = a;
    }

    public ObjectA getA() {
        return a;
    }

    public void setA(ObjectA a) {
        this.a = a;
    }

    public void stop() {
        a.setFlag(false);
    }
    @Override
    public void run() {
        long i = 0;
        while(a.isFlag()){
            i++;
        }
        System.out.println("stop My Thread "+i);
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.getProperty("java.vm.name"));
        VolatileObjectTest test = new VolatileObjectTest(new ObjectA());
        new Thread(test).start();
        Thread.sleep(1000);
        test.stop();
        Thread.sleep(1000);
        System.out.println("Main Thread "+test.getA().isFlag());
    }

    static class ObjectA{
        private boolean flag = true;

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }
    }
}
