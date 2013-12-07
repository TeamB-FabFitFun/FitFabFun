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
        gender = "";
        age = 0;
        activities = new ArrayList<String>();
        cart = new ArrayList<String>();
    }

    // This assumes all valid members have first names registered
    public boolean isEmpty() {
        return "".equals(firstName);
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

    public ArrayList<String> getActivities() {
        return activities;
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

    public ArrayList<String> getCart() {
        return cart;
    }

    public boolean inCart(String actId) {
        boolean found = false;
        for (String actStr : cart) {
            if (actStr.equals(actId)) {
                found = true;
            }
        }
        return found;
    }

    public boolean inActivities(String actId) {
        boolean found = false;
        for (String actStr : activities) {
            if (actStr.equals(actId)) {
                found = true;
            }
        }
        return found;
    }
}