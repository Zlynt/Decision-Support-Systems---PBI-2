import weka.core.Instances;

public class Josue {
	public Josue() {

	}
	
	public void executar_codigo() {
		TASKDATA1 taskdata1 = new TASKDATA1();
		TASKDATA2 taskdata2 = new TASKDATA2();
		
		try {
			
			taskdata1.generateCSV();
//			taskdata1.csv_to_instances();
			System.out.println("[TASKDATA1] Processing the CSV input...");
			taskdata1.save_arff(taskdata1.csv_to_instances());
			
			System.out.println("[TASKDATA1] Loading arff...");
			Instances taskdata1_instances = taskdata1.load_arff();

			System.out.println("[TASKDATA1] Mining association rules...");
			System.out.println(taskdata1.apriori_mine_association_rules(taskdata1_instances, 0.01, 0.7));

			
			taskdata2.generateCSV();
//			taskdata2.csv_to_instances();
			System.out.println("[TASKDATA2] Processing the CSV input...");
			taskdata2.save_arff(taskdata2.csv_to_instances());
			
			System.out.println("[TASKDATA2] Loading arff...");
			Instances taskdata2_instances = taskdata2.load_arff();

			System.out.println("[TASKDATA2] Mining association rules...");
			//System.out.println(taskdata2.apriori_mine_association_rules(taskdata2_instances, 0.01, 0.7));
			System.out.println(taskdata2.apriori_mine_association_rules(taskdata2_instances, 0.01, 0.7));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
