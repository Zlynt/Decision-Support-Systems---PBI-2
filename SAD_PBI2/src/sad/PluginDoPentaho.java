package sad;

import java.io.File;
import java.io.IOException;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

import TASKDATA3.AttributeList;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

public class PluginDoPentaho {
	protected static final String projectPath = "C:\\\\sad\\\\implementacao";
	protected static final String csv_path = projectPath + "\\CSV";
	protected static final String arff_path = projectPath + "\\ARFF";

	public PluginDoPentaho() {

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
		TASKDATA3 taskdata3 = new TASKDATA3();

		taskdata3.generateCSV();

		System.out.println("[TASKDATA3] Processing the CSV input...");

		System.out.println("[TASKDATA3] Loading arff...");
		Instances taskdata3_instances = taskdata3.load_arff(taskdata3.csv_to_instances().toString());

		System.out.print("[TASKDATA3] Mining association rules...");
		String taskData3MinedAssociationRules = taskdata3.apriori_mine_association_rules(taskdata3.csv_to_instances(),
				0.01, 0.7);
		System.out.println("done!");

		return taskData3MinedAssociationRules;
	}

	public String getTASKDATA4Rules() {
		//Generate the CSV file
		
		
		String debug = "";
		
		
		return debug;
	}
}
