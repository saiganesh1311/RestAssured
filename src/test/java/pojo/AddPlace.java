package pojo;

import java.util.List;

public class AddPlace {
	
	private GetLocation location;
	private int accuracy;
	private String name;
	private String phone_number;
	private String address;
	private List<String> types;
	private String website;
	private String language;
	public GetLocation getLocation() {
		return location;
	}
	public void setLocation(GetLocation location) {
		this.location = location;
	}
	public int getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<String> getType() {
		return types;
	}
	public void setType(List<String> type) {
		this.types = type;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
		
	}


