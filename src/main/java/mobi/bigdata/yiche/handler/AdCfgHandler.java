package mobi.bigdata.yiche.handler;

import mobi.bigdata.yiche.util.QPSTool;
import net.sf.json.JSONObject;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class AdCfgHandler implements HttpHandler {
	private String sspAddr;
	
	public AdCfgHandler(String sspAddr){
		this.sspAddr = sspAddr;
	}

	public void handleRequest(HttpServerExchange arg0) throws Exception {
		// TODO Auto-generated method stub
		
	
	    if (arg0.isInIoThread())
        {
		  arg0.dispatch(this);
          return;
        }
	    
	    
		
	    QPSTool.addOne();
        JSONObject resultObj = new JSONObject();
        resultObj.put("ssp_addr", sspAddr);
        
        JSONObject returnObj = new JSONObject();
        returnObj.put("status", "200");
        returnObj.put("result", resultObj);
        
        arg0.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        arg0.getResponseSender().send(returnObj.toString());
	}

}
