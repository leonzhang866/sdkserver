package mobi.bigdata.yiche.bean;

import com.google.common.base.Objects;

public class DataCfgResponseBean {
	
	private String status;
	private ResultForDataCfgResponseBean result;
	
	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public ResultForDataCfgResponseBean getRfcrb() {
		return result;
	}



	public void setRfcrb(ResultForDataCfgResponseBean rfcrb) {
		this.result = rfcrb;
	}


	
	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("status", status)
		.add("result", result)
		.toString();
	}
	
	

	
}
