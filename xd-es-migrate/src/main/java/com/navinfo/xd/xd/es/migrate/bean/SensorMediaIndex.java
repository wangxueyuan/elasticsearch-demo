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
public class SensorMediaIndex implements Serializable {
	private static final long serialVersionUID = -7007095923583125669L;
	@Id
	@Description(value = "文档主键")
	public Long id;
	
	@Field(type = FieldType.Keyword)
	@Description(value = "类型:0 未定义 类型,1:other,2:image,3:video")
	public Integer type;
	
	@Field(type = FieldType.Keyword)
	@Description(value = "文件格式，如JPG|PNG|WEBP|MP4")
	public String format;
	
	@Field(type = FieldType.Double)
	@Description(value = "wgs84-经度")
	public Double longitude;
	
	@Field(type = FieldType.Double)
	@Description(value = "wgs84-纬度")
	public Double latitude;
	
	@Field(type = FieldType.Double)
	@Description(value = "海拔，单位：米")
	public Double altitude;
	
	@Field(type = FieldType.Long)
	@Description(value = "截帧图片时间戳")
	public Long timestamp;
	
	@Description(value = "订单下发时间:毫秒")
	@Field(type = FieldType.Long)
	public Long gpsTime;
	
	@Field(type = FieldType.Keyword)
	@Description(value = "设备编号")
	public String vin;
	
	@Field(type = FieldType.Text)
	@Description(value = "文件存放在路径")
	public String path;
	
	@Field(type = FieldType.Text)
	@Description(value = "文件名")
	public String name;
	
	@Field(type = FieldType.Keyword)
	@Description(value = "关联索引track字段mediaId")
	public Long mediaId;
	
	@Field(type = FieldType.Integer)
	@Description(value = "video持续的时间")
	public Integer duration;
	
	@Field(type = FieldType.Keyword)
	@Description(value = "数据格式版本，如1.0.0")
	public String version;
	
	@Field(type = FieldType.Keyword)
	@Description(value = "数据提供商，如：didi和minieye")
	public String oemid;
	
	@Field(type = FieldType.Keyword)
	@Description(value = "数据内容类型:纯轨迹 0,轨迹加图片1,点云2,矢量数据3")
	public Integer sourceType;
	
	@Field(type = FieldType.Keyword)
	@Description(value = "载体:TAXI = 0;NET_CAR = 1;LOGISTICS_DELIVERY = 2;BUS = 3;UAV = 4;PRIVATE = 5;")
	public Integer carrierType;
	
	@Field(type = FieldType.Text)
	@Description(value = "设备类型：didi-device和M4")
	public String deviceType;
	
	@Field(type = FieldType.Text)
	@Description(value = "源任务主键")
	public String sourceJob;
	
	@Field(type = FieldType.Text)
	@Description(value = "源任务发布者")
	public String publisher;
	
	@Field(type = FieldType.Keyword)
	@Description(value = "采集任务主键")
	public String jobId;
	
	@Field(type = FieldType.Keyword)
	@Description(value = "数据采集接入请求协议源")
	public String sensorSource;
	
	@Field(type = FieldType.Integer)
	@Description(value = "图片大小，单位byte。1KB=1024Byte")
	public Integer size;
	
	@Field(type = FieldType.Keyword)
	@Description(value = "区县代码")
	public String countyCode;
	
	@Description(value = "区县名称")
	public String countyName;
	
	@Field(type = FieldType.Keyword)
	@Description(value = "地市代码")
	public String cityCode;
	
	@Description(value = "地方名称")
	public String cityName;
	
	@Field(type = FieldType.Keyword)
	@Description(value = "省代码")
	public String provinceCode;
	
	@Description(value = "省名称")
	public String provinceName;
	
	@Transient
	@Description(value = "图片字节流")
	public byte[] content;
	
	@Transient
	@Description(value = "目录分类")
	public String category;
	
	@Transient
	@Description(value = "图片上传状态")
	public int sendStatus;
	
	@Transient
	@Description(value = "关联订单主键")
	public Long orderId;
	
	@Transient
	@Description(value = "第三方平台下载url")
	public String downloadUrl;
	
	@Transient
	@Description(value = "记录从第三方平台下载图片错误信息")
	public String downloadMsg;
	
	@Description(value = "云平台存储路径")
	public String logical_path;
	
	@Description(value = "储存索引数据集")
	public long dataset_id;
}