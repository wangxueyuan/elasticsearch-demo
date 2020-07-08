package com.navinfo.xd.xd.es.migrate.runner;

import com.navinfo.xd.utils.CoordinateUtils;
import com.navinfo.xd.xd.es.migrate.bean.GeoPoint;
import com.navinfo.xd.xd.es.migrate.bean.CurTrackIndex;
import com.navinfo.xd.xd.es.migrate.bean.PreTrackIndex;
import com.navinfo.xd.xd.es.migrate.repository.CurTrackIndexRepository;
import com.navinfo.xd.xd.es.migrate.repository.PreTrackIndexRepository;
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
 * @CreateTime: 3/2/2020 14:59
 * @Version: 1.0
 **/
@Slf4j
//@Component
public class InitTrackRunner implements ApplicationRunner {
    @Autowired
    private PreTrackIndexRepository oldTrackRepository;
    @Autowired
    private CurTrackIndexRepository newTrackIndexRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Start to migrate data...");
        List<CurTrackIndex> newTrackIndices = new ArrayList<>();
        long count= oldTrackRepository.count();
        int pageNum= (int) (count/10000);
        for (int i = 0; i <= pageNum; i++) {
            PageRequest pageRequest = new PageRequest(i, 10000);
            Iterable<PreTrackIndex> trackIndices= oldTrackRepository.findAll(pageRequest);
            trackIndices.forEach(oldTrackIndex -> {
                CurTrackIndex newTrackIndex = new CurTrackIndex();
                BeanUtils.copyProperties(oldTrackIndex,newTrackIndex);
                newTrackIndex.setLogical_path("unknown");//云存储需要
                newTrackIndex.setCountyCode(oldTrackIndex.getCountyAdminCode());
                newTrackIndex.setCountyName(oldTrackIndex.getCountyAdminCodeName());
                newTrackIndex.setCityCode(oldTrackIndex.getCityAdminCode());
                newTrackIndex.setCityName(oldTrackIndex.getCityAdminCodeName());
                newTrackIndex.setProvinceCode(oldTrackIndex.getProvinceAdminCode());
                newTrackIndex.setProvinceName(oldTrackIndex.getProvinceAdminCodeName());
                newTrackIndex.setCoordinateSystem("WGS84");
                newTrackIndex.setLocationMode(1);//默认
                GeoPoint geoPoint = new GeoPoint(oldTrackIndex.getLatitude(),oldTrackIndex.getLongitude());
                newTrackIndex.setGeoPoint(geoPoint);

                Double longitude = oldTrackIndex.getLongitude();
                Double latitude = oldTrackIndex.getLatitude();
                // gcj02
                Coordinate gcj02 = CoordinateUtils.wgs84ToGcj02(latitude, longitude);
                newTrackIndex.setGeoPointGcj02(new GeoPoint(gcj02.x, gcj02.y));
                newTrackIndices.add(newTrackIndex);
            });
            newTrackIndexRepository.saveAll(newTrackIndices);
            log.info("End to migrate data, the count of index is {}",newTrackIndices.size());
            newTrackIndices.clear();
        }




    }
}
