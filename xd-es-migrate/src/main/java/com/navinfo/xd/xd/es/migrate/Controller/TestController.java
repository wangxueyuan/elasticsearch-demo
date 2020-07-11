package com.navinfo.xd.xd.es.migrate.Controller;

import com.navinfo.xd.xd.es.migrate.bean.CurTrackIndex;
import com.navinfo.xd.xd.es.migrate.bean.SensorTrackIndex;
import com.navinfo.xd.xd.es.migrate.bean.request.UpdateIndexRequest;
import com.navinfo.xd.xd.es.migrate.repository.CurTrackIndexRepository;
import com.navinfo.xd.xd.es.migrate.repository.SensorTrackIndexRepository;
import com.navinfo.xd.xd.es.migrate.service.SensorMediaIndexService;
import com.navinfo.xd.xd.es.migrate.service.SensorTrackIndexService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * @Author: Wang X.Y.
 * @CreateTime: 4/30/2020 10:43
 * @Version: 1.0
 **/
@Slf4j
@RestController
public class TestController {

    @Autowired
    private SensorTrackIndexRepository sensorTrackIndexRepository;
    @Autowired
    private CurTrackIndexRepository curTrackIndexRepository;
    @Autowired
    private SensorTrackIndexService sensorTrackIndexService;
    @Autowired
    private SensorMediaIndexService sensorMediaIndexService;


    @GetMapping("/migrate")
    public void migrate() {
        log.info("Start to export data...");
        TreeSet<Long> set = new TreeSet<>();
//        FileTrackStore fileTrackStore = new FileTrackStore
//        ("C:\\Users\\wangxueyuan\\Desktop\\fsdownload\\mapgoo_0429.txt");
//        long count= sensorTrackIndexRepository.countByOemidAndCityNameAndTimestampBetween("mapgoo","北京",
//        1588003209000L,1588089549000L);
        long count = sensorTrackIndexRepository.count();
        log.info("export size is {}", count);
        int pageNum = (int) (count / 10000);
        for (int i = 0; i <= pageNum; i++) {
            PageRequest pageRequest = new PageRequest(i, 10000);
            Page<SensorTrackIndex> pageContent = sensorTrackIndexRepository.findAll(pageRequest);
//                    getSensorTrackIndicesByOemidAndCityNameAndTimestampBetween("mapgoo","北京",1587700027000L,
//                    1587702427000L,pageRequest);
            List<CurTrackIndex> curTrackIndices = new ArrayList<>();
            pageContent.forEach(sensorTrackIndex -> {
                CurTrackIndex curTrackIndex = new CurTrackIndex();
                BeanUtils.copyProperties(sensorTrackIndex, curTrackIndex);
                curTrackIndices.add(curTrackIndex);
            });
            curTrackIndexRepository.saveAll(curTrackIndices);
        }
        log.info("the tree set is {}", set.size());
    }

    @GetMapping("/get/aggregation")
    public void getAggregation() {

        System.out.println("test");
        sensorTrackIndexService.AggregationByOemId("oemid");

    }

    @GetMapping("/update/tracks")
    public String updateTracks(String jobId) {
        try {
            sensorTrackIndexService.updateSensorTrackIndices(jobId);
        } catch (Exception e) {
            return "false";
        }
        return "success";
    }

    @PostMapping("/update/tracks/sourceJob/publisher")
    public String updateTracksSourceJob(@RequestBody List<UpdateIndexRequest> requests) {
        try {
            for (UpdateIndexRequest request : requests) {
                log.info("jobid->{},sourcejob->{},publisher->{} ",request.getJobId(),request.getSourceJob(),
                        request.getPublisher());
                String jobId = request.getJobId();
                String sourceJob = request.getSourceJob();
                String publisher = request.getPublisher();
                sensorTrackIndexService.updateSensorTrackIndicesSourceJobPublisher(jobId,sourceJob,publisher);
                sensorMediaIndexService.updateSensorMediaIndicesSourceJobPublisher(jobId,sourceJob,publisher);
            }
        } catch (Exception e) {
            return "false";
        }
        return "success";
    }

    @GetMapping("/update/medias/sourceJob/publisher")
    public String updateMediasSourceJob(@RequestParam String jobId, @RequestParam String sourceJob,
                                        @RequestParam String publisher) {
        try {
            sensorMediaIndexService.updateSensorMediaIndicesSourceJobPublisher(jobId,sourceJob,publisher);
        } catch (Exception e) {
            return "false";
        }
        return "success";
    }
}
