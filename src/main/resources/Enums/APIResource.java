package Enums;
//enum is special class which has collection of methods or constants.
public enum APIResource {

	postAPI("/auth/realms/wiga-dev/protocol/openid-connect/token"),
	getAPI("search-service/getanswers");
	private String resource;
	
	APIResource(String resource){
		this.resource=resource;
	}
	
	public String getResource() {
		return resource;
	}
}

