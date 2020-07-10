package sad;

import java.io.IOException;

import org.pentaho.di.core.exception.KettleException;

import weka.core.Instances;

public class PluginDoPentaho {

	public String getStuff() {
		return "hum,";
	}

	public String getRegrasDeAssociacao(String taskdataNumber) {
		String output = "";

		try {
			switch (taskdataNumber) {
			case "1":
				TASKDATA1 taskdata1 = new TASKDATA1();
				taskdata1.generateCSV();
				taskdata1.save_arff(taskdata1.csv_to_instances());
				Instances taskdata1_instances = taskdata1.load_arff();
				output = taskdata1.apriori_mine_association_rules(taskdata1_instances);
				break;
			case "2":
				TASKDATA2 taskdata2 = new TASKDATA2();
				taskdata2.generateCSV();
				taskdata2.save_arff(taskdata2.csv_to_instances());
				Instances taskdata2_instances = taskdata2.load_arff();
				output = taskdata2.apriori_mine_association_rules(taskdata2_instances);
				break;
			case "3":
				TASKDATA3 taskdata3 = new TASKDATA3();
				taskdata3.generateCSV();
				taskdata3.save_arff(taskdata3.csv_to_instances());
				Instances taskdata3_instances = taskdata3.load_arff();
				output = taskdata3.apriori_mine_association_rules(taskdata3_instances, 0.01, 0.7);
				break;
			case "4":
				TASKDATA4 taskdata4 = new TASKDATA4();
				taskdata4.generateCSV();
				taskdata4.save_arff(taskdata4.csv_to_instances());
				Instances taskdata4_instances = taskdata4.load_arff();
				output = taskdata4.apriori_mine_association_rules(taskdata4_instances, 0.01);
				break;
			default:
				break;
			}

			if (output != "") {
				output = output.split("Best rules found:\n")[1];
				String tmp_output = output;
				//for (int i = 0; i < output.split("\n").length; i++) {
					//String regra = output.split("\n")[i].split(" lift:")[0];
					//tmp_output += regra.replace(" " + i + ". ", "") + "\n";
				//}

				output = tmp_output;
			}
			return output;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return output;
		}
	}
}
