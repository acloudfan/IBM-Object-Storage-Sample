package com.acloudfan.cos.util;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.openstack4j.api.OSClient;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.common.Payload;
import org.openstack4j.model.common.Payloads;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.storage.object.SwiftObject;
import org.openstack4j.model.storage.object.options.ObjectLocation;
import org.openstack4j.openstack.OSFactory;

/**
 * @author acloudfan.com
 * 
 * Utility object for carrying out CRUD on the openstack storage.
 */
public class OpenstackStorage {

	final	private	OSClient	os;
	
	public	OpenstackStorage(String url, String userId, String password, String project, String domainName)
	throws Exception {
		
		Identifier domainIdentifier = Identifier.byName(domainName);
		Identifier projectIdentifier= Identifier.byName(project);
		
		os = OSFactory.builderV3()
				.endpoint(url)
				.credentials(userId, password)
				.scopeToProject(projectIdentifier, domainIdentifier)
				.authenticate();
	}
	
	/**
	 * Returns the list of containers under the account
	 */
	public	List containers(){
		return os.objectStorage().containers().list();
	}
	
	/**
	 * Returns the list of objects under the account->container
	 */
	public	List objects(String container){
		return os.objectStorage().objects().list(container);
	}
	
	/**
	 * Create a container
	 */
	public	ActionResponse	createContainer(String name){
		return os.objectStorage().containers().create(name);
	}
	
	/**
	 * Delete a container
	 */
	public	ActionResponse	deleteContainer(String name){
		return os.objectStorage().containers().delete(name);
	}
	
	/**
	 * Create an object in a container - file path
	 */
	public String createObject(String container, String obj, String filePath){
		Payload<File> payload = Payloads.create(new File(filePath));
		return os.objectStorage().objects().put(container, obj, payload);
	}
	
	/**
	 * Create an object in a container - file path
	 */
	public String createObject(String container, String obj, InputStream stream){
		Payload<InputStream> payload = Payloads.create(stream);
		return os.objectStorage().objects().put(container, obj, payload);
	}
	
	public SwiftObject getObject(String container, String obj){
		ObjectLocation objLocation = ObjectLocation.create(container, obj);
		return os.objectStorage().objects().get(objLocation);
	}
}
