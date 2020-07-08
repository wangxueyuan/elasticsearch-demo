package com.navinfo.xd.xd.es.migrate.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Data
@Document(indexName = "media_backup_0331", type = "sensor")
public class PreMediaIndex implements Serializable {
    private static final long serialVersionUID = -7007095923583125669L;
    @Field(type = FieldType.Long)
    public Integer type;
    @Field(type = FieldType.Text)
    public String format;
    @Field(type = FieldType.Float)
    public Double longitude;
    @Field(type = FieldType.Float)
    public Double latitude;
    @Field(type = FieldType.Float)
    public Double altitude;
    @Field(type = FieldType.Long)
    public Long timestamp;
    @Field(type = FieldType.Text)
    public String vin;
    @Field(type = FieldType.Text)
    public String path;
    @Field(type = FieldType.Text)
    public String name;
    @Field(type = FieldType.Long)
    public Long mediaId;
    @Field(type = FieldType.Long)
    public Integer duration;
    @Field(type = FieldType.Text)
    public String version;
    @Field(type = FieldType.Text)
    public String oemid;
    @Field(type = FieldType.Long)
    public Integer sourceType;
    @Field(type = FieldType.Long)
    public Integer carrierType;
    @Field(type = FieldType.Text)
    public String deviceType;
    @Field(type = FieldType.Text)
    public String sourceJob;
    @Field(type = FieldType.Text)
    public String publisher;
    @Id
    public Long id;
    @Field(type = FieldType.Text)
    public String jobId;
    @Field(type = FieldType.Text)
    public String sensorSource;
    @Field(type = FieldType.Long)
    public Integer size;
    @Field(type = FieldType.Text)
    public String countyAdminCode;
    public String countyAdminCodeName;
    @Field(type = FieldType.Text)
    public String cityAdminCode;
    public String cityAdminCodeName;
    @Field(type = FieldType.Text)
    public String provinceAdminCode;
    public String provinceAdminCodeName;
    // 云存储要求
    public String logical_path;
    public long dataset_id;
}