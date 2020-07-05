import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.text.Collator;
import java.util.Comparator;
import java.util.LinkedList;

import TASKDATA3.Transaction;
import TASKDATA3.TransactionList;
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
		TransactionList transactionList = new TransactionList();

		System.out.print("Processing tuples...");
		for (int i = 0; i < data_loaded.length; i++) {

			if (read_data) {
				String tmp_month = data_loaded[i].split(",")[0];
				String tmp_prod = data_loaded[i].split(",")[1];

				//System.out.println("["+(i-5)+" de "+(data_loaded.length-5)+"] " + tmp_month + " ; " + tmp_prod);
				
				Transaction transaction = new Transaction(tmp_month);
				transaction.addProduct(tmp_prod);
				transactionList.addTransaction(transaction);
			}

			if (data_loaded[i].contains("@data")) {
				read_data = true;
			}
		}
		System.out.println("done!");

		System.out.print("Converting to arff...");
		String arff_file = transactionList.toARFF();
		System.out.println("done!");

		PrintWriter out = new PrintWriter("debug_arff_taskdata_3.txt");
		out.println(arff_file);
		out.close();

		Reader inputString = new StringReader(arff_file);
		BufferedReader reader = new BufferedReader(inputString);
		ArffReader arff = new ArffReader(reader);
		return arff.getData();

	}

}
