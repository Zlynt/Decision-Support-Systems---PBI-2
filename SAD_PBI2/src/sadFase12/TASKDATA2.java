package sadFase12;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.Collator;
import java.util.Comparator;
import java.util.LinkedList;

import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.ArffLoader.ArffReader;

import TASKDATA2.ListTuple;

public class TASKDATA2 extends TASKDATA {

	// TASKDATA Constructor
	public TASKDATA2() {
		super(2, false); // TASKDATA2; The 2 number goes from the TASKDATA number
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
		ListTuple listTuple = new ListTuple();

		for (int i = 0; i < data_loaded.length; i++) {
			if (read_data) {
				String tmp_productline = data_loaded[i].split(",")[0].replace("'", "");
				if (tmp_productline.compareTo("Classic Cars") == 0)
					tmp_productline = "Classic_Cars";
				if (tmp_productline.compareTo("Trucks and Buses") == 0)
					tmp_productline = "Trucks_and_Buses";
				if (tmp_productline.compareTo("Vintage Cars") == 0)
					tmp_productline = "Vintage_Cars";
				String tmp_country = data_loaded[i].split(",")[1].replace("'", "");
				//System.out.println(tmp_productline + ", " + tmp_country);

				listTuple.addTuple(tmp_productline, tmp_country);
				listTuple.addProductLine(tmp_productline);
				listTuple.addCountry(tmp_country);

			}

			if (data_loaded[i].contains("@data")) {
				read_data = true;
			}
		}

		String arff = "";
		arff += "@relation TASKDATA2\n\n"; // The format for the arff TAKSDATA1

		String[] arrayList = { "?", "'ThisProductLine'" };
		for (int i = 0; i < listTuple.getProductLineList().size(); i++) {
			arff += "@attribute 'PRODUCTLINE=" + listTuple.getProductLineList().get(i) + "' {" + arrayList[1] + "}\n";
		}
		
		String[] arrayList2 = { "?", "'ThisCountry'" };
		for (int i = 0; i < listTuple.getCountryList().size(); i++) {
			arff += "@attribute 'COUNTRY=" + listTuple.getCountryList().get(i) + "' {" + arrayList2[1] + "}\n";
		}
		//System.out.println(arff);
		
		arff+="\n@data\n";
		//System.out.println(listTuple.getTuple().size());
		//System.out.println(listTuple.getTuple());
		for (int i = 0; i < listTuple.getTuple().size(); i++)
		{
			for (int j = 0; j < listTuple.getProductLineList().size(); j++)
			{
				if(listTuple.getTuple().get(i).getProductLine().compareTo(listTuple.getProductLineList().get(j)) == 0)
					arff+=arrayList[1];
				else 
					arff+=arrayList[0];
				arff+=",";
			}
			for (int k = 0; k < listTuple.getCountryList().size(); k++)
			{
				if(listTuple.getTuple().get(i).getCountry().compareTo(listTuple.getCountryList().get(k)) == 0)
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
//		return null;
	}

}
