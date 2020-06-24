package com.xupt.Socket;

import com.xupt.mapper.DeviceMapper;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Author kyh
 * @Email 13669259476@163.com
 * @Time 2020.4.10
 * Description:
 **/
public class SocketListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //获得Spring容器
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
        //从Spring容器中获得SelectDataServlet的实例
        DeviceMapper deviceMapper = ctx.getBean(DeviceMapper.class);
        new Thread(()->{
            new SocketServer(deviceMapper);
        }).start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
