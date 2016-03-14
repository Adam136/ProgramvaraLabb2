
public class ClientProgram {

	public static void main(String[] args) {
		
		FootballGoalsSource goals = new FootballGoalsSource();
//		SineWave sine = new SineWave();
		SMHITest smhi = new SMHITest();
		
		DataCollectionBuilder dcb = new DataCollectionBuilder(goals, smhi, Resolution.DAY);
		
		System.out.println(dcb.getResult().getData());
	}
}
