import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import com.dbheap.entity.DataInfo;
import com.dbheap.util.HashBucketProcessor;

public class DBIndexQuery {
	/***
	 * 
	 * @param args
	 *            Terminal arguments must be start with page size, then
	 *            searching string e.g. 1024 Soul Origin Wintergarden
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime, endTime;
		File indexFile, heapFile;
		DBIndexQuery query = new DBIndexQuery();
		HashBucketProcessor processor;
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
		processor = HashBucketProcessor.getInstance();
		startTime = System.currentTimeMillis();
		query.indexQuery(Integer.parseInt(args[0]), indexFile, heapFile, processor, searchingStr);
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
	private void indexQuery(int pageSize, File indexFile, File heapFile, HashBucketProcessor processor,
			String searchingStr) {
		DataInputStream dis = null, indexDis = null;
		int dataSize, titleSize, hashcode, bucketSize = 8, indexOffset, recordAmount;
		long dataOffset, preOffset = 0;
		byte[] data;
		DataInfo info = new DataInfo();
		String col = "BN_NAME", colVal, dataLine;

		try {
			// load head information
			dis = new DataInputStream(new FileInputStream(heapFile));
			indexDis = new DataInputStream(new FileInputStream(indexFile));
			recordAmount = dis.readInt();
			dataSize = dis.readInt();
			titleSize = dis.readInt();
			dis.read((data = new byte[titleSize]));
			info.setColumes(new String(data));
			processor.setSlotSize((int)(recordAmount / 0.7));
			data = new byte[dataSize];

			// get target offset
			hashcode = processor.hashCode(searchingStr);
			indexOffset = hashcode * bucketSize;
			indexDis.skipBytes(indexOffset);

			while ((dataOffset = indexDis.readLong()) != -1) {
				dataOffset -= preOffset;
				dis.skip(dataOffset);
				dis.read(data);
				dataLine = new String(data);
				colVal = info.getColumeVals(col, dataLine);

				if (colVal.equals(searchingStr)) {
					info.printData(dataLine);
					return;
				}
				preOffset = dataOffset;
			}

			System.out.println("Can't find result");
		} catch (EOFException eof) {
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
			} finally {
				try {
					if (indexDis != null)
						indexDis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
