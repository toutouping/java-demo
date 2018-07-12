package com.ssm.promotion.test.ws;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Before;
import org.junit.Test;

import com.ssm.promotion.core.adapter.StringObjectMapAdapter;
import com.ssm.promotion.core.adapter.StringObjectMapAdapter.MapConvertor;
import com.ssm.promotion.core.entity.Org;

public class OrgServiceTest {  
    
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetCategory() {
		WebClient webClient = WebClient
				.create("http://localhost:8080/java-demo/webservice")
				.path("/orgService/org")
				.path("/1");
		Response respone = webClient
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON)
				.get();
		System.out.println(respone.getMetadata());
		System.out.println(respone.readEntity(String.class));
		assertEquals(9, 9);
	}

	@Test
	public void addOrgs() {
		WebClient webClient = WebClient
				.create("http://localhost:8080/java-demo/webservice/orgService") // 访问地址
				.path("/addOrgs", "post"); // 地址 和 参数

        Org u = new Org();
        u.setParentId("2");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("org", u);
        try {
        	MapConvertor map1 = new StringObjectMapAdapter().marshal(map);
	        Response respon = webClient
	        		.accept(MediaType.APPLICATION_JSON)
	                .type(MediaType.APPLICATION_JSON)
	                .post(map);
	        Response obj = webClient.invoke("sendMap", map);
			System.out.println(obj.getMetadata());
			System.out.println(obj.readEntity(String.class));
			System.out.println(respon.getMetadata());
			System.out.println(respon.readEntity(String.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
/*	@Test  
    public void testStringList() {  
        List<String> strList = new ArrayList<String>();  
        strList.addAll(Arrays.asList("lucy,lily,frank,steve".split(",")));  
        List<String> retStrList = helloClient.getStringList(strList);  
        for (String str : retStrList) {  
            System.out.println(str);  
        }  
    }  
  
	@Test  
    public void testUserList() {  
        List<User> userList = new ArrayList<User>();  
        for (int i = 1; i <= 10; i++) {  
            User user = new User();  
            user.setId("" + i);  
            user.setName("user_" + i);  
            user.setPassword("password_" + i);  
            user.setRegisterDate(new Timestamp(System.currentTimeMillis()));  
            Address address = new Address();  
            address.setStreet("street_" + i);  
            address.setPostcode(String.valueOf(Math.round((Math.random() * 1000000f))));  
            user.setAddress(address);  
  
            userList.add(user);  
        }  
        List<User> retList = helloClient.getUserList(userList);  
        for (User user : retList) {  
            System.out.println(user.toString());  
        }  
    }  */
  
    @Test  
    public void testMapData() {  

		WebClient webClient = WebClient
				.create("http://localhost:8080/java-demo/webservice/orgService") // 访问地址
				.path("/getMapData", "post"); // 地址 和 参数

        Map<String, Object> map = new HashMap<String, Object>();
        Org u = new Org();
        u.setParentId("2");
        map.put("org", u);
        // map.put("parentId", "2");
    	try {
			MapConvertor map1 = new StringObjectMapAdapter().marshal(map);
	        Response respon = webClient
	        		.accept(MediaType.APPLICATION_JSON)
	                .type(MediaType.APPLICATION_JSON)
	                .post(map1);
	        /*Response obj = webClient.invoke("sendMap", map1);
			System.out.println(obj.getMetadata());
			System.out.println(obj.readEntity(String.class));*/
			System.out.println(respon.getMetadata());
			System.out.println(respon.readEntity(String.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }  

}
