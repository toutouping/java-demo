package com.ssm.promotion.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ssm.promotion.core.entity.Center;
import com.ssm.promotion.core.entity.City;
import com.ssm.promotion.core.entity.MachineRoom;
import com.ssm.promotion.core.entity.Order;
import com.ssm.promotion.core.entity.User;
import com.ssm.promotion.core.entity.Params;
import com.ssm.promotion.core.entity.Reader;

/**
 * @author llp
 * @project_name java-demo
 * @date 2018-08-08
 */
@Repository
public interface OrderDao {
	/**
	 * 查找工单系你想
	 * @param order
	 * @return
	 */
    public List<Order> findOrder(Map<String, Object> map);

	/**
	 * 查找工单系你想
	 * @param order
	 * @return
	 */
    public List<Order> findMachines(Map<String, Object> map);
    
	/**
	 * 查找ID值
	 * @return
	 */
    public int findMaxIdOfCity();
	
	/**
	 * 查找ID值
	 * @return
	 */
    public int findMaxIdOfCenter();
	
    /**
     * 分中心信息
     * @return
     */
    public List<Center> findCenter();
    
    
    /**
     * 获取城市信息
     * @return
     */
    public List<City> findCity();

    /**
     * 新增城市
     * @param map
     * @return
     */
    public int addCity(Map<String, Object> map);

    /**
     * 新增中心
     * @param map
     * @return
     */
    public int addCenter(Map<String, Object> map);
    
    /**
     * 机房信息
     * @return
     */
    public List<MachineRoom> findMachineRoom(Map<String, Object> map);

    /**
     * 删除机房信息
     * @param map
     * @return
     */
	public int deleteMachineRoom(Map<String, Object> map);

	/**
	 * 获取读卡器信息
	 * @param centerId
	 * @return
	 */
	public List<City> readerList(@Param("centerId")String centerId);

	/**
	 * 新增机房信息
	 * @param machineRoom
	 * @return
	 */
	public int addMachineRoom(MachineRoom machineRoom);

	/**
	 * 更新机房信息
	 * @param machineRoom
	 * @return
	 */
	public int editMachineRoom(MachineRoom machineRoom);

	/**
	 * 获取用户信息
	 * @param user
	 * @return
	 */
	public List<User> userList(User user);

	/**
	 * 获取角色信息
	 * @return
	 */
	public List<City> roleList();

	/**
	 * 删除用户信息
	 * @param map
	 * @return
	 */
	public int deleteUser(Map<String, Object> map);

	/**
	 * 添加用户信息
	 * @param user
	 * @return
	 */
	public int addUser(User user);

	/**
	 * 获取用户数量
	 * @param user
	 * @return
	 */
	public int getUserCount(User user);

	/**
	 * 获取城市数量
	 * @param map
	 * @return
	 */
	public int getCityCount(Map<String, Object> map);

	/**
	 * 获取中心数量
	 * @param map
	 * @return
	 */
	public int getCenterCount(Map<String, Object> map);

	/**
	 * 查询参数列表
	 * @param map
	 * @return
	 */
	public List<Params> searchParamList(Map<String, Object> map);

	/**
	 * 更新用户参数
	 * @param paramList
	 * @return
	 */
	public int updateUserParam(@Param("list") List<Params> list);

	/**
	 * 新增用户参数
	 * @param paramList
	 * @return
	 */
	public int insertUserParam(@Param("list") List<Params> list);

	/**
	 * 删除城市
	 * @param city
	 * @return
	 */
	public int deleteCity(City city);

	/**
	 * 编辑城市信息
	 * @param city
	 * @return
	 */
	public int editCity(City city);

	/**
	 * 修改中心信息
	 * @param center
	 * @return
	 */
	public int editCenter(Center center);

	/**
	 * 删除城市中心
	 * @param center
	 * @return
	 */
	public int deleteCenter(Center center);

	/**
	 * 删除读卡器
	 * @param reader
	 * @return
	 */
	public int deleteReader(Reader reader);

	/**
	 * 获取读卡器数量
	 * @param map
	 * @return
	 */
	public int getReaderCount(Map<String, Object> map);

	/**
	 * 编辑读卡器
	 * @param reader
	 * @return
	 */
	public int editReader(Reader reader);

	/**
	 * 增加读卡器
	 * @param reader
	 * @return
	 */
	public int addReader(Reader reader);

	/**
	 * 获取工单信息
	 * @param map
	 * @return
	 */
	public List<Order> workOrders(Map<String, Object> map);

	/**
	 * 获取行员信息
	 * @param map
	 * @return
	 */
	public List<Order> bankers(Map<String, Object> map);
}
