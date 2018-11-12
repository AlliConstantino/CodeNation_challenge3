/import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVHandler {

	private static String csvPath = "/src/main/resources/data.csv";
	
	private static String basePath = (new File("")).getAbsolutePath();
	
	private static String csvSeparator = ",";
	
	private String line = "";
	
	private String[][] table;
	
	private BufferedReader reader;
	
	public CSVHandler() {
		
		try {
			reader = new BufferedReader(new FileReader(basePath.concat(csvPath)));
			int i = 0;
			ArrayList<String[]> preTable = new ArrayList<String[]>();
			while((line = reader.readLine()) != null) {
				String[] crudeLine = line.split(csvSeparator);
				preTable.add(crudeLine);
				i = crudeLine.length;
			}
			table = new String[preTable.size()][i];
			for(int j = 0;j < preTable.size();j++) {
				table[j] = preTable.get(j);
			}
			preTable = null;
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public String[][] getTable(){
		return table;
	}
	
	public String[] getColumn(int index) {
		String[] r = new String[table.length];
		for(int i = 0;i < table.length;i++) {
			r[i] = table[i][index];
		}
		return r;
	}
	
	public String[] getColumn(String name) {
		int index = 0;
		for(int i = 0;i < table[0].length;i++) {
			if(table[0][i].equals(name)) {
				index = i;
				break;
			}
		}
		String[] r = new String[table.length];
		for(int i = 0;i < table.length;i++) {
			r[i] = table[i][index];
		}
		return r;
	}
	
	public String[] getColumnWithoutDuplicates(int index) {
		String[] bruteData = getColumn(index);
		ArrayList<String> data = new ArrayList<String>();
		for(int i = 0;i < bruteData.length;i++) {
			if(!data.contains(bruteData[i])) {
				data.add(bruteData[i]);
			}
		}
		String[] fineData = data.toArray(bruteData);
		return fineData;
	}
	
	public String[] getColumnWithoutDuplicates(String name) {
		String[] bruteData = getColumn(name);
		ArrayList<String> data = new ArrayList<String>();
		for(int i = 0;i < bruteData.length;i++) {
			if(!data.contains(bruteData[i])) {
				data.add(bruteData[i]);
			}
		}
		String[] fineData = new String[data.size()];
		fineData = data.toArray(fineData);
		//System.out.println(fineData.length);
		//System.out.println(data.size());
		//System.out.println(bruteData.length);
		return fineData;
	}
}