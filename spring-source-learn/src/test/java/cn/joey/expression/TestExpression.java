package cn.joey.expression;

import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

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
    public void testParaviableRef() {
        try {
            ExpressionParser parser = new SpelExpressionParser();
            EvaluationContext context = new StandardEvaluationContext();
            Method parseInt = Integer.class.getDeclaredMethod("parseInt", String.class);
            context.setVariable("regParseInt", parseInt);
            context.setVariable("parseInt2", parseInt);
            String expression1 = "#regParseInt('3') == #parseInt2('3')";
            Boolean value = parser.parseExpression(expression1).getValue(context, boolean.class);
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
        Expression expression = parser.parseExpression("#end+#end");

        //设置上下文环境中的变量值
        context.setVariable("end", "200");

        //执行表达式，获取运行结果
        String value = (String) expression.getValue(context);
        System.out.println("the str=" + value);
    }
}
