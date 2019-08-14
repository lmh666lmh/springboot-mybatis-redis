//package org.spring.springboot.rabbit;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.beans.factory.config.ConfigurableBeanFactory;
//import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//import org.springframework.core.env.Environment;
//
//@Configuration
//public class RabbitmqConfig {
//
//    private static final Logger log = LogManager.getLogger(RabbitmqConfig.class.getName());
//
//    @Autowired
//    private Environment env;
//    @Autowired
//    private CachingConnectionFactory connectionFactory;
//    @Autowired
//    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;
//
////    @Value("${queueName}")
////    private String queueName;
////    @Value("${exchangeName}")
////    private String exchangeName;
////    @Value("${routingKeyName}")
////    private String routingKeyName;
//
//    /**
//     * 单一消费者
//     * @return
//     * SimpleRabbitListenerContainerFactory  用于管理  RabbitMQ监听器listener 的容器工厂
//     */
//    @Bean(name = "singleListenerContainer")
//    public SimpleRabbitListenerContainerFactory listenerContainer(){
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setMessageConverter(new Jackson2JsonMessageConverter());
//        factory.setConcurrentConsumers(1);
//        factory.setMaxConcurrentConsumers(1);
//        factory.setPrefetchCount(1);
//        factory.setTxSize(1);
//        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
//        return factory;
//    }
//
//    /**
//     * 多个消费者
//     * @return
//     */
//    @Bean(name = "multiListenerContainer")
//    public SimpleRabbitListenerContainerFactory multiListenerContainer(){
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factoryConfigurer.configure(factory,connectionFactory);
//        factory.setMessageConverter(new Jackson2JsonMessageConverter());
//        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
//        factory.setConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.concurrency",int.class));
//        factory.setMaxConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.max-concurrency",int.class));
//        factory.setPrefetchCount(env.getProperty("spring.rabbitmq.listener.prefetch",int.class));
//        return factory;
//    }
//
//    //RabbitTemplate 充当消息的发送组件
//    @Bean
////    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    public RabbitTemplate rabbitTemplate() {
//        System.out.println("222222");
//        connectionFactory.setPublisherConfirms(true);
//        connectionFactory.setPublisherReturns(true);
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMandatory(true);
//        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
//            @Override
//            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//                System.out.println("33333333333");
//                log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause);
//            }
//        });
//        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
//            @Override
//            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//                System.out.println("44444");
//                log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message);
//            }
//        });
//        return rabbitTemplate;
//    }
//
//    @Bean(name = "logUserQueue")
//    public Queue logUserQueue(){
////        System.out.println("queueName-->>"+queueName);
//        System.out.println("-=============---  "+env.getProperty("log.user.queue.name"));
//        return new Queue(env.getProperty("log.user.queue.name"),true);
//    }
//    @Bean
//    public DirectExchange logUserExchange(){
//        System.out.println("-=============---  "+env.getProperty("log.user.exchange.name"));
//        return new DirectExchange(env.getProperty("log.user.exchange.name"),true,false);
//    }
//
//    @Bean
//    public Binding logUserBinding(){
//        System.out.println("-=============---  "+env.getProperty("log.user.routing.key.name"));
//        return BindingBuilder.bind(logUserQueue()).to(logUserExchange()).with(env.getProperty("log.user.routing.key.name"));
//    }
//}
