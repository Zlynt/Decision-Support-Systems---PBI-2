package sadFase12;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

public class TASKDATA {
	protected static final String projectPath = "C:\\\\sad\\\\implementacao";
	protected static final String csv_path = projectPath + "\\CSV";
	protected static final String arff_path = projectPath + "\\ARFF";
	protected String taskdata_name;
	private Boolean enableDebug;

	// enableDebug -> Show extra debug info about methods
	public TASKDATA(int taskdata_number, boolean enableDebug) {
		this.taskdata_name = "TASKDATA" + taskdata_number;
		this.enableDebug = enableDebug;
	}

	public void generateCSV() throws KettleException {
		System.out.println("[" + taskdata_name + "] Generating CSV file...");
		KettleEnvironment.init();
		TransMeta transMeta = new TransMeta(projectPath + "\\" + taskdata_name + ".ktr");
		Trans trans = new Trans(transMeta); // create new transformation object
		trans.execute(null);
		trans.waitUntilFinished();
		System.out.println("[" + taskdata_name + "] CSV saved to " + csv_path + "\\" + taskdata_name + ".csv");
	}

	// Check if CSV file exists
	protected boolean csv_exists() {
		File tmpDir = new File(csv_path + "\\" + taskdata_name + ".csv");
		return tmpDir.exists();
	}

	// Check if arff file exists
	protected boolean arff_exists() {
		File tmpDir = new File(arff_path + "\\" + taskdata_name + ".arff");
		return tmpDir.exists();
	}

	// Load CSV file
	protected Instances read_csv() throws Exception {
		if (!csv_exists())
			throw new Exception(taskdata_name + ".csv does not exist!");

		CSVLoader csv = new CSVLoader();
		try {
			csv.setSource(new File(csv_path + "\\" + taskdata_name + ".csv"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Instances trainSet = null;
		try {
			trainSet = csv.getDataSet();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new Exception(e.toString());
		}
		return trainSet;
	}

	// Load the ARFF file
	public Instances load_arff() throws Exception {
		if (!arff_exists())
			throw new Exception(taskdata_name + ".arff does not exist!");

		BufferedReader reader = new BufferedReader(new FileReader(arff_path + "\\" + taskdata_name + ".arff"));
		ArffReader arff = new ArffReader(reader);

		if (enableDebug)
			System.out.println("[DEBUG] " + arff_path + "\\" + taskdata_name + ".arff was loaded!");

		return arff.getData();
	}

	// Save the arff file
	protected void save_arff(Instances instances) throws IOException {
		ArffSaver saver = new ArffSaver();
		saver.setInstances(instances);
		saver.setFile(new File(arff_path + "\\" + taskdata_name + ".arff"));
		saver.writeBatch();

		if (enableDebug)
			System.out.println("[DEBUG] ARFF saved on " + arff_path + "\\" + taskdata_name + ".arff");
	}

	// Mine the association rules
	public String apriori_mine_association_rules(Instances instances) throws Exception {

		Apriori model = new Apriori();
		model.buildAssociations(instances);

		return model.toString();
	}

	// Mine the association rules
	public String apriori_mine_association_rules(Instances instances, double lowerBoundMinSupport) throws Exception {

		Apriori model = new Apriori();
		model.setLowerBoundMinSupport(lowerBoundMinSupport);
		model.buildAssociations(instances);

		return model.toString();
	}

	// Mine the association rules
	public String apriori_mine_association_rules(Instances instances, double lowerBoundMinSupport, double minSupport) throws Exception {

		Apriori model = new Apriori();
		model.setLowerBoundMinSupport(lowerBoundMinSupport);
		model.setMinMetric(minSupport);
		model.buildAssociations(instances);

		return model.toString();
	}

}
