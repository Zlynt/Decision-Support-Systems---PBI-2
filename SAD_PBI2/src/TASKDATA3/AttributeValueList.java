package TASKDATA3;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class AttributeValueList {
	private LinkedList<String> list;

	public AttributeValueList() {
		list = new LinkedList<String>();
	}

	public void add(String value) {
		list.add(value);
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return Collator.getInstance().compare(o1, o2);
			}
		});
	}

	public void remove(String value) {
		list.remove(value);
	}

	public LinkedList<String> getList() {
		return list;
	}
	
	public boolean contains(String value) {
		return list.contains(value);
	}
}
