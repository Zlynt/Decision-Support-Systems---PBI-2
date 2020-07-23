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

//	protected static List<String> currentRuleArray = new ArrayList<String>();
//	protected static List<String> leftSideArray = new ArrayList<String>();
//	protected static List<String> rightSideArray = new ArrayList<String>();
//	protected static List<String> rightSupportArray = new ArrayList<String>();
//	protected static List<String> confArray = new ArrayList<String>();
//	protected static List<String> liftArray = new ArrayList<String>();
//	protected static List<String> levArray = new ArrayList<String>();
//	protected static List<String> convArray = new ArrayList<String>();

	public static void applyElements(List<String> list, Document doc, Element element, 
			 List<String> support, List<String> conf, List<String> lift, List<String> lev, List<String> conv)
	{
		for (int i = 0; i < list.size(); i++) {
//			// Rule elements
//			Element ruleElement = doc.createElement("Rule");
//			element.appendChild(ruleElement);
//			int tmp_i = i + 1;
//			ruleElement.setAttribute("id", String.valueOf(tmp_i));
//
//			// Support elements
//			Element supportElement = doc.createElement("support");
//			supportElement.appendChild(doc.createTextNode(support.get(i)));
//			ruleElement.appendChild(supportElement);
//
//			// Conf elements
//			Element confElement = doc.createElement("conf");
//			confElement.appendChild(doc.createTextNode(conf.get(i)));
//			ruleElement.appendChild(confElement);
//
//			// Lift elements
//			Element liftElement = doc.createElement("lift");
//			liftElement.appendChild(doc.createTextNode(lift.get(i)));
//			ruleElement.appendChild(liftElement);
//
//			// Lev elements
//			Element levElement = doc.createElement("lev");
//			levElement.appendChild(doc.createTextNode(lev.get(i)));
//			ruleElement.appendChild(levElement);
			
			// rule elements
			Element ruleElement = doc.createElement("Rule");
			element.appendChild(ruleElement);
			int tmp_i = i + 1;
			ruleElement.setAttribute("id", String.valueOf(tmp_i));

			// support elements
			Element supportElement = doc.createElement("support");
			supportElement.appendChild(doc.createTextNode(support.get(i)));
			ruleElement.appendChild(supportElement);

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
								List<String> right, List<String> support,
								List<String> conf, List<String> lift, List<String> lev, List<String> conv){
		current.clear();
		left.clear();
		right.clear();
		support.clear();
		conf.clear();
		lift.clear();
		lev.clear();
		conv.clear();
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		PluginDoPentaho pluginDoPentaho = new PluginDoPentaho();
		List<String> strategy_part1Array = new ArrayList<String>();
		List<String> strategy_part2Array = new ArrayList<String>();
		List<String> currentRuleArray = new ArrayList<String>();
		List<String> leftSideArray = new ArrayList<String>();
		List<String> rightSideArray = new ArrayList<String>();
		List<String> rightSupportArray = new ArrayList<String>();
		List<String> confArray = new ArrayList<String>();
		List<String> liftArray = new ArrayList<String>();
		List<String> levArray = new ArrayList<String>();
		List<String> convArray = new ArrayList<String>();
		
//		Element supportElement, confElement, liftElement , levElement;

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
				leftSideArray.add(leftSide);

				String rightSide = currentRule.split(" ==> ")[1];
				rightSideArray.add(rightSide);

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


			applyElements(currentRuleArray, doc, taskData1Element, rightSupportArray, confArray, liftArray, levArray, convArray);
//			for (int i = 0; i < currentRuleArray.size(); i++) {
//				// rule elements
//				Element ruleElement = doc.createElement("Rule");
//				taskData1Element.appendChild(ruleElement);
//				int tmp_i = i + 1;
//				ruleElement.setAttribute("id", String.valueOf(tmp_i));
//
//				// support elements
//				Element supportElement = doc.createElement("support");
//				supportElement.appendChild(doc.createTextNode(rightSupportArray.get(i)));
//				ruleElement.appendChild(supportElement);
//
//				// conf elements
//				Element confElement = doc.createElement("conf");
//				confElement.appendChild(doc.createTextNode(confArray.get(i)));
//				ruleElement.appendChild(confElement);
//
//				// lift elements
//				Element liftElement = doc.createElement("lift");
//				liftElement.appendChild(doc.createTextNode(liftArray.get(i)));
//				ruleElement.appendChild(liftElement);
//
//				// lev elements
//				Element levElement = doc.createElement("lev");
//				levElement.appendChild(doc.createTextNode(convArray.get(i)));
//				ruleElement.appendChild(levElement);
//			}

			clear(currentRuleArray, leftSideArray, rightSideArray, rightSupportArray, confArray, liftArray, levArray, convArray);

			Element taskData2Element = doc.createElement("Taskdata2");
			rootElement.appendChild(taskData2Element);

			currentTask = pluginDoPentaho.getTASKDATA2Rules().split("Best rules found:\n\n")[1];
			for (int i = 0; i < currentTask.split("\n").length; i++) {
				String currentRule = currentTask.split("\n")[i];
				currentRuleArray.add(currentRule);

				String leftSide = currentRule.split(" ==> ")[0];
				leftSideArray.add(leftSide);
				
				System.out.println(leftSide);
				
//				String info;
//				int compare = 
//				String strategy_part1 = leftSide.split("COUNTRY=")[1];
//				strategy_part1 = strategy_part1.split(" ")[0];
//				//System.out.println(strategy_part1);
//				strategy_part1Array.add(strategy_part1);

				
				String rightSide = currentRule.split(" ==> ")[1];
				rightSideArray.add(rightSide);

				String rightSupport = rightSide.split(" ")[rightSide.split(" ").length - 9];
//				//if ("")
//				String strategy_part2 = currentRule.split("PRODUCTLINE=")[1];
//				rightSupport = rightSupport.replaceAll("[^\\d.]", "");
//				strategy_part2 = strategy_part2.split("  "+rightSupport)[0];
//				strategy_part2Array.add(strategy_part2);
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

			for (int i = 0; i < currentRuleArray.size(); i++) {
				// rule elements
				Element ruleElement = doc.createElement("Rule");
				taskData2Element.appendChild(ruleElement);
				int tmp_i = i + 1;
				ruleElement.setAttribute("id", String.valueOf(tmp_i));
				
				//strategy elements
//				Element strategy_part1 = doc.createElement("strategy_part1");
//				strategy_part1.appendChild(doc.createTextNode(strategy_part1Array.get(i)));
//				ruleElement.appendChild(strategy_part1);
//				Element strategy_part2 = doc.createElement("strategy_part2");
//				strategy_part2.appendChild(doc.createTextNode(strategy_part2Array.get(i)));
//				ruleElement.appendChild(strategy_part2);

				// support elements
				Element supportElement = doc.createElement("support");
				supportElement.appendChild(doc.createTextNode(rightSupportArray.get(i)));
				ruleElement.appendChild(supportElement);

				// conf elements
				Element confElement = doc.createElement("conf");
				confElement.appendChild(doc.createTextNode(confArray.get(i)));
				ruleElement.appendChild(confElement);

				// lift elements
				Element liftElement = doc.createElement("lift");
				liftElement.appendChild(doc.createTextNode(liftArray.get(i)));
				ruleElement.appendChild(liftElement);
				
				// lev elements
				Element levElement = doc.createElement("lev");
				levElement.appendChild(doc.createTextNode(levArray.get(i)));
				ruleElement.appendChild(levElement);

				// conv elements
				Element convElement = doc.createElement("conv");
				convElement.appendChild(doc.createTextNode(convArray.get(i)));
				ruleElement.appendChild(convElement);
			}

			clear(currentRuleArray, leftSideArray, rightSideArray, rightSupportArray, confArray, liftArray, levArray, convArray);
			strategy_part1Array.clear();
			strategy_part2Array.clear();
			
			Element taskData3Element = doc.createElement("Taskdata3");
			rootElement.appendChild(taskData3Element);

			currentTask = pluginDoPentaho.getTASKDATA3Rules().split("Best rules found:\n\n")[1];
			for (int i = 0; i < currentTask.split("\n").length; i++) {
				String currentRule = currentTask.split("\n")[i];
				currentRuleArray.add(currentRule);

				String leftSide = currentRule.split(" ==> ")[0];
				leftSideArray.add(leftSide);
				
				String strategy_part1 = leftSide.split("CLIENT=")[1];
				strategy_part1 = strategy_part1.split(" ")[0];
				//System.out.println(strategy_part1);
				strategy_part1Array.add(strategy_part1);

				String rightSide = currentRule.split(" ==> ")[1];
				rightSideArray.add(rightSide);

				String rightSupport = rightSide.split(" ")[rightSide.split(" ").length - 9];
				String strategy_part2 = currentRule.split("PRODUCT=")[1];
				rightSupport = rightSupport.replaceAll("[^\\d.]", "");
				strategy_part2 = strategy_part2.split("  "+rightSupport)[0];
				//System.out.println(strategy2);
				rightSupportArray.add(rightSupport);
				strategy_part2Array.add(strategy_part2);
				
				
				//strategy2 = strategy2.split(" ")[0];
				//System.out.println(strategy2);

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

			for (int i = 0; i < currentRuleArray.size(); i++) {
				// rule elements
				Element ruleElement = doc.createElement("Rule");
				taskData3Element.appendChild(ruleElement);
				int tmp_i = i + 1;
				ruleElement.setAttribute("id", String.valueOf(tmp_i));
				
				//strategy elements
				Element strategy_part1 = doc.createElement("strategy_part1");
				strategy_part1.appendChild(doc.createTextNode(strategy_part1Array.get(i)));
				ruleElement.appendChild(strategy_part1);
				Element strategy_part2 = doc.createElement("strategy_part2");
				strategy_part2.appendChild(doc.createTextNode(strategy_part2Array.get(i)));
				ruleElement.appendChild(strategy_part2);
				
				// support elements
				Element supportElement = doc.createElement("support");
				supportElement.appendChild(doc.createTextNode(rightSupportArray.get(i)));
				ruleElement.appendChild(supportElement);

				// conf elements
				Element confElement = doc.createElement("conf");
				confElement.appendChild(doc.createTextNode(confArray.get(i)));
				ruleElement.appendChild(confElement);

				// lift elements
				Element liftElement = doc.createElement("lift");
				liftElement.appendChild(doc.createTextNode(liftArray.get(i)));
				ruleElement.appendChild(liftElement);

				// lev elements
				Element levElement = doc.createElement("lev");
				levElement.appendChild(doc.createTextNode(convArray.get(i)));
				ruleElement.appendChild(levElement);
			}

			currentRuleArray.clear();
			leftSideArray.clear();
			rightSideArray.clear();
			strategy_part1Array.clear();
			strategy_part2Array.clear();
			rightSupportArray.clear();
			confArray.clear();
			liftArray.clear();
			convArray.clear();

			Element taskData4Element = doc.createElement("Taskdata4");
			rootElement.appendChild(taskData4Element);

			currentTask = pluginDoPentaho.getTASKDATA4Rules().split("Best rules found:\n\n")[1];
			for (int i = 0; i < currentTask.split("\n").length; i++) {
				String currentRule = currentTask.split("\n")[i];
				currentRuleArray.add(currentRule);

				String leftSide = currentRule.split(" ==> ")[0];
				leftSideArray.add(leftSide);

				String rightSide = currentRule.split(" ==> ")[1];
				rightSideArray.add(rightSide);

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

			for (int i = 0; i < currentRuleArray.size(); i++) {
				// Rule elements
				Element ruleElement = doc.createElement("Rule");
				taskData4Element.appendChild(ruleElement);
				int tmp_i = i + 1;
				ruleElement.setAttribute("id", String.valueOf(tmp_i));

				// Support elements
				Element supportElement = doc.createElement("support");
				supportElement.appendChild(doc.createTextNode(rightSupportArray.get(i)));
				ruleElement.appendChild(supportElement);

				// Conf elements
				Element confElement = doc.createElement("conf");
				confElement.appendChild(doc.createTextNode(confArray.get(i)));
				ruleElement.appendChild(confElement);

				// Lift elements
				Element liftElement = doc.createElement("lift");
				liftElement.appendChild(doc.createTextNode(liftArray.get(i)));
				ruleElement.appendChild(liftElement);

				// Lev elements
				Element levElement = doc.createElement("lev");
				levElement.appendChild(doc.createTextNode(convArray.get(i)));
				ruleElement.appendChild(levElement);
			}

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
