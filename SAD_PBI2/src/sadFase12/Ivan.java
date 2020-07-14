package sadFase12;
import java.io.PrintWriter;

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

			taskdata3.generateCSV();
			
			System.out.println("[TASKDATA3] Processing the CSV input...");
			taskdata3.save_arff(taskdata3.csv_to_instances());

			System.out.println("[TASKDATA3] Loading arff...");
			Instances taskdata3_instances = taskdata3.load_arff();

			System.out.print("[TASKDATA3] Mining association rules...");
			String taskData3MinedAssociationRules = taskdata3.apriori_mine_association_rules(taskdata3_instances, 0.01, 0.7);
			System.out.println("done!");
			System.out.println(taskData3MinedAssociationRules);
			
			
			
			
			
			// Task Data 4
			
			taskdata4.generateCSV();
			
			System.out.println("[TASKDATA4] Processing the CSV input...");
			taskdata4.save_arff(taskdata4.csv_to_instances());

			System.out.println("[TASKDATA4] Loading arff...");
			Instances taskdata4_instances = taskdata4.load_arff();

			System.out.println("[TASKDATA4] Mining association rules...");
			String taskData4MinedAssociationRules = taskdata4.apriori_mine_association_rules(taskdata4_instances, 0.01);
			System.out.println(taskData4MinedAssociationRules);
			
			
			PrintWriter out = new PrintWriter("Ivan_Mined_Associations_Debug.txt");
			out.println("===== TASKDATA3 =====");
			out.println("Pergunta de negocio: Qual a relação entre os produtos comprados e os clientes que as compram?");
			out.println("=====================");
			out.println(taskData3MinedAssociationRules); 
			out.println();
			out.println("===== TASKDATA4 =====");
			out.println("Pergunta de negocio: Qual a relação entre produtos nos EUA (Market Basket Analisys)?");
			out.println("=====================");
			out.println(taskData4MinedAssociationRules);
			out.close();			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
