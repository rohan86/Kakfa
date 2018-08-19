package com.github.rohan86.kafka;

import java.util.*;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import static org.apache.kafka.common.utils.Utils.propsToStringMap;
import static org.apache.kafka.common.utils.Utils.sleep;


public class KakfaProducer_Simple {
    private final static String BOOTSTRAP_SERVERS ="localhost:9092";
    private final static String KEY_SERIALIZER="org.apache.kafka.common.serialization.StringSerializer";
    private final static String VALUE_SERIALIZER="org.apache.kafka.common.serialization.StringSerializer";
    private final static String ACK_CFG="all";
    private final static int BUFF_MEM_CFG=33554432;


public static void main(String[] args)
    {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,KEY_SERIALIZER);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,VALUE_SERIALIZER);
        props.put(ProducerConfig.ACKS_CONFIG,ACK_CFG);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG,BUFF_MEM_CFG);



        KafkaProducer<String,String > producer = new KafkaProducer<String, String>(props);

        for (int i =0;i < 50; i++)
        {
            ProducerRecord<String,String> data = new ProducerRecord<String,String>("Test","Key" + i,"This is the "+ i + "th record");
            producer.send(data);
            //Future<RecordMetadata> recordMetadata = producer.send(data);
            System.out.println("Record Sent : " + i);
            sleep(100);
        }
        producer.close();
    }
}
