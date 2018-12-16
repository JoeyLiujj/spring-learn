package cn.joey.spring.webmvc.servlet;

import cn.joey.spring.annotation.GPController;
import cn.joey.spring.annotation.GPRequestMapping;
import cn.joey.spring.annotation.GPRequestParam;
import cn.joey.spring.aop.GPAopProxy;
import cn.joey.spring.aop.GPAopProxyUtils;
import cn.joey.spring.context.GPApplicationContext;
import cn.joey.spring.webmvc.GPDefaultViewResolver;
import cn.joey.spring.webmvc.GPHandlerAdapter;
import cn.joey.spring.webmvc.GPHandlerMapping;
import cn.joey.spring.webmvc.GPModelAndView;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @auther liujiji
 * @date 2018/12/13 16:14
 */
public class GPDispatcherServlet extends HttpServlet {

    private final String LOCATION="contextConfigLocation";

    private List<GPHandlerMapping> handlerMapping = new ArrayList<GPHandlerMapping>();

    private Map<GPHandlerMapping, GPHandlerAdapter> handlerAdapters = new HashMap<GPHandlerMapping, GPHandlerAdapter>();

    private List<GPDefaultViewResolver> viewResolvers =new ArrayList<GPDefaultViewResolver>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        //初始化上下文
        //初始化策略
        GPApplicationContext context = new GPApplicationContext(config.getInitParameter(LOCATION));
        initStrategy(context);
    }

    public void initStrategy(GPApplicationContext context){
        //初始化策略 初始化九大组件
        initMultipartResolver(context);
        initThemeResolver(context);
        initHandlerMapping(context);  //主要实现
        initHandlerAdapter(context);  //主要实现
        initViewResolver(context);    //主要实现
        initFlashMapManager(context);
        initLocaleResolver(context);
        initHandlerExceptionResolver(context);
        initRequestToViewNameTranslator(context);
    }

    //将controller中配置的RequestMapping和Method进行一一对应
    private void initHandlerMapping(GPApplicationContext context) {
        //按照我们的通常的理解应该是一个map
        //Map《String,Method> map
        //map.put(url,Method)

        String[] beanNames = context.getBeanDefinitionNames();

        try {
            for(String beanName:beanNames){
                Object proxy = context.getBean(beanName);
                Object controller = GPAopProxyUtils.getTargetObject(proxy);
                Class<?> clazz = controller.getClass();

                if (!clazz.isAnnotationPresent(GPController.class)) {
                    continue;
                }
                String baseUrl = "";
                if (clazz.isAnnotationPresent(GPRequestMapping.class)) {
                    GPRequestMapping requestMapping = clazz.getAnnotation(GPRequestMapping.class);
                    baseUrl = requestMapping.value();
                }

                Method[] methods = clazz.getMethods();
                for(Method method:methods){
                    if(!method.isAnnotationPresent(GPRequestMapping.class)){
                        continue;
                    }

                    GPRequestMapping requestMapping = method.getAnnotation(GPRequestMapping.class);
                    String regex = ("/"+baseUrl+requestMapping.value().replaceAll("\\*",".*").replaceAll("/+","/"));

                    Pattern pattern = Pattern.compile(regex);
                    this.handlerMapping.add(new GPHandlerMapping(pattern,controller,method));
                    System.out.println("Mapping: "+regex+" , "+method);
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void initHandlerAdapter(GPApplicationContext context) {

        for (GPHandlerMapping handlerMapping : this.handlerMapping) {
            Map<String,Integer> paramMapping = new HashMap<String,Integer>();
            Annotation[][] pa = handlerMapping.getMethod().getParameterAnnotations();
            for(int i=0;i<pa.length;i++){
                for(Annotation a:pa[i]){
                    if(a instanceof GPRequestParam){
                        String paramName = ((GPRequestParam) a).value();
                        if(!"".equals(paramName.trim())){
                            paramMapping.put(paramName,i);
                        }
                    }
                }
            }

            //处理非命名参数
            Class<?>[] paramTypes = handlerMapping.getMethod().getParameterTypes();

            for (int i = 0; i <paramTypes.length ; i++) {
                Class<?> type = paramTypes[i];
                if(type == HttpServletRequest.class||type == HttpServletResponse.class){
                    paramMapping.put(type.getName(),i);
                }
                
            }
            this.handlerAdapters.put(handlerMapping,new GPHandlerAdapter(paramMapping));
        }

    }

    private void initViewResolver(GPApplicationContext context) {
        String templateRoot = context.getConfig().getProperty("templateRoot");
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        File templateRootDir = new File(templateRootPath);

        for(File template:templateRootDir.listFiles()){
            this.viewResolvers.add(new GPDefaultViewResolver(template.getName(),template));
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            doDispatch(req, resp);
        }catch(Exception e){
            resp.getWriter().write("");
            e.printStackTrace();
        }

    }

    public void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取到对应的request对象，根据request得到请求的url以及参数
        //然后根据url找到对应的Controller，调用controller的方法
        //然后对返回的对象进行处理，如果
        //返回一个GPModelAndView
        //然后处理

//        得到一个handlerMapping对象，
        GPHandlerMapping handler = getHandler(req);
        if(handler ==null){
            resp.getWriter().write("<font size='25' color = 'red'>  404 Not Found </font> <br/> <>");
        }

        GPHandlerAdapter ha = getHandlerAdapter(handler);

        GPModelAndView mv = ha.handle(req,resp,handler);

        processDispatchResult(resp,mv);


    }

    private GPHandlerAdapter getHandlerAdapter(GPHandlerMapping handler) {
        if (this.handlerAdapters.isEmpty()) {
            return null;
        }
        return this.handlerAdapters.get(handler);
    }

    private GPHandlerMapping getHandler(HttpServletRequest req) {
        if(this.handlerMapping.isEmpty()) return null;
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();

        url = url.replace(contextPath,"").replaceAll("/+","/");

        for(GPHandlerMapping handlerMapping:this.handlerMapping){
            Matcher matcher = handlerMapping.getPattern().matcher(url);
            if(!matcher.matches()) {continue;}
            return handlerMapping;

        }
        return null;
    }

    private void processDispatchResult(HttpServletResponse resp, GPModelAndView mv) throws Exception {
        if(null==mv)return ;
        if (this.viewResolvers.isEmpty()) {
            return ;

        }

        for(GPDefaultViewResolver viewResolver:viewResolvers){
            if(!mv.getViewName().equals(viewResolver.getViewName())){continue;}
            String out = viewResolver.viewResolver(mv);
            if(out!=null){
                resp.getWriter().write(out);
                break;
            }
        }

    }


    private void initHandlerExceptionResolver(GPApplicationContext context) { }
    private void initRequestToViewNameTranslator(GPApplicationContext context) { }
    private void initMultipartResolver(GPApplicationContext context) { }
    private void initLocaleResolver(GPApplicationContext context) { }
    private void initFlashMapManager(GPApplicationContext context) { }
    private void initThemeResolver(GPApplicationContext context) { }
}
