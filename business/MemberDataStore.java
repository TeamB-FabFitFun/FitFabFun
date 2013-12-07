package business;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MemberDataStore implements java.io.Serializable {

    private ArrayList<Member> memberDS;

    private final String fileName = "/fabfitfun/members.txt";
    
    public MemberDataStore() {

        memberDS = new ArrayList<Member>();
        
        BufferedReader br = null;
        String line;
        String delims = "[,]";
        try {
            br = new BufferedReader(new FileReader(fileName));         
            
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(delims);
				int tokenLength = tokens.length;
                if (tokenLength >= 6) { 
                    // Set member fields from file
                    Member member = new Member();
                    member.setFirstName(tokens[0]);
                    member.setLastName(tokens[1]);
                    member.setEmail(tokens[2]);
                    member.setPassword(tokens[3]);
                    member.setGender(tokens[4]);
                    member.setAge(Integer.parseInt(tokens[5]));
                    
                    int i=7;
                    if (tokenLength >= i) {
                        int iActIdxEnd = 6 + Integer.parseInt(tokens[6]);
                        while (i < tokens.length && i <= iActIdxEnd) {
                            member.addActivity(tokens[i]);
                            i++;
                        }
                        i++;
						if (tokenLength >= i) {
					        int iCartIdxEnd = iActIdxEnd + 1 + Integer.parseInt(tokens[iActIdxEnd+1]);
						    while (i < tokens.length && i <= iCartIdxEnd) {
                                member.addToCart(tokens[i]);
                                i++;
                            }
                        }
                    }
                    // Add member to data store
                    memberDS.add(member);
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
    
    public ArrayList<Member> getMemberDS() {
        return memberDS;
    }
    
	public void addMember (Member mbr) {
		try {
			File file = new File(fileName);
	 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
	 
			FileWriter fw = new FileWriter(fileName, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(mbr.getFirstName()+","+mbr.getLastName()+","+mbr.getEmail()+","+mbr.getPassword()+","+mbr.getGender()+","+mbr.getAge()+"\r\n");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public Member getMember(String mbrEmail) {
        for (Member member: memberDS) {
            if (member.getEmail().equalsIgnoreCase(mbrEmail)) {
                return member;
            }
        }   
        return null;
    }
    
}
