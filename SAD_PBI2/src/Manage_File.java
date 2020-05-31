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
	
	
	int missingFiles[];
	
	public void CheckFiles()
	{
		System.out.println("Checking the existance of the first path");
		if (fileOrigin.exists())
		{
			System.out.println("SAD Folder exists");
			if(filePBI.exists())
			{
				System.out.println("PBI Folder exists");
				if(fileEclipse.exists())
				{
					System.out.println("Eclipse Folder exists");
				}
				else 
				{
					System.out.println("Don't exist Eclipse Folder");
				}
				
				if(filePentaho.exists())
				{
					System.out.println("Pentaho Folder exists");
				}
				else 
				{
					System.out.println("Don't exist Pentaho Folder");
				}
				
				if(fileWeka.exists())
				{
					System.out.println("Weka Folder exists");
				}
				else 
				{
					System.out.println("Don't exist Weka Folder");
					
				}
				
				if(fileImplementation.exists())
				{
					System.out.println("Implementation Folder exists");
				}
				else 
				{
					System.out.println("Don't exist implementacao Folder");
				}
				
				if(fileConfig.exists())
				{
					System.out.println("Config Folder exists");
				}
				else
				{
					System.out.println("Don't exist config Folder");
				}
			}
			else
			{
				System.out.println("Don't exist PBI Folder");
				System.out.println("Creating new files...");
			}
		}
		else
		{
			System.out.println("Don't exist SAD Folder");
			System.out.println("Creating new files...");
		}
	}
	
	public void CreateNewFile()
	{
		
	}
}
