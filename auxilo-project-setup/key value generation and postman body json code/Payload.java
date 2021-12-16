package passthroughTemplate;

import java.util.Scanner;

public class Payload {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		String str = s.nextLine();
		String fs = "";
		String[] stringarray = str.split(",");

		for (int i = 0; i < stringarray.length; i++) {
			if (stringarray[i].contains("\"")) {

				fs = fs + test(stringarray[i]);
			}

		}
		System.out.println(fs);

	}

	public static String test(String str) {
		int i = str.indexOf("\"");
		int j = str.lastIndexOf("\"");
		String key = str.substring(i, j+1);
		
		
		return key + ":"+"\" \""+",";
	}

}
