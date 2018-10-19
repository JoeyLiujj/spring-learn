package cn.joey.exception;

public class CustomException extends RuntimeException {
    private int code;

    /**
     * 定义有参构造函数时，需要重写父类的无参构造函数
     */
    public CustomException() {
        super();
    }

    public CustomException(int code,String message){
        super(message);
        this.code=code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
