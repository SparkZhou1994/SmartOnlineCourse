package spark.course.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RabbitMQConfig
 * @Description TODO
 * @Author Spark
 * @Date 2/18/2019 2:36 PM
 * @Version 1.0
 **/
@Configuration
public class RabbitMQConfig {
        @Bean
        public Queue logDirectQueue() {
            return new Queue("logDirectQueue");
        }

        @Bean
        public DirectExchange logDirectExchange() {
            return new DirectExchange("log.exchange.direct", false, false);
        }

        /**
         * 根据路由键绑定队列到交换器上
         *
         * @return
         */
        @Bean
        public Binding logDirectBinding() {
            return BindingBuilder.bind(logDirectQueue()).to(logDirectExchange()).with("logDirectQueue");
        }
}
