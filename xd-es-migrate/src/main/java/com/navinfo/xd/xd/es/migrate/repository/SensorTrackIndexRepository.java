package com.navinfo.xd.xd.es.migrate.repository;

import com.navinfo.xd.xd.es.migrate.bean.SensorTrackIndex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @Author: Wang X.Y.
 * @CreateTime: 4/29/2020 16:14
 * @Version: 1.0
 **/
public interface SensorTrackIndexRepository  extends ElasticsearchRepository<SensorTrackIndex, Long> {
    Page<SensorTrackIndex> getSensorTrackIndicesByOemidAndCityNameAndTimestampBetween(String oemid, String cityName, long start, long end, PageRequest request);
    int countByOemidAndCityNameAndTimestampBetween(String oemid, String cityName,long start, long end);

    List<SensorTrackIndex> getSensorTrackIndicesByJobId(String jobId);
}
