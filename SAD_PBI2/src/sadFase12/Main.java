package sadFase12;

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
		
		TASKDATA1 taskdata1 = new TASKDATA1();
		TASKDATA2 taskdata2 = new TASKDATA2();
		TASKDATA3 taskdata3 = new TASKDATA3();
		TASKDATA4 taskdata4 = new TASKDATA4();
		
		//Generate CSV
		taskdata1.generateCSV();
		System.out.println("");
		taskdata2.generateCSV();
		System.out.println("");
		taskdata3.generateCSV();
		System.out.println("");
		taskdata4.generateCSV();
		System.out.println("");
		
		//CSV to ARFF
		System.out.println("[TASKDATA1] Processing the CSV input...");
		taskdata1.save_arff(taskdata1.csv_to_instances());
		System.out.println("[TASKDATA2] Processing the CSV input...");
		taskdata2.save_arff(taskdata2.csv_to_instances());
		System.out.println("[TASKDATA3] Processing the CSV input...");
		taskdata3.save_arff(taskdata3.csv_to_instances());
		System.out.println("[TASKDATA4] Processing the CSV input...");
		taskdata4.save_arff(taskdata4.csv_to_instances());
		System.out.println("");
		
		//Loading ARFF
		System.out.println("[TASKDATA1] Loading arff...");
		Instances taskdata1_instances = taskdata1.load_arff();
		System.out.println("[TASKDATA2] Loading arff...");
		Instances taskdata2_instances = taskdata2.load_arff();
		System.out.println("[TASKDATA3] Loading arff...");
		Instances taskdata3_instances = taskdata3.load_arff();
		System.out.println("[TASKDATA4] Loading arff...");
		Instances taskdata4_instances = taskdata4.load_arff();
		System.out.println("");
		
		//Mine the rules
		System.out.println("[TASKDATA1] Mining association rules...");
		String taskdata1_rules = taskdata1.apriori_mine_association_rules(taskdata1_instances, 0.01, 0.7);
		System.out.println("[TASKDATA2] Mining association rules...");
		String taskdata2_rules = taskdata2.apriori_mine_association_rules(taskdata2_instances, 0.01, 0.7);
		System.out.print("[TASKDATA3] Mining association rules...");
		String taskdata3_rules = taskdata3.apriori_mine_association_rules(taskdata3_instances, 0.01, 0.7);
		System.out.println("[TASKDATA4] Mining association rules...");
		String taskdata4_rules = taskdata4.apriori_mine_association_rules(taskdata4_instances, 0.01);
		
		//Show the rules
		System.out.println("\n==== TASKDATA1 Association Rules ====");
		System.out.println(taskdata1_rules);
		System.out.println("======================================");
		System.out.println("\n==== TASKDATA2 Association Rules ====");
		System.out.println(taskdata2_rules);
		System.out.println("======================================");
		System.out.println("\n==== TASKDATA3 Association Rules ====");
		System.out.println(taskdata3_rules);
		System.out.println("======================================");
		System.out.println("\n==== TASKDATA4 Association Rules ====");
		System.out.println(taskdata4_rules);
		System.out.println("======================================");
	}

}
