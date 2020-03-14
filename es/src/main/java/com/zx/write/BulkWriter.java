package com.zx.write;

import com.zx.bean.Student;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;

import java.io.IOException;

public class BulkWriter {
    public static void main(String[] args) {
        JestClientFactory factory = new JestClientFactory();
        HttpClientConfig conf = new HttpClientConfig.Builder("http://hadoop102:9200").build();
        factory.setHttpClientConfig(conf);
        JestClient client = factory.getObject();
        Student s1 = new Student("80", "different class", "pulp", "jaxer", 45.5F, "male");
        Student s2 = new Student("88", "supernova", "liam", "oasis", 145.5F, "male");
        Bulk bulk = new Bulk.Builder()
                .defaultIndex("student")
                .defaultType("_doc")
                .addAction(new Index.Builder(s1).id("1010").build())
                .addAction(new Index.Builder(s2).id("1011").build())
                .build();
        try {
            client.execute(bulk);
        } catch (IOException e) {
            e.printStackTrace();
        }

        client.shutdownClient();

    }
}
