package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Member implements Serializable {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private int age;
    private ArrayList<String> activities;
    private ArrayList<String> cart;

    public Member() {
        firstName = "";
        lastName = "";
        email = "";
        password = "";
        gender = "N";
        age = 0;
        activities = new ArrayList<String>();
        cart = new ArrayList<String>();
    }

    public Member(String fName, String lName, String memEmail, String memPwd, String memGender, int memAge, String[] memActivities, String[] memCart) {
        firstName = fName;
        lastName = lName;
        email = memEmail;
        password = memPwd;
        gender = memGender;
        age = memAge;
        activities = new ArrayList<String>(Arrays.asList(memActivities));
        cart = new ArrayList<String>(Arrays.asList(memCart));
    }

    public void setFirstName(String fName) {
        if (fName == null) {
            firstName = "";
        } else {
            firstName = fName;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lName) {
        if (lName == null) {
            lastName = "";
        } else {
            lastName = lName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String inEmail) {
        if (inEmail == null) {
            email = "";
        } else {
            email = inEmail;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String inPwd) {
        if (inPwd == null) {
            password = "";
        } else {
            password = inPwd;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setGender(String inGender) {
        if (inGender == null) {
            gender = "N";
        } else {
            gender = inGender;
        }
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

    public void setActivities(String[] inActivities) {
        if (inActivities == null) {
            activities = new ArrayList<String>();
        } else {
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

    //TBD - Recommend this return an ArrayList
    public String[] getActivities() {
        return activities.toArray(new String[activities.size()]);
    }
    //TBD
//	public ArrayList<String> getActivities() {
//            return activities;
//        }

    //TBD - Is this actually needed?
    public String getActivity(int index) {
        String actStr = "";
        if (index < activities.size()) {
            actStr = activities.get(index);
        }
        return actStr;
    }

    public void addToCart(String inActivity) {
        cart.add(inActivity);
    }

    public void removeFromCart(String activity) {
        cart.remove(activity);
    }

    public void clearCart() {
        cart.clear();
    }

    public void checkoutCart() {
        for (String actStr : cart) {
            addActivity(actStr);
            removeFromCart(actStr);
        }
    }

    //TBD - Recommend this return an ArrayList
    public String[] getCart() {
        return cart.toArray(new String[cart.size()]);
    }
    //TBD
//        public ArrayList<String> getCart() {
//            return cart;
//        }
}