package KokoBot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class FileUtilities {
	
	public static String makeLine(String[] columns, String[] padding) {
		String result = "";
		for(int i = 0;i < columns.length;i++) {
			result += padding[i]+columns[i]+padding[i]+(i==columns.length-1 ? "" : " ");
		}
	return result;
	}
	
	
	public static void addToFile(String path, String ammendment) throws IOException {
		boolean carriage = true;
		BufferedReader br = new BufferedReader(new FileReader(path));
		Stream<String> lines = br.lines();
		if(lines.toArray().length==0) {
			carriage=false;
		}
		br.close();
		BufferedWriter bf = new BufferedWriter(new FileWriter(path,true));
		bf.append((carriage?"\n":"")+ammendment);
		bf.close();
		
		
	}
	
	
	
	public static void overrideFile(String path, List<String> activeEvents) throws IOException {
		BufferedWriter bf = new BufferedWriter(new FileWriter(path, false));
		Iterator<String> iter = activeEvents.iterator();
		while(iter.hasNext()) {
		 	bf.write(iter.next()+(!iter.hasNext() ? "" : '\n'));
		}
		bf.close();
	}
	
	public static List<String> getLines(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		Iterator<String> iter = br.lines().iterator();
		List<String> lines = new ArrayList<String>();
		while(iter.hasNext()) {
		 	lines.add(iter.next());
		}
		br.close();
		return lines;
	}

}
