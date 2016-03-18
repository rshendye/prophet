package com.prophet.datatypes;

/**
 * This class aims to store two objects which are related to each other in some way 
 * The class does not determine what the relationship is, the users are expected 
 * to know what the objects stand for. 
 * @author rshendye
 *
 */
public class Pair<T, V> {
	T first;
	V second;
	
	public Pair (T first, V second) {
		this.first = first;
		this.second = second;
	}

	public T getFirst() {
		return first;
	}

	public void setFirst(T first) {
		this.first = first;
	}

	public V getSecond() {
		return second;
	}

	public void setSecond(V second) {
		this.second = second;
	}

	public boolean hasFirst() {
		return first != null;
	}

	public boolean hasSecond() {
		return second != null;
	}
}
