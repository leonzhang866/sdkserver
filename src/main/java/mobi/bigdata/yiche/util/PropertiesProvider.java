package mobi.bigdata.yiche.util;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesProvider extends TimerTask {

	
   public static List getSchema(){
	   ArrayList allValue = new ArrayList();
	   Enumeration enu=schemaProp.elements();
	   while(enu.hasMoreElements()){
	       String value = (String)enu.nextElement();
	       allValue.add(value);
	   } 
	   return allValue;
	   
   }
	
	public static String getUploadInterval(String appKey) {
		String interval = (String) dataProp.get(appKey);
		if(interval == null || interval.equals("")){
			interval = "0";
		}
		logger.debug(appKey + " is reqeust return value is " + interval);
		return interval;
	}
	
	public static String getScanInterval(String appKey) {
		String scanInterval = (String) scanProp.get(appKey);
		if(scanInterval == null || scanInterval.equals("")){
			scanInterval = "0";
		}
		logger.debug(appKey + " is reqeust return value is " + scanInterval);
		return scanInterval;
	}
	

	private static final Logger logger = LoggerFactory
			.getLogger(PropertiesProvider.class);

	private static Properties adProp = new Properties();
	private static Properties dataProp = new Properties();
	private static Properties schemaProp = new Properties();
	private static Properties scanProp = new Properties();
	
	private static String adPropFilePath = System.getProperty("user.dir")
			+ "/ad.properties";

	private static String dataPropFilePath = System.getProperty("user.dir")
			+ "/data.properties";

	private static String schemaPropFilePath = System.getProperty("user.dir")
			+ "/schema.properties";

	private static String scanPropFilePath = System.getProperty("user.dir")
			+ "/scan.properties";

	public void run() {
		// TODO Auto-generated method stub
		try {
			FileInputStream fis;
			fis = new FileInputStream(adPropFilePath);
			adProp.load(fis);
			fis.close();

			fis = new FileInputStream(dataPropFilePath);
			dataProp.load(fis);
			fis.close();

			fis = new FileInputStream(schemaPropFilePath);
			schemaProp.load(fis);
			fis.close();
			
			fis = new FileInputStream(scanPropFilePath);
			scanProp.load(fis);
			fis.close();
			
			
			logger.debug("schema now is  " + getSchema() );
			logger.debug("ak = test for upload interval is  " + this.getUploadInterval("test") );
			logger.debug("ak = test for scan interval is  " + this.getScanInterval("test") );

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("prop file read error" + e);

		}
	}

}

