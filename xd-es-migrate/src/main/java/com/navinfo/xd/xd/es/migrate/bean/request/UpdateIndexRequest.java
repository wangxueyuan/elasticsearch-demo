package com.navinfo.xd.xd.es.migrate.bean.request;

import lombok.Data;

/**
 * @Author: Wang X.Y.
 * @CreateTime: 7/14/2020 11:47
 * @Version: 1.0
 **/
@Data
public class UpdateIndexRequest {
    private String jobId;
    private String sourceJob;
    private String publisher;
}
