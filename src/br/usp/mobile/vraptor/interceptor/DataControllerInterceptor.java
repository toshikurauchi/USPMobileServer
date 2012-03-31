package br.usp.mobile.vraptor.interceptor;

import org.apache.log4j.Logger;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.usp.mobile.factory.DataController;

@Intercepts
public class DataControllerInterceptor implements Interceptor {
	private static final Logger logger = Logger.getLogger(DataControllerInterceptor.class);

	private final DataController controller;

	public DataControllerInterceptor(DataController controller) {
		this.controller = controller;
	}

	public void intercept(InterceptorStack stack, ResourceMethod method, Object instance) throws InterceptionException {
		try {
			logger.debug("iniciando transacao");
			controller.beginTransaction();
			stack.next(method, instance);
			logger.debug("commitando");
			controller.commit();
			logger.debug("commitado");
		} catch(Exception e) {
			if (controller.hasTransaction()) {
				logger.warn("fazendo rollback por causa de exception " + method, e);
				controller.rollback();
				throw new InterceptionException(e);
			}
		}
		finally {
			try {
				controller.close();
			} catch (RuntimeException e) {
				// log and throw, dada a importancia
				logger.warn("problemas no close " + method, e);
				throw e;
			}
		}
	}

	public boolean accepts(ResourceMethod method) {
		return true;
	}
}
