import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.dbheap.entity.DataInfo;
import com.dbheap.model.HashBucketProcessor;

public class DBIndexQuery {
	/***
	 * 
	 * @param args
	 * 			Terminal arguments must be start with page size, then searching string
	 * 			e.g. 1024 Soul Origin Wintergarden
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime, endTime;
		File indexFile, heapFile;
		DBIndexQuery query = new DBIndexQuery();
		HashBucketProcessor processor = HashBucketProcessor.getInstance();
		indexFile = new File("./index." + args[0]);
		if (!indexFile.exists()) {
			System.out.println("Please make sure path is correct for index." + args[0] + "file.");
			return;
		}

		heapFile = new File("./heap." + args[0]);
		if (!heapFile.exists()) {
			System.out.println("Please make sure path is correct for heap." + args[0] + "file.");
			return;
		}
		String searchingStr;

		StringBuffer sb = new StringBuffer();
		for (int i = 1; i < args.length; i++) {
			sb.append(args[i]);
			sb.append(" ");
		}

		searchingStr = sb.toString().trim();
		
		query.createHashBucketSlots(indexFile, processor);
		startTime = System.currentTimeMillis();
		query.indexQuery(Integer.parseInt(args[0]), heapFile, processor, searchingStr);
		endTime = System.currentTimeMillis();

		System.out.println("Query with index cost " + (endTime - startTime) + "ms.");
	}

	/**
	 * @param pageSize
	 *            Size of one block in heap file
	 * @param heapFile
	 *            File saves data
	 * @param processor
	 *            Used to get slots and hashcode for searching string
	 * @param searchingStr
	 *            Target string for searching
	 */
	private void indexQuery(int pageSize, File heapFile, HashBucketProcessor processor, String searchingStr) {
		DataInputStream dis = null;
		long curOffset, preOffset;
		int dataSize, titleSize, hashcode;
		byte[] data;
		DataInfo info = new DataInfo();
		String col = "BN_NAME", colVal, dataLine;
		List<Long> offsets;

		try {
			// load head information
			dis = new DataInputStream(new FileInputStream(heapFile));
			dataSize = dis.readInt();
			titleSize = dis.readInt();
			dis.read((data = new byte[titleSize]));
			info.setColumes(new String(data));
			data = new byte[dataSize];

			hashcode = searchingStr.hashCode();
			offsets = processor.getOffsets(hashcode);

			if (offsets.size() > 0) {
				curOffset = offsets.get(0);
				dis.skip(curOffset);
				dis.read(data);
				dataLine = new String(data).trim();
				colVal = info.getColumeVals(col, dataLine);
				if (colVal.equals(searchingStr)) {
					// target result is in the first node
					info.printData(dataLine);
					return;
				} else {
					// target result is in the chain
					for (int i = 1; i < offsets.size(); i++) {
						// calculate current position in heap file
						preOffset = curOffset + dataSize;
						// each offset is an offset from beginning 1st block
						// so we should calculate offset we should skip if data
						// in a chain
						curOffset = offsets.get(i);
						dis.skip(curOffset - preOffset);
						dis.read(data);
						dataLine = new String(data).trim();
						colVal = info.getColumeVals(col, dataLine);
						if (colVal.equals(searchingStr)) {
							info.printData(dataLine);
							return;
						}
					}
				}
			}

			System.out.println("Can't find result");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (dis != null)
					dis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Load index from index file to memory
	 * 
	 * @param indexFile
	 *            index file
	 * @param processor
	 *            processor for creating and getting index slots
	 */
	private void createHashBucketSlots(File indexFile, HashBucketProcessor processor) {
		BufferedReader reader = null;
		String slotLine;
		String[] offsetsInfo, offsetInfo;

		try {
			reader = new BufferedReader(new FileReader(indexFile));
			processor.initSlots();

			while ((slotLine = reader.readLine()) != null) {
				slotLine.trim();
				offsetsInfo = slotLine.split("\t");
				for (String offsetLine : offsetsInfo) {
					offsetInfo = offsetLine.split(",");
					for (int i = 1; i < offsetInfo.length; i++) {
						processor.addOffset(Integer.parseInt(offsetInfo[0]), Long.parseLong(offsetInfo[i]));
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
