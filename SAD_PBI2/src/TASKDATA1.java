import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.Collator;
import java.util.Comparator;
import java.util.LinkedList;

import TASKDATA1.ListTuple;

import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.ArffLoader.ArffReader;

public class TASKDATA1 extends TASKDATA {

	// TASKDATA Constructor
	public TASKDATA1() {
		super(1, false); // TASKDATA1; The 1 number goes from the TASKDATA number
	}

	public Instances csv_to_instances() throws Exception {
		if (!csv_exists())
			throw new Exception(taskdata_name + ".csv does not exist!");

		// Read CSV
		Instances trainSet = read_csv();

		String tmp_data_loaded = trainSet.toString();
		while (tmp_data_loaded.contains(" '"))
			tmp_data_loaded = tmp_data_loaded.replace(" '", "'");

		tmp_data_loaded = tmp_data_loaded.replace("@relationTASKDATA1", "");
		tmp_data_loaded = tmp_data_loaded.replace("@relationTASKDATA1", "");
		String[] data_loaded = tmp_data_loaded.split("\n");

		boolean read_data = false;
		ListTuple listTuple = new ListTuple();

		for (int i = 0; i < data_loaded.length; i++) {
			if (read_data) {
				String tmp_productcode = data_loaded[i].split(",")[0];
				String tmp_dealsize = data_loaded[i].split(",")[1].replace("'", "");
				String tmp_quarter = data_loaded[i].split(",")[2];
				// System.out.println(tmp_productcode + ", " + tmp_dealsize + ", " +
				// tmp_quarter);

				listTuple.addTuple(tmp_productcode, tmp_dealsize, tmp_quarter);
				listTuple.addProductCode(tmp_productcode);
				listTuple.addDealSize(tmp_dealsize);
				listTuple.addQtrID(tmp_quarter);

			}

			if (data_loaded[i].contains("@data")) {
				read_data = true;
			}

		}

		String arff = "";
		arff += "@relation TASKDATA1\n\n"; // The format for the arff TAKSDATA1

		arff += "@attribute PRODUCTCODE {";
		for (int i = 0; i < listTuple.getProductCodeList().size(); i++) {
			arff += listTuple.getProductCodeList().get(i) + ",";
		}
		arff = arff.substring(0, arff.length() - 1); // remove last comma
		arff += "}\n";

		String[] arrayList = {"?","'ThisDealsize'"};
		for (int i = 0; i < listTuple.getDealSizeList().size(); i++) {
			arff += "@attribute 'DEALSIZE=" + listTuple.getDealSizeList().get(i) + "' {" + arrayList[1] + "}\n";
			// System.out.println(listTuple.getDealSizeList().get(i));
		}

		String[] arrayList2 = {"?","'ThisQTR'"};
		for (int i = 0; i < listTuple.getQtrIDList().size(); i++) {
			arff += "@attribute 'QTR_ID=" + listTuple.getQtrIDList().get(i) + "' {" + arrayList2[1] + "}\n";
		}

		arff += "\n@data\n";
		//System.out.println(listTuple.getTuple().size());
		//System.out.println(listTuple.getTuple());
		for (int i = 0; i < listTuple.getTuple().size(); i++) {
			arff+=listTuple.getTuple().get(i).getProductCode()+",";
			for (int j = 0; j < listTuple.getDealSizeList().size(); j++) {
				if(listTuple.getTuple().get(i).getDealSize().compareTo(listTuple.getDealSizeList().get(j)) == 0)
					arff+=arrayList[1];
				else 
					arff+=arrayList[0];
				arff+=",";
			}
			for (int k = 0; k < listTuple.getQtrIDList().size(); k++) {
				if(listTuple.getTuple().get(i).getQtr_Id().compareTo(listTuple.getQtrIDList().get(k)) == 0)
					arff+=arrayList2[1];
				else 
					arff+=arrayList2[0];
				arff+=",";	
			}
			arff = arff.substring(0, arff.length() - 1); // remove last comma
			arff += "\n";
		}
		//System.out.println(arff);

		Reader inputString = new StringReader(arff);
		BufferedReader reader = new BufferedReader(inputString);
		ArffReader arff_reader = new ArffReader(reader);
		return arff_reader.getData();

	}

}
