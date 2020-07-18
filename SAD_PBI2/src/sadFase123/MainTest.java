package sadFase123;

import org.pentaho.di.core.exception.KettleException;

public class MainTest {

	public static void main(String[] args) throws KettleException {
		// TODO Auto-generated method stub
		PluginDoPentaho pluginDoPentaho = new PluginDoPentaho();

		// pluginDoPentaho.getTASKDATA1Rules();
		// pluginDoPentaho.getTASKDATA2Rules();
		// pluginDoPentaho.getTASKDATA3Rules();
		String currentTask = pluginDoPentaho.getTASKDATA3Rules().split("Best rules found:\n\n")[1];
		for (int i = 0; i < currentTask.split("\n").length; i++) {
			String currentRule = currentTask.split("\n")[i];
			
			String leftSide = currentRule.split(" ==> ")[0];
			String rightSide = currentRule.split(" ==> ")[1];

			String rightSupport = rightSide.split(" ")[rightSide.split(" ").length-9];
			String conf = rightSide.split(" ")[rightSide.split(" ").length-5];
			String lift = rightSide.split(" ")[rightSide.split(" ").length-4];
			String lev = rightSide.split(" ")[rightSide.split(" ").length-3];
			String conv = rightSide.split(" ")[rightSide.split(" ").length-1];

			String tmp_rightSupport = "";
			for(int p = 0; p < rightSide.split(" ").length-9; p++) {
				tmp_rightSupport += rightSide.split(" ")[p];
			}
			rightSide = tmp_rightSupport;
			
			//System.out.println("Sup "+rightSupport);
			//System.out.println("Lift: "+lift);
			//System.out.println("Lev "+lev);
			//System.out.println("Conv "+conv);
			//System.out.println("Confr: "+conf);
			//System.out.println(rightSide);
			TASKDATA taskdata = new TASKDATA("3");
			taskdata.create_xml_after_apriori();
		}
	}

}
