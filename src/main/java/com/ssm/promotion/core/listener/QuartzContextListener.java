package com.ssm.promotion.core.listener;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 解决tomcat停止时出现内存溢出
 * @author 87677
 *
 */
public class QuartzContextListener implements ServletContextListener {

	/*
	 * 内存溢出出现原因
	 * 1、jdbc Driver在tomcat运行时进行注册，但是当tomcat停止时没有解除注册;
     * 2、使用quartz跑定时任务时，tomcat停了，quartz的线程没有停掉；
     * 3、web 容器重启一个叫Abandoned connection cleanup thread的线程失败.
	 */
	
	/**
	 * contextDestroyed
	 */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("webService stop");
        try {
            while(DriverManager.getDrivers().hasMoreElements()) {
                DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
            }
            System.out.println("jdbc Driver close");
            AbandonedConnectionCleanupThread.shutdown();
            System.out.println("clean thread success");
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("web start");
    }
}