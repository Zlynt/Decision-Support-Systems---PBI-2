package sadFase12;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.text.Collator;
import java.util.Comparator;
import java.util.LinkedList;

import TASKDATA3.AttributeList;
import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.ArffLoader.ArffReader;

public class TASKDATA3 extends TASKDATA {

	// TASKDATA Constructor
	public TASKDATA3() {
		super(3, false); // TASKDATA3; The 3 number goes from the TASKDATA number
	}

	// Convert CSV to instances by converting csv to arff and then to instances
	public Instances csv_to_instances() throws Exception {
		if (!csv_exists())
			throw new Exception(taskdata_name + ".csv does not exist!");

		// Read CSV
		Instances trainSet = read_csv();

		// #region Convert the received data into a matrix with all products and tid's
		String tmp_data_loaded = trainSet.toString();
		while (tmp_data_loaded.contains(" '"))
			tmp_data_loaded = tmp_data_loaded.replace(" '", "'");

		tmp_data_loaded = tmp_data_loaded.replace("@relationTASKDATA3", "");
		tmp_data_loaded = tmp_data_loaded.replace("@relationTASKDATA3", "");
		String[] data_loaded = tmp_data_loaded.split("\n");

		boolean read_data = false;
		AttributeList productList = new AttributeList();
		AttributeList clientList = new AttributeList();
		String arff_data = "";

		for (int i = 0; i < data_loaded.length; i++) {

			if (read_data) {
				String tmp_tid = data_loaded[i].split(",")[0];
				String tmp_prod = data_loaded[i].split(",")[1];
				String tmp_client = data_loaded[i].split(",")[2];

				productList.addAttribute(tmp_prod);
				clientList.addAttribute(tmp_client);
				arff_data += tmp_prod + "," + tmp_client + "\n";
			}

			if (data_loaded[i].contains("@data")) {
				read_data = true;
			}
		}

		String arff_file = "@relation TASKDATA3\n\n";
		
		arff_file += "@attribute PRODUCT {";
		for (int i = 0; i < productList.getAttributeList().size(); i++) {
			arff_file += productList.getAttributeList().get(i)+",";
		}
		arff_file = arff_file.substring(0, arff_file.length() - 1);
		arff_file += "}\n";

		arff_file += "@attribute CLIENT {";
		for (int i = 0; i < clientList.getAttributeList().size(); i++) {
			arff_file += clientList.getAttributeList().get(i)+",";
		}
		arff_file = arff_file.substring(0, arff_file.length() - 1);
		arff_file += "}\n";
		
		arff_file += "\n@data\n"+arff_data;

		//System.out.println(arff_file);
		Reader inputString = new StringReader(arff_file);
		BufferedReader reader = new BufferedReader(inputString);
		ArffReader arff = new ArffReader(reader);
		return arff.getData();
		// return null;

	}

}
