package FirstKafkaPass;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SimpleProducer {
   /* public SimpleProducer(String kabull1, SimpleStringSchema simpleStringSchema, Properties p) {
    }*/

    public static void main(String[] args) throws IOException {

        System.out.println("Hello fucking world");
        String bootstrapServers = "127.0.0.1:9092";
        //create Producer properties

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        //create the producer
        System.out.println("Test1");
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        System.out.println("Test2");
        BufferedReader br = new BufferedReader(new FileReader("openaq.csv"));
        System.out.println("Test3");
        String line = null;

        try {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (line != null) {
            //System.out.println(line);
            String[] words = line.split(":");
            // create a producer record
            ProducerRecord<String, String> record =
                    new ProducerRecord<String, String>("Kabull1",words[0]);
            producer.send(record);
            // send data asynchronous
        }
        System.out.println("Test4");
        br.close();
        //producer.send(record);
        producer.flush();
        producer.close();


    }
}
