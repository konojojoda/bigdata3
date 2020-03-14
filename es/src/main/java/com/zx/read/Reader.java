package com.zx.read;

import com.zx.bean.Student;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.search.aggregation.Aggregation;
import io.searchbox.core.search.aggregation.MetricAggregation;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.List;

public class Reader {
    public static void main(String[] args) {
        JestClientFactory factory = new JestClientFactory();
        HttpClientConfig config = new HttpClientConfig.Builder("http://hadoop102:9200").build();
        factory.setHttpClientConfig(config);
        JestClient client = factory.getObject();
//        Search search = new Search.Builder("{\n" +
//                "  \"aggs\": {\n" +
//                "    \"groupByClass\": {\n" +
//                "      \"terms\": {\n" +
//                "        \"field\": \"class_id.keyword\",\n" +
//                "        \"size\": 10\n" +
//                "      },\n" +
//                "    \"aggs\": {\n" +
//                "      \"maxScore\": {\n" +
//                "        \"max\": {\n" +
//                "          \"field\": \"score\"\n" +
//                "        }\n" +
//                "      }\n" +
//                "    }\n" +
//                "    }\n" +
//                "  }\n" +
//                "}")
//                .addIndex("student")
//                .addType("_doc")
//                .build();


        SearchSourceBuilder builder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        TermQueryBuilder term = new TermQueryBuilder("class_id", "191125");
        boolQueryBuilder.filter(term);
        builder.query(boolQueryBuilder);

        Search search = new Search.Builder(builder.toString())
                .addType("_doc")
                .addIndex("student")
                .build();
        SearchResult result =null;
        try {
            result = client.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("查询条数为：" + result.getTotal() + "条");
        List<SearchResult.Hit<Student, Void>> hits = result.getHits(Student.class);
        for (SearchResult.Hit<Student, Void> hit : hits) {

            System.out.println(hit.source);
        }
        MetricAggregation aggregations = result.getAggregations();
        Aggregation maxScore = aggregations.getAggregation("maxScore", Aggregation.class);
        System.out.println(maxScore);
        client.shutdownClient();
    }
}
