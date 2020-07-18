package sadFase123;

import org.pentaho.di.core.exception.KettleException;
import java.util.List;
import java.util.ArrayList;

public class MainTest {

	public static void main(String[] args) throws KettleException {
		// TODO Auto-generated method stub
		PluginDoPentaho pluginDoPentaho = new PluginDoPentaho();
		List<String> currentRuleArray = new ArrayList<String>();
		List<String> leftSideArray = new ArrayList<String>();
		List<String> rightSideArray = new ArrayList<String>();
		List<String> rightSupportArray = new ArrayList<String>();
		List<String> confArray= new ArrayList<String>();
		List<String> liftArray= new ArrayList<String>();
		List<String> levArray= new ArrayList<String>();
		List<String> convArray= new ArrayList<String>();
		

		// pluginDoPentaho.getTASKDATA1Rules();
		// pluginDoPentaho.getTASKDATA2Rules();
		// pluginDoPentaho.getTASKDATA3Rules();
		String currentTask = pluginDoPentaho.getTASKDATA3Rules().split("Best rules found:\n\n")[1];
		for (int i = 0; i < currentTask.split("\n").length; i++) {
			String currentRule = currentTask.split("\n")[i];
			currentRuleArray.add(currentRule);
			//System.out.println(currentRule);
			
			String leftSide = currentRule.split(" ==> ")[0];
			leftSideArray.add(leftSide);
			//System.out.println(leftSide);
			
			String rightSide = currentRule.split(" ==> ")[1];
			rightSideArray.add(rightSide);
			//System.out.println(rightSide);

			String rightSupport = rightSide.split(" ")[rightSide.split(" ").length-9];
			rightSupportArray.add(rightSupport);
			//System.out.println(rightSupport);
			
			String conf = rightSide.split(" ")[rightSide.split(" ").length-5];
			confArray.add(conf);
			//System.out.println(conf);
			
			String lift = rightSide.split(" ")[rightSide.split(" ").length-4];
			liftArray.add(lift);
			//System.out.println(lift);
			
			String lev = rightSide.split(" ")[rightSide.split(" ").length-3];
			levArray.add(lev);
			//System.out.println(lev);
			
			String conv = rightSide.split(" ")[rightSide.split(" ").length-1];
			convArray.add(conv);
			//System.out.println(conv);

			String tmp_rightSupport = "";
			for(int p = 0; p < rightSide.split(" ").length-9; p++) {
				tmp_rightSupport += rightSide.split(" ")[p];
			}
			rightSide = tmp_rightSupport;
			
//			System.out.println("Sup "+rightSupport);
//			System.out.println("Lift: "+lift);
//			System.out.println("Lev "+lev);
//			System.out.println("Conv "+conv);
//			System.out.println("Confr: "+conf);
//			System.out.println(rightSide);
			
		}
		
		for (int i = 0; i < rightSupportArray.size(); i++)
		{
			System.out.println(rightSupportArray.get(i));
		}
		
		for (int i = 0; i < confArray.size(); i++)
		{
			System.out.println(confArray.get(i));
		}
		
		for (int i = 0; i < liftArray.size(); i++)
		{
			System.out.println(liftArray.get(i));
		}
		
		for (int i = 0; i < levArray.size(); i++)
		{
			System.out.println(levArray.get(i));
		}
		
		TASKDATA taskdata = new TASKDATA("3");
		taskdata.create_xml_after_apriori();
	}

}
