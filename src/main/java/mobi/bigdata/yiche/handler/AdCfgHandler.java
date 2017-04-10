package mobi.bigdata.yiche.handler;

import java.util.Random;

import org.json.JSONArray;

import mobi.bigdata.yiche.util.QPSTool;
import org.json.JSONObject;
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
	    
        Random r = new Random();
        int adType = r.nextInt(3);
	    
	    
        JSONObject resultObj = new JSONObject();
        resultObj.put("code", "2000");
        resultObj.put("msg", "success");
        
        JSONObject contentObj = new JSONObject();
        contentObj.put("dvid", "81ec124a6d6380e7d8fdfeef46ec3166");
        
        
        JSONArray contentResultArray = new JSONArray();
        JSONObject arrResultObj = new JSONObject();
        arrResultObj.put("statusCode", "3000");
        arrResultObj.put("statusMsg", "success");
        arrResultObj.put("pid", "1035");
        arrResultObj.put("type", String.valueOf(adType));
        
        
        JSONObject adMatResultObj = new JSONObject();
    	adMatResultObj.put("creativeid", "129046");
    	JSONArray adMatArray = new JSONArray();
    	adMatResultObj.put("exposureTp", "http://www.baidu.com");
    	adMatResultObj.put("clickTp", "http://www.baidu.com");

        switch (adType){
        case 0:
        	adMatArray.put("https://dl.ctags.cn/ad/mat/txt_pic_0/pic_text.jpg");
        	adMatResultObj.put("title", "Das auto new arrival.");   
            adMatResultObj.put("url", "http://www.taoche.com/volkswagen/");
        	break;
        case 1:
        	adMatArray.put("https://dl.ctags.cn/ad/mat/multi_pic_1/group1.jpg");
        	adMatArray.put("https://dl.ctags.cn/ad/mat/multi_pic_1/group2.jpg");
        	adMatArray.put("https://dl.ctags.cn/ad/mat/multi_pic_1/group3.jpg");
        	adMatResultObj.put("title", "Das auto new arrival.");   
            adMatResultObj.put("url", "http://www.taoche.com/volkswagen/");
        	break;
        case 2:
        	adMatArray.put("https://dl.ctags.cn/ad/mat/big_pic_2/bigpic.jpg");
        	adMatResultObj.put("title", "Das auto new arrival."); 
            adMatResultObj.put("url", "http://www.taoche.com/volkswagen/");
        	break;
        case 3:
        	adMatArray.put("https://dl.ctags.cn/ad/mat/video_3/viedo_pic.png");
        	adMatResultObj.put("title", "Das auto new arrival."); 
            adMatResultObj.put("url", "https://dl.ctags.cn/ad/mat/video_3/test.mp4");
        	break;
        }
        adMatResultObj.put("picUrl", adMatArray);
        arrResultObj.put("result",adMatResultObj);
        
        contentResultArray.put(arrResultObj); //arr 填充一个obj
        
        contentObj.put("result", contentResultArray);
        resultObj.put("content", contentObj);
        
        arg0.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        arg0.getResponseSender().send(resultObj.toString());
	}

}
