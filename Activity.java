package business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Activity
{
	private ArrayList<String> names;
	private ArrayList<String> dates;
	private ArrayList<String> ages;
	private ArrayList<String> openings;
	private ArrayList<String> fees;
	
	private int number;
	
	public Activity() {
		names = new ArrayList<String>();
		dates = new ArrayList<String>();
		ages = new ArrayList<String>();
		openings = new ArrayList<String>();
		fees = new ArrayList<String>();
		
		number = 0;
	}
	
	public Activity(String fileName) {
		names = new ArrayList<String>();
		dates = new ArrayList<String>();
		ages = new ArrayList<String>();
		openings = new ArrayList<String>();
		fees = new ArrayList<String>();
		number = 0;
		
		BufferedReader br = null;
		String line;
		String delims = "[,]";
		try {
			br = new BufferedReader(new FileReader(fileName));
			
			while ((line=br.readLine()) != null) {
				String[] tokens = line.split(delims);
				if (tokens.length >= 5) {
					names.add(tokens[0]);
					dates.add(tokens[1]);
					ages.add(tokens[2]);
					openings.add(tokens[3]);
					fees.add(tokens[4]);
					number++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public String[] getNames() {
		return names.toArray(new String[number]);
	}
	
	public String getName (int index) {
		if (index < number) {
			return names.toArray(new String[number])[index];
		}
		else {
			return "null";
		}
	}
	
	public String getDate (int index) {
		if (index < number) {
			return dates.toArray(new String[number])[index];
		}
		else {
			return "null";
		}
	}
	
	public String getAge (int index) {
		if (index < number) {
			return ages.toArray(new String[number])[index];
		}
		else {
			return "null";
		}
	}
	
	public String getOpening (int index) {
		if (index < number) {
			return openings.toArray(new String[number])[index];
		}
		else {
			return "null";
		}
	}
	
	public String getFee (int index) {
		if (index < number) {
			return fees.toArray(new String[number])[index];
		}
		else {
			return "null";
		}
	}
	
	public int getNumber()	{
		return number;
	}
}
