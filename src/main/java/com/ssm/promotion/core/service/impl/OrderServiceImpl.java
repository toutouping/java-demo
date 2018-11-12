package com.ssm.promotion.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.promotion.core.dao.OrderDao;
import com.ssm.promotion.core.entity.Center;
import com.ssm.promotion.core.entity.City;
import com.ssm.promotion.core.entity.MachineRoom;
import com.ssm.promotion.core.entity.Order;
import com.ssm.promotion.core.entity.User;
import com.ssm.promotion.core.entity.Params;
import com.ssm.promotion.core.entity.Reader;
import com.ssm.promotion.core.exception.BusinessException;
import com.ssm.promotion.core.service.OrderService;

/**
 * @author llp
 * @project_name java-demo
 * @date 2018-08-08
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

	@Override
	public List<Order> findOrder(Map<String, Object> map) {
        return orderDao.findOrder(map);
	}

	@Override
	public List<Center> findCenter() {
        return orderDao.findCenter();
	}
    
	@Override
	@Transactional
    public int addCity(Map<String, Object> map) throws BusinessException {
		return orderDao.addCity(map);
	}

	@Override
    public int addCenter(Map<String, Object> map) throws BusinessException {
		return orderDao.addCenter(map);
	}
    
	@Override
	public List<MachineRoom> findMachineRoom(Map<String, Object> map) {
        return orderDao.findMachineRoom(map);
	}

	@Override
	public List<City> findCity() {
        return orderDao.findCity();
	}

	@Override
	public int deleteMachineRoom(Map<String, Object> map) {
		return orderDao.deleteMachineRoom(map);
	}

	@Override
	public List<City> readerList(String centerId) {
		return orderDao.readerList(centerId);
	}

	@Override
	public int addMachineRoom(MachineRoom machineRoom) {
		return orderDao.addMachineRoom(machineRoom);
	}

	@Override
	public int editMachineRoom(MachineRoom machineRoom) {
		return orderDao.editMachineRoom(machineRoom);
	}

	@Override
	public List<User> userList(User user) {
		return orderDao.userList(user);
	}

	@Override
	public List<City> roleList() {
		return orderDao.roleList();
	}

	@Override
	public int deleteUser(Map<String, Object> map) {
		return orderDao.deleteUser(map);
	}

	@Override
	public int addUser(User user) {
		return orderDao.addUser(user);
	}

	@Override
	public int getUserCount(User user) {
		return orderDao.getUserCount(user);
	}

	@Override
	public int getCityCount(Map<String, Object> map) {
		return orderDao.getCityCount(map);
	}

	@Override
	public int getCenterCount(Map<String, Object> map) {
		return orderDao.getCenterCount(map);
	}

	@Override
	public List<Params> searchParamList(Map<String, Object> map) {
		return orderDao.searchParamList(map);
	}

	@Override
	public int updateUserParam(List<Params> paramList) {
		return orderDao.updateUserParam(paramList);
	}

	@Override
	public int insertUserParam(List<Params> paramList) {
		return orderDao.insertUserParam(paramList);
	}

	@Override
	public int deleteCity(City city) {
		return orderDao.deleteCity(city);
	}

	@Override
	public int editCity(City city) {
		return orderDao.editCity(city);
	}

	@Override
	public int editCenter(Center center) {
		return orderDao.editCenter(center);
	}

	@Override
	public int deleteCenter(Center center) {
		return orderDao.deleteCenter(center);
	}

	@Override
	public int deleteReader(Reader reader) {
		return orderDao.deleteReader(reader);
	}

	@Override
	public int getReaderCount(Map<String, Object> map) {
		return orderDao.getReaderCount(map);
	}

	@Override
	public int editReader(Reader reader) {
		return orderDao.editReader(reader);
	}

	@Override
	public int addReader(Reader reader) {
		return orderDao.addReader(reader);
	}

	@Override
	public List<Order> workOrders(Map<String, Object> map) {
        return orderDao.workOrders(map);
	}

	@Override
	public List<Order> bankers(Map<String, Object> map) {
        return orderDao.bankers(map);
	}

}
