import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

public class TASKDATA {
	private static final String csv_path  = "C:\\sad\\implementacao\\CSV";
	private static final String arff_path = "C:\\sad\\implementacao\\ARFF";
	protected String taskdata_name;
	
	public TASKDATA(int taskdata_number) {
		this.taskdata_name = "TASKDATA"+taskdata_number;
	}
	
	//Check if CSV file exists
	protected boolean csv_exists() {
		File tmpDir = new File(csv_path+"\\"+taskdata_name+".csv");
		return tmpDir.exists();
	}
	
	//Check if arff file exists
	protected boolean arff_exists() {
		File tmpDir = new File(arff_path+"\\"+taskdata_name+".arff");
		return tmpDir.exists();
	}
	
	//Load CSV file
	protected Instances read_csv() throws Exception {
		if(!csv_exists())
			throw new Exception(taskdata_name+".csv does not exist!");
		
		CSVLoader csv = new CSVLoader();
		try {
			csv.setSource(new File(csv_path+"\\"+taskdata_name+".csv"));
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
	

	//Load the ARFF file
	public Instances load_arff() throws Exception {
		if(!arff_exists())
			throw new Exception(taskdata_name+".arff does not exist!");
		
		BufferedReader reader = new BufferedReader(new FileReader(arff_path+"\\"+taskdata_name+".arff"));
		ArffReader arff = new ArffReader(reader);
		return arff.getData();
	}
	
	//Save the arff file
	protected void save_arff(Instances instances) throws IOException {
		ArffSaver saver = new ArffSaver();
		saver.setInstances(instances);
		saver.setFile(new File(arff_path+"\\"+taskdata_name+".arff"));
		saver.writeBatch();
	}
	
	//Mine the association rules
	public String apriori_mine_association_rules(Instances instances) throws Exception {

		Apriori model = new Apriori();
		model.buildAssociations(instances);
		
		return model.toString();
	}
}
