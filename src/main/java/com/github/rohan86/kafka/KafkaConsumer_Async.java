package com.github.rohan86.kafka;

import java.util.*;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;


public class KafkaConsumer_Async {
    
    private final static String BOOTSTRAP_SERVERS ="localhost:9092";
    private final static String KEY_DESERIALIZER="org.apache.kafka.common.serialization.StringDeserializer";
    private final static String VALUE_DESERIALIZER="org.apache.kafka.common.serialization.StringDeserializer";
    private final static String GROUPID="Demo_group";


    public static void main(String[] args)
    {

        BasicConfigurator.configure();
        Logger logger = LoggerFactory.getLogger(KafkaConsumer_Async.class);
        String topic = "Test";
        List<String> topiclist = new ArrayList<String>();
        topiclist.add(topic);

        Properties props = new  Properties();

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,KEY_DESERIALIZER);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,VALUE_DESERIALIZER);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,GROUPID);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");


        KafkaConsumer <String,String> consumer = new KafkaConsumer <String,String>(props);

        consumer.subscribe(topiclist);
        logger.info("subscribted to topic" + topic);

        int i= 0;

        try
        {
            while(true)
            {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord <String,String>rec : records)
                {
                    logger.info("Offset : " + rec.offset() + "--Key:" + rec.key() + " --Value : " + rec.value() + "-- Partition" + rec.partition());
                }
                    consumer.commitAsync(new OffsetCommitCallback() {
                        public void onComplete(Map<TopicPartition, OffsetAndMetadata> map, Exception e) {

                        }
                    });
            }
        }
        catch (Exception e)
        {

        }
        finally {
            consumer.close();
        }
    }
}
