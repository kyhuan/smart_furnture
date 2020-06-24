package com.xupt.web.interceptor;

import com.xupt.web.annotation.CreateDate;
import com.xupt.web.annotation.UpdateDate;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Properties;

@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class AuditingInterceptor implements Interceptor {
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
		
		System.out.println("!!!");
		if (SqlCommandType.INSERT == sqlCommandType) {
//			System.out.println("INSERT!!");
			Date now = new Date();
			
//			Integer userId = WebManager.getUserId();
			Integer userId = 1;
			
			Object object = invocation.getArgs()[1];
			doInjection(object, CreateDate.class, now);
//			doInjection(object, CreateOperator.class, userId);
		} else if (SqlCommandType.UPDATE == sqlCommandType) {
//			System.out.println("UPDATE!!");
			Date now = new Date();
			
//			Integer userId = WebManager.getUserId();
			Integer userId = 1;
			
			Object object = invocation.getArgs()[1];
			doInjection(object, UpdateDate.class, now);
//			doInjection(object, UpdateOperator.class, userId);
//			doInjection(object, UUIDField.class, UUID.randomUUID().toString());
		}
		return invocation.proceed();
	}
	
	@Override
	public Object plugin(Object target) {
		if (target instanceof Executor) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}
	
	@Override
	public void setProperties(Properties properties) {
	}
	
	private boolean doInjection(Object object, Class<? extends Annotation> annotationClass, Object injectData) {
		try {
			Class<?> objectClass = object.getClass();
			Field[] fields = objectClass.getDeclaredFields();
			for (Field field : fields) {
//				System.out.println(field.getName());
				if (field.getAnnotation(annotationClass) != null) {
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), objectClass);
					Method writeMethod = propertyDescriptor.getWriteMethod();
					writeMethod.invoke(object, injectData);
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
