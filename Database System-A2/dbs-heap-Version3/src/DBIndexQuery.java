import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.dbheap.entity.DataInfo;
import com.dbheap.util.HashBucketProcessor;

public class DBIndexQuery {
	/***
	 * 
	 * @param args
	 *            Terminal arguments must be same as query in Assignment 1, then
	 *            searching string e.g. Soul Origin Wintergarden 1024
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime, endTime;
		File indexFile, heapFile;
		DBIndexQuery query = new DBIndexQuery();
		HashBucketProcessor processor;
		indexFile = new File("./index." + args[args.length - 1]);
		if (!indexFile.exists()) {
			System.out.println("Please make sure path is correct for index." + args[args.length - 1] + "file.");
			return;
		}

		heapFile = new File("./heap." + args[args.length - 1]);
		if (!heapFile.exists()) {
			System.out.println("Please make sure path is correct for heap." + args[args.length - 1] + "file.");
			return;
		}
		String searchingStr;

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < args.length - 1; i++) {
			sb.append(args[i]);
			sb.append(" ");
		}

		searchingStr = sb.toString().trim();
		processor = HashBucketProcessor.getInstance();
		startTime = System.currentTimeMillis();
		query.indexQuery(Integer.parseInt(args[args.length - 1]), indexFile, heapFile, processor, searchingStr);
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
		int dataSize, titleSize, hashcode, bucketSize = 8, indexOffset, preIndexOffset = 0, indexSkipOffset, recordAmount, hashTimes = 3, index;
		long dataOffset, preOffset = 0, skipOffset;
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
			processor.setSlotSize((int) (recordAmount / 0.7));
			data = new byte[dataSize];

			System.out.println("Searching...");
			
			index = 0;
			while (index < hashTimes) {
				// get target offset
				hashcode = processor.hashCode(searchingStr, index);
				indexOffset = hashcode * bucketSize;
				indexSkipOffset = indexOffset - preIndexOffset;
				indexDis.skipBytes(indexSkipOffset);
				
				if (index < hashTimes - 1) {
					dataOffset = indexDis.readLong();
					if (dataOffset != -1) {
						skipOffset = dataOffset - preOffset;
						dis.skip(skipOffset);
						dis.read(data);
						dataLine = new String(data);
						colVal = info.getColumeVals(col, dataLine);

						if (colVal.equals(searchingStr)) {
							info.printData(dataLine);
							return;
						}
						preOffset = dataOffset + dataSize;
						preIndexOffset = indexOffset + 8;
					}
					index++;
				} else {
					while ((dataOffset = indexDis.readLong()) != -1) {
						skipOffset = dataOffset - preOffset;
						dis.skip(skipOffset);
						dis.read(data);
						dataLine = new String(data);
						colVal = info.getColumeVals(col, dataLine);

						if (colVal.equals(searchingStr)) {
							info.printData(dataLine);
							return;
						}
						preOffset = dataOffset + dataSize;
					}
					index++;
				}
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
