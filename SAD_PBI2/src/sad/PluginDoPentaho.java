package sad;

import java.io.IOException;

import org.pentaho.di.core.exception.KettleException;

import weka.core.Instances;

public class PluginDoPentaho {

	public PluginDoPentaho() {
		
	}
	
	public String getStuff() {
		return "hum,";
	}

	public String getTASKDATA1Rules() {
		return "I'm a Dummy Rule from TASKDATA1";
	}
	
	public String getTASKDATA2Rules() {
		return "I'm a Dummy Rule from TASKDATA2";
	}
	
	public String getTASKDATA3Rules() {
		return "I'm a Dummy Rule from TASKDATA3";
	}
	
	public String getTASKDATA4Rules() {
		return "I'm a Dummy Rule from TASKDATA4";
	}
}
