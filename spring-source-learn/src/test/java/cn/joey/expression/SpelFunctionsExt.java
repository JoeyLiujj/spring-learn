package cn.joey.expression;

import org.springframework.util.NumberUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class SpelFunctionsExt {
    /**
     * 定义一个decimal函数
     */
    public static String decimal(Object t, int decimals) {
        Number number = NumberUtils.parseNumber(t.toString(), Number.class);
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(decimals); //设置数值的小数部分允许的最大位数
        return nf.format(number);
    }

    public static String calParam(String a,String b,String c,String d,String e,String f){
        return ""+a+b+c+d+e+f;
    }
    public static int sumAccTraceMoney(Integer a){
        return 0;
    }
}
