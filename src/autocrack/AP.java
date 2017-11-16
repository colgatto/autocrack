package autocrack;

import java.util.ArrayList;
import java.util.Date;

public class AP {
	//Storage.add(new AP("solo mac"));
	//System.out.println(Storage.exist("terzo mac"));
	/**
	ArrayList<AP> allAP = Storage.getAll();
	for(AP ap : allAP) {
		System.out.println(ap);
	}
	/**/
	private String BSSID;
	private String ESSID;
	private String channel;
	private String security;
	private String capFile;
	private ArrayList<String> clients;
	Date date;
	
	AP(String BSSID, String ESSID, String channel, String security, String capFile, Date date){
		this.BSSID = BSSID;
		this.ESSID = ESSID;
		this.channel = channel;
		this.security = security;
		this.capFile = capFile;
		this.date = date;
		clients = new ArrayList<>();
	}
	AP(String BSSID, String ESSID, String channel, String security){
		this(BSSID,ESSID,channel,security,null,new Date());
	}
	AP(String BSSID, String channel, String security){
		this(BSSID,channel,security,null,null,new Date());
	}
	AP(){
		this(null,null,null,null,null,new Date());
	}
	
	public String getBSSID() {
		return BSSID;
	}
	public String getESSID() {
		return ESSID;
	}
	public String getCapFile() {
		return capFile;
	}
	public String getChannel() {
		return channel;
	}
	public String getSecurity() {
		return security;
	}
	public Date getDate() {
		return date;
	}
	
	public void setBSSID(String BSSID) {
		this.BSSID = BSSID;
	}
	public void setESSID(String ESSID) {
		this.ESSID = ESSID;
	}
	public void setCapFile(String capFile) {
		this.capFile = capFile;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public void setSecurity(String security) {
		this.security = security;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public boolean hasClients(){
		return this.clients.size()>0;
	}
	
	public void addClient(String MAC) {
		this.clients.add(MAC);
	}
	
	@Override
	public String toString() {
		String init = (getDate() != null ? "" + getDate() + "\t" : "")
				+ (getBSSID() != null ? "" + getBSSID() + "\t" : "")
				+ (getESSID() != null ? "" + getESSID() + "" : "");
				//+ (getChannel() != null ? "" + getChannel() + "" : "")
				//+ (getSecurity() != null ? "" + getSecurity() + "" : "")
				//+ (getCapFile() != null ? "" + getCapFile() + "" : "");
		if(this.clients.size()>0){
			for(String MAC : this.clients)
				init += "\n\t"+MAC;
		}
		return init;
	}
	
}
