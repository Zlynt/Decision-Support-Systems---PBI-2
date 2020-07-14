package sadFase123;

import weka.associations.Apriori;
import weka.core.Instances;

public class PluginDoPentaho {
	private TASKDATA4 taskData4;

	public PluginDoPentaho() {
		taskData4 = new TASKDATA4();
	}

	public String getStuff() {
		return "hum,";
	}

	public String getTASKDATA1Rules() {
		return "I'm a Dummy Rule from TASKDATA1";
	}

	public String getTASKDATA2Rules() {
		return "I'm a Dummy Rule from TASKDATA2";
	}

	public String getTASKDATA3Rules() throws Exception {
		// Generate the CSV file

		String debug = "";

		return debug;
	}

	public Apriori getTASKDATA4Rules() {
		String debug = "";
		Apriori apriori = null;
		try {

			// Generate the CSV file and save it
			System.out.println("[TASKDATA4] Generating CSV...");
			taskData4.generateCSV();
			System.out.println("[TASKDATA4] CSV generated.");
			debug += "CSV_GENERATED ";

			// Transform CSV into ARFF and save it
			System.out.println("[TASKDATA4] Converting the CSV to ARFF...");
			taskData4.csvToArff();
			System.out.println("[TASKDATA4] ARFF saved.");
			debug += "CSV_CONVERTED ARFF_SAVED ";
			
			System.out.println("[TASKDATA4] Mining associations...");
			Instances instances = taskData4.load_arff();
			debug += "GOT_INSTANCES ";
			
			apriori = new Apriori(); //Isto é a causa dos problemas. O Pentaho Server não se dá com o Apriori
			apriori.setLowerBoundMinSupport(0.01);
			apriori.buildAssociations(instances);
			//String associationRules = model.toString();
			System.out.println("[TASKDATA4] Association rules mined!1");
			debug += "RULES_MINED ";
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.toString());
			debug += "MESSED_UP";
		}

		return apriori;
	}
}
