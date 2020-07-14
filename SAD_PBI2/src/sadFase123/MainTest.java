package sadFase123;

import org.pentaho.di.core.exception.KettleException;

public class MainTest {

	public static void main(String[] args) throws KettleException {
		// TODO Auto-generated method stub
		PluginDoPentaho plugin = new PluginDoPentaho();
		
		plugin.getTASKDATA1Rules();
		plugin.getTASKDATA2Rules();
		plugin.getTASKDATA3Rules();
		plugin.getTASKDATA4Rules();
	}

}
