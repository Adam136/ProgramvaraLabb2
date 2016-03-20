import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ResYear {
	Map<String, MatchedDataPair> loopMap;
	Map<LocalDate, MatchedDataPair> pairMap;

	public ResYear(Map<LocalDate, MatchedDataPair> map) {
		pairMap = map;
	}
	public Map<String, MatchedDataPair> getMap()
	{

		Map<Integer, Double> xValues = new HashMap<Integer, Double>();
		Map<Integer, Integer> numbersOfPairs = new HashMap<Integer, Integer>();
		Map<Integer, Double> yValues = new HashMap<Integer, Double>();

		for (Map.Entry<LocalDate, MatchedDataPair> entry : pairMap.entrySet()) {
			for (Map.Entry<LocalDate, MatchedDataPair> entry2 : pairMap.entrySet()) {
				if(entry.getKey().equals(entry2.getKey())) {
						if (xValues.containsKey(entry.getKey().getYear()))
						{
							double tempGoal = xValues.get(entry.getKey().getYear());
							double tempTemp = yValues.get(entry.getKey().getYear());
							tempGoal += entry.getValue().getXValue();
							tempTemp += entry.getValue().getYValue();
							xValues.put(entry.getKey().getYear(), tempGoal);
							yValues.put(entry.getKey().getYear(), tempTemp);
							int addXNumber = numbersOfPairs.get(entry.getKey().getYear());
							addXNumber ++;
							
							numbersOfPairs.put(entry.getKey().getYear(), addXNumber);
							
							
						}
						else
						{
							xValues.put(entry.getKey().getYear(), entry.getValue().getXValue());
							yValues.put(entry.getKey().getYear(), entry.getValue().getXValue());
							numbersOfPairs.put(entry.getKey().getYear(), 1);
						}
//					}

				}

			}

		}
		loopMap = new HashMap<String, MatchedDataPair>();
		for (int x = 1900; x < 2016; x++)
		{
			if (xValues.containsKey(x))
			{
				double temp = xValues.get(x);
				temp = temp / numbersOfPairs.get(x);
				xValues.put(x, temp);
				temp = yValues.get(x);
				temp = temp / numbersOfPairs.get(x);
				yValues.put(x, temp);
				MatchedDataPair tempPair = new MatchedDataPair(xValues.get(x), yValues.get(x));
				loopMap.put("År: " + x , tempPair);
			}
		
		}
		return loopMap;
	}
}
