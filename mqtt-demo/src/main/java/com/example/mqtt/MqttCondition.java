package com.example.mqtt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 自定义配置,通过这个配置，来控制启动项目的时候是否启动mqtt
 * @author nisang
 * 2024/1/4 15:19
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
public class MqttCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata annotatedTypeMetadata) {
        //1、能获取到ioc使用的beanfactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        //2、获取类加载器
        ClassLoader classLoader = context.getClassLoader();
        //3、获取当前环境信息
        Environment environment = context.getEnvironment();
        String isOpen = environment.getProperty("mqtt.isOpen");
        return Boolean.valueOf(isOpen);
    }
}
