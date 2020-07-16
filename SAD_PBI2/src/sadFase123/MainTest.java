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
			//currentRule = currentRule.replace("=Bought", "");
			String currentRuleLeft = currentRule.split("==>")[0];
			String currentRuleRight = currentRule.split("==>")[1];
			
			String ruleToPrint = "";
			if(currentRuleLeft.split("=Bought").length == 2) {
				ruleToPrint += currentRuleLeft.split("=Bought")[0] + " ( Support("+currentRuleLeft.split("=Bought")[1].replace(" ", "")+") )";
			}else {
				for(int p = 0; p < currentRuleLeft.split("=Bought").length-1; p++) {
					ruleToPrint += currentRuleLeft.split("=Bought")[p] + " && ";
				}
				ruleToPrint = ruleToPrint.substring(0, ruleToPrint.length()-4);
				ruleToPrint += " ) Support("+currentRuleLeft.split("=Bought")[currentRuleLeft.split("=Bought").length-1]+") )";
			}
			
			ruleToPrint += " ==> ";
			if(currentRuleRight.split("=Bought").length == 2) {
				ruleToPrint += currentRuleRight.split("=Bought")[0] + " ( Support("+currentRuleRight.split("=Bought")[1].replace(" ", "")+") )";
			}else {
				for(int p = 0; p < currentRuleRight.split("=Bought").length-1; p++) {
					ruleToPrint += currentRuleRight.split("=Bought")[p] + " && ";
				}
				ruleToPrint = ruleToPrint.substring(0, ruleToPrint.length()-4);
				ruleToPrint += " ) Support("+currentRuleRight.split("=Bought")[currentRuleRight.split("=Bought").length-1]+") )";
			}
			
			
			System.out.println(ruleToPrint);
		}
	}

}
