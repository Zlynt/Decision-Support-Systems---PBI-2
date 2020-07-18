package sadFase123;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TASKDATA {
	protected static final String projectPath = "C:\\sad\\implementacao";
	protected static final String csvPath = projectPath + "\\CSV";
	protected static final String arffPath = projectPath + "\\ARFF";
	protected static  String xmlPath = projectPath + "\\XML\\TASKDATAResults.xml";
	protected String csvFileLocation;
	protected String arffFileLocation;
	protected String krtFileLocation;
	protected String taskNumber;
	protected String taskName;

	public TASKDATA(String taskNumber) {
		this.taskNumber = taskNumber;
		this.taskName = "TASKDATA" + taskNumber;

		csvFileLocation = csvPath + "\\" + taskName + ".csv";
		arffFileLocation = arffPath + "\\" + taskName + ".arff";
		krtFileLocation = projectPath + "\\" + taskName + ".ktr";

	}

	/**
	 * Generates the CSV file for the current TASKDATA
	 * 
	 * @throws KettleException
	 */
	public void generateCSV() throws KettleException {
		KettleEnvironment.init();
		TransMeta transMeta = new TransMeta(krtFileLocation);
		Trans trans = new Trans(transMeta); // create new transformation object
		trans.execute(null);
		trans.waitUntilFinished();
	}

	/**
	 * Convert a csv file into arff
	 * 
	 * @return True, if the file was converted
	 */
	public boolean csvToArff() {
		/**
		 * Override this function, load your csv file, convert your data and save it has
		 * an ARFF file
		 */
		return false;
	}

	/**
	 * Check if the ktr file exists for the current TASKDATA
	 * 
	 * @return True if the ktr file exists for the current TASKDATA
	 */
	protected boolean ktr_exists() {
		return (new File(krtFileLocation)).exists();
	}

	/**
	 * Check if the CSV file exists for the current TASKDATA
	 * 
	 * @return True if the csv file exists for the current TASKDATA
	 */
	protected boolean csv_exists() {
		return (new File(csvFileLocation)).exists();
	}

	/**
	 * Check if the ARFF file exists for the current TASKDATA
	 * 
	 * @return True if the arff file exists for the current TASKDATA
	 */
	protected boolean arff_exists() {
		return (new File(arffFileLocation)).exists();
	}

	/**
	 * Mine assocation rules
	 * 
	 * @param instances
	 * @param lowerBoundMinSupport
	 * @param minSupport
	 * @return
	 * @throws Exception
	 */
	public String apriori_mine_association_rules(Instances instances) throws Exception {

		Apriori model = new Apriori();
		model.buildAssociations(instances);

		return model.toString();
	}

	/**
	 * Mine assocation rules
	 * 
	 * @param instances
	 * @param lowerBoundMinSupport
	 * @return
	 * @throws Exception
	 */
	public String apriori_mine_association_rules(Instances instances, double lowerBoundMinSupport) throws Exception {

		Apriori model = new Apriori();
		model.setLowerBoundMinSupport(lowerBoundMinSupport);
		model.buildAssociations(instances);

		return model.toString();
	}

	/**
	 * Mine assocation rules
	 * 
	 * @param instances
	 * @param lowerBoundMinSupport
	 * @param minSupport
	 * @return
	 * @throws Exception
	 */
	public String apriori_mine_association_rules(Instances instances, double lowerBoundMinSupport, double minSupport)
			throws Exception {

		Apriori model = new Apriori();
		model.setLowerBoundMinSupport(lowerBoundMinSupport);
		model.setMinMetric(minSupport);
		model.buildAssociations(instances);

		return model.getAssociationRules().toString();
	}

	public Instances load_arff() throws Exception {

		BufferedReader bufferedReader = new BufferedReader(new FileReader(arffFileLocation));
		ArffReader arffReader = new ArffReader(bufferedReader);

		return arffReader.getData();
	}

	public void create_xml_after_apriori() {
		// TODO: Creating a XML file based on the results of the associations for each
		// TASKDATA

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			
			Element rootElement = doc.createElement("company");
			doc.appendChild(rootElement);

			// staff elements
			Element staff = doc.createElement("Staff");
			rootElement.appendChild(staff);

			// set attribute to staff element
			Attr attr = doc.createAttribute("id");
			attr.setValue("1");
			staff.setAttributeNode(attr);

			// shorten way
			// staff.setAttribute("id", "1");

			// firstname elements
			Element firstname = doc.createElement("firstname");
			firstname.appendChild(doc.createTextNode("yong"));
			staff.appendChild(firstname);

			// lastname elements
			Element lastname = doc.createElement("lastname");
			lastname.appendChild(doc.createTextNode("mook kim"));
			staff.appendChild(lastname);

			// nickname elements
			Element nickname = doc.createElement("nickname");
			nickname.appendChild(doc.createTextNode("mkyong"));
			staff.appendChild(nickname);

			// salary elements
			Element salary = doc.createElement("salary");
			salary.appendChild(doc.createTextNode("100000"));
			staff.appendChild(salary);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
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
