package com.ssm.promotion.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
 
import javax.ws.rs.core.Response;
 
import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;
 
/**
 * 
 *<p>Description : 客户端发送请求共通方法</p>
 *<p>Date        : May 3, 2013</p>
 *<p>Remark      : </p>
 * @version
 */
public class HttpClient {

    private static final Logger logger = Logger.getLogger(HttpClient.class);// 日志文件
	
	public final static String ESB_REQUEST_ID = "TP-REQUEST-ID";
	public final static String ESB_SERVICE_ID = "TP-SERVICE-ID";
	public final static String ESB_APP_ID = "TP-APP-ID";
	public final static String ESB_ACCESS_TOKEN = "TP-ACCESS-TOKEN";
	public final static String ESB_REQUEST_TIME = "TP-REQUEST-TIME";
	public final static String ESB_REQUEST_HASH = "TP-REQUEST-HASH";
	public final static String ESB_REQUEST_EXT = "TP-REQUEST-EXT";
	
	private static final String ENCODING = "UTF-8";
	
	/**
	 * json
	 */
	private static final String APPLICATION_JSON = "application/json";
 
	/**
	 * xml
	 */
	private static final String APPLICATION_XML = "text/xml";
	/**
	 * form
	 */
	private static final String APPLICATION_FORM = "application/x-www-form-urlencoded";
	
	private static final long defaultReciveTimeout = 60000;
	private static long reciveTimeout;
	
	/**
	 * 设置HttpClient超时时间
	 * 
	 * 注意：因为是静态变量所以修改此处，会影响整个应用程序的超时。
	 *       如果不想影响到其他处调用的超时，在每次调用请求方法后，
	 *       需要再次调用 setDefaultTimeout()，恢复成默认设置。
	 * 
	 * @param timeout
	 */
	public static void setReciveTimeout(long timeout) {
		reciveTimeout = timeout;
	}
	
	public static void setDefaultTimeout() {
		reciveTimeout = defaultReciveTimeout;
		System.out.println("HttpClient default reciveTimeout is: " + reciveTimeout);
	}
	
	/**
	 * createClient
	 * 
	 * @param url
	 * @param providers
	 * @return
	 */
	private static WebClient createClient(String url, List<Object> providers) {
		WebClient client;
		if (providers != null && providers.size() > 0) {
			client = WebClient.create(url, providers);
		} else {
			client = WebClient.create(url);
		}
		ClientConfiguration config = WebClient.getConfig(client);
		config.getHttpConduit().getClient().setReceiveTimeout(reciveTimeout);
		return client;
	}
	
	private static WebClient createClient(String url) {
		return createClient(url, null);
	}
	
	/**
	 * post Json
	 * 
	 * @param url 请求地址
	 * @param o POJO对象
	 * @return 返回服务器响应
	 * @throws TpException
	 *          
	 */
	public static Object postJson(String url, Object o) {
		List<Object> providers = new ArrayList<Object>();
		// 配置json转换器
		providers.add(new org.codehaus.jackson.jaxrs.JacksonJsonProvider());
		// 创建Webclient对象
		WebClient client = createClient(url, providers);
		// 发送请求，服务器返回响应
		Object res = client.accept(APPLICATION_JSON).type(APPLICATION_JSON)
				.post(o, o.getClass());
		return res;
	}
	

	/**
	 * post Json
	 * 
	 * @param url 请求地址
	 * @param o Map对象
	 * @return 返回服务器响应
	 * @throws TpException
	 *          
	 */
	public static Object postJson(String url,  Map<String, String> o) {
		List<Object> providers = new ArrayList<Object>();
		// 配置json转换器
		providers.add(new org.codehaus.jackson.jaxrs.JacksonJsonProvider());
		// 创建Webclient对象
		WebClient client = createClient(url, providers);
		// 发送请求，服务器返回响应
		Object res = client.accept(APPLICATION_JSON).type(APPLICATION_JSON)
				.post(o, o.getClass());
		return res;
	}
 
