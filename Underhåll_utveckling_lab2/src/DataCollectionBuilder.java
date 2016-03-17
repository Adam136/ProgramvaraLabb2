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
						finalResult.put("V�rde nr: " + ordning + " ", tempPair);
					}
					
					if(resolution == Resolution.MONTH) {
						
						pairMap.put(entry.getKey().getMonth(), tempPair);
					}
					
					ordning++;
				}
			}
		}
		
		if(resolution == Resolution.MONTH) {
			
			double[] xMonthValues = new double[12];
			double[] yMonthValues = new double[12];
			double[] xNumberOfValues = new double[12];
			double[] yNumberOfValues = new double[12];
			
			for (Map.Entry<Month, MatchedDataPair> entry : pairMap.entrySet()) {
				for (Map.Entry<Month, MatchedDataPair> entry2 : pairMap.entrySet()) {
					if(entry.getKey().equals(entry2.getKey())) {
						if(entry.getKey().equals("JANUARY")){
							xMonthValues[0] = entry.getValue().getXValue();
							xNumberOfValues[0] ++;
							yMonthValues[0] = entry.getValue().getYValue();
							yNumberOfValues[0] ++;							
						}
						if(entry.getKey().equals("FEBRUARY")){
							xMonthValues[1] = entry.getValue().getXValue();
							xNumberOfValues[1] ++;
							yMonthValues[1] = entry.getValue().getYValue();
							yNumberOfValues[1] ++;
						}
						if(entry.getKey().equals("MARCH")){
							xMonthValues[2] = entry.getValue().getXValue();
							xNumberOfValues[2] ++;
							yMonthValues[2] = entry.getValue().getYValue();
							yNumberOfValues[2] ++;
						}
						if(entry.getKey().equals("APRIL")){
							xMonthValues[3] = entry.getValue().getXValue();
							xNumberOfValues[3] ++;
							yMonthValues[3] = entry.getValue().getYValue();
							yNumberOfValues[3] ++;
						}
						if(entry.getKey().equals("MAY")){
							xMonthValues[4] = entry.getValue().getXValue();
							xNumberOfValues[4] ++;
							yMonthValues[4] = entry.getValue().getYValue();
							yNumberOfValues[4] ++;
						}
						if(entry.getKey().equals("JUNE")){
							xMonthValues[5] = entry.getValue().getXValue();
							xNumberOfValues[5] ++;
							yMonthValues[5] = entry.getValue().getYValue();
							yNumberOfValues[5] ++;
						}
						if(entry.getKey().equals("JULY")){
							xMonthValues[6] = entry.getValue().getXValue();
							xNumberOfValues[6] ++;
							yMonthValues[6] = entry.getValue().getYValue();
							yNumberOfValues[6] ++;
						}
						if(entry.getKey().equals("AUGUST")){
							xMonthValues[7] = entry.getValue().getXValue();
							xNumberOfValues[7] ++;
							yMonthValues[7] = entry.getValue().getYValue();
							yNumberOfValues[7] ++;
						}
						if(entry.getKey().equals("SEPTEMBER")){
							xMonthValues[8] = entry.getValue().getXValue();
							xNumberOfValues[8] ++;
							yMonthValues[8] = entry.getValue().getYValue();
							yNumberOfValues[8] ++;
						}
						if(entry.getKey().equals("OCTOBER")){
							xMonthValues[9] = entry.getValue().getXValue();
							xNumberOfValues[9] ++;
							yMonthValues[9] = entry.getValue().getYValue();
							yNumberOfValues[9] ++;
						}
						if(entry.getKey().equals("NOVEMBER")){
							xMonthValues[10] = entry.getValue().getXValue();
							xNumberOfValues[10] ++;
							yMonthValues[10] = entry.getValue().getYValue();
							yNumberOfValues[10] ++;
						}
						if(entry.getKey().equals("DECEMBER")){
							xMonthValues[11] = entry.getValue().getXValue();
							xNumberOfValues[11] ++;
							yMonthValues[11] = entry.getValue().getYValue();
							yNumberOfValues[11] ++;
						}
					}
				}
			}
			
			for(int k = 0; k < 12; k++) {
				
				xMonthValues[k] = xMonthValues[k] / xNumberOfValues[k];
				yMonthValues[k] = yMonthValues[k] / yNumberOfValues[k];
				MatchedDataPair tempPair = new MatchedDataPair(xMonthValues[k], yMonthValues[k]);
				System.out.print(tempPair);
				finalResult.put("Värde nr: " + k , tempPair);
			}
		}
		
			
		dataCollection = new DataCollection(title, xData.getUnit(), yData.getUnit(), finalResult);
	}
	
	public DataCollection getResult() {
		
		return dataCollection;
	}
}
