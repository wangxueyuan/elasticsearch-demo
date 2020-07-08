package com.navinfo.xd.xd.es.migrate.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Data
@Document(indexName = "media", type = "sensor")
public class CurMediaIndex implements Serializable {
    private static final long serialVersionUID = -7007095923583125669L;
    @Field(type = FieldType.Keyword)
    public Integer type;
    @Field(type = FieldType.Keyword)
    public String format;
    @Field(type = FieldType.Double)
    public Double longitude;
    @Field(type = FieldType.Double)
    public Double latitude;
    @Field(type = FieldType.Double)
    public Double altitude;
    @Field(type = FieldType.Long)
    public Long timestamp;
    @Field(type = FieldType.Keyword)
    public String vin;
    @Field(type = FieldType.Text)
    public String path;
    @Field(type = FieldType.Text)
    public String name;
    @Field(type = FieldType.Keyword)
    public Long mediaId;
    @Field(type = FieldType.Integer)
    public Integer duration;
    @Field(type = FieldType.Keyword)
    public String version;
    @Field(type = FieldType.Keyword)
    public String oemid;
    @Field(type = FieldType.Keyword)
    public Integer sourceType;
    @Field(type = FieldType.Keyword)
    public Integer carrierType;
    @Field(type = FieldType.Text)
    public String deviceType;
    @Field(type = FieldType.Text)
    public String sourceJob;
    @Field(type = FieldType.Text)
    public String publisher;
    @Id
    public Long id;
    @Field(type = FieldType.Keyword)
    public String jobId;
    @Field(type = FieldType.Keyword)
    public String sensorSource;
    @Field(type = FieldType.Integer)
    public Integer size;
    @Field(type = FieldType.Keyword)
    public String countyCode;
    public String countyName;
    @Field(type = FieldType.Keyword)
    public String cityCode;
    public String cityName;
    @Field(type = FieldType.Keyword)
    public String provinceCode;
    public String provinceName;
    // 云存储要求
    @Description(value = "云平台存储路径")
    public String logical_path;
    @Description(value = "储存索引数据集")
    public long dataset_id;
    @Transient
    public byte[] content;
    @Transient
    public String category;
    @Transient
    public int sendStatus;
    @Transient
    public String orderId;
}