	/**
	 * post Xml
	 * 
	 * @param url 请求地址
	 * @param o POJO对象
	 * @return 返回服务器响应
	 */
	public static Response postXML(String url, Object o) {
		WebClient client = createClient(url); // 创建创建Web client对象
		Response res = client
				.accept(APPLICATION_XML)
				.type(APPLICATION_XML) // 发送请求，服务器返回响应
				.post(o);
		return res;
	}
	
	/**
	 * post Xml with additional headers
	 * 
	 * @param url 请求地址
	 * @param o POJO对象
	 * @return 返回服务器响应
	 */
	public static Response postXML(String url, Object o, Map<String, String> addHeaders) {
		// 创建创建Web client对象
		WebClient client = createClient(url);
		addHeaders(client, addHeaders);
		 // 发送请求，服务器返回响应
		Response res = client
				.accept(APPLICATION_XML)
				.type(APPLICATION_XML)
				.post(o);
		return res;
	}
	
	/**
	 * post object to xml
	 * 
	 * @param url 请求地址
	 * @param o POJO对象
	 * @return 返回服务器响应
	 */
	public static Object postXML(String url, Object o, Class<?> clazz){
		// 创建创建Web client对象
		WebClient client = createClient(url);
		// 发送请求，服务器返回响应
		Object res = client
				.accept(APPLICATION_XML)
				.type(APPLICATION_XML)
				.post(o, clazz);
		return res;
	}
	
	public static Object postXML(String url, Object o, Class<?> clazz, Map<String, String> addHeaders){
		// 创建创建Web client对象
		WebClient client = createClient(url);
		addHeaders(client, addHeaders);
		// 发送请求，服务器返回响应
		Object res = client
				.accept(APPLICATION_XML)
				.type(APPLICATION_XML)
				.post(o, clazz);
		return res;
	}
 
	/**
	 * get xml
	 * @param url 请求地址
	 * @param o POJO对象
	 * @return 返回服务器响应
	 */
	public static Object getXML(String url, Object o) {
		// 创建创建Web client对象
		WebClient client = createClient(url);
		// 发送请求，服务器返回响应
		Object obj = client
				.accept(APPLICATION_XML)
				.type(APPLICATION_XML)
				.get(o.getClass());
		return obj;
	}
	
	public static Object getXML(String url, Object o, Map<String, String> addHeaders) {
		// 创建创建Web client对象
		WebClient client = createClient(url);
		addHeaders(client, addHeaders);
		// 发送请求，服务器返回响应
		Object obj = client
				.accept(APPLICATION_XML)
				.type(APPLICATION_XML)
				.get(o.getClass());
		return obj;
	}
 
	/**
	 * get json
	 * 
	 * @param url 请求地址
	 * @param o POJO对象
	 * @return 返回服务器响应
	 */
	public static Object getJson(String url, Object o) {
		// 创建创建Web client对象
		WebClient client = createClient(url);
		// 发送请求，服务器返回响应
		Object obj = client
				.accept(APPLICATION_JSON)
				.type(APPLICATION_JSON)
				.get(o.getClass());
		return obj;
	}
 
	public static Object getJson(String url, Object o, Map<String, String> addHeaders) {
		// 创建创建Web client对象
		WebClient client = createClient(url);
		addHeaders(client, addHeaders);
		// 发送请求，服务器返回响应
		Object obj = client
				.accept(APPLICATION_JSON)
				.type(APPLICATION_JSON)
				.get(o.getClass());
		return obj;
	}
	
	/**
	 * addHeaders
	 * 
	 * @param client
	 * @param addHeaders
	 */
	private static void addHeaders(WebClient client, Map<String, String> addHeaders) {
		if (addHeaders != null) {
			Set<String> keys = addHeaders.keySet();
			for(String key : keys) {
				client.header(key, addHeaders.get(key));
			}
		}
	}
	
}
