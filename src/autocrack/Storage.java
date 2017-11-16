package autocrack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class Storage {
	
	private final static String storagePath = "storageData.txt";
	
	public static ArrayList<AP> getAll() {
		JSONObject allAP = getJSONlist();
		ArrayList<AP> listAP = new ArrayList<>();
		for(Object o : allAP.getJSONArray("root")) {
			if(o instanceof JSONObject) {
				JSONObject accessP = (JSONObject)o;
				AP ap = new AP();
				try{
					ap.setESSID(accessP.getString("BSSID"));
				}catch(JSONException e) {}
				try{
					ap.setESSID(accessP.getString("ESSID"));
				}catch(JSONException e) {}
				try{
					ap.setCapFile(accessP.getString("capFile"));
				}catch(JSONException e) {}
				listAP.add(ap);
			}
		}
		return listAP;
	}
	
	public static boolean add(AP accessPoint) {
		JSONObject allAP = getJSONlist();
		JSONObject accessP = new JSONObject(accessPoint);
		allAP.getJSONArray("root").put(accessP);
		try{
			FileWriter fw=new FileWriter(storagePath);
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(allAP.toString());
			bw.close();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("error"+e.getMessage());
			return false;
		}
	}
	
	public static boolean exist(String BSSID) {
		JSONObject allAP = getJSONlist();
		for(Object o : allAP.getJSONArray("root")) {
			if(o instanceof JSONObject) {
				JSONObject accessP = (JSONObject)o;
				if(accessP.get("BSSID").equals(BSSID))
					return true;
			}
		}
		return false;
	}
	
	public static APlist readCsv(String filename) {
		ArrayList<AP> allAP = new ArrayList<>();
		ArrayList<String[]> allClient = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
			boolean APside = true;
			for(String line; (line = br.readLine()) != null; ) {
				if(line.equals("Station MAC, First time seen, Last time seen, Power, # packets, BSSID, Probed ESSIDs"))
					APside = false;
				if(APside) {
					if(!line.equals("BSSID, First time seen, Last time seen, channel, Speed, Privacy, Cipher, Authentication, Power, # beacons, # IV, LAN IP, ID-length, ESSID, Key")) {
						String[] splitData = line.split("\\s*,\\s*");
						if(splitData.length > 1) {
							Date date = dateFromString(splitData[1]);
							allAP.add(new AP(splitData[0],splitData.length >= 14 ? splitData[13] : "",splitData[3],splitData[5]+" "+splitData[6]+" "+splitData[7],null,date));
						}
					}
				}else{
					if(!line.equals("Station MAC, First time seen, Last time seen, Power, # packets, BSSID, Probed ESSIDs")) {
						String[] splitData = line.split("\\s*,\\s*");
						if(splitData.length > 1) {
							allClient.add(new String[]{splitData[5],splitData[0]});
						}
					}
				}
			}
		}catch(FileNotFoundException e) {	
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		APlist list = new APlist(allAP);
		AP ap;
		for(String[] client : allClient) {
			list.addClient(client);
		}
		return list;
	}
	
	private static Date dateFromString(String str) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = dateFormat.parse(str);
		}catch(ParseException e){
			date = new Date();
			e.printStackTrace();
		}
		return date;
	}
	
	private static String readAll(Reader rd) throws Exception {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
	
	private static JSONObject getJSONlist() {
		try {
			FileReader fr = new FileReader(storagePath);
			BufferedReader br = new BufferedReader(fr);
			String jsonText = readAll(br);
			return new JSONObject(jsonText);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("error"+e.getMessage());
			return new JSONObject();
		}
	}

	
}
