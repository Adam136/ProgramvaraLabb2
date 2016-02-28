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
		Map<Month, MatchedDataPair> pairMap = new HashMap<Month, MatchedDataPair>();
		
		for (Map.Entry<LocalDate, Double> entry : xData.getValues().entrySet()) {
			for (Map.Entry<LocalDate, Double> entry2 : yData.getValues().entrySet()) {
				if(entry.getKey().equals(entry2.getKey())) {
					
					double tempX = entry.getValue();
					double tempY = entry2.getValue();
					MatchedDataPair tempPair = new MatchedDataPair(tempX, tempY);
					
					if(resolution == Resolution.DAY) {
						finalResult.put("Värde nr: " + ordning + " ", tempPair);
					}
					
					if(resolution == Resolution.MONTH) {
						
						pairMap.put(entry.getKey().getMonth(), tempPair);
					}
					
					ordning++;
				}
			}
		}
		
		if(resolution == Resolution.MONTH) {
			
			for (Map.Entry<Month, MatchedDataPair> entry : pairMap.entrySet()) {
				for (Map.Entry<Month, MatchedDataPair> entry2 : pairMap.entrySet()) {
					if(entry.getKey().equals(entry2.getKey())) {
						//TO-DO
					}
				}
			}
		}
			
		dataCollection = new DataCollection(title, xData.getUnit(), yData.getUnit(), finalResult);
	}
	
	public DataCollection getResult() {
		
		return dataCollection;
	}
}
