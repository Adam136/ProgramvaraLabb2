
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

public class SMHITest implements DataSource {
	static String data;

	public static void main(String[] args) {
		System.out.println(new SMHITest().getValues());
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public String getUnit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<LocalDate, Double> getValues() {
		// TODO Auto-generated method stub
		SMHISource openDataMetobsReader = new SMHISource();
		try {
			String parameterKey = openDataMetobsReader.getParameters();
			String stationKey = openDataMetobsReader.getStationNames(parameterKey);
			String periodName = openDataMetobsReader.getPeriodNames(parameterKey, stationKey);
			data = openDataMetobsReader.getData(parameterKey, stationKey, periodName);
//			System.out.println(data);
		} catch (XPathExpressionException | IOException
				| ParserConfigurationException | SAXException e) {
			e.printStackTrace();			
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
		Map<LocalDate, Double> temperatur = new TreeMap<>();
		for (LocalDate currentDate = LocalDate.parse("2014-jan-01", formatter); currentDate.getYear() < 2015; currentDate = currentDate.plusDays(1))
		{
			String date = currentDate.toString();
			int stringIndex = (data.indexOf(date));
			String tempString = data.substring((stringIndex + 31), (stringIndex + 35));
			if(tempString.contains(";"))
				tempString = tempString.substring(0, 3);
			double temp = Double.parseDouble(tempString);
//			System.out.println(currentDate + " = " + temp);
			temperatur.put(currentDate, temp);
		}
			
		
		return temperatur;
	}
}
