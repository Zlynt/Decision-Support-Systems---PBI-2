package sadFase123;

import java.io.BufferedReader;
import java.io.File;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.ArffLoader.ArffReader;

public class TASKDATA4 extends TASKDATA {
	// We use salesData and productList in this class to save resources on the
	// device running this code
	private HashMap<String, LinkedList<String>> salesData;
	private LinkedList<String> productList;

	public TASKDATA4() {
		super("4");

		salesData = new HashMap<String, LinkedList<String>>();
		productList = new LinkedList<String>();
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

			boolean isData = false;
			for (int i = 0; i < taskdataArff.split("\n").length; i++) {
				String currentLine = taskdataArff.split("\n")[i];

				if (isData) {
					String currentTid = currentLine.split(",")[0];
					String currentProduct = currentLine.split(",")[1];

					LinkedList<String> currentSaleData = salesData.get(currentTid);

					if (currentSaleData == null) {
						currentSaleData = new LinkedList<String>();
						currentSaleData.add(currentProduct);
					} else {
						if (!currentSaleData.contains(currentProduct))
							currentSaleData.add(currentProduct);
					}

					if (!productList.contains(currentProduct)) {
						productList.add(currentProduct);
					}

					salesData.put(currentTid, currentSaleData);

				} else {
					if (currentLine.compareTo("@data") == 0)
						isData = true;
				}
			}

			// We use the same variable to save memory on the device running this code
			taskdataArff = "@relation TASKDATA4\n\n";

			// Declare the products
			for (int i = 0; i < productList.size(); i++) {
				taskdataArff += "@attribute " + productList.get(i) + " {Bought}\n";
			}

			taskdataArff += "\n@data\n";

			for (Map.Entry<String, LinkedList<String>> entry : salesData.entrySet()) {
				LinkedList<String> transaction = entry.getValue();

				for (int i = 0; i < productList.size(); i++) {
					if (transaction.contains(productList.get(i)))
						taskdataArff += "Bought,";
					else
						taskdataArff += "?,";
				}

				// Remove extra comma
				taskdataArff = taskdataArff.substring(0, taskdataArff.length() - 1);

				taskdataArff += "\n";

			}

			// Remove the pentaho export bug that is the extra space
			// 'PRODUCTNAME________________'
			while (taskdataArff.contains(" '"))
				taskdataArff = taskdataArff.replace(" '", "'");

			// Save the arff file
			Reader inputString = new StringReader(taskdataArff);
			BufferedReader reader = new BufferedReader(inputString);
			ArffReader arff = new ArffReader(reader);

			ArffSaver saver = new ArffSaver();
			saver.setInstances(arff.getData());
			saver.setFile(new File(arffFileLocation));
			saver.writeBatch();
			
			//Mine association 
		} catch (Exception ex) {
			return false;
		}

		return true;
	}

}
