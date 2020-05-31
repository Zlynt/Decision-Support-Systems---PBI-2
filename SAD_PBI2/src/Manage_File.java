import java.io.*;
import java.nio.*;
import java.nio.file.Files;


public class Manage_File {

	final static String origin = "C:\\sad";
	final static String pbiPath = "C:\\sad\\PBI";
	final static String eclipsePath = "C:\\sad\\PBI\\Eclipse";
	final static String pentahoPath = "C:\\sad\\PBI\\Pentaho";
	final static String wekaPath = "C:\\sad\\PBI\\Weka";
	final static String implementationPath = "C:\\sad\\PBI\\implementacao";
	final static String configPath = "C:\\sad\\PBI\\config";
	
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
	
	public void CreateNewFile()
	{
		if (originFile == false)
		{
			System.out.println("Creating all files...");
			//process of creating file
			System.out.println("Done!");
		}
		else if (PBIFile == false)
		{
			System.out.println("Creating the rest of the files...");
			//process of creating file
			System.out.println("Done!");
		}
		else if (PBIFile == false)
		{
			if(eclipseFile == false)
			{
				System.out.println("Creating Eclipse file...");
				//process of creating file
				System.out.println("Done!");
			}
			
			
			if (pentahoFile == false)
			{
				System.out.println("Creating Pentaho file...");
				//process of creating file
				System.out.println("Done!");
			}
			
			
			if (wekaFile == false)
			{
				System.out.println("Creating Weka file...");
				//process of creating file
				System.out.println("Done!");
			}
			
			
			if (implementationFile == false)
			{
				System.out.println("Creating implementation file...");
				//process of creating file
				System.out.println("Done!");
			}
			
			
			if (configFile == false)
			{
				System.out.println("Creating config file...");
				//process of creating file
				System.out.println("Done!");
			}
		}
		else 
		{
			System.out.println("Nothing to create files...");
			System.out.println("Clear!");
		}
	}
}
