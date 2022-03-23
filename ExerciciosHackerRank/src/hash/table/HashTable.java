package hash.table;

import java.util.ArrayList;
import java.util.List;

public class HashTable {
	
	List<String> dataKeys;
	List<Integer> dataValues;
	
	public HashTable() {
		this.dataKeys = new ArrayList<>();
		this.dataValues = new ArrayList<>();
	}
	
	private Integer _hash(String key) {
		int hash = 0;
		for (int i=0; i<key.length(); i++) {
			hash = (hash + key.charAt(i) * i ) % 50;
		}
		return hash;
	}
	
	public Integer get(String key) {
		int index = _hash(key);
		return this.dataValues.get(index);
	}
	
	public void put(String key, Integer value) {
		int index = _hash(key);
		this.dataKeys.add(index, key);
		this.dataValues.add(index, value);
	}
	
	public List<String> keys(){
		return this.dataKeys;
	}
}
