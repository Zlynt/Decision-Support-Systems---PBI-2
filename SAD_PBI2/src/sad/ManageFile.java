package sad;
import java.io.*;


public class ManageFile {

	final static String origin = "C:\\sad";
	final static String pbiPath = "C:\\sad\\PBI";
	final static String eclipsePath = "C:\\sad\\PBI\\Eclipse";
	final static String pentahoPath = "C:\\sad\\PBI\\Pentaho";
	final static String wekaPath = "C:\\sad\\PBI\\Weka";
	final static String implementationPath = "C:\\sad\\implementacao";
	final static String configPath = "C:\\sad\\config";
	
	File fileOrigin 		= new File(origin);
	File filePBI 			= new File(pbiPath);
	File fileEclipse 		= new File(eclipsePath);
	File filePentaho 		= new File(pentahoPath);
	File fileWeka 			= new File(wekaPath);
	File fileImplementation = new File(implementationPath);
	File fileConfig 		= new File(configPath);
	
	
	boolean originFile, PBIFile, eclipseFile, pentahoFile, wekaFile, implementationFile, configFile;
	
	public void CheckFiles()
	{
		System.out.println("Checking the existance of the first path");
		if (fileOrigin.exists())
		{
			originFile = true;
			System.out.println("SAD Folder exists");
			if(filePBI.exists())
			{
				PBIFile = true;
				System.out.println("PBI Folder exists");
				if(fileEclipse.exists())
				{
					eclipseFile = true;
					System.out.println("Eclipse Folder exists");
				}
				else 
				{
					eclipseFile = false;
					System.out.println("Don't exist Eclipse Folder");
				}
				
				if(filePentaho.exists())
				{
					pentahoFile = true;
					System.out.println("Pentaho Folder exists");
				}
				else 
				{
					pentahoFile = false;
					System.out.println("Don't exist Pentaho Folder");
				}
				
				if(fileWeka.exists())
				{
					wekaFile = true;
					System.out.println("Weka Folder exists");
				}
				else 
				{
					wekaFile = false;
					System.out.println("Don't exist Weka Folder");
					
				}
				
				if(fileImplementation.exists())
				{
					implementationFile = true;
					System.out.println("Implementation Folder exists");
				}
				else 
				{
					implementationFile = false;
					System.out.println("Don't exist implementacao Folder");
				}
				
				if(fileConfig.exists())
				{
					configFile = true;
					System.out.println("Config Folder exists");
				}
				else
				{
					configFile = false;
					System.out.println("Don't exist config Folder");
				}
			}
			else
			{
				PBIFile = false;
				System.out.println("Don't exist PBI Folder");
				System.out.println("Creating new files...");
			}
		}
		else
		{
			originFile = false;
			System.out.println("Don't exist SAD Folder");
			System.out.println("Creating new files...");
		}
	}
	
	public void CreateNewFile()	throws IOException
	{	
		boolean result_origin 			= false;
		boolean result_pbi 				= false;
		boolean result_eclipse 			= false;
		boolean result_pentaho 			= false;
		boolean result_weka 			= false;
		boolean result_implementation 	= false;
		boolean result_config 			= false;
		
		if (originFile == false)
		{
			System.out.println("Creating all files...");
			System.out.println("Creating directory: " + fileOrigin.getName());
			System.out.println("Creating directory: " + filePBI.getName());
			System.out.println("Creating directory: " + fileEclipse.getName());
			System.out.println("Creating directory: " + filePentaho.getName());
			System.out.println("Creating directory: " + fileWeka.getName());
			System.out.println("Creating directory: " + fileImplementation.getName());
			System.out.println("Creating directory: " + fileConfig.getName());
			try
			{
					fileOrigin.mkdir();
					filePBI.mkdir();
					fileEclipse.mkdir();
					filePentaho.mkdir();
					fileWeka.mkdir();
					fileImplementation.mkdir();
					fileConfig.mkdir();
					
					result_origin = true;
					result_pbi = true;
					result_eclipse = true;
					result_pentaho = true;
					result_weka = true;
					result_implementation = true;
					result_config = true;
			}
			catch(SecurityException se)
			{
				 
			}
			
			
			System.out.println("Done!");
			System.out.println("Please create the rest of the files!");
		}
		else 
		{
			if (implementationFile == false)
			{
				System.out.println("Creating implementation file...");
				System.out.println("Creating directory: " + fileImplementation.getName());
				try
				{
						fileImplementation.mkdir();
						result_config = true;
				}
				catch(SecurityException se)
				{
					 
				}
				System.out.println("Done!");
				System.out.println("Please create the rest of the files!");
			}
			
			if (configFile == false)
			{
				System.out.println("Creating config file...");
				System.out.println("Creating directory: " + fileConfig.getName());
				try
				{
						fileConfig.mkdir();
						result_config = true;
				}
				catch(SecurityException se)
				{
					 
				}
				System.out.println("Done!");
				System.out.println("Please create the rest of the files!");
			}
			
			if (PBIFile == false)
			{
				System.out.println("Creating the rest of the files...");
				System.out.println("Creating directory: " + filePBI.getName());
				try
				{
					filePBI.mkdir();
					fileEclipse.mkdir();
					filePentaho.mkdir();
					fileWeka.mkdir();

					result_pbi = true;
					result_eclipse = true;
					result_pentaho = true;
					result_weka = true;

				}
				catch(SecurityException se)
				{
					 
				}
				System.out.println("Done!");
				System.out.println("Please create the rest of the files!");
			}
			else if (PBIFile != false)
			{
				if(eclipseFile == false)
				{
					System.out.println("Creating Eclipse file...");
					System.out.println("Creating directory: " + fileEclipse.getName());
					try
					{
							fileEclipse.mkdir();
							result_eclipse = true;
					}
					catch(SecurityException se)
					{
						 
					}
					System.out.println("Done!");
					System.out.println("Please create the rest of the files!");
				}
				
				
				if (pentahoFile == false)
				{
					System.out.println("Creating Pentaho file...");
					System.out.println("Creating directory: " + filePentaho.getName());
					try
					{
							filePentaho.mkdir();
							result_pentaho = true;
					}
					catch(SecurityException se)
					{
						 
					}
					System.out.println("Done!");
					System.out.println("Please create the rest of the files!");
				}
				
				
				if (wekaFile == false)
				{
					System.out.println("Creating Weka file...");
					System.out.println("Creating directory: " + fileWeka.getName());
					try
					{
							fileWeka.mkdir();
							result_weka = true;
					}
					catch(SecurityException se)
					{
						 
					}
					System.out.println("Done!");
					System.out.println("Please create the rest of the files!");
				}
			}
			
			
		}
		
	}
}
