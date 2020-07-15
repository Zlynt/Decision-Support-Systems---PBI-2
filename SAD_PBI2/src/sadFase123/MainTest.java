package sadFase123;

import org.pentaho.di.core.exception.KettleException;

public class MainTest {

	public static void main(String[] args) throws KettleException {
		// TODO Auto-generated method stub
		PluginDoPentaho pluginDoPentaho = new PluginDoPentaho();
		
		//pluginDoPentaho.getTASKDATA1Rules();
		//pluginDoPentaho.getTASKDATA2Rules();
		//pluginDoPentaho.getTASKDATA3Rules();
		String currentTask = pluginDoPentaho.getTASKDATA4Rules().split("Best rules found:\n\n")[1];
		for(int i = 0; i < currentTask.split("\n").length; i++) {
			String currentRule = currentTask.split("\n")[i];
			currentRule = currentRule.split(" lift:")[0];
			
			String currentConfidence = currentRule.split("    ")[currentRule.split("    ").length-1];
			currentRule = currentRule.substring(0, currentRule.length() - (currentConfidence+"    ").length());
			currentConfidence = currentConfidence.replace("<", "");
			currentConfidence = currentConfidence.replace(">", "");
			currentConfidence = currentConfidence.replace("conf:(", "");
			currentConfidence = currentConfidence.substring(0, currentConfidence.length()-1);
			
			System.out.println("<p>"+currentRule+" | "+currentConfidence+"</p>");
		}
	}

}
