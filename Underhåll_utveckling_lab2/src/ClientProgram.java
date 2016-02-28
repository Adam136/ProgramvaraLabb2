
public class ClientProgram {

	public static void main(String[] args) {
		
		FootballGoalsSource goals = new FootballGoalsSource();
		SineWave sine = new SineWave();
		
		DataCollectionBuilder dcb = new DataCollectionBuilder(goals, sine, Resolution.DAY);
		
		System.out.println(dcb.getResult().getData());
	}
}
