package it.mondovich.data;

import it.mondovich.data.persistence.LoggingLifecycleListener;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public class PMFactory {

	private static final PersistenceManagerFactory pmfInstance = JDOHelper
			.getPersistenceManagerFactory("transactions-optional");
	private static boolean isListenerAdded = false;

	private PMFactory() {
		
	}

	public static PersistenceManagerFactory get() {
		if (!isListenerAdded){
			pmfInstance.addInstanceLifecycleListener(new LoggingLifecycleListener(), null);
			isListenerAdded = true;
		}
		return pmfInstance;
	}

}
