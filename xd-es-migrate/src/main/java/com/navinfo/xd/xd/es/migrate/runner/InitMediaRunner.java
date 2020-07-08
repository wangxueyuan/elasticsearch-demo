package com.navinfo.xd.xd.es.migrate.runner;

import com.navinfo.xd.utils.CoordinateUtils;
import com.navinfo.xd.xd.es.migrate.bean.CurMediaIndex;
import com.navinfo.xd.xd.es.migrate.bean.GeoPoint;
import com.navinfo.xd.xd.es.migrate.bean.PreMediaIndex;
import com.navinfo.xd.xd.es.migrate.bean.PreMediaIndex;
import com.navinfo.xd.xd.es.migrate.repository.CurMediaIndexRepository;
import com.navinfo.xd.xd.es.migrate.repository.PreMediaIndexRepository;
import com.vividsolutions.jts.geom.Coordinate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Wang X.Y.
 * @CreateTime: 4/1/2020 00:41
 * @Version: 1.0
 **/
@Slf4j
//@Component
public class InitMediaRunner implements ApplicationRunner {
    @Autowired
    private PreMediaIndexRepository preMediaRepository;
    @Autowired
    private CurMediaIndexRepository newMediaIndexRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<CurMediaIndex> newMediaIndices = new ArrayList<>();
        long count= preMediaRepository.count();
        log.info("Start to migrate media data with count->{}...",count);
        int pageNum= (int) (count/10000);
        for (int i = 0; i <= pageNum; i++) {
            PageRequest pageRequest = new PageRequest(i, 10000);
            Iterable<PreMediaIndex> mediaIndices= preMediaRepository.findAll(pageRequest);
            mediaIndices.forEach(oldMediaIndex -> {
                CurMediaIndex newMediaIndex = new CurMediaIndex();
                BeanUtils.copyProperties(oldMediaIndex,newMediaIndex);
                newMediaIndex.setLogical_path(oldMediaIndex.getPath());//云存储需要
                newMediaIndex.setCountyCode(oldMediaIndex.getCountyAdminCode());
                newMediaIndex.setCountyName(oldMediaIndex.getCountyAdminCodeName());
                newMediaIndex.setCityCode(oldMediaIndex.getCityAdminCode());
                newMediaIndex.setCityName(oldMediaIndex.getCityAdminCodeName());
                newMediaIndex.setProvinceCode(oldMediaIndex.getProvinceAdminCode());
                newMediaIndex.setProvinceName(oldMediaIndex.getProvinceAdminCodeName());
                newMediaIndices.add(newMediaIndex);
            });
            newMediaIndexRepository.saveAll(newMediaIndices);
            log.info("End to migrate data, the count of index is {}",newMediaIndices.size());
            newMediaIndices.clear();
        }




    }
}
