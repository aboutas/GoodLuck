package Job1;

import FirstKafkaPass.MyConsumer;
import FirstKafkaPass.ProducerKabul;
import FirstKafkaPass.SimpleProducer;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Properties;

public class FlinkJob1 {

    public static void main(String[] args) {

        System.out.println("Hello Fucking World");
        String server = "127.0.0.1:9092";

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties p = new Properties();
        p.setProperty("BootstrapServer",server );

        //DataStream<String> KafkaData = (DataStream<String>) env.addSource(new MyConsumer("Other" , new SimpleStringSchema(),p));

    }
}

