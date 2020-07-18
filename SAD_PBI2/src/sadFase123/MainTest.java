package sadFase123;

import org.pentaho.di.core.exception.KettleException;
import java.util.List;
import java.io.File;
import java.util.ArrayList;

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

public class MainTest {

	protected static final String projectPath = "C:\\sad\\implementacao";
	protected static final String xmlPath = projectPath + "\\XML\\TASKDATAResults.xml";
	
	public static void main(String[] args) throws KettleException {
		// TODO Auto-generated method stub
		PluginDoPentaho pluginDoPentaho = new PluginDoPentaho();
		List<String> currentRuleArray = new ArrayList<String>();
		List<String> productTestArray = new ArrayList<String>();
		List<String> leftSideArray = new ArrayList<String>();
		List<String> rightSideArray = new ArrayList<String>();
		List<String> rightSupportArray = new ArrayList<String>();
		List<String> confArray = new ArrayList<String>();
		List<String> liftArray = new ArrayList<String>();
		List<String> levArray = new ArrayList<String>();
		List<String> convArray = new ArrayList<String>();

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();

			// pluginDoPentaho.getTASKDATA1Rules();
			// pluginDoPentaho.getTASKDATA2Rules();
			// pluginDoPentaho.getTASKDATA3Rules();
			String currentTask = pluginDoPentaho.getTASKDATA3Rules().split("Best rules found:\n\n")[1];
			for (int i = 0; i < currentTask.split("\n").length; i++) {
				String currentRule = currentTask.split("\n")[i];
				currentRuleArray.add(currentRule);
				// System.out.println(currentRule);

				String producttest = currentRule.split("PRODUCT=")[1];
				productTestArray.add(producttest);

				String leftSide = currentRule.split(" ==> ")[0];
				leftSideArray.add(leftSide);
				// System.out.println(leftSide);

				String rightSide = currentRule.split(" ==> ")[1];
				rightSideArray.add(rightSide);
				// System.out.println(rightSide);

				String rightSupport = rightSide.split(" ")[rightSide.split(" ").length - 9];
				rightSupport = rightSupport.replaceAll("[^\\d.]", "");
				rightSupportArray.add(rightSupport);
				// System.out.println(rightSupport);

				String conf = rightSide.split(" ")[rightSide.split(" ").length - 5];
				conf = conf.replaceAll("[^\\d.]", "");
				confArray.add(conf);
				// System.out.println(conf);

				String lift = rightSide.split(" ")[rightSide.split(" ").length - 4];
				lift = lift.replaceAll("[^\\d.]", "");
				liftArray.add(lift);
				// System.out.println(lift);

				String lev = rightSide.split(" ")[rightSide.split(" ").length - 3];
				lev = lev.replaceAll("[^\\d.]", "");
				levArray.add(lev);
				// System.out.println(lev);

				String conv = rightSide.split(" ")[rightSide.split(" ").length - 1];
				conv = conv.replaceAll("[^\\d.]", "");
				convArray.add(conv);
				// System.out.println(conv);

				String tmp_rightSupport = "";
				for (int p = 0; p < rightSide.split(" ").length - 9; p++) {
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

			for (int i = 0; i < rightSideArray.size(); i++) {
				System.out.println(rightSideArray.get(i));
			}
			for (int i = 0; i < currentRuleArray.size(); i++) {
				System.out.println(currentRuleArray.get(i));
			}

			for (int i = 0; i < rightSupportArray.size(); i++) {
				System.out.println(rightSupportArray.get(i));
			}

			for (int i = 0; i < confArray.size(); i++) {
				System.out.println(confArray.get(i));
			}

			for (int i = 0; i < liftArray.size(); i++) {
				System.out.println(liftArray.get(i));
			}

			for (int i = 0; i < levArray.size(); i++) {
				System.out.println(levArray.get(i));
			}
			
			Element rootElement = doc.createElement("Taskdata");
			doc.appendChild(rootElement);
			
			for (int i = 0; i < currentRuleArray.size(); i++)
			{
				// staff elements
				Element staff = doc.createElement("Rule");
				rootElement.appendChild(staff);
				int tmp_i = i + 1;
				
				// set attribute to staff element
//				Attr attr = doc.createAttribute("id");
//				attr.setValue(String.valueOf(tmp_i));
//				staff.setAttributeNode(attr);

				// shorten way
				staff.setAttribute("id", String.valueOf(tmp_i));

				
				// firstname elements
				Element firstname = doc.createElement("support");
				firstname.appendChild(doc.createTextNode(rightSupportArray.get(i)));
				staff.appendChild(firstname);

				// lastname elements
				Element lastname = doc.createElement("conf");
				lastname.appendChild(doc.createTextNode(confArray.get(i)));
				staff.appendChild(lastname);

				// nickname elements
				Element nickname = doc.createElement("lift");
				nickname.appendChild(doc.createTextNode(liftArray.get(i)));
				staff.appendChild(nickname);

				// salary elements
				Element salary = doc.createElement("lev");
				salary.appendChild(doc.createTextNode(convArray.get(i)));
				staff.appendChild(salary);
			}
			
			

			

			// write the content into xml file
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			//Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(xmlPath));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");
			
			
			
			
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

}
