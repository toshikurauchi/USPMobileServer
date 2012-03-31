package br.usp.mobile.factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
public class DataController implements ComponentFactory<Session> {
	
	private Session session;
	private Transaction transaction;

	public DataController(SessionFactory factory) {
		session = factory.openSession();
	}
	
	public Session getInstance() {
		return session;
	}

	public void beginTransaction() {
		if (this.hasTransaction()) {
			throw new IllegalStateException("Transação dentro de transação não é suportada");
		}
		this.transaction = session.beginTransaction();
	}

	public boolean hasTransaction() {
		return this.transaction != null;
	}

	public void rollback() {
		if (!hasTransaction()) {
			throw new IllegalStateException("Não existe transação");
		}
		this.transaction.rollback();
		this.transaction = null;
	}

	public void close() {
		this.session.close();
	}

	public void commit() {
		if (!hasTransaction()) {
			throw new IllegalStateException("Não existe transação");
		}
		this.transaction.commit();
		this.transaction = null;
	}
}
