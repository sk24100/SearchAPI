package shared;

public class APIdata {

	String userId;
	String url;
	String requestType;
	String parameter;
	String body;
	String expected1;
	String expected2;

	public String getExpected1() {
		return expected1;
	}

	public void setExpected1(String expected1) {
		this.expected1 = expected1;
	}

	public String getExpected2() {
		return expected2;
	}

	public void setExpected2(String expected2) {
		this.expected2 = expected2;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "APIdata [userId=" + userId + ", url=" + url + ", requestType=" + requestType + ", parameter="
				+ parameter + ", body=" + body + ", expected1=" + expected1 + ", expected2=" + expected2 + "]";
	}
	
}
