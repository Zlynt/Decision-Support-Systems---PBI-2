package TASKDATA3;

import java.util.LinkedList;

public class AttributeList {
	private LinkedList<String> list;

	public AttributeList() {
		list = new LinkedList<String>();
	}

	public void addAttribute(String attribute) {
		if (!list.contains(attribute))
			list.add(attribute);
	}

	public LinkedList<String> getAttributeList() {
		return list;
	}
}
