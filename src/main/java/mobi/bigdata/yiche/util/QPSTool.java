package mobi.bigdata.yiche.util;

import java.util.concurrent.atomic.AtomicLong;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QPSTool {

	private static final Logger logger = LoggerFactory.getLogger(QPSTool.class);
	
	private static AtomicLong qpm = new AtomicLong(0L);
	
	public static void setZero(){
		qpm.set(0L);
	}
	
	public static void addOne(){
		qpm.incrementAndGet();
		//logger.info("QPSTool is writing.");
	}
	
	public static String getQpm(){
		return qpm.toString();
	}
}
