package KokoBot;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtilities {
	
	public static String makeLine(String[] columns, String[] padding) {
		String result = "";
		for(int i = 0;i < columns.length;i++) {
			result += padding[i]+columns[i]+padding[i]+(i==columns.length-1 ? "" : " ");
		}
	return result;
	}
	
	
	public static void addToFile(String path, String ammendment) throws IOException {
		BufferedWriter bf = new BufferedWriter(new FileWriter(path,true));
		bf.append("\n"+ammendment);
		bf.close();
		
		
	}
	
	
	
	public static void overrideFile(String path, String[] Lines) throws IOException {
		BufferedWriter bf = new BufferedWriter(new FileWriter(path, false));
		for(int i = 0;i<Lines.length;i++) {
		 	bf.write(Lines[i]+(i==Lines.length-1 ? "" : '\n'));
		}
		bf.close();
	}

}
