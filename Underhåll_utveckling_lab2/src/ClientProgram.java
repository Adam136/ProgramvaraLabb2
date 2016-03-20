
public class ClientProgram {

	public static void main(String[] args) {
		
		FootballGoalsSource goals = new FootballGoalsSource();
//		SineWave sine = new SineWave();
		SMHITest smhi = new SMHITest();
		
		DataCollectionBuilder dcbM = new DataCollectionBuilder(goals, smhi, Resolution.MONTH);
		System.out.println(dcbM.getResult().getData());
//		
//		DataCollectionBuilder dcbD = new DataCollectionBuilder(goals, smhi, Resolution.DAY);
//		System.out.println(dcbD.getResult().getData());
		
//		DataCollectionBuilder dcbQ = new DataCollectionBuilder(goals, smhi, Resolution.QUARTER);
//		System.out.println(dcbQ.getResult().getData());
//		DataCollectionBuilder dcbY = new DataCollectionBuilder(goals, smhi, Resolution.YEAR);
//		System.out.println(dcbY.getResult().getData());
	}
	
}
