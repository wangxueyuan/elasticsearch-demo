package com.navinfo.xd.xd.es.migrate.repository;

import com.navinfo.xd.xd.es.migrate.bean.PreTrackIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PreTrackIndexRepository extends ElasticsearchRepository<PreTrackIndex, Long> {
}