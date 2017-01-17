package mobi.bigdata.yiche.bean;

import java.util.List;

import com.google.common.base.Objects;

public class ResultForDataCfgResponseBean {
	private String upload_interval;
	private String scan_interval;
	private List<SchemaObject> schema;


	public List<SchemaObject> getSchema() {
		return schema;
	}



	public void setSchema(List<SchemaObject> schema) {
		this.schema = schema;
	}



	public String getUpload_interval() {
		return upload_interval;
	}



	public void setUpload_interval(String upload_interval) {
		this.upload_interval = upload_interval;
	}



	public String getScan_interval() {
		return scan_interval;
	}



	public void setScan_interval(String scan_interval) {
		this.scan_interval = scan_interval;
	}



	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("scan_interval", scan_interval)
				.add("upload_interval", upload_interval)
				.add("schema", schema)
		.toString();
	}
	
}
