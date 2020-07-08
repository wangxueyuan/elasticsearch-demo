package com.navinfo.xd.xd.es.migrate.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

import java.io.Serializable;
@Document(indexName = "track_0430", type = "sensor")
@Data
public class CurTrackIndex implements Serializable {
	private static final long serialVersionUID = 1843793671857886940L;
	@Id
	@Description(value = "文档主键")
	public Long id;
	@Field(type = FieldType.Keyword)
	@Description(value = "关联事件")
	public Long eventId;
	@Field(type = FieldType.Keyword)
	@Description(value = "轨迹点采集类型：POSITION_TYPE_UNDEFINED = 0;RAW_GPS = 1;FILTERED = 2;MAP_MATCHED_REGULAR_MAP= 3;MAP_MATCHED_HD_MAP = 4;WIFI = 5")
	public Integer type;
	@Description(value = "GPS定位模式1:3D, 2:2D, 默认 1")
	@Field(type = FieldType.Integer)
	public Integer locationMode;
	@Field(type = FieldType.Keyword)
	@Description(value = "设备编号")
	public String vin;
	@Field(type = FieldType.Keyword)
	@Description(value = "数据源坐标系经纬度")
	public String geom;
	@Field(type = FieldType.Keyword)
	@Description(value = "数据源坐标系如gcj-02")
	public String coordinateSystem;
	@Field(type = FieldType.Double)
	@Description(value = "经度，double(-180.0, +180.0)")
	public Double longitude;
	@Field(type = FieldType.Double)
	@Description(value = "纬度，double(-90.0, +90.0)")
	public Double latitude;
	@Field(type = FieldType.Double)
	@Description(value = "海拔，单位：米")
	public Double altitude;
	@Description(value = "行驶方向角")
	@Field(type = FieldType.Double)
	public Double gpsBearing;
	@Field(type = FieldType.Double)
	@Description(value = "航向，取值规则：范围为[0,359]，0度为正北方向，顺时针")
	public Double heading;
	@Field(type = FieldType.Keyword)
	@Description(value = "航向检测类型")
	public Integer headingType;
	@Field(type = FieldType.Double)
	@Description(value = "翻滚角")
	public Double roll;
	@Field(type = FieldType.Double)
	@Description(value = "俯仰角")
	public Double pitch;
	@Field(type = FieldType.Double)
	@Description(value = "速度值，单位：km/hs")
	public Double speed;
	@Field(type = FieldType.Keyword)
	@Description(value = "速度检测类型")
	public Integer speedType;
	@Field(type = FieldType.Long)
	@Description(value = "采集轨迹点时间戳（如：GPS时间戳）:毫秒")
	public Long timestamp;
	@Description(value = "系统时间:毫秒")
	@Field(type = FieldType.Keyword)
	public Long systemTime;
	@Field(type = FieldType.Integer)
	@Description(value = "精度，单位：米")
	public Double precisions;
	@Field(type = FieldType.Keyword)
	@Description(value = "地图匹配道路链节ID")
	public Long linkId;
	@Field(type = FieldType.Keyword)
	@Description(value = "地图等级")
	public Integer level;
	@Field(type = FieldType.Integer)
	@Description(value = "莫顿码第13级图幅号")
	public Integer mesh;
	@Field(type = FieldType.Integer)
	@Description(value = "莫顿码第5级图幅号")
	public Integer block;
	@Field(type = FieldType.Keyword)
	@Description(value = "1代表该轨迹点含有图片，0代表该轨迹点不含图片")
	public Integer count;
	@Field(type = FieldType.Keyword)
	@Description(value = "关联索引media字段mediaId")
	public Long mediaId;
	@Field(type = FieldType.Keyword)
	@Description(value = "是否插值点 1 是 0 否")
	public Integer interpolationPoint;
	@Field(type = FieldType.Text)
	@Description(value = "关联轨迹存储的文本文件路径")
	public String name;
	@Field(type = FieldType.Keyword)
	@Description(value = "数据格式版本，如1.0.0")
	public String version;
	@Field(type = FieldType.Keyword)
	@Description(value = "数据提供商，如：didi和minieye")
	public String oemid;
	@Field(type = FieldType.Keyword)
	@Description(value = "载体:TAXI = 0;NET_CAR = 1;LOGISTICS_DELIVERY = 2;BUS = 3;UAV = 4;PRIVATE = 5;")
	public Integer carrierType;
	@Field(type = FieldType.Text)
	@Description(value = "设备类型：didi-device和M4")
	public String deviceType;
	@Field(type = FieldType.Keyword)
	@Description(value = "数据内容类型:纯轨迹 0,轨迹加图片1,点云2,矢量数据3")
	public Integer sourceType;
	@Field(type = FieldType.Text)
	@Description(value = "源任务id主键，由SD|HD或其他部门发起的任务主键")
	public String sourceJob;
	@Field(type = FieldType.Text)
	@Description(value = "源任务发布者")
	public String publisher;
	@Field(type = FieldType.Keyword)
	@Description(value = "车道数")
	public Integer laneNum;
	@Field(type = FieldType.Keyword)
	@Description(value = "采集任务id")
	public String jobId;
	@GeoPointField
	@Description(value = "WGS84轨迹点，由经度（longitude）和纬度（latitude）组成。ES Geometry类型")
	public GeoPoint geoPoint;
	@GeoPointField
	@Description(value = "GCJ02轨迹点，由加密坐标经度（longitude）和纬度（latitude）组成。ES Geometry类型")
	public GeoPoint geoPointGcj02;
	@Field(type = FieldType.Keyword)
	@Description(value = "数据采集接入请求协议源：NISDII、NISENSOR、SENSORIS")
	public String sensorSource;
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
	@Description(value = "垂直精度")
	@Field(type = FieldType.Double)
	public Double vDop;
	@Description(value = "水平精度")
	@Field(type = FieldType.Double)
	public Double hDop;
	@Description(value = "手机磁偏角")
	@Field(type = FieldType.Double)
	public Double phoneDirection;
	@Description(value = "卫星数")
	@Field(type = FieldType.Integer)
	public Integer starNum;
	@Description(value = "云平台存储路径")
	public String logical_path;
	@Description(value = "储存索引数据集")
	public Long dataset_id;
}