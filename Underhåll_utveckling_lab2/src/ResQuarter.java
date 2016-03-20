import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ResQuarter {
	Map<String, MatchedDataPair> loopMap;
	Map<LocalDate, MatchedDataPair> pairMap;

	public ResQuarter(Map<LocalDate, MatchedDataPair> map) {
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
						if(entry.getKey().getMonth().toString().equals(months[x]))
						{
							if (x < 3)
							{
								xMonthValues[0] += entry.getValue().getXValue();
								xNumberOfValues[0] ++;
								yMonthValues[0] += entry.getValue().getYValue();
								yNumberOfValues[0] ++;
							}
							else if (x < 6)
							{
								xMonthValues[1] += entry.getValue().getXValue();
								xNumberOfValues[1] ++;
								yMonthValues[1] += entry.getValue().getYValue();
								yNumberOfValues[1] ++;
							}
							else if (x < 9)
							{
								xMonthValues[2] += entry.getValue().getXValue();
								xNumberOfValues[2] ++;
								yMonthValues[2] += entry.getValue().getYValue();
								yNumberOfValues[2] ++;
							}
							else if (x < 12)
							{
								xMonthValues[3] += entry.getValue().getXValue();
								xNumberOfValues[3] ++;
								yMonthValues[3] += entry.getValue().getYValue();
								yNumberOfValues[3] ++;
							}
							x = 13;
						}
					}



				}

			}

		}
		loopMap = new HashMap<String, MatchedDataPair>();
		for(int k = 0; k < 4; k++) {
			
			xMonthValues[k] = xMonthValues[k] / xNumberOfValues[k];
			yMonthValues[k] = yMonthValues[k] / yNumberOfValues[k];
			MatchedDataPair tempPair = new MatchedDataPair(xMonthValues[k], yMonthValues[k]);
			loopMap.put("Kvartal: " + (k+1) , tempPair);
		}
		return loopMap;
	}
}
