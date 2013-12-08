package business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberDataBase {

    private String dbName = "/fabfitfun/fffDB";
    private static boolean initialized = false;

    public static boolean isInitialized() {
        return initialized;
    }

    public MemberDataBase() {
        if (!initialized) {
            initialized = true;

            try {
                String driver = "org.apache.derby.jdbc.EmbeddedDriver";
                String connectionURL = "jdbc:derby:" + dbName + ";create=true";
                String createString =
                        "CREATE TABLE membersTbl ("
                        + "FIRSTNAME VARCHAR(30), "
                        + "LASTNAME VARCHAR(30), "
                        + "EMAIL VARCHAR(30) PRIMARY KEY, "
                        + "PASSWORD VARCHAR(30), "
                        + "GENDER VARCHAR(6), "
                        + "AGE INT, "
                        + "ACTIVITIES VARCHAR(50), "
                        + "CART VARCHAR(50))";
                Class.forName(driver);

                Connection conn = DriverManager.getConnection(connectionURL);
                Statement stmt = conn.createStatement();

                // Drop the table if you wish to reset it.  Otherwise, keep this commented.
                //String dropString = "DROP TABLE membersTbl";
                //stmt.executeUpdate(dropString);

                try {
                    stmt.executeUpdate(createString);
                } catch (SQLException e) {
                    // "X0Y32" mean the table already exists.  That's ok.
                    if (!e.getSQLState().equals("X0Y32")) {
                        throw e;
                    }
                }
                conn.close();
            } catch (Exception e) {
                initialized = false;
                e.printStackTrace();
            }
        }
    }

    // Returns false when the member's email already exists in the database
    public boolean addMember(Member mbr) {
        boolean success = true;
        try {
            String connectionURL = "jdbc:derby:" + dbName;
            Connection conn = DriverManager.getConnection(connectionURL);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select * from membersTbl where email = '"
                    + mbr.getEmail().toLowerCase() + "'");

            if (rs.next()) {
                // Matching email was found.  Don't add
                success = false;
            } else {
                PreparedStatement psInsert =
                        conn.prepareStatement(
                        "insert into membersTbl values (?,?,?,?,?,?,?,?)");

                psInsert.setString(1, mbr.getFirstName());
                psInsert.setString(2, mbr.getLastName());
                psInsert.setString(3, mbr.getEmail().toLowerCase());
                psInsert.setString(4, mbr.getPassword());
                psInsert.setString(5, mbr.getGender());
                psInsert.setInt(6, mbr.getAge());
                psInsert.setString(7, "");
                psInsert.setString(8, "");

                psInsert.executeUpdate();
            }

            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public Member getMember(String mbrEmail) {
        Member member = null;
        try {
            String connectionURL = "jdbc:derby:" + dbName;
            Connection conn = DriverManager.getConnection(connectionURL);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select * from membersTbl where email = '"
                    + mbrEmail.toLowerCase() + "'");

            if (rs.next()) {
                String[] actIds = rs.getString("ACTIVITIES").split(",");
                String[] cartIds = rs.getString("CART").split(",");

                if (cartIds.length == 1 && "".equals(cartIds[0])) {
                    cartIds = new String[0];
                }
                if (actIds.length == 1 && "".equals(actIds[0])) {
                    actIds = new String[0];
                }

                member = new Member(
                        rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PASSWORD"),
                        rs.getString("GENDER"),
                        rs.getInt("AGE"),
                        actIds,
                        cartIds);
            }

            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return member;
    }

    // Returns false when no matching member email was found
    // This method does not update email address. 
    public boolean updateMember(Member mbr) {
        boolean success = false;
        try {
            String connectionURL = "jdbc:derby:" + dbName;
            Connection conn = DriverManager.getConnection(connectionURL);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select * from membersTbl where email = '"
                    + mbr.getEmail().toLowerCase() + "'");

            if (rs.next()) {
                success = true;
                PreparedStatement psUpdate = conn.prepareStatement(
                        "UPDATE membersTbl SET "
                        + "FIRSTNAME = ?, "
                        + "LASTNAME = ?, "
                        + "PASSWORD = ?, "
                        + "GENDER = ?, "
                        + "AGE = ?, "
                        + "ACTIVITIES = ?, "
                        + "CART = ? "
                        + "WHERE EMAIL = ?");

                try {
                    psUpdate.clearParameters();
                    psUpdate.setString(1, mbr.getFirstName());
                    psUpdate.setString(2, mbr.getLastName());
                    psUpdate.setString(3, mbr.getPassword());
                    psUpdate.setString(4, mbr.getGender());
                    psUpdate.setInt(5, mbr.getAge());

                    String actStrCsv = "";
                    for (String actStr : mbr.getActivities()) {
                            actStrCsv += actStr + ",";
                    }
                    psUpdate.setString(6, actStrCsv);

                    String cartStrCsv = "";
                    for (String cartStr : mbr.getCart()) {
                            cartStrCsv += cartStr + ",";
                    }
                    psUpdate.setString(7, cartStrCsv);

                    psUpdate.setString(8, mbr.getEmail().toLowerCase());
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
    public void printMembersTbl() {
        try {
            String connectionURL = "jdbc:derby:" + dbName;
            Connection conn = DriverManager.getConnection(connectionURL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select * from membersTbl");

            System.out.println("PRINTING Members Table: \n");
            int num = 0;
            while (rs.next()) {
                System.out.println(++num
                        + ": FIRSTNAME: " + rs.getString(1)
                        + "   LASTNAME: " + rs.getString(2)
                        + "   EMAIL: " + rs.getString(3)
                        + "   PASSWORD: " + rs.getString(4)
                        + "   GENDER: " + rs.getString(5)
                        + "   AGE: " + rs.getInt(6)
                        + "   ACTIVITIES: " + rs.getString(7)
                        + "   CART: " + rs.getString(8) + "\n");
            }

            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
