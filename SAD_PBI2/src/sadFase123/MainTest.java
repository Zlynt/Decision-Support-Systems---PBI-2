package sadFase123;

import org.pentaho.di.core.exception.KettleException;
import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import weka.core.Instances;

public class MainTest {

	protected static final String projectPath = "C:\\sad\\implementacao";
	protected static final String xmlPath = projectPath + "\\XML\\TASKDATAResults.xml";
	
	public static void applyElements(List<String> list, Document doc, Element element, List<String> premise, List<String> consequence,
			 List<String> leftSupport,  List<String> rightSupport, List<String> conf, List<String> lift, List<String> lev, List<String> conv)
	{
		for (int i = 0; i < list.size(); i++) {
			// rule elements
			Element ruleElement = doc.createElement("Rule");
			element.appendChild(ruleElement);
//			int tmp_i = i + 1;
//			ruleElement.setAttribute("id", String.valueOf(tmp_i));
			
			//premise elements
			Element premiseElement = doc.createElement("premise");
			premiseElement.appendChild(doc.createTextNode(premise.get(i)));
			ruleElement.appendChild(premiseElement);
			
			//consequence elements
			Element consequenceElement = doc.createElement("consequence");
			consequenceElement.appendChild(doc.createTextNode(consequence.get(i)));
			ruleElement.appendChild(consequenceElement);

			// support elements
			Element leftSupportElement = doc.createElement("leftSupport");
			leftSupportElement.appendChild(doc.createTextNode(leftSupport.get(i)));
			ruleElement.appendChild(leftSupportElement);
			
			// support elements
			Element rightSupportElement = doc.createElement("rightSupport");
			rightSupportElement.appendChild(doc.createTextNode(rightSupport.get(i)));
			ruleElement.appendChild(rightSupportElement);

			// conf elements
			Element confElement = doc.createElement("conf");
			confElement.appendChild(doc.createTextNode(conf.get(i)));
			ruleElement.appendChild(confElement);

			// lift elements
			Element liftElement = doc.createElement("lift");
			liftElement.appendChild(doc.createTextNode(lift.get(i)));
			ruleElement.appendChild(liftElement);

			// lev elements
			Element levElement = doc.createElement("lev");
			levElement.appendChild(doc.createTextNode(lev.get(i)));
			ruleElement.appendChild(levElement);
			
			// conv elements
			Element convElement = doc.createElement("conv");
			convElement.appendChild(doc.createTextNode(conv.get(i)));
			ruleElement.appendChild(convElement);
		}
	}

