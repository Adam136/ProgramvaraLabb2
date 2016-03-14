
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.json.JSONException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * Example class for the SMHI metobs API.
 *
 */
public class SMHISource {

	// Url for the metobs API
	private String metObsAPI = "http://opendata-download-metobs.smhi.se/api";


	/**
	 * Print all available parameters.
	 *
	 * @return The key for the last parameter.
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws XPathExpressionException
	 * @throws JSONException
	 */
	public String getParameters() throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {

		Document parameterDocument = readXmlFromUrl(metObsAPI + "/version/latest.xml");
		NodeList parametersNodeList = (NodeList) parseXML(parameterDocument, "/version/resource/key");

		String parameterKey = null;

//		for (int i = 0; i < parametersNodeList.getLength(); i++) {
//			parameterKey = parametersNodeList.item(i).getTextContent();
//			System.out.println(parameterKey);
//		}
		
		parameterKey = "2";

		return parameterKey;
	}


	/**
	 * Print all available stations for the given parameter. Return the id for the last station.
	 *
	 * @param parameterKey The key for the wanted parameter
	 * @return The id for the last station
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws XPathExpressionException
	 */
	public String getStationNames(String parameterKey)
			throws  ParserConfigurationException, SAXException, IOException, XPathExpressionException {

		Document stationsDocument = readXmlFromUrl(metObsAPI + "/version/latest/parameter/" + parameterKey + ".xml");
		NodeList stationsNodeList = (NodeList) parseXML(stationsDocument, "/metObsParameter/station/key");

		String stationId = null;
//		for (int i = 0; i < stationsNodeList.getLength(); i++) {
//			stationId = stationsNodeList.item(i).getTextContent();
//			System.out.println(stationId);
//		}

		stationId = "98210";
		return stationId;
	}


	/**
	 * Print all available periods for the given parameter and station. Return the key for the last period.
	 *
	 * @param parameterKey The key for the wanted parameter
	 * @param stationKey The key for the wanted station
	 * @return The name for the last period
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws XPathExpressionException
	 */
	public String getPeriodNames(String parameterKey, String stationKey)
			throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {

		Document periodsDocument = readXmlFromUrl(metObsAPI + "/version/latest/parameter/" + parameterKey + "/station/" + stationKey + ".xml");
		NodeList periodsNodeList = (NodeList) parseXML(periodsDocument, "/metObsStation/period/key");

		String periodName = null;
//		for (int i = 0; i < periodsNodeList.getLength(); i++) {
//			periodName = periodsNodeList.item(i).getTextContent();
//			System.out.println(periodName);
//		}
		
//		periodName = "latest-months"; 
		periodName = "corrected-archive";

		return periodName;
	}


	/**
	 * Get the data for the given parameter, station and period.
	 *
	 * @param parameterKey The key for the wanted parameter
	 * @param stationKey The key for the wanted station
	 * @param periodName The name for the wanted period
	 * @return The data
	 * @throws IOException
	 * @throws JSONException
	 */
	public String getData(String parameterKey, String stationKey, String periodName) throws IOException {
		return readStringFromUrl(metObsAPI + "/version/latest/parameter/" + parameterKey + "/station/" + stationKey + "/period/" + periodName + "/data.csv");
	}


	public Document readXmlFromUrl(String url) throws IOException, ParserConfigurationException, SAXException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		return builder.parse(url);
	}


	public NodeList parseXML(Document document, String expression) throws XPathExpressionException {

		XPath xPath =  XPathFactory.newInstance().newXPath();
		return (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
	}


	public String readStringFromUrl(String url) throws IOException {

		InputStream inputStream = new URL(url).openStream();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
			StringBuilder stringBuilder = new StringBuilder();
			int cp;
			while ((cp = reader.read()) != -1) {
				stringBuilder.append((char) cp);
			}
			return stringBuilder.toString();
		} finally {
			inputStream.close();
		}
	}
}