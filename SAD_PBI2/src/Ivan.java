import weka.associations.Apriori;
import weka.core.Instances;

public class Ivan {
	public Ivan() {
		
	}
	
	public void executar_codigo() {
		TASKDATA4 taskdata4 = new TASKDATA4();
		try {
			System.out.println("[TASKDATA4] Processing the CSV input...");
			taskdata4.save_arff(taskdata4.csv_to_instances());
			
			System.out.println("[TASKDATA4] Loading arff...");
			Instances taskdata4_instances = taskdata4.load_arff();

			System.out.println("[TASKDATA4] Mining association rules...");
			System.out.println(taskdata4.apriori_mine_association_rules(taskdata4_instances));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
