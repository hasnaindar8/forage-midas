package com.jpmc.midascore;

import com.jpmc.midascore.foundation.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "${kafka-topic}", groupId = "test-group")
    public void listen(Transaction record) {
        logger.info("Consumed transaction: {}", record);
    }
}
