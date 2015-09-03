package com.ra.familia.dao;

public class Pair<K,V> {
	private K key;
	private V value;

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}
	
	
	public void put(K key,V value)
	{
		this.key = key;
		this.value =value;
	}

}
