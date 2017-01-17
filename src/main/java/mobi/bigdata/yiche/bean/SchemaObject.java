package mobi.bigdata.yiche.bean;

import com.google.common.base.Objects;

public class SchemaObject {

	private String value;
	
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("value", value)
		.toString();
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}
	
}
