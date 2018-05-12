import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import com.dbheap.entity.DataInfo;
import com.dbheap.entity.OffsetNode;
import com.dbheap.model.HashBucketProcessor;

public class DBIndexCreator {

	/**
	 * 
	 * @param args
	 *            Terminal arguments must be only page size
	 */
	public static void main(String[] args) {
		File file;
		if (!(args[0].matches("\\d+"))) {
			System.err.println("Please enter 'text pagesize' as your arguments.");
			return;
		}

		file = new File("./heap." + args[0]);
		if (!file.exists()) {
			System.err.println("Please make sure that you have a corresponding heap file.");
			return;
		}
		// TODO Auto-generated method stub

		DBIndexCreator creator = new DBIndexCreator();
		HashBucketProcessor processor = HashBucketProcessor.getInstance();
		creator.createIndex(file, Integer.parseInt(args[0]), processor);
		creator.writeIndexToFile(Integer.parseInt(args[0]), processor);
	}

	/**
	 * 
	 * @param file
	 *            Heap file for saving data
	 * @param pageSize
	 *            Size of one data block
	 * @param processor
	 *            Processor for creating hashcode slots
	 */
	private void createIndex(File file, int pageSize, HashBucketProcessor processor) {
		DataInputStream dis = null;
		long offset = 0;
		byte[] data;
		String colVal, dataLine;
		int dataSize, pageCounter = 1, titleSize, curOffsetInPage = 0, hashcode, dataCounter = 0;
		DataInfo info = new DataInfo();

		try {
			// load head info
			dis = new DataInputStream(new FileInputStream(file));
			dataSize = dis.readInt();
			titleSize = dis.readInt();
			dis.read(data = new byte[titleSize]);
			info.setColumes(new String(data));
			data = new byte[dataSize];
			processor.initSlots();

			while (dis.read(data) != -1) {

				if (curOffsetInPage == 0) {
					System.out.println("Creating Indext for page " + pageCounter + "...");
					// 4 bytes is a head info for each block
					curOffsetInPage += 4;
					offset += 4;
					// number of datas in current block
					dataCounter = dis.readInt();
				}

				dataCounter--;
				dataLine = new String(data);
				colVal = info.getColumeVals("BN_NAME", dataLine);
				hashcode = colVal.trim().hashCode();

				processor.addOffset(hashcode, offset);

				curOffsetInPage += dataSize;
				offset += dataSize;

				// skip useless bytes at the end of each block
				if (pageSize - curOffsetInPage < dataSize || dataCounter == 0) {
					dis.skipBytes(pageSize - curOffsetInPage);
					offset += (pageSize - curOffsetInPage);
					pageCounter++;
					curOffsetInPage = 0;
				}
			}
			processor.setDataSize(dataSize);

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
	}

	private void writeIndexToFile(int pageSize, HashBucketProcessor processor) {
		BufferedWriter writer = null;
		StringBuffer sb = new StringBuffer();
		Set<Integer> keys;
		Hashtable<Integer, ArrayList<Long>> slots = processor.getSlots();
		ArrayList<Long> offsets;
		try {
			writer = new BufferedWriter(new FileWriter("./index." + pageSize));

			// offsets for same hashcode that are divided by ','
			keys = slots.keySet();
			for (Integer key : keys) {
				sb.append(key + "");
				sb.append(",");
				offsets = slots.get(key);
				for (long offset: offsets)
					sb.append(offset + ",");
				// remove ',' at the end of string
				sb.delete(sb.length() - 1, sb.length());
				// for different slots that are divided by new line
				if (sb.length() > 0) {
					writer.write(sb.toString());
					writer.newLine();
					sb.delete(0, sb.length());
				}
			}
			System.out.println("Index Creating finished");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
