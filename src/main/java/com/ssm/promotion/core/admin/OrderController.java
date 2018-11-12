package com.ssm.promotion.core.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;

import org.apache.log4j.Logger;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssm.promotion.core.entity.Center;
import com.ssm.promotion.core.entity.City;
import com.ssm.promotion.core.entity.MachineRoom;
import com.ssm.promotion.core.entity.Order;
import com.ssm.promotion.core.entity.Params;
import com.ssm.promotion.core.entity.Reader;
import com.ssm.promotion.core.entity.ResultBean;
import com.ssm.promotion.core.entity.User;
import com.ssm.promotion.core.service.OrderService;
import com.ssm.promotion.core.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author llp
 * @project_name cmb-center
 * @date 2018/8/8
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    private static final Logger log = Logger.getLogger(OrderController.class);// 日志文件

    @Resource
    private OrderService orderService;

    /**
     * 查询工单信息
     * @param order
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/orderList", method = RequestMethod.POST)
    public void orderList(@RequestBody Order order, HttpServletResponse response) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
    	if(order != null) {
            map.put("startTime",UTCStringtODefaultString(order.getStartTime()));
            map.put("endTime",UTCStringtODefaultString(order.getEndTime()));
            map.put("workorderId", order.getWorkorderId());
            map.put("userName", order.getUserName());
            map.put("banker", order.getBanker());
            map.put("centerId", order.getCenterId());
            map.put("cityId", order.getCityId());
            map.put("company", order.getCompany());
            map.put("department", order.getDepartment());
            map.put("machineRoomId", order.getMachineRoomId());
    	}
        List<Order> resultList = orderService.findOrder(map);
        
        JSONArray jsonArray = JSONArray.fromObject(resultList);
        ResultBean<JSONArray> bean = new ResultBean<JSONArray>(jsonArray);
        ResponseUtil.write(response, toJsonObject(bean));
    }
    
    /**
     * 查询工单信息
     * @param order
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/searchOrdersBy", method = RequestMethod.POST)
    public void searchOrdersBy(@RequestBody Order order, HttpServletResponse response) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
    	if(order != null) {
            map.put("workorderId", order.getWorkorderId());
            map.put("userName", order.getUserName());
    	}
        List<Order> resultList = orderService.workOrders(map);
        
        JSONArray jsonArray = JSONArray.fromObject(resultList);
        ResultBean<JSONArray> bean = new ResultBean<JSONArray>(jsonArray);
        ResponseUtil.write(response, toJsonObject(bean));
    }
    
    /**
     * 查询行员信息
     * @param order
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/bankers", method = RequestMethod.POST)
    public void bankers(@RequestBody Order order, HttpServletResponse response) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
    	if(order != null) {
            map.put("workorderId", order.getWorkorderId());
            map.put("banker", order.getBanker());
            map.put("bankerNum", order.getBankerNum());
    	}
        List<Order> resultList = orderService.bankers(map);
        
        JSONArray jsonArray = JSONArray.fromObject(resultList);
        ResultBean<JSONArray> bean = new ResultBean<JSONArray>(jsonArray);
        ResponseUtil.write(response, toJsonObject(bean));
    }
    
    /**
     * 更改中心信息
     * @param center
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editReader", method = RequestMethod.POST)
    public void editReader(@RequestBody Reader reader, HttpServletResponse response) throws Exception {
    	ResultBean<JSONArray> bean = null;
    	if(reader != null && reader.getId() != null){

        	/*Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", reader.getName());
    		int count1 = orderService.getReaderCount(map);
    		if (count1 > 0) {
                bean = new ResultBean();
                bean.setCode(-1);
                bean.setMsg("该读卡器编号已经存在，请重新填写。");
    		} else {*/
        		int count2 = orderService.editReader(reader);
        		if (count2 > 0) {
                    bean = new ResultBean();
                    bean.setMsg("修改成功。");
        		} else {
                    bean = new ResultBean();
                    bean.setCode(-1);
                    bean.setMsg("修改失败，请确认是否已经存在该名称。");
        		}
    		/*}*/
    		
    	}
    	if (bean == null) {
            bean = new ResultBean();
            bean.setCode(-1);
            bean.setMsg("修改失败");
    	}
        ResponseUtil.write(response, toJsonObject(bean));
    }

    /**
     * 更改中心信息
     * @param center
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addReader", method = RequestMethod.POST)
    public void addReader(@RequestBody Reader reader, HttpServletResponse response) throws Exception {
    	ResultBean<JSONArray> bean = null;
    	if(reader != null && reader.getId() != null){

        	Map<String, Object> map = new HashMap<String, Object>();
            map.put("number", reader.getNumber());
    		int count1 = orderService.getReaderCount(map);
    		if (count1 > 0) {
                bean = new ResultBean();
                bean.setCode(-1);
                bean.setMsg("该读卡器编号已经存在，请重新填写。");
    		} else {
        		int count2 = orderService.addReader(reader);
        		if (count2 > 0) {
                    bean = new ResultBean();
                    bean.setMsg("修改成功。");
        		} else {
                    bean = new ResultBean();
                    bean.setCode(-1);
                    bean.setMsg("修改失败，请确认是否已经存在该名称。");
        		}
    		}
    		
    	}
    	if (bean == null) {
            bean = new ResultBean();
            bean.setCode(-1);
            bean.setMsg("修改失败");
    	}
        ResponseUtil.write(response, toJsonObject(bean));
    }
    
    /**
     * 更改中心信息
     * @param center
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editCenter", method = RequestMethod.POST)
    public void editCenter(@RequestBody Center center, HttpServletResponse response) throws Exception {
    	ResultBean<JSONArray> bean = null;
    	if(center != null && center.getId() != null){

        	Map<String, Object> map = new HashMap<String, Object>();
            map.put("center", center.getName());
    		int count1 = orderService.getCenterCount(map);
    		if (count1 > 0) {
                bean = new ResultBean();
                bean.setCode(-1);
                bean.setMsg("该中心已经存在，请重新填写。");
    		} else {
        		int count2 = orderService.editCenter(center);
        		if (count2 > 0) {
                    bean = new ResultBean();
                    bean.setMsg("修改成功。");
        		} else {
                    bean = new ResultBean();
                    bean.setCode(-1);
                    bean.setMsg("修改失败，请确认是否已经存在该名称。");
        		}
    		}
    		
    	}
    	if (bean == null) {
            bean = new ResultBean();
            bean.setCode(-1);
            bean.setMsg("修改失败");
    	}
        ResponseUtil.write(response, toJsonObject(bean));
    }
    
    /**
     * 更改城市信息
     * @param city
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editCity", method = RequestMethod.POST)
    public void editCity(@RequestBody City city, HttpServletResponse response) throws Exception {
    	ResultBean<JSONArray> bean = null;
    	if(city != null && city.getId() != null){

	    	Map<String, Object> map = new HashMap<String, Object>();
	        map.put("city", city.getName());
    		int count1 = orderService.getCityCount(map);
    		if (count1 > 0) {
                bean = new ResultBean();
                bean.setCode(-1);
                bean.setMsg("该城市已经存在。");
    		} else {
        		int count2 = orderService.editCity(city);
        		if (count2 > 0) {
                    bean = new ResultBean();
                    bean.setMsg("修改成功。");
        		}
    		}
    		
    	}
    	if (bean == null) {
            bean = new ResultBean();
            bean.setCode(-1);
            bean.setMsg("修改失败");
    	}
        ResponseUtil.write(response, toJsonObject(bean));
    }
    

    
    /**
     * 删除中心
     * @param center
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteReader", method = RequestMethod.POST)
    public void deleteReader(@RequestBody Reader reader, HttpServletResponse response) throws Exception {
    	ResultBean<JSONArray> bean = null;
    	if(reader != null || reader.getId() != null){
    		int count = orderService.deleteReader(reader);
    		if (count > 0) {
                bean = new ResultBean();
                bean.setMsg("删除成功。");
    		} else {
                bean = new ResultBean();
                bean.setCode(-1);
                bean.setMsg("删除失败，请确认是否存在该城市。");
    		}
    	}
    	if (bean == null) {
            bean = new ResultBean();
            bean.setCode(-1);
            bean.setMsg("删除失败");
    	}
        ResponseUtil.write(response, toJsonObject(bean));
    }
    
    /**
     * 删除中心
     * @param center
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteCenter", method = RequestMethod.POST)
    public void deleteCenter(@RequestBody Center center, HttpServletResponse response) throws Exception {
    	ResultBean<JSONArray> bean = null;
    	if(center != null || center.getId() != null){
    		int count = orderService.deleteCenter(center);
    		if (count > 0) {
                bean = new ResultBean();
                bean.setMsg("删除成功。");
    		} else {
                bean = new ResultBean();
                bean.setCode(-1);
                bean.setMsg("删除失败，请确认是否存在该城市。");
    		}
    	}
    	if (bean == null) {
            bean = new ResultBean();
            bean.setCode(-1);
            bean.setMsg("删除失败");
    	}
        ResponseUtil.write(response, toJsonObject(bean));
    }
    
    /**
     * 新增城市
     * @param city
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteCity", method = RequestMethod.POST)
    public void deleteCity(@RequestBody City city, HttpServletResponse response) throws Exception {
    	ResultBean<JSONArray> bean = null;
    	if(city != null || city.getId() != null){
    		int count = orderService.deleteCity(city);
    		if (count > 0) {
                bean = new ResultBean();
                bean.setMsg("删除成功。");
    		} else {
                bean = new ResultBean();
                bean.setCode(-1);
                bean.setMsg("删除失败，请确认是否存在该城市。");
    		}
    	}
    	if (bean == null) {
            bean = new ResultBean();
            bean.setCode(-1);
            bean.setMsg("删除失败");
    	}
        ResponseUtil.write(response, toJsonObject(bean));
    }
    
    /**
     * 新增城市
     * @param order
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addCity", method = RequestMethod.POST)
    public void addCity(@RequestBody Order order, HttpServletResponse response) throws Exception {
    	ResultBean<JSONArray> bean = null;
    	if(order == null || order.getCity().isEmpty()){
            bean = new ResultBean();
            bean.setCode(-1);
            bean.setMsg("添加失败");
    	}else{
	    	Map<String, Object> map = new HashMap<String, Object>();
	        map.put("city", order.getCity());
	        
    		int count = orderService.getCityCount(map);
    		if (count > 0) {
                bean = new ResultBean();
                bean.setCode(-1);
                bean.setMsg("该城市已经存在。");
    		} else {
    	        int result = orderService.addCity(map);
    	        if(result >0 ) {
                    bean = new ResultBean();
                    bean.setMsg("添加成功。");
    	        }else{
    	            bean = new ResultBean();
    	            bean.setCode(-1);
    	            bean.setMsg("添加失败");
    	        }
    		}
    	}
        ResponseUtil.write(response, toJsonObject(bean));
    }
    
    /**
     * 删除机房信息
     * @param order
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteMachineRoom", method = RequestMethod.POST)
    public void deleteMachineRoom(@RequestBody Order order, HttpServletResponse response) throws Exception {

    	ResultBean<JSONArray> bean = null;
    	if(order == null || order.getMachineRoomId().isEmpty()){
            bean = new ResultBean();
            bean.setCode(-1);
            bean.setMsg("删除失败");
    	}else {
        	Map<String, Object> map = new HashMap<String, Object>();
            map.put("machineRoomId", order.getMachineRoomId());
            
            int result = orderService.deleteMachineRoom(map);
            if(result >0 ) {
                bean = new ResultBean();
                bean.setMsg("删除成功。");
            }else{
                bean = new ResultBean();
                bean.setCode(-1);
                bean.setMsg("删除失败");
            }
    	}
        ResponseUtil.write(response, toJsonObject(bean));
    }

    /**
     * 删除机房信息
     * @param order
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public void deleteUser(@RequestBody User user, HttpServletResponse response) throws Exception {

    	ResultBean<JSONArray> bean = null;
    	if(user == null || user.getId() == null){
            bean = new ResultBean();
            bean.setCode(-1);
            bean.setMsg("删除失败");
    	}else {
        	Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", user.getId());
            
            int result = orderService.deleteUser(map);
            if(result >0 ) {
                bean = new ResultBean();
                bean.setMsg("删除成功。");
            }else{
                bean = new ResultBean();
                bean.setCode(-1);
                bean.setMsg("删除失败");
            }
    	}
        ResponseUtil.write(response, toJsonObject(bean));
    }
    
    /**
     * 增加中心信息
     * @param order
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addCenter", method = RequestMethod.POST)
    public void addCenter(@RequestBody Order order, HttpServletResponse response) throws Exception {

    	ResultBean<JSONArray> bean = null;
    	if(order == null || order.getCenter().isEmpty()){
            bean = new ResultBean();
            bean.setCode(-1);
            bean.setMsg("添加失败");
    	}else {
        	Map<String, Object> map = new HashMap<String, Object>();
            map.put("center", order.getCenter());
	        
    		int count = orderService.getCenterCount(map);
    		if (count > 0) {
                bean = new ResultBean();
                bean.setCode(-1);
                bean.setMsg("该中心已经存在，请重新填写。");
    		} else {
	            int result = orderService.addCenter(map);
	            if(result >0 ) {
	                bean = new ResultBean();
	                bean.setMsg("添加成功");
	            }else{
	                bean = new ResultBean();
	                bean.setCode(-1);
	                bean.setMsg("添加失败");
	            }
	    	}
    	}
        ResponseUtil.write(response, toJsonObject(bean));
    }
    

    /**
     * 增加中心信息
     * @param user
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public void addUser(@RequestBody User user, HttpServletResponse response) throws Exception {

    	ResultBean<JSONArray> bean = null;
    	if(user == null || user.getUserName().isEmpty()
    		|| user.getPassword().isEmpty()
    		|| user.getRoleId() == null){
            bean = new ResultBean();
            bean.setCode(-1);
            bean.setMsg("信息填写不完整");
    	}else {
    		int count = orderService.getUserCount(user);
    		if (count > 0) {
                bean = new ResultBean();
                bean.setCode(-1);
                bean.setMsg("该用户名称已经存在，请重新填写。");
    		} else {
                int result = orderService.addUser(user);
                if(result >0 ) {
                    bean = new ResultBean();
                    bean.setMsg("添加成功");
                }else{
                    bean = new ResultBean();
                    bean.setCode(-1);
                    bean.setMsg("添加失败");
                }
    		}
    	}
        ResponseUtil.write(response, toJsonObject(bean));
    }
    
    /**
     * 查询分中心
     * @param order
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/centerList", method = RequestMethod.GET)
    public void centerList(HttpServletResponse response) throws Exception {
        List<Center> centerList = orderService.findCenter();
        JSONArray jsonArray = JSONArray.fromObject(centerList);
        ResultBean<JSONArray> bean = new ResultBean<JSONArray>(jsonArray);
        ResponseUtil.write(response, toJsonObject(bean));
    }

    /**
     * 查询分中心
     * @param order
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/cityList", method = RequestMethod.GET)
    public void cityList(HttpServletResponse response) throws Exception {
        List<City> cityList = orderService.findCity();
        JSONArray jsonArray = JSONArray.fromObject(cityList);
        ResultBean<JSONArray> bean = new ResultBean<JSONArray>(jsonArray);
        ResponseUtil.write(response, toJsonObject(bean));
    }

   /**
    * 获取读卡器
    * @param centerId
    * @param response
    * @throws Exception
    */
    @RequestMapping(value = "/readerList", method = RequestMethod.GET)
    public void readerList(@RequestParam(value = "centerId", required = false) String centerId,HttpServletResponse response) throws Exception {
        List<City> resultList = orderService.readerList(centerId);
        JSONArray jsonArray = JSONArray.fromObject(resultList);
        ResultBean<JSONArray> bean = new ResultBean<JSONArray>(jsonArray);
        ResponseUtil.write(response, toJsonObject(bean));
    }

    /**
     * 获取读卡器
     * @param centerId
     * @param response
     * @throws Exception
     */
     @RequestMapping(value = "/roleList", method = RequestMethod.GET)
     public void roleList(HttpServletResponse response) throws Exception {
         List<City> resultList = orderService.roleList();
         JSONArray jsonArray = JSONArray.fromObject(resultList);
         ResultBean<JSONArray> bean = new ResultBean<JSONArray>(jsonArray);
         ResponseUtil.write(response, toJsonObject(bean));
     }
     
    /**
     * 获取读卡器
     * @param centerId
     * @param response
     * @throws Exception
     */
     @RequestMapping(value = "/userList", method = RequestMethod.POST)
     public void userList(@RequestBody User user,HttpServletResponse response) throws Exception {
         List<User> resultList = orderService.userList(user);
         JSONArray jsonArray = JSONArray.fromObject(resultList);
         ResultBean<JSONArray> bean = new ResultBean<JSONArray>(jsonArray);
         ResponseUtil.write(response, toJsonObject(bean));
     }

     /**
      * 获取读卡器
      * @param centerId
      * @param response
      * @throws Exception
      */
      @RequestMapping(value = "/updateUserParam", method = RequestMethod.POST)
      @Consumes("application/json")
      public void updateUserParam(@RequestBody List<Params> paramList,HttpServletResponse response) throws Exception {

      	ResultBean bean = null;
      	String userId = null;
      	
      	if (paramList != null && paramList.size() > 0) {
      		userId = paramList.get(0).getUserId();
      		
      		if(userId == null || userId.isEmpty()) {
                bean = new ResultBean();
                bean.setCode(-1);
                bean.setMsg("页面数据获取有问题，请重新选择用户。");
        	    ResponseUtil.write(response, toJsonObject(bean));
      		}
      		
        	Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", userId);
            List<Params> resultList = orderService.searchParamList(map);
            if (resultList.size() > 0) { // 更新
    		   int count = orderService.updateUserParam(paramList);
      		   if (count > 0) {
                  bean = new ResultBean();
                  bean.setMsg("更新成功");
      		   }
            } else { // 新增
    		   int count = orderService.insertUserParam(paramList);
      		   if (count > 0) {
                  bean = new ResultBean();
                  bean.setMsg("新增成功");
      		   }
            }
      	}
      	
      	if (bean == null) {
          bean = new ResultBean();
          bean.setCode(-1);
          bean.setMsg("更新失败。");
      	}
      	
	    ResponseUtil.write(response, toJsonObject(bean));
      }
     
     /**
      * 查询分中心
      * @param order
      * @param response
      * @return
      * @throws Exception
      */
     @RequestMapping(value = "/searchParamList", method = RequestMethod.GET)
     public void searchParamList(@RequestParam(value = "userId", required = false) String userId, String centerId,
     		HttpServletResponse response) throws Exception {
     	Map<String, Object> map = new HashMap<String, Object>();
         map.put("userId", userId);
         List<Params> resultList = orderService.searchParamList(map);
         JSONArray jsonArray = JSONArray.fromObject(resultList);
         ResultBean<JSONArray> bean = new ResultBean<JSONArray>(jsonArray);
         ResponseUtil.write(response, toJsonObject(bean));
     }
    

     /**
      * 更新、添加机房信息
      * @param machineRoom
      * @param response
      * @throws Exception
      */
     @RequestMapping(value = "/addEditMachineRoom", method = RequestMethod.POST)
     public void addEditMachineRoom(@RequestBody MachineRoom machineRoom, HttpServletResponse response) throws Exception {

     	ResultBean<JSONArray> bean = null;
     	if(machineRoom != null
	 		&& !machineRoom.getCode().isEmpty()
	 		&& !machineRoom.getCenterId().isEmpty()
	 		&& !machineRoom.getCityId().isEmpty()
	 		&& !machineRoom.getInReaderId().isEmpty()
	 		&& !machineRoom.getName().isEmpty()){ 
         		int result = 0;
         		if(machineRoom.getId().isEmpty()){ // 添加新机房信息

    	     		// 判断machineRoom code是否已经存在
    	     		Map<String, Object> map = new HashMap<String, Object>();
    	            map.put("code", machineRoom.getCode());
    	            List<MachineRoom> machineRoomList = orderService.findMachineRoom(map);

    	            if (machineRoom.getId().isEmpty() && machineRoomList != null && machineRoomList.size()>0 ) {
     	          	   bean = new ResultBean();
     	               bean.setCode(-1);
     	               bean.setMsg("机房编号已经存在,请重新输入。");
    	            } else {
    	               result = orderService.addMachineRoom(machineRoom);
    	            }
    	            
         		}else{ // 更新机房信息
         		   result = orderService.editMachineRoom(machineRoom);
         		}
                if(result >0 ) {
                   bean = new ResultBean();
                   bean.setMsg("操作成功。");
                }
     	}
     	if(bean == null ) {
     	   bean = new ResultBean();
           bean.setCode(-1);
           bean.setMsg("操作失败。");
     	}
        ResponseUtil.write(response, toJsonObject(bean));
     }

    /**
     * 查询分中心
     * @param order
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/machineRoomList", method = RequestMethod.GET)
    public void machineRoomList(@RequestParam(value = "cityId", required = false) String cityId,
    		@RequestParam(value = "machineRoomId", required = false) String machineRoomId,
    		@RequestParam(value = "centerId", required = false) String centerId,
    		HttpServletResponse response) throws Exception {
    	log.info("machineRoomList - cityId: " + cityId + " - centerId: " + centerId);
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("cityId", cityId);
        map.put("centerId", centerId);
        map.put("machineRoomId", machineRoomId);
        List<MachineRoom> machineRoomList = orderService.findMachineRoom(map);
        JSONArray jsonArray = JSONArray.fromObject(machineRoomList);
        ResultBean<JSONArray> bean = new ResultBean<JSONArray>(jsonArray);
        ResponseUtil.write(response, toJsonObject(bean));
    }
    
    /**
     * 转换为JSONObject结构
     * @return
     */
    private JSONObject toJsonObject(ResultBean<JSONArray> resultBean){
    	JSONObject result = new JSONObject();
    	result.put("code", resultBean.getCode());
    	result.put("msg", resultBean.getMsg());
    	result.put("data", resultBean.getData());
    	return result;
    }

    /**
     * 时间格式话转换
     * @param UTCString
     * @return
     */
    public static String UTCStringtODefaultString(String UTCString) {  
		if (UTCString.isEmpty()) {
			return "";
		}else{
			try  {
    			UTCString = UTCString.replace("Z", " UTC");  
    			SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
    			SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    			Date date;
				date = utcFormat.parse(UTCString);  
				return defaultFormat.format(date);
        	} catch(ParseException pe){
        		log.error("UTCStringtODefaultString error");
        		return "";
        	} catch (java.text.ParseException e){
        		log.error("UTCStringtODefaultString error");
        		return "";
    		}  
		}  
   }
    
}
