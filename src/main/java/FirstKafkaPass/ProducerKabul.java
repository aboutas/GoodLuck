package FirstKafkaPass;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class ProducerKabul {
    public ProducerKabul(String other, SimpleStringSchema simpleStringSchema, Properties p) {

    }

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
        //BufferedReader br = new BufferedReader(new FileReader("openaq.csv"));
        Scanner scanner =  new Scanner(new File("openaq.csv"));
        scanner.useDelimiter(",");

        System.out.println("Test3");
        String line = null;

        /*try {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        while (scanner.hasNext()) {
            //System.out.println(line);
            // String[] words = line.split(":");
            System.out.print(scanner.next() + "|");
            // create a producer record
            ProducerRecord<String, String> record =
                    new ProducerRecord<String, String>("Other",scanner.next() + "|");
            producer.send(record);
            // send data asynchronous
        }
        System.out.println("Test4");
        //br.close();
        scanner.close();
        //producer.send(record);
        producer.flush();
        producer.close();


    }
}
