package com.gestaoescalas.notification.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Centraliza as configurações de mensageria do notification-service,
 * declarando a fila, a exchange, o binding e o conversor JSON
 * utilizados na comunicação via RabbitMQ.
 */
@Configuration
public class RabbitMQConfig {

    /**
     * Nome da fila de eventos definido nas configurações
     * da aplicação.
     */
    @Value("${app.messaging.queue}")
    private String shiftQueueName;

    /**
     * Nome da exchange responsável por receber e direcionar
     * os eventos publicados pelo schedule-service.
     */
    private static final String SHIFT_EXCHANGE = "shift.exchange";

    /**
     * Padrão de roteamento utilizado para direcionar à fila
     * os eventos relacionados aos turnos.
     */
    private static final String SHIFT_ROUTING_KEY = "shift.#";

    /**
     * Declara a fila responsável por armazenar os eventos
     * relacionados aos turnos.
     *
     * @return uma fila durável com o nome definido nas configurações.
     */
    @Bean
    public Queue shiftQueue() {
        return new Queue(shiftQueueName, true);
    }

    /**
     * Declara a exchange do tipo Topic, responsável por receber
     * e direcionar as mensagens publicadas pelo schedule-service.
     *
     * @return uma exchange identificada por SHIFT_EXCHANGE.
     */
    @Bean
    public TopicExchange shiftExchange() {
        return new TopicExchange(SHIFT_EXCHANGE);
    }

    /**
     * Estabelece a ligação entre a fila de eventos e a exchange,
     * utilizando o padrão de roteamento definido para os turnos.
     *
     * @return um binding entre a fila e a exchange.
     */
    @Bean
    public Binding shiftBinding() {
        return BindingBuilder
                .bind(shiftQueue())
                .to(shiftExchange())
                .with(SHIFT_ROUTING_KEY);
    }

    /**
     * Configura o conversor responsável pela serialização e
     * desserialização das mensagens JSON utilizadas no RabbitMQ.
     *
     * @return um conversor de mensagens baseado no Jackson.
     */
    @Bean
    public MessageConverter jacksonJsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
