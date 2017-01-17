package mobi.bigdata.yiche;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Deque;
import java.util.Properties;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mobi.bigdata.yiche.handler.AdCfgHandler;
import mobi.bigdata.yiche.handler.DataCfgHandler;
import mobi.bigdata.yiche.util.Constant;
import mobi.bigdata.yiche.util.PropertiesProvider;
import mobi.bigdata.yiche.util.QPSTool;
import mobi.bigdata.yiche.util.QpmTimerTask;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.PathHandler;
import io.undertow.server.handlers.accesslog.AccessLogHandler;
import io.undertow.server.handlers.accesslog.DefaultAccessLogReceiver;
import io.undertow.util.Headers;

public class Tool {
	private static Logger logger = LoggerFactory.getLogger(Tool.class);

	public static void main(String[] args) {

		// java
		// -Dlogback.configurationFile=/Users/yiche/Desktop/mySrc/Java/TestServer/target/logback.xml
		// -jar config_server-1.0.jar

		
		
		
	
		
		
		
		String envPropFilePath = System.getProperty("user.dir")
				+ "/env.properties";

		Properties evnProp = new Properties();

		logger.info(envPropFilePath);

		try {

			FileInputStream fis1;
			fis1 = new FileInputStream(envPropFilePath);
			evnProp.load(fis1);
			fis1.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("prop file read error" + e);
			return;
		}

		String bindServerIp = (String) evnProp.get("bind_server_ip");
		int bindServerPort = Integer.parseInt((String) evnProp
				.get("bind_server_port"));
		logger.info("server ip = " + bindServerIp);
		logger.info("server port = " + bindServerPort);

		PathHandler path = io.undertow.Handlers.path();
		path.addPrefixPath("/data/cfg", new DataCfgHandler());
		path.addPrefixPath("/ad/cfg", new AdCfgHandler("test"));

		QPSTool.setZero();
		TimerTask tt = new QpmTimerTask();
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(tt, 60 * 1000, 60 * 1000);

		PropertiesProvider pp = new PropertiesProvider();
		Timer timerForPP = new Timer();
		timerForPP.scheduleAtFixedRate(pp, 0, 60 * 1000);
		logger.info("upload interval test value is "
				+ pp.getUploadInterval("test"));
		logger.info("schema is " + pp.getSchema().toString());

		// final ExecutorService accessLogExecutorService =
		// Executors.newFixedThreadPool(12);
		// File accessLogs = new File(Constant.ACCESS_LOG_PATH_PREFIX);
		// Date date = new Date();
		// String dateStr = new
		// SimpleDateFormat("yyyy-MM-dd_hh-mm").format(date);
		// DefaultAccessLogReceiver dal = new
		// DefaultAccessLogReceiver(accessLogExecutorService, accessLogs,
		// "access-log-" + dateStr, ".log");
		// AccessLogHandler accessLogHandler = new AccessLogHandler(path,dal,
		// "combined", Tool.class.getClassLoader());

		final Undertow server = Undertow.builder().setHandler(path)
				.addHttpListener(bindServerPort, bindServerIp).build();
		server.start();
		logger.info("server started..");

	}
}
