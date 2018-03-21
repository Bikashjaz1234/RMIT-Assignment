import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class Week8_2 {
	// Variable for Jar URL
	private final static String JAR_URL = "jar:http://m1-c45n1.csit.rmit.edu.au/~Course/HelloWorld.jar!/";
	
  public static void main(String[] args) throws IOException {
	  // Create a File URL
	  URL FileSysUrl = new URL(JAR_URL);
	  // Create a connection
	  JarURLConnection jarURLConnection = (JarURLConnection)FileSysUrl.openConnection();
	  // Get the Jar file
	  JarFile jarFile = jarURLConnection.getJarFile();
	  
	  // Get the file entries
      Enumeration e = jarFile.entries();
      // Use the code that the Lab gives us to through the jar file entries.
      while (e.hasMoreElements()) {
      JarEntry je = (JarEntry) e.nextElement();
      // Get and Print the name
      System.out.println("The name is: " + je.getName());
      // Get and Print the size
      long uncompressedSize = je.getSize();
      System.out.println("The size is: " + uncompressedSize);
      // Get and Print the compressed size
      long compressedSize = je.getCompressedSize();
      System.out.println("The compressd size is: " + compressedSize);

      System.out.println();
    }
  }
}