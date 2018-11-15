package com.taikang.common.weChatUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.codehaus.xfire.client.Client;
import org.springframework.util.StringUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * 发送微信消息工具类
 * 
 * @author itw_gusen
 *
 */
public class WeChatUtil {

	/*
	 * static Logger logger =
	 * LoggerFactory.getLogger(QuotationServiceImpl.class);
	 */

	/**
	 * 发送微信消息
	 * 
	 * @param workStr   标题
	 * @param remarkStr 内容
	 * @param openId    openId
	 * @return
	 */
	public static String sendWechat(String workStr, String remarkStr, String openId) {

		String first = "";
		String url = "";
		// 发消息
		// 拿到配置文件的openId
		// String openId =
		// PropertiesUtil.parseStr("openId")+","+PropertiesUtil.parseStr("addweipeng");
		String str = "";
		// 调用发送信息方法
		String str1 = sendMessage(openId, workStr, remarkStr, first, url);
		// 拼装返回值
		if (str.equals("")) {
			str = str1;
		} else {
			str = str + " , " + str1;
		}
		/* logger.debug("====成功发送====" + i); */
		return str;
	}

	private static String sendMessage(String openId, String workStr, String remarkStr, String first, String url) {
		JSONObject json = new JSONObject();

		if (url == null || url.replace(" ", "").length() == 0) {
			url = "";
		}

		if (StringUtils.hasLength(openId)) {
			// String appId="wxcd7143c00e5bb6f7";
			//String appId = PropertiesUtil.parseStr("wechat_appId");// appid
			String appId = openId;// appid
			// templateId="jHYMst39MM2XDtJyxkcGJ_AAeksV9YLykY7qk1gs9gw";//模板消息的id
			//String templateId = PropertiesUtil.parseStr("eporder_templateId");// 模板消息的id
			String templateId = "";
			//sendWeChatUrl=http\://10.130.201.92/SendMessage/services/WechatMessage/sendMessage
			// sendUrl="http://wxpt.taikang.com/tkmap/ws/templateMsg?wsdl";//生产发送模板消息的链接地址
			//String sendUrl = PropertiesUtil.parseStr("eporderMsg_url");// 生产发送模板消息的链接地址
			String sendUrl = "http://10.130.201.92/SendMessage/services/WechatMessage/sendMessage";
			/*
			 * logger.debug("-----appId----:" + sendUrl);
			 * logger.debug("-----templateId----:" + templateId);
			 * logger.debug("-----sendUrl----:" + sendUrl);
			 */

			String topColor = "";
			// String url="";
			Client client;
			Object[] results = null;
			try {
				client = new Client(new URL(sendUrl));
				client.setTimeout(10000);

				String data1 = getData(templateId, workStr, remarkStr, first);// 拼装模板消息正文,返回json

				/* logger.debug("-----data----:" + data1); */

				results = client.invoke("send", new Object[] { appId, openId, templateId, url, topColor, data1 });

				// Object[] results = client.invoke("send", new Object[]
				// {"wx90b252e89d5742e3",openId,"2DNke7kWNc0O67NVLp6VK0wWyo1QSNiErcCN0j_PCH8","",
				// "", data });
				/* logger.debug("==sendMessage接口返回===return===" + results[0]); */
				JSONObject jsonObj = JSONObject.parseObject((results[0].toString()));
				String rspDesc = jsonObj.getString("rspDesc");// 发送模板消息返回消息
				/* logger.debug("====发送模版消息 ==== rspDesc：" + rspDesc); */
				String rspCode = jsonObj.getString("rspCode");// 发送模板消息返回码
				String code = "0".equals(rspCode) ? "200" : rspCode;
				/* logger.debug("====发送模版消息 ==== rspCode：" + rspCode); */
				json.put("rspDesc", rspDesc);
				json.put("rspCode", code);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			/* logger.debug("-----sendMessage----:over"); */
		}
		return json.toString();
	}

	private static String getData(String templateId, String workStr, String remarkStr, String first1) {
		JSONObject json = new JSONObject();
		//String tid = PropertiesUtil.parseStr("eporder_templateId");
		String tid = "";

		if (templateId.equals(tid)) {

			// 待办工作提醒,发送抢票险相关信息,发送给指定的人
			JSONObject first = new JSONObject();
			JSONObject work = new JSONObject();
			JSONObject remark = new JSONObject();

			first.put("value", first1);

			work.put("value", workStr);
			// work.put("color", "#FF8C00");
			remark.put("value", remarkStr);

			json.put("first", first);

			json.put("work", work);
			json.put("remark", remark);
		}
		return json.toString();
	}

	
	/**
	 * 发送http请求
	 * 
	 * @author ron
	 * @createDate 2015-07-14
	 * @param requestUrl
	 *            　请求地址
	 * @param requestMethod
	 *            　请求方式(GET,POST)
	 * @param outputStr
	 *            提交的数据
	 * @return　JSONObject(通过JSONObject.get(key)的方式获取JSON对象的属性值)
	 * @throws IOException
	 */
	public static String httpRequest(String requestUrl, String requestMethod,
			String messageStr) throws IOException {
		String tmp = null;
		BufferedReader bufferedReader = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		HttpURLConnection conn = null;
		InputStreamReader inputStreamReader = null;
		try {

			URL url = new URL(requestUrl);
			conn = (HttpURLConnection) url.openConnection();
			// conn.setRequestProperty("content-type", "text/html");
			conn.setRequestProperty("content-type", "application/json");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setReadTimeout(1800000);
			conn.setConnectTimeout(1800000);
			// 设置请求方式(GET/POST)
			conn.setRequestMethod(requestMethod);
			if (messageStr != null) {
				outputStream = conn.getOutputStream();
				outputStream.write(messageStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 从输入流读取返回内容
			inputStream = conn.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();

			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			// 释放资源

			tmp = buffer.toString();
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != bufferedReader) {
				bufferedReader.close();
			}
			if (null != inputStreamReader) {
				inputStreamReader.close();
			}
			if (null != inputStream) {
				inputStream.close();
				inputStream = null;
			}
			if (null != conn) {
				conn.disconnect();
			}
		}
		return tmp;
	}
	
	
	
	
	
	
}
