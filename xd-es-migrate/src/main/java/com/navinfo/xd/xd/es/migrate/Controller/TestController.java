package com.navinfo.xd.xd.es.migrate.Controller;

import com.navinfo.xd.xd.es.migrate.bean.CurTrackIndex;
import com.navinfo.xd.xd.es.migrate.bean.SensorTrackIndex;
import com.navinfo.xd.xd.es.migrate.repository.CurTrackIndexRepository;
import com.navinfo.xd.xd.es.migrate.repository.SensorTrackIndexRepository;
import com.navinfo.xd.xd.es.migrate.service.SensorTrackIndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    private UserRepository customerRepository;
    @Autowired
    private SensorTrackIndexRepository sensorTrackIndexRepository;
    @Autowired
    private CurTrackIndexRepository curTrackIndexRepository;
    @Autowired
    private Media1IndexRepository media1IndexRepository;
    @Autowired
    private SensorTrackIndexService sensorTrackIndexService;

    @GetMapping("/test")
    public void test(@RequestParam("name") String name, @RequestParam("time") String time,
                     @RequestParam("id") String id) {
        Media1 media1 = new Media1(name, time, id);
        media1IndexRepository.save(media1);
    }

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

    @GetMapping("/get/customer")
    public void getCustomer() {
        Iterable<User> iterable = customerRepository.findAll();
        iterable.forEach(customer -> log.info(customer.getFirstname()));
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
}
