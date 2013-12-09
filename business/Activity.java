package business;

public class Activity implements java.io.Serializable {

    private String actId;
    private String name;
    private String date;
    private String category;
    private String openings;
    private String fee;

    public Activity() {
        actId = "";
        name = "";
        date = "";
        category = "";
        openings = "";
        fee = "";
    }

    // Getters
    public String getActId() {
        return actId;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public String getOpenings() {
        return openings;
    }

    public String getFee() {
        return "$" + fee;
    }
    
    public int getFeeInt() {
        return Integer.parseInt(fee);
    }
    
    // Setters
    public void setActId(String str) {
        actId = str;
    }

    public void setName(String str) {
        name = str;
    }

    public void setDate(String str) {
        date = str;
    }

    public void setCategory(String str) {
        category = str;
    }

    public void setOpenings(String str) {
        openings = str;
    }

    public void setFee(String str) {
        fee = str;
    }
}
