/**
 *  * @author naren
 *
 */
package Functions;

import io.restassured.specification.RequestSpecification;

import java.io.IOException;
//import java.io.String
import java.io.StringReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

//import org.glassfish.grizzly.InputSource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class APIGetCall {

//	SimpleClientHttpRequestFactory rq = new SimpleClientHttpRequestFactory();
//	Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("host name", 1234));

	public void code1() {
		String API = "url";
		RequestSpecification request = RestAssured.given();
		Response response = request.get(API);
		int responsecode = response.getStatusCode();
		if (responsecode == 200) {
			String responseBody = response.getBody().asString();
			JsonPath pathEvaluator = response.jsonPath();
			String value = pathEvaluator.get("required attribute from response");
		} else {
			System.out.println("error message about invalid response code");
		}
	}

	// get calls using multiple parameters in URL
	public void codeWithbuildingUrlfromparameters() {
		SimpleClientHttpRequestFactory rq = new SimpleClientHttpRequestFactory();
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("host name", 1234));
		rq.setProxy(proxy);
		RestTemplate Rt = new RestTemplate(rq);
		String url = "main URl of api";
		String path = "Path of URl with attributes as parameters(like -{version}{channel})";
		String version = "1.1.1";
		String Channel = "PROD";
		final URI getVersionURI = UriComponentsBuilder.fromHttpUrl(url + path).buildAndExpand(version, Channel).toUri();

		HttpHeaders hpheaders = new HttpHeaders();
		hpheaders.setContentType(MediaType.APPLICATION_JSON);

		final ResponseEntity<String> exchange = Rt.exchange(getVersionURI, HttpMethod.GET,
				new HttpEntity(null, hpheaders), new ParameterizedTypeReference<String>() {
				});
		// to directly hit the URL without parametrization
		final ResponseEntity<String> exchange2 = retrieveAPIResponse(url);
		int responsecode = exchange.getStatusCodeValue();
		if (responsecode == 200) {
			parseXMLResponse(exchange.getBody());

		} else {
			System.out.println("error message on console");
		}
	}

	// Parse the xml respons eretrieved from hitting the api and segeregate teh
	// values as required for testing
	public void parseXMLResponse(String body) {
		String XML = body;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		InputSource is;
		String[] values = null;
		try {
			builder = factory.newDocumentBuilder();
			is = new InputSource(new StringReader(XML));
			Document doc = builder.parse(is);
			NodeList value1FromXMLResponse = doc.getElementsByTagName("Tageorattributelabel");
			String value = value1FromXMLResponse.item(0).getTextContent().trim();
			System.out.println("value");
		} catch (ParserConfigurationException e) {
			System.out.println("exception occured due to " + e);
		} catch (SAXException e) {
			System.out.println("exception occured due to " + e);
		} catch (IOException e) {
			System.out.println("exception occured due to " + e);
		}

	}

	// Gte call without using any parameters
	public static ResponseEntity<String> retrieveAPIResponse(String url) {
		ResponseEntity<String> exchange = null;
		SimpleClientHttpRequestFactory rq = new SimpleClientHttpRequestFactory();
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("host name", 1234));
		rq.setProxy(proxy);
		RestTemplate Rt = new RestTemplate(rq);
		String URL = url;
		HttpHeaders hpheaders = new HttpHeaders();
		hpheaders.setContentType(MediaType.APPLICATION_JSON);
		try {
			exchange = Rt.exchange(URL, HttpMethod.GET, new HttpEntity(null, null),
					new ParameterizedTypeReference<String>() {
					});
		}
		// As few api's doesnt send 404 or 400 response we need to catch the exception
		catch (Exception e) {
			System.out.println("message to be given on exception");
		}
		return exchange;

	}
}