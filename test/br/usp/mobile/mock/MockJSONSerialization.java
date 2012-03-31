package br.usp.mobile.mock;

import br.com.caelum.vraptor.serialization.JSONSerialization;
import br.com.caelum.vraptor.serialization.NoRootSerialization;
import br.com.caelum.vraptor.serialization.Serializer;

public class MockJSONSerialization implements JSONSerialization, Serializer {
	private Object toSerialize;

	public Object getToSerialize() {
		return toSerialize;
	}
	
	public boolean accepts(String arg0) {
		return false;
	}

	public <T> Serializer from(T arg0, String arg1) {
		return this;
	}

	public <T> Serializer from(T toSerialize) {
		this.toSerialize = toSerialize;
		return this;
	}

	public JSONSerialization indented() {
		return this;
	}

	public <T> NoRootSerialization withoutRoot() {
		return this;
	}

	public Serializer exclude(String... arg0) {
		return this;
	}

	public Serializer include(String... arg0) {
		return this;
	}

	public Serializer recursive() {
		return this;
	}

	public void serialize() {
		
	}

}
