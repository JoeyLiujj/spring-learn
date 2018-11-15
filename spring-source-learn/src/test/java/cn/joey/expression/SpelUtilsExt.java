package cn.joey.expression;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.HashMap;

public class SpelUtilsExt {
    /**
     * 只需要把参数放到Map中传递过来，自定义函数可以不绑定
     * 如计算过公式：#a+#min(#a,#b,#c)，只需要传递a、b、c三个值，而min函数不要传递Method方法。
     * 程序自动从自定义函数类spelFunctions中获取公式中使用的函数。
     * 如果用到自定义函数，需要自己在spelFunctions类中新增方法
     *
     * @param expression
     * @param paramMap
     * @return Object
     * @throws Exception
     */
    public static Object spelExecuteExp(String expression, HashMap<String, Object> paramMap) throws Exception {
        Object result;
        if (expression == null || paramMap == null || "".equals(expression)) {
            throw new Exception("入参为空，或者空串");
        }
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariables(paramMap);
        initFunctions(expression, context);
        Expression exp = parser.parseExpression(expression);
        result = exp.getValue(context);
        return result;
    }

    /**
     * 根据计算公式使用的自定义函数名称，把spelFunctions中自定义函数加载到StandardEvaluationContext
     *
     * @param expression
     * @param context
     */
    private static void initFunctions(String expression, StandardEvaluationContext context) {
        //获取spel自定义的所有函数
        Method[] methods = SpelFunctionsExt.class.getMethods();
        for (int i = 0; i < methods.length; i++) {
            if (expression.contains(methods[i].getName())) {
                context.setVariable(methods[i].getName(), methods[i]);
            }
        }
    }
}
