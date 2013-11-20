package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Member implements Serializable
{
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String gender;
	private int age;
	private ArrayList<String> activities;
	
	public Member() {
		firstName = "";
		lastName = "";
		email = "";
		password = "";
		gender = "N";
		age = 0;
		activities = new ArrayList<String>();
	}
	
	public Member(String fName, String lName, String memEmail, String memPwd, String memGender, int memAge, String[] memActivities ) {
		firstName = fName;
		lastName = lName;
		email = memEmail;
		password = memPwd;
		gender = memGender;
		age = memAge;
		activities = new ArrayList<String> (Arrays.asList(memActivities));
	}
	
	public void setFirstName(String fName) {
		if (fName == null)
			firstName = "";
		else
			firstName = fName;
	}
	
	public String getFirstName()	{
		return firstName;
	}
	
	public void setLastName(String lName) {
		if (lName == null)
			lastName = "";
		else
			lastName = lName;
	}
	
	public String getLastName()	{
		return lastName;
	}
	
	public void setEmail(String inEmail) {
		if (inEmail == null)
			email = "";
		else
			email = inEmail;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPassword(String inPwd) {
		if (inPwd == null)
			password = "";
		else
			password = inPwd;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setGender(String inGender) {
		if (inGender == null)
			gender = "N";
		else
			gender = inGender;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setAge(int inAge) {
		age = inAge;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setActivities(String [] inActivities) {
		if (inActivities == null) {
			activities = new ArrayList<String>();
		}
		else {
			activities = new ArrayList<String>(Arrays.asList(inActivities));
		}
	}
	
	public void addActivity(String inActivity) {
		activities.add(inActivity);
	}
	
	public void removeActivity(int index) {
		activities.remove(index);
	}
	
	public void clearActivities() {
		activities.clear();
	}
	
	public String[] getActivities() {
		return activities.toArray(new String[activities.size()]);
	}
	
	public String getActivity (int index) {
		int numActivities = activities.size();
		if (numActivities > index) {
			return activities.toArray(new String[numActivities])[index];
		}
		else {
			return "";
		}
	}
}