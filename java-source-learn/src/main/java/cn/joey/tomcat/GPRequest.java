package cn.joey.tomcat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;

/**
 * @auther liujiji
 * @date 2019/6/18 10:41
 */
public class GPRequest {
    private ChannelHandlerContext ctx;
    private HttpRequest req;

    public GPRequest(ChannelHandlerContext ctx, HttpRequest req) {
        this.ctx = ctx;
        this.req = req;
    }

    public String getUrl() {
        return req.getUri();
    }

    public String getMethod() {
        return req.getMethod().name();
    }

    public Map<String,List<String>> getParameters(){
        QueryStringDecoder decoder = new QueryStringDecoder(req.getUri());
        return decoder.parameters();
    }

    public String getParameter(String name){
        Map<String,List<String>> params = getParameters();
        List<String> param = params.get(name);
        if(null==param){
            return null;
        }else{
            return param.get(0);
        }
    }
}
