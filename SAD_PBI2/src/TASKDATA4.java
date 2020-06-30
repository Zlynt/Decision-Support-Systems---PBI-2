import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;

import TASKDATA4.Transaction;
import TASKDATA4.TransactionList;
import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;

public class TASKDATA4 extends TASKDATA {

	// TASKDATA Constructor
	public TASKDATA4() {
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

		tmp_data_loaded = tmp_data_loaded.replace("@relationTASKDATA4", "");
		tmp_data_loaded = tmp_data_loaded.replace("@relationTASKDATA4", "");
		String[] data_loaded = tmp_data_loaded.split("\n");

		boolean read_data = false;
		TransactionList transactionList = new TransactionList();

		for (int i = 0; i < data_loaded.length; i++) {

			if (read_data) {
				String tmp_tid = data_loaded[i].split(",")[0];
				String tmp_prod = data_loaded[i].split(",")[1];

				// System.out.println("[" + tmp_tid + "][" + tmp_month + "] " + tmp_prod);
				Transaction transaction = new Transaction(Integer.parseInt(tmp_tid));
				transaction.addProduct(tmp_prod);
				transactionList.addTransaction(transaction);
			}

			if (data_loaded[i].contains("@data")) {
				read_data = true;
			}
		}

		// System.out.println(arff_file);
		Reader inputString = new StringReader(transactionList.toARFF());
		BufferedReader reader = new BufferedReader(inputString);
		ArffReader arff = new ArffReader(reader);
		return arff.getData();

	}

}
