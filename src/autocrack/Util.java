package autocrack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Util {
	
	public static BufferedReader[] live(String[] commands) throws IOException {
		Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec(commands);
		BufferedReader[] std = new BufferedReader[2];
		std[0] = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		std[1] = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
		return std;
	}
	
	public static String[] run(String[] commands) throws IOException {
		BufferedReader[] std = live(commands);
		String[] output = new String[2];
		output[0] = "";
		output[1] = "";
		String s = null;
		while ((s = std[0].readLine()) != null) {
			output[0]+=s+"\n";
		}
		while ((s = std[1].readLine()) != null) {
			output[1]+=s+"\n";
		}
		return output;
	}
}
