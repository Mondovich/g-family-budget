package it.mondovich;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EMFactory {
	
	private static final EntityManagerFactory emfInstance =
	        Persistence.createEntityManagerFactory("transactions-optional");

    private EMFactory() {}

    public static EntityManagerFactory get() {
        return emfInstance;
    }

}
