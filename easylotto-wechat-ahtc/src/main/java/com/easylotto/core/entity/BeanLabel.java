package com.easylotto.core.entity;



public class BeanLabel {
	
	private Object value;
	private String label;
	
	public BeanLabel(Object value,String label){
		this.value = value;
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	

}
