package com.navinfo.xd.xd.es.migrate.repository;

import com.navinfo.xd.xd.es.migrate.bean.CurTrackIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: Wang X.Y.
 * @CreateTime: 3/2/2020 15:31
 * @Version: 1.0
 **/
public interface CurTrackIndexRepository extends ElasticsearchRepository<CurTrackIndex, Long> {

}
