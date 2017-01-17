package mobi.bigdata.yiche.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import io.undertow.server.HttpServerExchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xnio.channels.StreamSourceChannel;

public class Http {

	private static final Logger logger = LoggerFactory.getLogger(Http.class);
	
	public static String getPostString(HttpServerExchange exchange,int limit){
		if(limit <= 0){
			return null;
		}
	    ByteBuffer byteBuffer=ByteBuffer.allocate(limit);
	    try {
	    	StreamSourceChannel requestChannel = exchange.getRequestChannel();
			requestChannel.read(byteBuffer);
		} catch (IOException e) {
			logger.error("e:"+e.getMessage(),e);
			return null;
		}
	    byteBuffer.rewind();
	    byte[] bytes = new byte[limit];
	    byteBuffer.get(bytes);
	    String post = new String(bytes, Charset.forName("UTF-8") );
	    return post;
	}
	
}
