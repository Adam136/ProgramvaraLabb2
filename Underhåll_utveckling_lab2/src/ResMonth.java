import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ResMonth {
	Map<String, MatchedDataPair> loopMap;
	Map<LocalDate, MatchedDataPair> pairMap;
	
	
	
	public ResMonth(Map<LocalDate, MatchedDataPair> map) {
		pairMap = map;
	}
	
	
	
	public Map<String, MatchedDataPair> getMap()
	{
		double[] xMonthValues = new double[12];
		double[] yMonthValues = new double[12];
		double[] xNumberOfValues = new double[12];
		double[] yNumberOfValues = new double[12];
		
		for (Map.Entry<LocalDate, MatchedDataPair> entry : pairMap.entrySet()) {
			for (Map.Entry<LocalDate, MatchedDataPair> entry2 : pairMap.entrySet()) {
				if(entry.getKey().equals(entry2.getKey())) {
					String[] months = new String[12];
					months[0] = "JANUARY";
					months[1] = "FEBRUARY";
					months[2] = "MARCH";
					months[3] = "APRIL";
					months[4] = "MAY";
					months[5] = "JUNE";
					months[6] = "JULY";
					months[7] = "AUGUST";
					months[8] = "SEPTEMBER";
					months[9] = "OCTOBER";
					months[10] = "NOVEMBER";
					months[11] = "DECEMBER";

					for (int x=0; x < 12; x++)
					{
						if(entry.getKey().getMonth().toString().equals(months[x])){
							xMonthValues[x] += entry.getValue().getXValue();
							xNumberOfValues[x] ++;
							yMonthValues[x] += entry.getValue().getYValue();
							yNumberOfValues[x] ++;
						}
							
					}
				}
			}
		}
		loopMap = new HashMap<String, MatchedDataPair>();
		for(int k = 0; k < 12; k++) {
			
			xMonthValues[k] = xMonthValues[k] / xNumberOfValues[k];
			yMonthValues[k] = yMonthValues[k] / yNumberOfValues[k];
			MatchedDataPair tempPair = new MatchedDataPair(xMonthValues[k], yMonthValues[k]);
			loopMap.put("Värde nr: " + k , tempPair);
		}
		return loopMap;
	}
}
