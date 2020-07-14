package sadFase123;

import weka.associations.Apriori;
import weka.core.Instances;

public class PluginDoPentaho {
	private TASKDATA3 taskData3;
	private TASKDATA4 taskData4;

	public PluginDoPentaho() {
		taskData4 = new TASKDATA4();
		taskData3 = new TASKDATA3();
	}

	public String getTASKDATA1Rules() {
		return "I'm a Rule from TASKDATA1";
	}

	public String getTASKDATA2Rules() {
		return "I'm a Rule from TASKDATA2";
	}

	public String getTASKDATA3Rules() throws Exception {
		// Generate the CSV file

		String debug = "";

		try {


			// Generate the CSV file and save it
			System.out.println("[TASKDATA3] Generating CSV...");
			taskData3.generateCSV();
			System.out.println("[TASKDATA3] CSV generated.");
			debug += "CSV_GENERATED ";
			
			/*
			// Task Data 3

			taskdata3.generateCSV();

			System.out.println("[TASKDATA3] Processing the CSV input...");
			taskdata3.save_arff(taskdata3.csv_to_instances());

			System.out.println("[TASKDATA3] Loading arff...");
			Instances taskdata3_instances = taskdata3.load_arff();

			System.out.print("[TASKDATA3] Mining association rules...");
			String taskData3MinedAssociationRules = taskdata3.apriori_mine_association_rules(taskdata3_instances, 0.01,
					0.7);
			System.out.println("done!");
			System.out.println(taskData3MinedAssociationRules);
			return taskData3MinedAssociationRules;*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return debug;
	}

	public String getTASKDATA4Rules() {
		try {

			// Generate the CSV file and save it
			System.out.println("[TASKDATA4] Generating CSV...");
			taskData4.generateCSV();
			System.out.println("[TASKDATA4] CSV generated.");

			// Transform CSV into ARFF and save it
			System.out.println("[TASKDATA4] Converting the CSV to ARFF...");
			taskData4.csvToArff();
			System.out.println("[TASKDATA4] ARFF saved.");

			System.out.println("[TASKDATA4] Mining associations...");
			Instances instances = taskData4.load_arff();

			Apriori apriori = new Apriori();
			apriori.setLowerBoundMinSupport(0.01);
			apriori.buildAssociations(instances);
			System.out.println("[TASKDATA4] Association rules mined!");

			return apriori.toString();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.toString());
		}

		return "";
	}
}
