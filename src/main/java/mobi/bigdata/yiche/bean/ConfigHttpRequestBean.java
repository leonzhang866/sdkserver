package mobi.bigdata.yiche.bean;



import com.google.common.base.Objects;

public class ConfigHttpRequestBean {

	private String id;
	private String ak;
	private String sdkv;
	private String pf;
	

	
	public String getSdkv() {
		return sdkv;
	}



	public void setSdkv(String sdkv) {
		this.sdkv = sdkv;
	}



	public String getPf() {
		return pf;
	}



	public void setPf(String pf) {
		this.pf = pf;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getAk() {
		return ak;
	}



	public void setAk(String ak) {
		this.ak = ak;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("id", id)
		.add("ak", ak)
		.add("sdkv", sdkv)
		.add("pf", pf)
		.toString();
	}
	
}
