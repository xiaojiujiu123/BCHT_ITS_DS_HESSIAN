package com.bcht.its.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ配置
 */
@Component
@ConfigurationProperties(prefix = "spring.rabbitmq")
@Getter
@Setter
public class AmqpConfig {
    public static final String EXCHANGE   = "ITS_EXCHANGE_ITS_TFCPASS";
    private String host;
    private int port;
    private String username;
    private String password;
    private String virtualhost = "/";


    /**
     * 创建ConnectionFactory
     * @return
     */
    @Bean
    public ConnectionFactory myConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualhost);
        connectionFactory.setPublisherConfirms(true); //必须要设置
        return connectionFactory;
    }


    @Bean
    public RabbitAdmin BchtAdmin(){
      return new RabbitAdmin(myConnectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(myConnectionFactory());
        rabbitTemplate.setExchange(EXCHANGE);
        return rabbitTemplate;
    }



}
