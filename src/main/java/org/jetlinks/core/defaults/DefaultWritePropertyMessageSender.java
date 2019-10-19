package org.jetlinks.core.defaults;

import org.jetlinks.core.device.DeviceOperator;
import org.jetlinks.core.message.WritePropertyMessageSender;
import org.jetlinks.core.message.property.WritePropertyMessage;
import org.jetlinks.core.message.property.WritePropertyMessageReply;
import org.jetlinks.core.utils.IdUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

public class DefaultWritePropertyMessageSender implements WritePropertyMessageSender {

    private WritePropertyMessage message = new WritePropertyMessage();

    private DeviceOperator operator;

    public DefaultWritePropertyMessageSender(DeviceOperator operator) {
        this.operator = operator;
        message.setMessageId(IdUtils.newUUID());
        message.setDeviceId(operator.getDeviceId());
    }

    @Override
    public WritePropertyMessageSender custom(Consumer<WritePropertyMessage> messageConsumer) {
        messageConsumer.accept(message);
        return this;
    }

    @Override
    public WritePropertyMessageSender header(String header, Object value) {
        message.addHeader(header, value);
        return this;
    }

    @Override
    public WritePropertyMessageSender messageId(String messageId) {
        message.setMessageId(messageId);
        return this;
    }

    @Override
    public WritePropertyMessageSender write(String property, Object value) {
        message.addProperty(property, value);
        return this;
    }

    @Override
    public Flux<WritePropertyMessageReply> send() {
        return operator.messageSender().send(Mono.just(message));
    }
}
