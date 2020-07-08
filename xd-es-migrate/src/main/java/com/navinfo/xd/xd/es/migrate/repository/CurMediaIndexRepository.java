package com.navinfo.xd.xd.es.migrate.repository;

import com.navinfo.xd.xd.es.migrate.bean.CurMediaIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: Wang X.Y.
 * @CreateTime: 4/1/2020 00:36
 * @Version: 1.0
 **/
public interface CurMediaIndexRepository  extends ElasticsearchRepository<CurMediaIndex, Long> {
}
