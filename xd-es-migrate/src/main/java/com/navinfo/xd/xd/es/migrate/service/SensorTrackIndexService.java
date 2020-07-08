package com.navinfo.xd.xd.es.migrate.service;

import com.navinfo.xd.xd.es.migrate.bean.SensorTrackIndex;
import com.navinfo.xd.xd.es.migrate.repository.SensorTrackIndexRepository;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.InternalTerms;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Wang X.Y.
 * @CreateTime: 5/9/2020 14:09
 * @Version: 1.0
 **/
@Service
public class SensorTrackIndexService {
    @Autowired
    private SensorTrackIndexRepository stir;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public void AggregationByOemId(String fieldName) {
        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        NativeSearchQueryBuilder nsqb = new NativeSearchQueryBuilder();
        nsqb.withQuery(queryBuilder);
        nsqb.withSearchType(SearchType.DFS_QUERY_THEN_FETCH);
        nsqb.withIndices("track").withTypes("sensor");
        TermsAggregationBuilder builder = AggregationBuilders.terms(fieldName).field("oemid");
        nsqb.addAggregation(builder);

        NativeSearchQuery nativeSearchQuery = nsqb.build();
        Aggregations aggregations=elasticsearchTemplate.query(nativeSearchQuery,response -> response.getAggregations());
        Map<String, Aggregation> results = aggregations.asMap();
        StringTerms stringTerms= (StringTerms) results.get("oemid");
        // 将bucket list 转换成 map ， key -> 名字   value-> 出现次数
        Map<String,Long> nameCountMap = stringTerms.getBuckets()
                .stream()
                .collect(Collectors.toMap(
                        StringTerms.Bucket::getKeyAsString,
                        InternalTerms.Bucket::getDocCount,
                        (x,y)->x)
                );
        System.out.println(nameCountMap);
    }


    public void updateSensorTrackIndices(String jobid) {
        List<SensorTrackIndex> result = new ArrayList<>();
        List<SensorTrackIndex> stis = stir.getSensorTrackIndicesByJobId(jobid);
        for (SensorTrackIndex sensorTrackIndex : stis) {
            String geom = "POINT(" + sensorTrackIndex.getGeoPoint().getLon() + " " + sensorTrackIndex.getGeoPoint().getLat()  +")";

            sensorTrackIndex.setGeom(geom);
            result.add(sensorTrackIndex);
        }
        stir.saveAll(result);
    }
}
