package com.ssm.promotion.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ssm.promotion.core.entity.User;
import com.ssm.promotion.core.util.AntiXssUtil;
import org.springframework.stereotype.Service;

import com.ssm.promotion.core.dao.DemoDao;
import com.ssm.promotion.core.service.DemoService;

/**
 * @author llp
 * @project_name java-demo
 * @date 2018-08-08
 */
@Service("demoService")
public class DemoServiceImpl implements DemoService {

    @Resource
    private DemoDao demoDao;

    @Override
    public User login(User user) {
        return demoDao.login(user);
    }

    @Override
    public List<User> findUser(Map<String, Object> map) {
        return demoDao.findUsers(map);
    }

    @Override
    public int updateUser(User user) {
        //防止有人胡乱修改导致其他人无法正常登陆
        if ("admin".equals(user.getUserName())) {
            return 0;
        }
        user.setUserName(AntiXssUtil.replaceHtmlCode(user.getUserName()));
        return demoDao.updateUser(user);
    }

    @Override
    public Long getTotalUser(Map<String, Object> map) {
        return demoDao.getTotalUser(map);
    }

    @Override
    public int addUser(User user) {
        if (user.getUserName() == null || user.getPassword() == null || getTotalUser(null) > 90) {
            return 0;
        }
        user.setUserName(AntiXssUtil.replaceHtmlCode(user.getUserName()));
        return demoDao.addUser(user);
    }

    @Override
    public int deleteUser(Integer id) {
        //防止有人胡乱修改导致其他人无法正常登陆
        if (2 == id) {
            return 0;
        }
        return demoDao.deleteUser(id);
    }

}
