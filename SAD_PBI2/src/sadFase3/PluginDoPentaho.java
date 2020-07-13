package sadFase3;

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

	public String getTASKDATA4Rules() {
		String debug = "";

		try {

			// Generate the CSV file and save it
			System.out.println("[TASKDATA4] Generating CSV...");
			taskData4.generateCSV();
			System.out.println("[TASKDATA4] CSV generated.");
			debug += "CSV_GENERATED ";

			// Transform CSV into ARFF and save it
			//System.out.println("[TASKDATA4] Converting the CSV to ARFF...");
			//taskData4.csvToArff();
			//System.out.println("[TASKDATA4] ARFF saved.");
			//debug += "CSV_CONVERTED ARFF_SAVED ";
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			debug += "MESSED_UP";
		}

		return debug;
	}
}
