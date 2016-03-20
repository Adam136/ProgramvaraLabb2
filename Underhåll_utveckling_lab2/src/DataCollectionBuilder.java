import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataCollectionBuilder {

	private DataSource xData;
	private DataSource yData;
	private Resolution resolution;
	private Map<String, List<MatchedDataPair>> resultData;
	private Map<String, MatchedDataPair> finalResult;
	private DataCollection dataCollection;
	private String title;
	
	public DataCollectionBuilder(DataSource xData, DataSource yData, Resolution resolution) {
		
		this.xData = xData;
		this.yData = yData;
		this.resolution = resolution;
		
		addToResult(xData, yData);
	}
	
	public void setTitle(String title) {
		
		this.title = title;
	}
	
	public String getTitle() {
		
		return title;
	}
	
	public void addToResult(DataSource x, DataSource y) {
				
		finalResult = new HashMap<String, MatchedDataPair>();
		int ordning = 0;
		title = "Testet";
		Map<LocalDate, MatchedDataPair> pairMap = new HashMap<LocalDate, MatchedDataPair>();
		
		for (Map.Entry<LocalDate, Double> entry : xData.getValues().entrySet()) {
			for (Map.Entry<LocalDate, Double> entry2 : yData.getValues().entrySet()) {
				if(entry.getKey().equals(entry2.getKey())) {
					
					double tempX = entry.getValue();
					double tempY = entry2.getValue();
					MatchedDataPair tempPair = new MatchedDataPair(tempX, tempY);
					
					if(resolution == Resolution.DAY) {
						finalResult.put("Värde nr: " + ordning + " ", tempPair);
					}
					
					else if(resolution != Resolution.DAY) {
						
						pairMap.put(entry.getKey(), tempPair);
					}
					
					ordning++;
				}
			}
		}
		
		if(resolution == Resolution.MONTH) {
			ResMonth resMonth = new ResMonth(pairMap);
			finalResult = resMonth.getMap();
		}
		
		if(resolution == Resolution.QUARTER) {
			ResQuarter resQuarter = new ResQuarter(pairMap);
			finalResult = resQuarter.getMap();
		}
		if(resolution == Resolution.YEAR) {
			ResYear resYear = new ResYear(pairMap);
			finalResult = resYear.getMap();
		}
		
			
		dataCollection = new DataCollection(title, xData.getUnit(), yData.getUnit(), finalResult);
	}
	
	public DataCollection getResult() {
		
		return dataCollection;
	}
}
