package SMHI;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

public class SMHITest {

	public static void main(String... args) {

		SMHISource openDataMetobsReader = new SMHISource();
		try {
			String parameterKey = openDataMetobsReader.getParameters();
			String stationKey = openDataMetobsReader.getStationNames(parameterKey);
			String periodName = openDataMetobsReader.getPeriodNames(parameterKey, stationKey);
			String data = openDataMetobsReader.getData(parameterKey, stationKey, periodName);
			System.out.println(data);
		} catch (XPathExpressionException | IOException
				| ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
	}
}
