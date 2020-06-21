import java.io.File;

public class TASKDATA {
	private static final String csv_path  = "C:\\sad\\implementacao\\CSV";
	private static final String arff_path = "C:\\sad\\implementacao\\ARFF";
	private String taskdata_name;
	
	public TASKDATA(int taskdata_number) {
		this.taskdata_name = "TASKDATA"+taskdata_number;
	}
	
	//Check if CSV file exists
	protected boolean csv_exists() {
		File tmpDir = new File(csv_path+"\\"+taskdata_name);
		return tmpDir.exists();
	}
	
	//Check if arff file exists
	protected boolean arff_exists() {
		File tmpDir = new File(arff_path+"\\"+taskdata_name);
		return tmpDir.exists();
	}
}
