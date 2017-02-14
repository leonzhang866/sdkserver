package mobi.bigdata.yiche.handler;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mobi.bigdata.yiche.bean.DataCfgResponseBean;
import mobi.bigdata.yiche.bean.ConfigHttpRequestBean;
import mobi.bigdata.yiche.bean.ResponseStatusBean;
import mobi.bigdata.yiche.bean.ResultForDataCfgResponseBean;
import mobi.bigdata.yiche.bean.SchemaObject;
import mobi.bigdata.yiche.util.Constant;
import mobi.bigdata.yiche.util.Http;
import mobi.bigdata.yiche.util.PropertiesProvider;
import mobi.bigdata.yiche.util.QPSTool;
import net.sf.json.JSONObject;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderValues;
import io.undertow.util.Headers;
import io.undertow.util.MimeMappings;

public class DataCfgHandler implements HttpHandler {

	private static final Logger logger = LoggerFactory
			.getLogger(DataCfgHandler.class);
	private static Gson gson = new GsonBuilder().serializeNulls().create();

	public DataCfgHandler() {

	}

	public void handleRequest(HttpServerExchange arg0) throws Exception {
		// TODO Auto-generated method stub

		if (arg0.isInIoThread()) {
			arg0.dispatch(this);
			return;
		}

		String method = arg0.getRequestMethod().toString();// HTTP_METHOD
		arg0.getResponseHeaders().put(Headers.CONTENT_TYPE,
				"application/json;charset=utf-8");

		QPSTool.addOne();

		if (method.equalsIgnoreCase("POST")) {

			HeaderValues contentType = arg0.getRequestHeaders().get(
					Headers.CONTENT_TYPE);
			String mime = MimeMappings.DEFAULT.getMimeType("json");
			if (!contentType.contains(mime)) {
				// client need to set application/json
				ResponseStatusBean rsb = new ResponseStatusBean();
				rsb.setStatus(Constant.error_code_http_request_method_not_support);
				String responseText = gson.toJson(rsb);
				arg0.getResponseSender().send(responseText,
						Charset.forName("UTF-8"));

				return;
			}

			HeaderValues contentLength = arg0.getRequestHeaders().get(
					Headers.CONTENT_LENGTH);

			if (logger.isDebugEnabled()) {
				logger.debug("lenght = " + contentLength.getFirst());
			}

			if (contentLength == null || contentLength.size() == 0
					|| Integer.parseInt(contentLength.getFirst()) <= 0) {
				arg0.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			int limit = Integer.parseInt(contentLength.getFirst());
			String post = Http.getPostString(arg0, limit);
			if (post == null || "".equals(post)) {
				arg0.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			try {
				ConfigHttpRequestBean query = gson.fromJson(post,
						ConfigHttpRequestBean.class);

				if (query.getAk() == null || query.getAk().equals("")
						|| query.getId() == null || query.getId().equals("")
						|| query.getPf() == null || query.getPf().equals("")
						|| query.getSdkv() == null
						|| query.getSdkv().equals("")) {

					ResponseStatusBean rsb = new ResponseStatusBean();
					rsb.setStatus(Constant.error_code_http_request_no_para);
					String responseText = gson.toJson(rsb);
					arg0.getResponseSender().send(responseText,
							Charset.forName("UTF-8"));

					return;
				}

				if (logger.isDebugEnabled()) {
					logger.debug("ak=" + query.getAk() + "&id=" + query.getId()
							+ "&pf=" + query.getPf() + "&sdkv="
							+ query.getSdkv());
				}

				ResultForDataCfgResponseBean result = new ResultForDataCfgResponseBean();
				result.setScan_interval(PropertiesProvider.getScanInterval(query.getAk())); 
				result.setUpload_interval(PropertiesProvider
						.getUploadInterval(query.getAk()));

				if (query.getPf().equals("1")) {
					// ios load schema
					List schemaObjects = new ArrayList();
					List schema = PropertiesProvider.getSchema();

					if (schema != null) {
						Iterator it1 = schema.iterator();
						while (it1.hasNext()) {
							SchemaObject so = new SchemaObject();
							so.setValue((String) it1.next());
							schemaObjects.add(so);
						}
					}

					result.setSchema(schemaObjects);
				}

				DataCfgResponseBean acrb = new DataCfgResponseBean();
				acrb.setStatus(Constant.return_code_ok);
				acrb.setRfcrb(result);

				String responseText = gson.toJson(acrb);
				arg0.getResponseSender().send(responseText,
						Charset.forName("UTF-8"));

			} catch (Exception e) {
				logger.error("parse gson exception" + e.toString());

				ResponseStatusBean rsb = new ResponseStatusBean();
				rsb.setStatus(Constant.error_code_http_request_para_mismatch);
				String responseText = gson.toJson(rsb);
				arg0.getResponseSender().send(responseText,
						Charset.forName("UTF-8"));
				return;
			}

		} else {

			ResponseStatusBean rsb = new ResponseStatusBean();
			rsb.setStatus(Constant.error_code_http_request_method_not_support);
			String responseText = gson.toJson(rsb);
			arg0.getResponseSender().send(responseText,
					Charset.forName("UTF-8"));
			return;
		}

	}

}
