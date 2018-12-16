package cn.joey.spring.webmvc;

import java.util.Map;

/**
 * @auther liujiji
 * @date 2018/12/13 16:38
 */
public class GPModelAndView {
    private Object view;
    private Map<String,Object> model;
    private String viewName;

    public GPModelAndView(Object view, Map<String, Object> model) {
        this.view = view;
        this.model = model;
    }
    public Object getView() {
        return view;
    }

    public void setView(Object view) {
        this.view = view;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }

    public String getViewName() {
        return viewName;
    }
}
