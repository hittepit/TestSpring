package be.fabrice.testspring.properties;

public class ClassicalPropertyInjectionBean {
	private String name;
	private int age;
	private String unknown;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getUnknown() {
		return unknown;
	}
	public void setUnknown(String unknown) {
		this.unknown = unknown;
	}
}
