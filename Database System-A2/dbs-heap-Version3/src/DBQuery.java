import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.dbheap.entity.DataInfo;

public class DBQuery {

	public static void main(String[] args) {
		File file;
		long startTime, endTime;
		String searchingStr;
		StringBuffer sb = new StringBuffer();
		DataInfo info = new DataInfo();
		ArrayList<String> result;
		DBQuery query;
		if (args.length < 2 || !(args[args.length - 1].matches("\\d+"))) {
			System.err.println("Please enter 'text pagesize' as your arguments.");
			return;
		}
		
		file = new File("./heap." + args[args.length - 1]);
		if (!file.exists()) {
			System.err.println("Please make sure that you have a corresponding heap file.");
			return;
		}
		
		query = new DBQuery();
		
		for (int i = 0; i < args.length - 1; i++) {
			sb.append(args[i]);
			sb.append(" ");
		}
	
		sb.deleteCharAt(sb.length() - 1);
		searchingStr = sb.toString();
		startTime = System.currentTimeMillis();
		result = query.query(file, info, "BN_NAME", searchingStr, Integer.parseInt(args[args.length - 1]));
		System.out.println("\nResults:");
		for (String data: result) {
			info.printData(data);
		}
		// Because index query process endTime after data print function, we do same thing here
		endTime = System.currentTimeMillis();
		System.out.println("\nHeap file querying process is finished. Cost " + (endTime - startTime) + "ms.");
	}
	
	private ArrayList<String> query(File file, DataInfo info, String targetTitle, String searchingWord, int pageSize) {
		int pageCounter = 0;
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> pageDatas = new ArrayList<String>();
		DataInputStream dis = null;
		String dataStr;
		byte[] page = new byte[pageSize - 4];
		int dataSize, counter, titleSize;
		byte[] data;
		try {
			dis = new DataInputStream(new FileInputStream(file));
			dis.skipBytes(4); // we don't need record amount here
			dataSize = dis.readInt();
			titleSize = dis.readInt();
			dis.read((data = new byte[titleSize]));
			info.setColumes(new String(data));
			data = new byte[dataSize];
			
			System.out.println("Searching...");
			// read real data
			while (true) {
				pageDatas.clear();
				try {
					counter = dis.readInt();
				} catch(EOFException eofE) {
					break;
				}
				pageCounter++;
				// System.out.println("Querying page " + pageCounter + "...");
				dis.read(page);
				for (int i = 0; i < counter; i++) {
					for (int m = 0; m < dataSize; m++) {
						data[m] = page[i * dataSize + m];
					}
					dataStr = new String(data).trim();
					if (info.isSubStringInTragetColume(searchingWord, targetTitle, dataStr)) {
						result.add(dataStr);
						return result;
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

}
