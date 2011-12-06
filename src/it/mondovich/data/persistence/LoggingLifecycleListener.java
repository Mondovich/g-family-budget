package it.mondovich.data.persistence;

import javax.jdo.listener.CreateLifecycleListener;
import javax.jdo.listener.DeleteLifecycleListener;
import javax.jdo.listener.InstanceLifecycleEvent;
import javax.jdo.listener.LoadLifecycleListener;
import javax.jdo.listener.StoreLifecycleListener;
import javax.jdo.spi.PersistenceCapable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoggingLifecycleListener implements CreateLifecycleListener,
		DeleteLifecycleListener, LoadLifecycleListener, StoreLifecycleListener {
	
	private static Log log = LogFactory.getLog(LoggingLifecycleListener.class);
	
	public void postCreate(InstanceLifecycleEvent event) {
		log.info("Lifecycle : create for "
				+ ((PersistenceCapable) event.getSource()).jdoGetObjectId());
	}

	public void preDelete(InstanceLifecycleEvent event) {
		log.info("Lifecycle : preDelete for "
				+ ((PersistenceCapable) event.getSource()).jdoGetObjectId());
	}

	public void postDelete(InstanceLifecycleEvent event) {
		log.info("Lifecycle : postDelete for "
				+ ((PersistenceCapable) event.getSource()).jdoGetObjectId());
	}

	public void postLoad(InstanceLifecycleEvent event) {
		log.info("Lifecycle : load for "
				+ ((PersistenceCapable) event.getSource()).jdoGetObjectId());
	}

	public void preStore(InstanceLifecycleEvent event) {
		log.info("Lifecycle : preStore for "
				+ ((PersistenceCapable) event.getSource()).jdoGetObjectId());
	}

	public void postStore(InstanceLifecycleEvent event) {
		log.info("Lifecycle : postStore for "
				+ ((PersistenceCapable) event.getSource()).jdoGetObjectId());
	}
}