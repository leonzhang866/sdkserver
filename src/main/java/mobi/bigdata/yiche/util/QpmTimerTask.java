package mobi.bigdata.yiche.util;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//timer.schedule(task, delay, period)   
public class QpmTimerTask extends TimerTask{

	private static final Logger logger = LoggerFactory.getLogger(QpmTimerTask.class);
	public void run() {
		// TODO Auto-generated method stub
		String qpm = QPSTool.getQpm();
		QPSTool.setZero();
		
		logger.info("QPM=" + qpm);
		
	}

}
