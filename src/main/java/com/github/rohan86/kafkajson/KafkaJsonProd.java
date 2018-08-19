package com.github.rohan86.kafkajson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.naming.Name;
import java.util.Properties;
import java.util.Scanner;

public class KafkaJsonProd {

    private final static String BOOTSTRAP_SERVERS ="localhost:9092";
    private final static String KEY_SERIALIZER="org.apache.kafka.common.serialization.StringSerializer";
    private final static String VALUE_SERIALIZER ="org.apache.kafka.common.serialization.StringSerializer";
    private final static String ACK_CFG="all";
    private final static int BUFF_MEM_CFG=33554432;



        // Configure Properties
        public static void main(String[] args) throws Exception{

            BasicConfigurator.configure();
            Logger logger = LoggerFactory.getLogger(KafkaJsonProd.class);



            logger.info("Define Configuration settings for producer");


            Properties props = new Properties();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVERS);
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,KEY_SERIALIZER);
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,VALUE_SERIALIZER);
            props.put(ProducerConfig.ACKS_CONFIG,ACK_CFG);
            props.put(ProducerConfig.BUFFER_MEMORY_CONFIG,BUFF_MEM_CFG);



            //KafkaProducer<String,JsonNode> producer = new KafkaProducer<String, JsonNode>(props);
            Producer producer = new KafkaProducer(props);

            //ObjectMapper mapper = new ObjectMapper();

            //String line = in.nextLine();

            //while (!line.equals("exit")) {

                //c.parseString(line);

                //JsonNode jsonNode = mapper.valueToTree(c);

                ObjectNode cust = JsonNodeFactory.instance.objectNode();
                cust.put("Name","Reya");
                cust.put("Age",4);
                cust.put("Salary",500000);

                logger.info("Print value of jsonNode");






            ObjectMapper mapper = new ObjectMapper();

            ProducerRecord<String, String> rec = new ProducerRecord<String, String>("JsonRec","This",cust.toString());

            JsonNode node = mapper.readTree(rec.value());
            String N = node.get("Name").asText();
            System.out.println("Name *********************************************************" +  N);

            producer.send(rec);
                System.out.println("Record sent");
            //}

            producer.close();

        }
}
