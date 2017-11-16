package autocrack;

import java.util.ArrayList;

public class APlist {
	private ArrayList<AP> list;

	public APlist(ArrayList<AP> list) {
		this.list = list;
	}

	public ArrayList<AP> getList() {
		return list;
	}

	public void setList(ArrayList<AP> list) {
		this.list = list;
	}
	
	public boolean addClient(String[] client) {
		for(int i = 0;i<list.size();i++) {
			if(list.get(i).getBSSID().equals(client[0])) {
				list.get(i).addClient(client[1]);
				return true;
			}
		}
		return false;
	}
	
	public AP contains(String MAC) {
		for(AP ap : list) {
			if(ap.getBSSID()==MAC)
				return ap;
		}
		return null;
	}

	@Override
	public String toString() {
		String output = "";
		for(AP ap : list) {
			output+=ap+"\n";
		}
		return output;
	}
	
}
