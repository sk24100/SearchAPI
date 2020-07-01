package shared;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;



import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification reqPost;
	PrintStream log;
	
	public Utils() throws FileNotFoundException {
		if(reqPost==null)
		{
		log=new PrintStream(new FileOutputStream("logging.text"));
		}
	}
	
	
	public RequestSpecification requestSpecificationForPost() throws IOException {
//	if(reqPost==null)
//		{
//		log=new PrintStream(new FileOutputStream("logging.text"));
		reqPost = new RequestSpecBuilder().setBaseUri(getPropertiesValue("baseUrlPost"))
				.setContentType("application/x-www-form-urlencoded")
				.addParam("client_id", "wiga-webapp")
				.addParam("username", "angesh.bhardwaj@wipro.com")
				.addParam("password", "wiga123$")
				.addParam("grant_type", "password")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		
//		return reqPost;
//		}
		return reqPost;
	}

	public RequestSpecification requestSpecificationForGet() throws IOException {
	
		
		reqPost = new RequestSpecBuilder().setBaseUri(getPropertiesValue("baseUrlGet")).addParam("query", "hipaa")
				.addParam("bot_instance_id", "1062").addParam("query_id", "7b6ed203-a262-a867-9100-a2d234709745")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		
		return reqPost;
		

	}
	
	public static String getPropertiesValue(String key) throws IOException {
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\config\\global.properties");
		prop.load(fis);
		return (String) prop.get(key);
		
	}
	
	public String getJsonPath(Response response,String key) {
		String res=response.asString();
		JsonPath js=new JsonPath(res);
		return js.get(key).toString();
	}
	

}
