import weka.associations.Apriori;
import weka.core.Instances;

public class Ivan {
	public Ivan() {

	}

	public void executar_codigo() {
		TASKDATA3 taskdata3 = new TASKDATA3();
		TASKDATA4 taskdata4 = new TASKDATA4();

		try {
			
			
			
			
			// Task Data 3
			System.out.println("[TASKDATA3] Processing the CSV input...");
			taskdata3.save_arff(taskdata3.csv_to_instances());

			System.out.println("[TASKDATA3] Loading arff...");
			Instances taskdata3_instances = taskdata3.load_arff();

			System.out.print("[TASKDATA3] Mining association rules...");
			String association_rules = taskdata3.apriori_mine_association_rules(taskdata3_instances);
			System.out.println("done!");
			System.out.println(association_rules);
			
			
			
			
			/*
			// Task Data 4
			System.out.println("[TASKDATA4] Processing the CSV input...");
			taskdata4.save_arff(taskdata4.csv_to_instances());

			System.out.println("[TASKDATA4] Loading arff...");
			Instances taskdata4_instances = taskdata4.load_arff();

			System.out.println("[TASKDATA4] Mining association rules...");
			System.out.println(taskdata4.apriori_mine_association_rules(taskdata4_instances));
			*/
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
