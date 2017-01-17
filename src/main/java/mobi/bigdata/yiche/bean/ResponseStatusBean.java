package mobi.bigdata.yiche.bean;

import com.google.common.base.Objects;

public class ResponseStatusBean {

	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("status", status)
		.toString();
	}
}
