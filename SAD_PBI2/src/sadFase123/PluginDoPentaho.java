package sadFase123;

import weka.associations.Apriori;
import weka.core.Instances;

public class PluginDoPentaho {

	private TASKDATA1 taskData1;
	private TASKDATA2 taskData2;
	private TASKDATA3 taskData3;
	private TASKDATA4 taskData4;

	public PluginDoPentaho() {

		taskData1 = new TASKDATA1();
		taskData2 = new TASKDATA2();
		taskData4 = new TASKDATA4();
		taskData3 = new TASKDATA3();
	}

	public String getTASKDATA1Rules() {
		try {
			System.out.println("[TASKDATA1] Generating CSV...");
			taskData1.generateCSV();
			System.out.println("[TASKDATA1] CSV generated...");

			System.out.println("[TASKDATA1] Converting the CSV to ARFF...");
			taskData1.csvToArff();
			System.out.println("[TASKDATA1] ARFF saved...");

			System.out.println("[TASKDATA1] Mining association...");
			Instances instances = taskData1.load_arff();
			Apriori apriori = new Apriori();
			apriori.setLowerBoundMinSupport(0.01);
			apriori.setMinMetric(0.7);
			apriori.buildAssociations(instances);
			System.out.println("[TASKDATA1] Associations rule mined!");

			return apriori.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	public String getTASKDATA2Rules() {

		try {
			System.out.println("[TASKDATA2] Generating CSV...");
			taskData2.generateCSV();
			System.out.println("[TASKDATA2] CSV generated.");

			System.out.println("[TASKDATA2] Converting the CSV to ARFF...");
			taskData2.csvToArff();
			System.out.println("[TASKDATA2] ARFF saved.");

			System.out.println("[TASKDATA2] Mining association...");
			Instances instances = taskData2.load_arff();

			Apriori apriori = new Apriori();
			apriori.setLowerBoundMinSupport(0.01);
			apriori.setMinMetric(0.7);
			apriori.buildAssociations(instances);
			System.out.println("TASKDATA2 Association rules mined!");
			return apriori.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	public String getTASKDATA3Rules() {
		try {

			// Generate the CSV file and save it
			System.out.println("[TASKDATA3] Generating CSV...");
			taskData3.generateCSV();
			System.out.println("[TASKDATA3] CSV generated.");
			// debug += "CSV_GENERATED ";

			// Transform CSV into ARFF and save it
			System.out.println("[TASKDATA3] Converting the CSV to ARFF...");
			taskData3.csvToArff();
			System.out.println("[TASKDATA3] ARFF saved.");
			// debug += "CSV_TO_ARFF ARFF_SAVED";

			System.out.println("[TASKDATA3] Mining associations...");
			Instances instances = taskData3.load_arff();

			Apriori apriori = new Apriori();
			apriori.setLowerBoundMinSupport(0.01);
			apriori.setMinMetric(0.7);
			apriori.buildAssociations(instances);
			System.out.println("[TASKDATA3] Association rules mined!");

			return apriori.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
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
