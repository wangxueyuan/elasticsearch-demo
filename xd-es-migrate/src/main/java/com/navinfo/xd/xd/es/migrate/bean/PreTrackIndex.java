package com.navinfo.xd.xd.es.migrate.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Document(indexName = "track_backup_0331", type = "sensor")
@Data
public class PreTrackIndex implements Serializable {
    private static final long serialVersionUID = 6324444213831657245L;
    @Field(type = FieldType.Keyword)
    public Long eventId;
    @Field(type = FieldType.Long)
    public Integer type;
    @Field(type = FieldType.Text)
    public String vin;
    @Field(type = FieldType.Text)
    public String geom;
    @Field(type = FieldType.Float)
    public Double longitude;
    @Field(type = FieldType.Float)
    public Double latitude;
    @Field(type = FieldType.Float)
    public Double altitude;
    @Field(type = FieldType.Float)
    public Double heading;
    @Field(type = FieldType.Long)
    public Integer headingType;
    @Field(type = FieldType.Float)
    public Double roll;
    @Field(type = FieldType.Float)
    public Double pitch;
    @Field(type = FieldType.Double)
    public Double distance;
    @Field(type = FieldType.Float)
    public Double speed;
    @Field(type = FieldType.Long)
    public Integer speedType;
    @Field(type = FieldType.Long)
    public Long timestamp;
    @Field(type = FieldType.Long)
    public Integer precisions;
    @Field(type = FieldType.Text)
    public String value;
    @Field(type = FieldType.Long)
    public Long linkId;
    @Field(type = FieldType.Keyword)
    public Long featureId;
    @Field(type = FieldType.Long)
    public Integer mesh;
    @Field(type = FieldType.Long)
    public Integer block;
    @Field(type = FieldType.Long)
    public Integer count;
    @Field(type = FieldType.Long)
    public Long mediaId;
    @Field(type = FieldType.Long)
    public Integer interpolationPoint;
    @Field(type = FieldType.Text)
    public String name;
    @Field(type = FieldType.Text)
    public String version;
    @Field(type = FieldType.Text)
    public String oemid;
    @Field(type = FieldType.Long)
    public Integer carrierType;
    @Field(type = FieldType.Text)
    public String deviceType;
    @Field(type = FieldType.Long)
    public Integer sourceType;
    @Field(type = FieldType.Text)
    public String sourceJob;
    @Field(type = FieldType.Text)
    public String publisher;
    @Field(type = FieldType.Long)
    public int laneNum;
    @Id
    public Long id;
    @Field(type = FieldType.Text)
    public String jobId;
//    @GeoPointField
    public GeoPoint geoPoint;
    @Field(type = FieldType.Text)
    public String sensorSource;
    @Transient
    public String category;
    @Field(type = FieldType.Text)
    public String countyAdminCode;
    public String countyAdminCodeName;
    @Field(type = FieldType.Text)
    public String cityAdminCode;
    public String cityAdminCodeName;
    @Field(type = FieldType.Text)
    public String provinceAdminCode;
    public String provinceAdminCodeName;
    @Transient
    public int sendStatus;
    // 云存储要求
    public String logical_path;
    public long dataset_id;
}
