package com.acloudfan.cos.test;

import java.io.File;
import java.util.List;

import org.openstack4j.model.storage.object.SwiftObject;

import com.acloudfan.cos.util.OpenstackStorage;

/**
 * 
 * @author acloudfan.com
 * 
 * Sample for demonstrating the usage of IBM object storage service.
 * 
 * Credentials are HARD CODED - for testing. For production replace hard coded values with
 * environment variables.
 * 
 */
public class TestCos {
	
	// VCAP_SERVICES  credentials.userId
	private static String userId 		= "CHANGE THIS"; 
	// VCAP_SERVICES  credentials.domainName
	private static String domainName 	= "CHANGE THIS"; 
	// VCAP_SERVICES  credentials.project
	private static String project 		= 	"CHANGE THIS";	
	// VCAP_SERVICES  credentials.password
	private static String password		= "CHANGE THIS";	
	// Add /v3 to the URL in VCAP_SERVICES  auth_url
	private static String auth_url 		= "CHANGE THIS" +"/v3";

	public static void main(String[] args)
	throws Exception {
	
		/** 1. Create an instance of the utility class **/ 
		OpenstackStorage os = new OpenstackStorage(auth_url, userId, password, project, domainName);
		
		// List the containers
		List<String> containers = os.containers();
		System.out.println("1.Containers List = "+containers );
		
		/** 2. Create a new container named CloudContainer **/
		//os.createContainer("Second");
		
		/** 3. Create a new object named "sample_file.txt" **/
		
		//String filePath = "C:\\Users\\rajeev\\Desktop\\sample_file.txt";
		
		//os.createObject("Second", "sample_file.txt",filePath); 
		
		//System.out.println("Object Created Successfully");
		
		/** 4. Read the newly created object **/
		
		// SwiftObject so = os.getObject("Second", "sample_file.txt");
		
		// String filePath = "C:\\Users\\rajeev\\Desktop\\sample_file_RECEIVED.txt";
		// so.download().writeToFile(new File(filePath));
		
		// System.out.println("Object Written to Desktop");
	}
}
