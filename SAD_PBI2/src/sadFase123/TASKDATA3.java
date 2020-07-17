package sadFase123;

import java.io.BufferedReader;
import java.io.File;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.ArffLoader.ArffReader;

public class TASKDATA3 extends TASKDATA {
	public TASKDATA3() {
		super("3");
	}

	@Override
	public boolean csvToArff() {
		// If the csv file does not exist, don't do nothing
		if (!csv_exists())
			return false;

		// Load the csv
		CSVLoader csvLoader = new CSVLoader();

		try {
			csvLoader.setSource(new File(csvFileLocation));
			// Load the csv file
			Instances instances = csvLoader.getDataSet();
			String taskdataArff = instances.toString();

			LinkedList<String> productList = new LinkedList<String>();
			LinkedList<String> clientList = new LinkedList<String>();

			boolean isData = false;
			String arffData = "";
			for (int i = 0; i < taskdataArff.split("\n").length; i++) {
				String currentLine = taskdataArff.split("\n")[i];

				if (isData) {
					String currentLineProduct = currentLine.split(",")[1];
					String currentLineClient = currentLine.split(",")[2];
					while(currentLineProduct.contains("  "))
						currentLineProduct = currentLineProduct.replace("  ", " ");

					if (!productList.contains(currentLineProduct))
						productList.add(currentLineProduct);

					if (!clientList.contains(currentLineClient))
						clientList.add(currentLineClient);

					arffData += currentLineProduct + "," + currentLineClient + "\n";
				} else {
					if (currentLine.compareTo("@data") == 0)
						isData = true;
				}
			}

			String arff_file = "@relation TASKDATA3\n\n";

			arff_file += "@attribute PRODUCT {";
			for (int i = 0; i < productList.size(); i++) {
				arff_file += productList.get(i) + ",";
			}
			arff_file = arff_file.substring(0, arff_file.length() - 1);
			arff_file += "}\n";

			arff_file += "@attribute CLIENT {";
			for (int i = 0; i < clientList.size(); i++) {
				arff_file += clientList.get(i) + ",";
			}
			arff_file = arff_file.substring(0, arff_file.length() - 1);
			arff_file += "}\n";

			arff_file += "\n@data\n" + arffData;

			// Save the arff file
			Reader inputString = new StringReader(arff_file);
			BufferedReader reader = new BufferedReader(inputString);
			ArffReader arff = new ArffReader(reader);

			ArffSaver saver = new ArffSaver();
			saver.setInstances(arff.getData());
			saver.setFile(new File(arffFileLocation));
			saver.writeBatch();

		} catch (Exception ex) {
			return false;
		}
		return true;
	}
}
