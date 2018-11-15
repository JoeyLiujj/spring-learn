package cn.joey.expression;

import cn.joey.aop.CustomGenericTest;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;

public class TestExpression {

    @Test
    public void testParserContext() {
        ExpressionParser parser = new SpelExpressionParser();
        ParserContext context = new ParserContext() {
            @Override
            public boolean isTemplate() {
                return true;
            }

            @Override
            public String getExpressionPrefix() {
                return "${";
            }

            @Override
            public String getExpressionSuffix() {
                return "}";
            }
        };
        String template = "${'Hello'}${'World!'}";
        Expression expression = parser.parseExpression(template, context);
        String value = (String) expression.getValue();
        System.out.println("the value=" + value);

        Boolean aTrue = parser.parseExpression("true").getValue(Boolean.class);
        System.out.println(aTrue);

        Integer value1 = parser.parseExpression("4%3").getValue(Integer.class);
        System.out.println(value1);

        Boolean value2 = parser.parseExpression("1==2").getValue(Boolean.class);
        System.out.println(value2);

    }

    @Test
    public void testVariableRef() {
        try {
            ExpressionParser parser = new SpelExpressionParser();
            EvaluationContext context = new StandardEvaluationContext();
            Method calParam = SpelFunctionsExt.class.getDeclaredMethod("calParam",String.class,String.class,String.class,String.class,String.class,String.class);
            Method sumAccTraceMoney = SpelFunctionsExt.class.getDeclaredMethod("sumAccTraceMoney",Integer.class);
            context.setVariable("calParam", calParam);
            context.setVariable("sumAccTraceMoney", sumAccTraceMoney);
            context.setVariable("CValidate","10");
            context.setVariable("InsuredAppAge","11");
            int aa=0;
            context.setVariable("RALcInsureAccTraceClPojo",aa);
            context.setVariable("Prem",2);
            context.setVariable("Amnt",2);
            String expression1 = "#calParam(#CValidate,#InsuredAppAge,'12','18',(#sumAccTraceMoney(#RALcInsureAccTraceClPojo)==0?#Prem:#sumAccTraceMoney(#RALcInsureAccTraceClPojo))*0.6>#Amnt?(#sumAccTraceMoney(#RALcInsureAccTraceClPojo)==0?#Prem:#sumAccTraceMoney(#RALcInsureAccTraceClPojo))*0.6:#Amnt,#Amnt)";
//            String expression1 = "(#sumAccTraceMoney(#RALcInsureAccTraceClPojo)==0?#Prem:#sumAccTraceMoney(#RALcInsureAccTraceClPojo))*0.6>#Amnt?(#sumAccTraceMoney(#RALcInsureAccTraceClPojo)==0?#Prem:#sumAccTraceMoney(#RALcInsureAccTraceClPojo))*0.6:#Amnt";
//>#Amnt?(#sumAccTraceMoney(#RALcInsureAccTraceClPojo)==0?#Prem:#sumAccTraceMoney(#RALcInsureAccTraceClPojo))*0.6:#Amnt
            Object value = parser.parseExpression(expression1).getValue(context);
            System.out.println(value);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        //构造上下文，准备比如变量定义等等表达式运行需要的上下文数据
        EvaluationContext context = new StandardEvaluationContext();
        //创建解析器，提供SpelExpression的默认实现
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("#end*#end");
        //设置上下文环境中的变量值
        context.setVariable("end", 200);
        //执行表达式，获取运行结果
        Integer value = (Integer) expression.getValue(context);
        System.out.println("the str=" + value);
    }

    @Test
    public void testDecimalFunction() {
        String expression = "#decimal(#prem,5)";
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("prem","100.32");
        try {
            Object o = SpelUtilsExt.spelExecuteExp(expression, map);
            System.out.println(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDecimalExpression(){
        String expression = "#decimal(#prem,5)";
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        Method decimal = null;
        try {
            decimal = CustomGenericTest.class.getDeclaredMethod("decimal",Object.class,int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        context.setVariable("prem", "100.32");
        context.setVariable("decimal",decimal);
        Expression exp = parser.parseExpression(expression);
        Object value = exp.getValue(context);
        System.out.println(value);
    }

    @Test
    public void test1(){
        String expression = "#payintv == 0? (#insuredappage >= 0 and #insuredappage <= 40 ? #prem*0.3:(#insuredappage >= 41 and #insuredappage <= 55 ? #prem*0.2:0) ):(#payintv == 12?(#insuredappage >= 0 and #insuredappage <= 40 ? #prem*#payyears*0.3:(#insuredappage >= 41 and #insuredappage <= 55 ? #prem*#payyears*0.2:0)):0)";
        //创建解析器，提供SpelExpression的默认实现
        ExpressionParser parser = new SpelExpressionParser();
        //构造上下文，准备比如变量定义等等表达式运行需要的上下文数据
        StandardEvaluationContext context = new StandardEvaluationContext();
        //设置上下文环境中的变量值
        context.setVariable("insuredappage", 66);
        context.setVariable("payintv", 12);
        context.setVariable("payyears", 3);
        context.setVariable("prem",232.0);
        Expression exp = parser.parseExpression(expression);
        //执行表达式，获取运行结果
        Object value = exp.getValue(context);
        System.out.println(value);
    }

}
