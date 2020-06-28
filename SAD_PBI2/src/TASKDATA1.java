import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.Collator;
import java.util.Comparator;
import java.util.LinkedList;

import TASKDATA3.TransactionList;
import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.ArffLoader.ArffReader;

public class TASKDATA1 extends TASKDATA{

	//TASKDATA Constructor
	public TASKDATA1() {
		super(1); //TASKDATA1; The 1 number goes from the TASKDATA number
	}
	
	public Instances csv_to_instances() throws Exception {
		if (!csv_exists())
			throw new Exception(taskdata_name + ".csv does not exist!");

		// Read CSV
		Instances trainSet = read_csv();
		
		String tmp_data_loaded = trainSet.toString();
		while (tmp_data_loaded.contains(" '"))
			tmp_data_loaded = tmp_data_loaded.replace(" '", "'");
		
		tmp_data_loaded = tmp_data_loaded.replace("@relationTASKDATA2", "");
		tmp_data_loaded = tmp_data_loaded.replace("@relationTASKDATA2", "");
		String[] data_loaded = tmp_data_loaded.split("\n");
		
		boolean read_data = false;
		TransactionList transactionList = new TransactionList();
		
		Reader inputString = new StringReader(transactionList.toARFF());
		BufferedReader reader = new BufferedReader(inputString);
		ArffReader arff = new ArffReader(reader);
		return arff.getData();
	}

}
