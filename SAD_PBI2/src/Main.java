
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.associations.Apriori;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;


public class Main {

	public static void main(String[] args) throws Exception{
		System.out.println("============== PBI 2 ================");
		System.out.println("Ivan Teixeira");
		System.out.println("Josué Ferreira");
		System.out.println("=====================================");
		
		//File file = new File("C:\\sad\\implementacao\\TASKDATA1.csv");
		//File outputFile = new File("C:\\sad\\implementacao\\TASKDATA1.arff");
		
		TASKDATA4 taskdata4 = new TASKDATA4();
		taskdata4.process_and_save();
		

		//CSVLoader loader = new CSVLoader();
		//loader.setSource(file);
		//Instances data = loader.getDataSet();
		
		
		//ArffSaver saver = new ArffSaver();
		//saver.setInstances(data);
		//saver.setFile(outputFile);
		//saver.writeBatch();
		
//		String dataset = "C:\\sad\\implementacao\\TASKDATA1.arff";
//		DataSource source = new DataSource(dataset);
//		
//		//load data
//		Instances data = source.getDataSet();
//		
//		Apriori model = new Apriori();
//		model.buildAssociations(data);
//		System.out.println(model);
	}

}
