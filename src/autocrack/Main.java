package autocrack;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class Main {
	//throws java.lang.Exception
	final static String commandDir = "command";
	
	final static String capDir = "cap";
	
	public static void main (String[] args) throws java.lang.Exception {
		/**/
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		System.out.println("Run Autocrack ("+dateFormat.format(date)+")");
		/**/
		String[] interfaceName = Util.run(new String[]{commandDir+"/getInterfacesName.sh"});
		String monitorName = Util.run(new String[]{commandDir+"/startMonitor.sh" , interfaceName[0].split("\n")[0]})[0];
		//String monitorName = "wlan0mon";
		System.out.println("Start monitor on : " + monitorName);
		/**/
		Util.live(new String[]{commandDir+"/generalDump.sh",monitorName,"10"});
		System.out.println("scan Access Point (10 second)");
		Thread.sleep(10000);
		System.out.println("find:");
		APlist list = Storage.readCsv("cap/generalDump-01.csv");
		Util.live(new String[]{"rm","cap/generalDump-01.csv","cap/generalDump-01.cap"});
		for(AP ap : list.getList()) {
			if(ap.hasClients())
				System.out.println(ap.getESSID());
		}
			/**/
	}
}