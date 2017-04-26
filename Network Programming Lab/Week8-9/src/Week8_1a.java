import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

public class Week8_1a {

	public static void main(String[] args) throws IOException {
		// Write the URL of the website
		URL url = new URL("http://m1-c45n1.csit.rmit.edu.au/~Course/index.php");
		// Make a JAVA URL Connection
		URLConnection conn = url.openConnection();
		// Friendly output the URL and IP Address
		System.out.println("The URL is: " + url);
		InetAddress address = InetAddress.getByName(url.getHost());
		System.out.println("The Ip Address is: " + address.toString());
		
		// Use for loop to print the header field
		// Only print the first 8 header field
		for (int i = 0; i <= 7; i++) {
			// Print the header name
		      String headerName = conn.getHeaderFieldKey(i);
		      // Print the header value
		      String headerValue = conn.getHeaderField(i);
		      System.out.println("This is the " + (i+1) + " header field");
		      System.out.println("The Header name is: " + headerName);
		      System.out.println("The Header value is: " + headerValue);
		      
		      //Check if the header less than 8, it will stop
		      if (headerName == null && headerValue == null) {
		        System.out.println("No more headers");
		        break;
		      }
		    }

	}

}
