package business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ActivityDataStore implements java.io.Serializable {

    private ArrayList<Activity> activityDS;

    private final String fileName = "/fabfitfun/activities.txt";
    
    public ActivityDataStore() {

        activityDS = new ArrayList<Activity>();
        
        BufferedReader br = null;
        String line;
        String delims = "[,]";
        try {
            br = new BufferedReader(new FileReader(fileName));         
            
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(delims);
                if (tokens.length >= 6) { 
                    // Set activity fields from file
                    Activity activity = new Activity();
                    activity.setActId(tokens[0]);
                    activity.setName(tokens[1]);
                    activity.setDate(tokens[2]);
                    activity.setCategory(tokens[3]);
                    activity.setOpenings(tokens[4]);
                    activity.setFee(tokens[5]);
                    // Add activity to data store
                    activityDS.add(activity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public ArrayList<Activity> getActivityDS() {
        return activityDS;
    }
    
    public Activity getActivity(String actId) {
        Activity act = new Activity();
        for (Activity activity: activityDS) {
            if (activity.getActId().equalsIgnoreCase(actId)) {
                act = activity;
                break;
            }
        }   
        return act;
    }
    
}
