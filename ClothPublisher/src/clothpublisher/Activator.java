package clothpublisher;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	ServiceRegistration publishServiceRegistration;


	public void start(BundleContext context) throws Exception {

		System.out.println("Cloth Publisher Started");
		ClothServicePublish servicePublish = new ClothServicePublishImpl();
		publishServiceRegistration = context.registerService(ClothServicePublish.class.getName(), servicePublish, null);
	}

	public void stop(BundleContext context) throws Exception {

		System.out.println("Publisher Stoped");
		publishServiceRegistration.unregister();
	}

}