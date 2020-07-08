package com.navinfo.xd.xd.es.migrate.parser;




import com.navinfo.xd.xd.es.migrate.bean.SensorTrackIndex;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileTrackStore {
	private FileWriter writer;
	private String header = "eventId|type|vin|wkt|longitude|latitude|atitude|heading|headingtype|"
			+ "roll|pitch|speed|speedType|timestamp|Precisions|"
			+ "linkid|mesh|block|status|count|mediaid|InterpolationPoint|"
			+ "name|version|oemid|CarrierType|DeviceType|SourceType|SourceJob|"
			+ "Publisher|lanenum|id|jobId|sensorSource\n";

	public FileTrackStore(String path) {
		try {
			writer = new FileWriter(new File(path));
			writer.write(header);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void store(SensorTrackIndex track) {
		try {
			if (track != null) {
				writer.write(
						track.getEventId()
						+ "|" + track.getType()
						+ "|" + track.getVin() 
						+ "|" + track.getGeom()
						+ "|" + track.getLongitude() 
						+ "|" + track.getLatitude() 
						+ "|" + track.getAltitude()
						+ "|" + track.getHeading()
						+ "|" + track.getHeadingType()
						+ "|" + track.getRoll()
						+ "|" + track.getPitch()
						+ "|" + track.getSpeed()
						+ "|" + track.getSpeedType()
						+ "|" + track.getTimestamp() 
						+ "|" + track.getPrecisions() 
						+ "|" + track.getLinkId()
						+ "|" + track.getMesh()
						+ "|" + track.getBlock()
						+ "|" + 0
						+ "|" + track.getCount()
						+ "|" + track.getMediaId()
						+ "|" + track.getInterpolationPoint()
						+ "|" + track.getName()
						+ "|" + track.getVersion()
						+ "|" + track.getOemid()
						+ "|" + track.getCarrierType()
						+ "|" + track.getDeviceType()
						+ "|" + track.getSourceType()
						+ "|" + track.getSourceJob()
						+ "|" + track.getPublisher()
						+ "|" + track.getLaneNum()
						+ "|" + track.getId()
						+ "|" + track.getJobId() 
						+ "|" + "NISDII"
						+ "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		if (writer != null) {
			try {
				writer.flush();
				writer.close();
			} catch (Exception e) {
			}
		}
	}

	public void store(List<SensorTrackIndex> trackList) {
		for (SensorTrackIndex track : trackList) {
			store(track);
		}
	}

	// 保存文件到本地
	public static boolean saveFile(String path, byte[] data) {
		if (data != null) {
			try {
				File file = new File(path);
				if (file.exists()) {
					file.delete();
				}
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(data, 0, data.length);
				fos.flush();
				fos.close();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
