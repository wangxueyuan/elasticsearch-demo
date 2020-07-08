package com.navinfo.xd.xd.es.migrate.repository;

import com.navinfo.xd.xd.es.migrate.bean.SensorMediaIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @Author: Wang X.Y.
 * @CreateTime: 4/29/2020 16:14
 * @Version: 1.0
 **/
public interface SensorMediaIndexRepository extends ElasticsearchRepository<SensorMediaIndex, Long> {
    List<SensorMediaIndex> getSensorMediaIndicesByJobId(String jobId);
}
