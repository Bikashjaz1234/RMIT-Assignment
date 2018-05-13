import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.dbheap.entity.DataInfo;
import com.dbheap.util.HashBucketProcessor;

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
		int dataSize, pageCounter = 1, titleSize, curOffsetInPage = 0, hashcode, dataCounter = 0, recordAmount;
		DataInfo info = new DataInfo();

		try {
			// load head info
			dis = new DataInputStream(new FileInputStream(file));
			recordAmount = dis.readInt();
			dataSize = dis.readInt();
			titleSize = dis.readInt();
			dis.read(data = new byte[titleSize]);
			info.setColumes(new String(data));
			data = new byte[dataSize];
			processor.initSlots(recordAmount);

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
				hashcode = processor.hashCode(colVal, 0);

				processor.addOffset(colVal, hashcode, offset, 2);

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
			System.out.print("Index file creating finished!");
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
		DataOutputStream dos = null;
		long[] offsets;
		try {
			dos = new DataOutputStream(new FileOutputStream("./index." + pageSize));
			offsets = processor.getOffsets();
			
			for (long offset: offsets)
				dos.writeLong(offset);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (dos != null)
					dos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
