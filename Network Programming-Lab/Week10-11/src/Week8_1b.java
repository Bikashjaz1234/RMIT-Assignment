import java.net.*;
import java.io.*;


public class Week8_1b
{
  public static void main(String[] args)
  {
	// Create the URL content
    String output  = getUrlContents("http://m1-c45n1.csit.rmit.edu.au/~Course/index.php");
    System.out.println(output);
  }

  private static String getUrlContents(String theUrl)
  {
    StringBuilder content = new StringBuilder();

    // many of these calls can throw exceptions, so i've just
    // wrapped them all in one try/catch statement.
    try
    {
      // create a URL connection
      URL url = new URL(theUrl);

      // create a urlconnection for this URL
      URLConnection urlConnection = url.openConnection();

      // Create bufferReader for read the content 
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

      String line;

      // read from the URL content by using BufferReader
      while ((line = bufferedReader.readLine()) != null)
      {
        content.append(line + "\n");
      }
      bufferedReader.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return content.toString();
  }
}