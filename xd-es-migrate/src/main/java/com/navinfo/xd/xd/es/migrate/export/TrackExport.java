package com.navinfo.xd.xd.es.migrate.export;

import com.navinfo.xd.xd.es.migrate.bean.CurTrackIndex;
import com.navinfo.xd.xd.es.migrate.bean.SensorTrackIndex;
import com.navinfo.xd.xd.es.migrate.parser.FileTrackStore;
import com.navinfo.xd.xd.es.migrate.repository.CurTrackIndexRepository;
import com.navinfo.xd.xd.es.migrate.repository.SensorTrackIndexRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * @Author: Wang X.Y.
 * @CreateTime: 4/29/2020 16:20
 * @Version: 1.0
 **/
@Slf4j
public class TrackExport {
    @Autowired
    private SensorTrackIndexRepository sensorTrackIndexRepository;
    @Autowired
    private CurTrackIndexRepository curTrackIndexRepository;

    public void run(ApplicationArguments args) throws Exception {
        log.info("Start to export data...");
        TreeSet<Long> set = new TreeSet<>();
        FileTrackStore fileTrackStore = new FileTrackStore("C:\\Users\\wangxueyuan\\Desktop\\fsdownload\\mapgoo_0429.txt");
//        long count= sensorTrackIndexRepository.countByOemidAndCityNameAndTimestampBetween("mapgoo","北京",1588003209000L,1588089549000L);
        long count = sensorTrackIndexRepository.count();
        log.info("export size is {}",count);
        int pageNum= (int) (count/10000);
        for (int i = 0; i <= pageNum; i++) {
            PageRequest pageRequest = new PageRequest(i, 10000);
            Page<SensorTrackIndex> pageContent = sensorTrackIndexRepository.findAll(pageRequest);
//                    getSensorTrackIndicesByOemidAndCityNameAndTimestampBetween("mapgoo","北京",1587700027000L,1587702427000L,pageRequest);
            List<CurTrackIndex> curTrackIndices = new ArrayList<>();
            pageContent.forEach(sensorTrackIndex -> {
                CurTrackIndex curTrackIndex = new CurTrackIndex();
                BeanUtils.copyProperties(sensorTrackIndex, curTrackIndex);
                curTrackIndices.add(curTrackIndex);
            });
            curTrackIndexRepository.saveAll(curTrackIndices);
        }
        log.info("the tree set is {}",set.size());



    }


}
