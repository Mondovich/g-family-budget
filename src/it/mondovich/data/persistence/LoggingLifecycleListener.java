package it.mondovich.data.persistence;

import it.mondovich.data.PMFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.jdo.JDOUserException;
import javax.jdo.Query;
import javax.jdo.annotations.Unique;
import javax.jdo.listener.CreateLifecycleListener;
import javax.jdo.listener.DeleteLifecycleListener;
import javax.jdo.listener.InstanceLifecycleEvent;
import javax.jdo.listener.LoadLifecycleListener;
import javax.jdo.listener.StoreLifecycleListener;
import javax.jdo.spi.PersistenceCapable;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.appengine.api.datastore.Key;

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
		PersistenceCapable obj = ((PersistenceCapable) event.getSource());
		Field[] f = obj.getClass().getDeclaredFields();
		for (Field field : f) {
			if (field.isAnnotationPresent(Unique.class)) {
				try {
					Query q = PMFactory.get().getPersistenceManager().newQuery(obj.getClass());
					Method m = obj.getClass().getMethod("get"+StringUtils.capitalize(field.getName()), new Class[0]);
					Method getKey = obj.getClass().getMethod("getKey", new Class[0]);
					Key key = (Key) getKey.invoke(obj);
					q.setResult("count(this)");
					if (key == null) {
						q.setFilter(field.getName() + " == param");
						q.declareParameters("String param");
	 				} else {
	 					q.setFilter(field.getName() + " == param && key != id");
						q.declareParameters("String param, com.google.appengine.api.datastore.Key id");
	 				}
					Integer n = (Integer) ((key == null) ? q.execute(m.invoke(obj)) : q.execute(m.invoke(obj), getKey.invoke(obj)));
					if (n != null && n > 0) {
						throw new JDOUserException("Field " + field.getName() + " is not unique");
					}
				} catch (IllegalArgumentException e) {
					log.error(e);
				} catch (IllegalAccessException e) {
					log.error(e);
				} catch (SecurityException e) {
					log.error(e);
				} catch (NoSuchMethodException e) {
					log.error(e);
				} catch (InvocationTargetException e) {
					log.error(e);
				} catch (JDOUserException e) {
					throw e;
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
	}

	public void postStore(InstanceLifecycleEvent event) {
		log.info("Lifecycle : postStore for "
				+ ((PersistenceCapable) event.getSource()).jdoGetObjectId());
	}
}