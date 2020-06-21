import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.text.Collator;
import java.util.Comparator;
import java.util.LinkedList;

import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

public class TASKDATA4 {
	private String file = "C:\\sad\\implementacao\\CSV\\TASKDATA4.csv";
	private String outputFile = "C:\\sad\\implementacao\\ARFF\\TASKDATA4.arff";
	private CSVLoader csv;

	private LinkedList<String> loadedfile;

	public TASKDATA4() {
		csv = new CSVLoader();
		try {
			csv.setSource(new File(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Process and save the information
	public void process_and_save() {
		if (csv == null) {
			System.out.println("CSV needs to be loaded first!");
			return;
		}

		Instances trainSet = null;
		try {
			trainSet = csv.getDataSet();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String tmp_data_loaded = trainSet.toString().replace(" ", "");
		tmp_data_loaded = tmp_data_loaded.replace("@relationTASKDATA4", "");
		String[] data_loaded = tmp_data_loaded.split("\n");

		LinkedList<String> tid = new LinkedList<String>();
		LinkedList<String> products = new LinkedList<String>();

		boolean read_data = false;

		for (int i = 0; i < data_loaded.length; i++) {

			if (read_data) {
				String tmp_tid = data_loaded[i].split(",")[0];
				String tmp_prod = data_loaded[i].split(",")[1];

				if (!tid.contains(tmp_tid)) {
					tid.add(tmp_tid);
				}

				if (!products.contains(tmp_prod)) {
					products.add(tmp_prod);
				}

			}

			if (data_loaded[i].contains("@data")) {
				read_data = true;
			}
		}

		tid.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return Collator.getInstance().compare(o1, o2);
			}
		});

		products.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return Collator.getInstance().compare(o1, o2);
			}
		});

		// All sorted up
		LinkedList<LinkedList> buysPerTID = new LinkedList<LinkedList>();

		// Construir o ficheiro arff
		String arff_file = "@relation sales\n";
		arff_file += "@attribute 'TID' { ";
		for (String transaction_id : tid) {
			arff_file += "'" + transaction_id + "', ";
			buysPerTID.add(new LinkedList<String>());
		}
		arff_file.substring(0, arff_file.length() - 2);
		arff_file += "}\n";
		for (String products_id : products) {
			arff_file += "@attribute " + products_id + " { 'n','y'}\n";
		}

		arff_file += "@data\n";

		for (int i = 0; i < data_loaded.length; i++) {
			int index_of_tid = tid.indexOf(data_loaded[i].split(",")[0]);
			//System.out.println("DATA: ="+data_loaded[i].split(",")[0]);
			//System.out.println("INDEX= "+index_of_tid);
			if(index_of_tid != -1)
			buysPerTID.get(index_of_tid).add(data_loaded[i].split(",")[1]);
		}

		for (int i = 0; i < buysPerTID.size(); i++) {
			buysPerTID.get(i).sort(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return Collator.getInstance().compare(o1, o2);
				}
			});
		}

		for (int i = 0; i < buysPerTID.size(); i++) {
			arff_file += "'"+tid.get(i)+"',";
			for(int p = 0; p < products.size(); p++) {
				if(buysPerTID.get(i).contains(products.get(p))){
					arff_file += "'y',";
				}else {
					arff_file += "'n',";
				}
			}
			arff_file.substring(0, arff_file.length() - 1);
			arff_file += "\n";
		}
		
		Reader inputString = new StringReader(arff_file);
		BufferedReader reader = new BufferedReader(inputString);
		 try {
			ArffReader arff = new ArffReader(reader);
			 Instances data = arff.getData();
			 ArffSaver saver = new ArffSaver();
			 saver.setInstances(data);
			 saver.setFile(new File(outputFile));
			 saver.writeBatch();
			 System.out.println("TASKDATA4.arff saved!");
			 Apriori model = new Apriori();
			 model.buildAssociations(data);
			 System.out.println(model);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 


		/*try {
			PrintWriter out = new PrintWriter(outputFile); //Perguntar ao prof se posso usar isto em vez de ARFFSaver
			out.print(arff_file);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//ArffSaver saver = new ArffSaver();
		//saver.setInstances(arff_file);
		//saver.setFile(outputFile);
		//saver.writeBatch();

	}
}
