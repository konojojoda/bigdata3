package com.zx.write;

import com.zx.bean.Student;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;

import java.io.IOException;

public class SingleWiter {
    public static void main(String[] args) {
        JestClientFactory factory = new JestClientFactory();
        HttpClientConfig config = new HttpClientConfig.Builder("http://hadoop102:9200").build();
        factory.setHttpClientConfig(config);
        JestClient client = factory.getObject();


//        Index index = new Index.Builder("{\n" +
//                "  \"class_id\":\"191125\",\n" +
//                "  \"name\":\"bbb\",\n" +
//                "  \"age\":\"21\",\n" +
//                "  \"sex\":\"male\",\n" +
//                "  \"score\":80.0,\n" +
//                "  \"favo\":\"曲棍球\"\n" +
//                "}").index("student")
//                .type("_doc")
//                .id("1007")
//                .build();
        Student student = new Student("20", "191010",
                "摇滚", "george", 95.0F, "male");
        Index index = new Index.Builder(student)
                .index("student")
                .type("_doc")
                .id("1008")
                .build();

        try {
            client.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.shutdownClient();
    }
}
