package com.navinfo.xd.xd.es.migrate.service;

import com.navinfo.xd.utils.StringUtils;
import com.navinfo.xd.xd.es.migrate.bean.SensorMediaIndex;
import com.navinfo.xd.xd.es.migrate.repository.SensorMediaIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Wang X.Y.
 * @CreateTime: 5/9/2020 14:09
 * @Version: 1.0
 **/
@Service
public class SensorMediaIndexService {
    @Autowired
    private SensorMediaIndexRepository smir;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public void updateSensorMediaIndicesSourceJobPublisher(String jobid,String sourceJob,String publisher) {
        List<SensorMediaIndex> result = new ArrayList<>();
        List<SensorMediaIndex> stis = smir.getSensorMediaIndicesByJobId(jobid);
        for (SensorMediaIndex smi : stis) {
            if (StringUtils.isEmpty(smi.getSourceJob())) {
                smi.setSourceJob(sourceJob);
                smi.setPublisher(publisher);
                result.add(smi);
            }
        }
        smir.saveAll(result);
    }
}
