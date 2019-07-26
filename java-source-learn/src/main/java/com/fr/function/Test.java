package com.fr.function;
import com.fr.script.AbstractFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test extends AbstractFunction {

    // 参数顺序
    public Object run(Object[] args) {
        String result="";
        for(int i=0;i<args.length;i++){
            String a = args[i].toString();
            String[] s = a.split(",");
            result = s+result;
        }
        return result;
    }

    public static void main(String[] args) {
        Random random = new Random();
        random.nextInt();
    }
}