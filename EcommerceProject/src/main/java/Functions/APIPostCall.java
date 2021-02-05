package Functions;

import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIPostCall {
	
	public static String asJasonString(final Object obj) {
		try {
			final ObjectMapper map = new ObjectMapper();
			final String jsonContent = map.writeValueAsString(obj);
			return jsonContent;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
				
	}
	
	
	// invoke method top hit api using the object(content) already set for api and teh cookie for authentication
	public Long create(String Cookie, String obj) throws ClientProtocolException, IOException {
		HttpClient httpclient = HttpClientBuilder.create().build();
		SimpleClientHttpRequestFactory rq = new SimpleClientHttpRequestFactory();
		String APIUrl ="";
		HttpHeaders httpheaders = new HttpHeaders();
		httpheaders.setContentType(MediaType.APPLICATION_JSON);
		try {
			HttpPost request = new HttpPost(APIUrl);
			//if object recieved is in string format
			StringEntity parameters = new StringEntity(obj);
			//if string received in any other format other than string
			//	StringEntity parameters = new StringEntity(asJasonString(obj));
			request.addHeader("Cookie",Cookie);
			//add headers if they are applicable for api-checking in swagger in curl response
			request.addHeader("Content-Type", "application/json");
			request.addHeader("Accept", "text/plain");
			//add authentication header using id if applicable
			request.addHeader("ID","value");
			request.setEntity(parameters);
			HttpResponse response = httpclient.execute(request);
			System.out.println(response);
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return null;
		
	}
	
	
	
	public void APIPost(){
		
		String URL ="";
		PrintStream  fileOutErr = null;
		RequestSpecification request = RestAssured.given();
		//Add all the headers required for the post call
		request.header("Content-Type", "application/json");
		request.header("Language", "en_US");
		request.header("Cookie", "CookieValue");
		String requestBody = "{\"value1\": \"value2\": , \"value1\": \"value2\" }";
		Response response = null;
		try {
			response = request.given().contentType(ContentType.JSON).body(requestBody).post("APIurl");
			PrintStream fileOut = new PrintStream("./out.txt");
			System.setOut(fileOut);
		System.out.println("Response :" +response.asString());
		System.out.println("Status Code :" +response.getStatusCode());
		} catch(Exception e){
			System.setOut(fileOutErr);
			System.out.println("Error exception occured is : " +e.getLocalizedMessage());
			fileOutErr.flush();
		}
	}
}
