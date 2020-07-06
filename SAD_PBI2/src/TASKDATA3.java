import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.text.Collator;
import java.util.Comparator;
import java.util.LinkedList;

import TASKDATA3.AttributeValueList;
import TASKDATA3.Transaction;
import TASKDATA3.TransactionList;
import TASKDATA3.TransactionProcessor;
import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.ArffLoader.ArffReader;

public class TASKDATA3 extends TASKDATA {

	// TASKDATA Constructor
	public TASKDATA3() {
		super(3, true); // TASKDATA3; The 3 number goes from the TASKDATA number
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
		TransactionProcessor transactionList = new TransactionProcessor();

		System.out.print("[TASKDATA3] Processing tuples...");
		for (int i = 0; i < data_loaded.length; i++) {

			if (read_data) {
				String tupleMonth = data_loaded[i].split(",")[0];
				String tupleProduct = data_loaded[i].split(",")[1].replace("'", "");

				transactionList.addMonth(tupleMonth);
				transactionList.addProduct(tupleProduct);
				transactionList.addPurchase(tupleMonth, tupleProduct);

			}

			if (data_loaded[i].contains("@data")) {
				read_data = true;
			}
		}
		System.out.println("done!");
		for (int i = 0; i < transactionList.getPurchases().size(); i++) {
			String tupleMonth = transactionList.getPurchases().get(i)[0];
			String tupleProduct = transactionList.getPurchases().get(i)[1];
			// System.out.println("[" + tupleMonth + "] -> " + tupleProduct);
		}

		System.out.print("[TASKDATA3] Converting to arff...");
		String arff_file = "@relation TASKDATA3\n\n";

		// Set Month Attributes
		String[] boolean_month = { "'ThisMonth'", "'NotThisMonth'" };
		for (int i = 0; i < transactionList.getMonthList().size(); i++) {
			arff_file += "@attribute MONTH=" + transactionList.getMonthList().get(i) + " {" + boolean_month[0] + ","
					+ boolean_month[1] + "}\n";
		}

		// Set Product Attributes
		String[] boolean_product = { "'Purchased'", "'NotPurchased'" };
		for (int i = 0; i < transactionList.getProductList().size(); i++) {
			arff_file += "@attribute 'PRODUCTLINE=" + transactionList.getProductList().get(i) + "' {" + boolean_product[0]
					+ "," + boolean_product[1] + "}\n";
		}

		arff_file += "\n@data\n";

		LinkedList<String[]> purchasesList = transactionList.getPurchases();
		for (int a = 0; a < purchasesList.size(); a++) {

			for (int i = 0; i < transactionList.getMonthList().size(); i++) {
				if (purchasesList.get(a)[0].compareTo(transactionList.getMonthList().get(i)) == 0)
					arff_file += boolean_month[0];
				else
					arff_file += boolean_month[1];
				arff_file += ",";
			}

			for (int i = 0; i < transactionList.getProductList().size(); i++) {
				if (purchasesList.get(a)[1].compareTo(transactionList.getProductList().get(i)) == 0)
					arff_file += boolean_product[0];
				else
					arff_file += boolean_product[1];
				arff_file += ",";
			}

			// Remove extra comma
			arff_file = arff_file.substring(0, arff_file.length() - 1);
			arff_file += "\n";
		}

		System.out.println("done!");

		//PrintWriter out = new PrintWriter("debug_arff_taskdata_4.txt");
		//out.println(arff_file); out.close();

		Reader inputString = new StringReader(arff_file);
		BufferedReader reader = new BufferedReader(inputString);
		ArffReader arff = new ArffReader(reader);
		return arff.getData();

	}

}
