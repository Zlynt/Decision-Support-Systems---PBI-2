
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.CSVLoader;

import java.io.File;

public class Main {

	public static void main(String[] args) throws Exception{
		System.out.println("============== PBI 2 ================");
		System.out.println("Ivan Teixeira");
		System.out.println("Josué Ferreira");
		System.out.println("=====================================");

		
		CSVLoader loader = new CSVLoader();
		//loader.setSource(file);
		Instances data = loader.getDataSet();
		
	}

}
