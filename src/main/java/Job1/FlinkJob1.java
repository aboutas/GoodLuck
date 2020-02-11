package Job1;


import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;

import java.util.Collection;
import java.util.Properties;

public class FlinkJob1 {

    public static void main(String[] args) {

        System.out.println("Hello Fucking World");
        String server = "127.0.0.1:9092";

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties p = new Properties();
        p.setProperty("BootstrapServer",server );

        DataStream<String> KafkaData =  env.addSource(new FlinkKafkaConsumer("Other", new SimpleStringSchema(),p));

        KafkaData.flatMap(new FlatMapFunction<String, Tuple2<String ,Integer>>() {
            @Override
            public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {

            }

            public  void flatMap(String value, Collection<Tuple2<String, Integer>> out){

                String[] words = value.split("");
                for (String word : words)
                   out.containsAll((Collection<?>) new Tuple2<String,Integer>(word,1));
            }
        });
        KafkaData.keyBy(0);
        KafkaData.writeAsCsv("file:///home/t/Desktop/output.csv");
        try {
            env.execute("Example");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