	public static void clear(	List<String> current, List<String> left,
								List<String> right,  List<String> premise, List<String> consequence, List<String> leftSupport, List<String> rightSupport,
								List<String> conf, List<String> lift, List<String> lev, List<String> conv){
		current.clear();
		left.clear();
		right.clear();
		premise.clear();
		consequence.clear();
		leftSupport.clear();
		rightSupport.clear();
		conf.clear();
		lift.clear();
		lev.clear();
		conv.clear();
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		PluginDoPentaho pluginDoPentaho = new PluginDoPentaho();
		List<String> currentRuleArray = new ArrayList<String>();
		List<String> leftSideArray = new ArrayList<String>();
		List<String> rightSideArray = new ArrayList<String>();
		List<String> premiseArray = new ArrayList<String>();
		List<String> consequenceArray = new ArrayList<String>();
		List<String> combinationArray = new ArrayList<String>();
		List<String> leftSupportArray = new ArrayList<String>();
		List<String> rightSupportArray = new ArrayList<String>();
		List<String> confArray = new ArrayList<String>();
		List<String> liftArray = new ArrayList<String>();
		List<String> levArray = new ArrayList<String>();
		List<String> convArray = new ArrayList<String>();

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();

			Element rootElement = doc.createElement("Taskdatas");
			doc.appendChild(rootElement);

			Element taskData1Element = doc.createElement("Taskdata1");
			rootElement.appendChild(taskData1Element);

			String currentTask = pluginDoPentaho.getTASKDATA1Rules().split("Best rules found:\n\n")[1];
			for (int i = 0; i < currentTask.split("\n").length; i++) {
				String currentRule = currentTask.split("\n")[i];
				currentRuleArray.add(currentRule);

				String leftSide = currentRule.split(" ==> ")[0];
				String leftSupport = leftSide.split(" ")[leftSide.split(" ").length - 1];
				leftSide = leftSide.substring(0, leftSide.length() - leftSide.split(" ")[1].length());
				leftSide = leftSide.replace((i+1)+".","");
				leftSide = leftSide.replace("  PRODUCTNAME=", "");
				leftSideArray.add(leftSide);
				leftSupportArray.add(leftSupport);
				premiseArray.add(leftSide);

				String rightSide = currentRule.split(" ==> ")[1];
				String rightData = rightSide.split("=ThisDealsize")[0];
				rightSideArray.add(rightSide);
				rightData = rightData.replace("DEALSIZE=", "");
				consequenceArray.add(rightData);
				

				String rightSupport = rightSide.split(" ")[rightSide.split(" ").length - 9];
				rightSupport = rightSupport.replaceAll("[^\\d.]", "");
				rightSupportArray.add(rightSupport);

				String conf = rightSide.split(" ")[rightSide.split(" ").length - 5];
				conf = conf.replaceAll("[^\\d.]", "");
				confArray.add(conf);

				String lift = rightSide.split(" ")[rightSide.split(" ").length - 4];
				lift = lift.replaceAll("[^\\d.]", "");
				liftArray.add(lift);

				String lev = rightSide.split(" ")[rightSide.split(" ").length - 3];
				lev = lev.replaceAll("[^\\d.]", "");
				levArray.add(lev);

				String conv = rightSide.split(" ")[rightSide.split(" ").length - 1];
				conv = conv.replaceAll("[^\\d.]", "");
				convArray.add(conv);

				String tmp_rightSupport = "";
				for (int p = 0; p < rightSide.split(" ").length - 9; p++) {
					tmp_rightSupport += rightSide.split(" ")[p];
				}
				rightSide = tmp_rightSupport;
			}


			applyElements(currentRuleArray, doc, taskData1Element, premiseArray, consequenceArray, leftSupportArray, rightSupportArray, confArray, liftArray, levArray, convArray);
			clear(currentRuleArray, leftSideArray, rightSideArray, premiseArray, consequenceArray, leftSupportArray, rightSupportArray, confArray, liftArray, levArray, convArray);

			Element taskData2Element = doc.createElement("Taskdata2");
			rootElement.appendChild(taskData2Element);

			currentTask = pluginDoPentaho.getTASKDATA2Rules().split("Best rules found:\n\n")[1];
			for (int i = 0; i < currentTask.split("\n").length; i++) {
				String currentRule = currentTask.split("\n")[i];
				currentRuleArray.add(currentRule);

				String leftSide = currentRule.split(" ==> ")[0];
				String leftSupport = leftSide.split(" ")[leftSide.split(" ").length - 1];
				leftSupportArray.add(leftSupport);
				leftSide = leftSide.substring(0, leftSide.length() - leftSide.split(" ")[1].length());
				leftSide = leftSide.replace((i+1)+".","");
				if (leftSide.contains("  COUNTRY="))
				{
					leftSide = leftSide.replace("  COUNTRY=", "");
					leftSide = leftSide.replace("=ThisCountry", "");
				}
				else if (leftSide.contains(("  PRODUCTLINE=")))
				{
					leftSide = leftSide.replace("  PRODUCTLINE=", "");
					leftSide = leftSide.replace("=ThisProductLine", "");
				}
				
				leftSideArray.add(leftSide);
				premiseArray.add(leftSide);
//				System.out.println(leftSide);

				String rightSide = currentRule.split(" ==> ")[1];
				String rightData = null;
				if (rightSide.contains("COUNTRY="))
				{
					rightData = rightSide.split("=ThisCountry")[0];
					rightData = rightData.replace("COUNTRY=", "");
				}
				else if (rightSide.contains("PRODUCTLINE="))
				{
					rightData = rightSide.split("=ThisProductLine")[0];
					rightData = rightData.replace("PRODUCTLINE=", "");
				}
				
				rightSideArray.add(rightSide);
				consequenceArray.add(rightData);
				
				String rightSupport = rightSide.split(" ")[rightSide.split(" ").length - 9];
				rightSupportArray.add(rightSupport);

				String conf = rightSide.split(" ")[rightSide.split(" ").length - 5];
				conf = conf.replaceAll("[^\\d.]", "");
				confArray.add(conf);

				String lift = rightSide.split(" ")[rightSide.split(" ").length - 4];
				lift = lift.replaceAll("[^\\d.]", "");
				liftArray.add(lift);

				String lev = rightSide.split(" ")[rightSide.split(" ").length - 3];
				lev = lev.replaceAll("[^\\d.]", "");
				levArray.add(lev);

				String conv = rightSide.split(" ")[rightSide.split(" ").length - 1];
				conv = conv.replaceAll("[^\\d.]", "");
				convArray.add(conv);

				String tmp_rightSupport = "";
				for (int p = 0; p < rightSide.split(" ").length - 9; p++) {
					tmp_rightSupport += rightSide.split(" ")[p];
				}
				rightSide = tmp_rightSupport;
			}

			applyElements(currentRuleArray, doc, taskData2Element, premiseArray, consequenceArray, leftSupportArray, rightSupportArray, confArray, liftArray, levArray, convArray);
			clear(currentRuleArray, leftSideArray, rightSideArray, premiseArray, consequenceArray, leftSupportArray, rightSupportArray, confArray, liftArray, levArray, convArray);

			Element taskData3Element = doc.createElement("Taskdata3");
			rootElement.appendChild(taskData3Element);

			currentTask = pluginDoPentaho.getTASKDATA3Rules().split("Best rules found:\n\n")[1];
			for (int i = 0; i < currentTask.split("\n").length; i++) {
				String currentRule = currentTask.split("\n")[i];
				currentRuleArray.add(currentRule);

				String leftSide = currentRule.split(" ==> ")[0];
				String leftSupport = leftSide.split(" ")[leftSide.split(" ").length - 1];
				leftSupportArray.add(leftSupport);
				leftSideArray.add(leftSide);
				leftSide = leftSide.substring(0, leftSide.length() - leftSide.split(" ")[1].length());
				leftSide = leftSide.replace((i+1)+".","");
				leftSide = leftSide.replace("  CLIENT=", "Client ");
				premiseArray.add(leftSide);
				//System.out.println(leftSide);
				
				String rightSide = currentRule.split(" ==> ")[1];
				rightSideArray.add(rightSide);
				
				String rightSupport = rightSide.split(" ")[rightSide.split(" ").length - 9];
				rightSupport = rightSupport.replaceAll("[^\\d.]", "");
				rightSupportArray.add(rightSupport);
				
				String rightData = currentRule.split("PRODUCT=")[1];
				rightData = rightData.split("  "+rightSupport)[0];
				consequenceArray.add(rightData);
				//System.out.println(rightData);
				
				String tmp_data_combination = leftSide+" and "+rightData;
				tmp_data_combination = tmp_data_combination.replace("  ", " ");
				//System.out.println(tmp_data_combination);
				combinationArray.add(tmp_data_combination);
				
				String conf = rightSide.split(" ")[rightSide.split(" ").length - 5];
				conf = conf.replaceAll("[^\\d.]", "");
				confArray.add(conf);

				String lift = rightSide.split(" ")[rightSide.split(" ").length - 4];
				lift = lift.replaceAll("[^\\d.]", "");
				liftArray.add(lift);

				String lev = rightSide.split(" ")[rightSide.split(" ").length - 3];
				lev = lev.replaceAll("[^\\d.]", "");
				levArray.add(lev);

				String conv = rightSide.split(" ")[rightSide.split(" ").length - 1];
				conv = conv.replaceAll("[^\\d.]", "");
				convArray.add(conv);

				String tmp_rightSupport = "";
				for (int p = 0; p < rightSide.split(" ").length - 9; p++) {
					tmp_rightSupport += rightSide.split(" ")[p];
				}
				rightSide = tmp_rightSupport;
			}

			applyElements(currentRuleArray, doc, taskData3Element, premiseArray, consequenceArray, leftSupportArray, rightSupportArray, confArray, liftArray, levArray, convArray);
			clear(currentRuleArray, leftSideArray, rightSideArray, premiseArray, consequenceArray, leftSupportArray, rightSupportArray, confArray, liftArray, levArray, convArray);

			Element taskData4Element = doc.createElement("Taskdata4");
			rootElement.appendChild(taskData4Element);

			currentTask = pluginDoPentaho.getTASKDATA4Rules().split("Best rules found:\n\n")[1];
			for (int i = 0; i < currentTask.split("\n").length; i++) {
				String currentRule = currentTask.split("\n")[i];
				currentRuleArray.add(currentRule);

				String leftSide = currentRule.split(" ==> ")[0];
				String leftSupport = leftSide.split(" ")[leftSide.split(" ").length - 1];
				leftSupportArray.add(leftSupport);
				leftSideArray.add(leftSide);
				
				leftSide = leftSide.substring(0, leftSide.length() - leftSide.split(" ")[1].length());
				if (i+1 == 10)
					leftSide = leftSide.replace("10. ","");
				else
					leftSide = leftSide.replace(" "+(i+1)+". ","");
				
				if (i+1 >= 9)
				{
					leftSide = leftSide.replace("=Bought", " and ");
					leftSide = leftSide.replace("=Bough", "");
				}
				else 
					leftSide = leftSide.replace("=Bought", "");
				premiseArray.add(leftSide);				
				//System.out.println(leftSide);

				String rightSide = currentRule.split(" ==> ")[1];
				rightSideArray.add(rightSide);
				String rightData = rightSide.split("=Bought")[0];
				consequenceArray.add(rightData);
				//System.out.println(rightData);

				String rightSupport = rightSide.split(" ")[rightSide.split(" ").length - 9];
				rightSupport = rightSupport.replaceAll("[^\\d.]", "");
				rightSupportArray.add(rightSupport);

				String conf = rightSide.split(" ")[rightSide.split(" ").length - 5];
				conf = conf.replaceAll("[^\\d.]", "");
				confArray.add(conf);

				String lift = rightSide.split(" ")[rightSide.split(" ").length - 4];
				lift = lift.replaceAll("[^\\d.]", "");
				liftArray.add(lift);

				String lev = rightSide.split(" ")[rightSide.split(" ").length - 3];
				lev = lev.replaceAll("[^\\d.]", "");
				levArray.add(lev);

				String conv = rightSide.split(" ")[rightSide.split(" ").length - 1];
				conv = conv.replaceAll("[^\\d.]", "");
				convArray.add(conv);

				String tmp_rightSupport = "";
				for (int p = 0; p < rightSide.split(" ").length - 9; p++) {
					tmp_rightSupport += rightSide.split(" ")[p];
				}
				rightSide = tmp_rightSupport;
			}

			applyElements(currentRuleArray, doc, taskData4Element, premiseArray, consequenceArray, leftSupportArray, rightSupportArray, confArray, liftArray, levArray, convArray);

			// write the content into xml file
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(xmlPath));

			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

}
