package business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ActivityDataBase implements java.io.Serializable {

    private final String fileName = "/fabfitfun/activities.txt";
    private String dbName = "/fabfitfun/fffDB";
    private static boolean initialized = false;

    public static boolean isInitialized() {
        return initialized;
    }

    public ActivityDataBase() {
        if (!initialized) {
            initialized = true;

            try {
                String driver = "org.apache.derby.jdbc.EmbeddedDriver";
                String connectionURL = "jdbc:derby:" + dbName + ";create=true";
                String createString =
                        "CREATE TABLE activitiesTbl ("
                        + "ACTID VARCHAR(30) PRIMARY KEY, "
                        + "NAME VARCHAR(30), "
                        + "DATE VARCHAR(30), "
                        + "CATEGORY VARCHAR(30), "
                        + "OPENINGS VARCHAR(6), "
                        + "FEE VARCHAR(50))";

                Class.forName(driver);
                Connection conn = DriverManager.getConnection(connectionURL);
                Statement stmt = conn.createStatement();

                // Drop the table if you wish to reset it.  Otherwise, keep this commented.
                //String dropString = "DROP TABLE activitiesTbl";
                //stmt.executeUpdate(dropString);

                try {
                    stmt.executeUpdate(createString);
                } catch (SQLException e) {
                    // "X0Y32" mean the table already exists.  That's ok.
                    if (!e.getSQLState().equals("X0Y32")) {
                        throw e;
                    }
                }

                // Populate table from activities.txt if it is empty
                Statement stmt2 = conn.createStatement();
                ResultSet rs = stmt2.executeQuery(
                        "select * from activitiesTbl");
                if (!rs.next()) {
                    // Table was empty
                    buildTable(conn);
                }

                rs.close();
                conn.close();
            } catch (Exception e) {
                initialized = false;
                e.printStackTrace();
            }
        }
    }

    public void buildTable(Connection conn) {

        BufferedReader br = null;
        String line;
        String delims = "[,]";
        try {
            PreparedStatement psInsert =
                    conn.prepareStatement(
                    "insert into activitiesTbl values (?,?,?,?,?,?)");

            br = new BufferedReader(new FileReader(fileName));

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(delims);
                if (tokens.length >= 6) {
                    // Set activity fields from file
                    psInsert.setString(1, tokens[0]);
                    psInsert.setString(2, tokens[1]);
                    psInsert.setString(3, tokens[2]);
                    psInsert.setString(4, tokens[3]);
                    psInsert.setString(5, tokens[4]);
                    psInsert.setString(6, tokens[5]);

                    psInsert.executeUpdate();
                }
            }
        } catch (Exception e) {
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
    
    public Activity getActivity(String actId) {
        Activity activity = null;
        try {
            String connectionURL = "jdbc:derby:" + dbName;
            Connection conn = DriverManager.getConnection(connectionURL);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select * from activitiesTbl where actid = '"
                    + actId + "'");

            if (rs.next()) {
                activity = new Activity();
                activity.setActId(rs.getString("ACTID"));
                activity.setName(rs.getString("NAME"));
                activity.setDate(rs.getString("DATE"));
                activity.setCategory(rs.getString("CATEGORY"));
                activity.setOpenings(rs.getString("OPENINGS"));
                activity.setFee(rs.getString("FEE"));
            }

            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activity;
    }
    
     public ArrayList<Activity> getAllActivities() {
        ArrayList<Activity> activitiesList = new ArrayList<Activity>();
        
        try {
            String connectionURL = "jdbc:derby:" + dbName;
            Connection conn = DriverManager.getConnection(connectionURL);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select * from activitiesTbl");

            while (rs.next()) {
                Activity activity = new Activity();
                activity.setActId(rs.getString("ACTID"));
                activity.setName(rs.getString("NAME"));
                activity.setDate(rs.getString("DATE"));
                activity.setCategory(rs.getString("CATEGORY"));
                activity.setOpenings(rs.getString("OPENINGS"));
                activity.setFee(rs.getString("FEE"));
                
                activitiesList.add(activity);
            }

            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activitiesList;
    }
     
    // Reduces the number of openings by one for the activity
    public boolean decrementOpenings(String actId) {
        boolean success = false;
        try {
            String connectionURL = "jdbc:derby:" + dbName;
            Connection conn = DriverManager.getConnection(connectionURL);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select * from activitiesTbl where actid = '"
                    + actId + "'");

            if (rs.next()) {
                success = true;
                
                String oldOpenings = rs.getString("OPENINGS");
                int newOpeningsInt = Integer.parseInt(oldOpenings) - 1;
                String newOpenings = "" + newOpeningsInt;
                
                PreparedStatement psUpdate = conn.prepareStatement(
                        "UPDATE activitiesTbl SET "
                        + "OPENINGS = ? "
                        + "WHERE ACTID = ?");

                try {
                    psUpdate.clearParameters();
                    psUpdate.setString(1, newOpenings);
                    psUpdate.setString(2, actId);

                    psUpdate.executeUpdate();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
    
    // Displays the table.  For debugging purposes only
    public void printActivityTbl() {
        try {
            String connectionURL = "jdbc:derby:" + dbName;
            Connection conn = DriverManager.getConnection(connectionURL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select * from activitiesTbl");

            System.out.println("PRINTING Activities Table: \n");
            int num = 0;
            while (rs.next()) {
                System.out.println(++num
                        + ": ACTID: " + rs.getString(1)
                        + "   NAME: " + rs.getString(2)
                        + "   DATE: " + rs.getString(3)
                        + "   CATEGORY: " + rs.getString(4)
                        + "   OPENINGS: " + rs.getString(5)
                        + "   FEE: " + rs.getString(6) + "\n");
            }

            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
