import java.io.*;
import java.net.*;
import java.util.*;
import static java.lang.System.out;

public class Week4_1 
{
    public static void main(String args[]) throws SocketException {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        
        for (NetworkInterface netIf : Collections.list(nets)) {
            out.printf("Display name: %s\n", netIf.getDisplayName());
            out.printf("Name: %s\n", netIf.getName());
            //Get the MAC address
            byte[] mac = netIf.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            out.printf("Mac Address is: ");
            if (mac != null){
            	for (int i = 0; i < mac.length; i++) {
        			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
        		}
            }else{
            	System.out.print("Cannot get the Mac Address for this interface!");
            }
    		System.out.println(sb.toString());
    		
    		//Get the hostname
    		String hostname = "Unknown";
    		try
    		{
    		    InetAddress addr;
    		    addr = InetAddress.getLocalHost();
    		    hostname = addr.getHostName();
    		    System.out.println("Hostname is: " + hostname);
    		}
    		catch (UnknownHostException ex)
    		{
    		    System.out.println("Hostname can not be resolved");
    		}
            displaySubInterfaces(netIf);
            try 
              {
                InetAddress me = InetAddress.getLocalHost();
                String dottedQuad = me.getHostAddress();
                System.out.println("HostAddress is " + dottedQuad);
              } catch (UnknownHostException e) {
                System.out.println("I'm sorry. I don't know my own address.");
              }
            out.printf("\n");
        }
    }

    static void displaySubInterfaces(NetworkInterface netIf) throws SocketException {
        Enumeration<NetworkInterface> subIfs = netIf.getSubInterfaces();
        
        for (NetworkInterface subIf : Collections.list(subIfs)) {
            out.printf("\tSub Interface Display name: %s\n", subIf.getDisplayName());
            out.printf("\tSub Interface Name: %s\n", subIf.getName());
          //Get the MAC address
            byte[] mac = netIf.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            out.printf("Mac Address is: ");
            if (mac != null){
            	for (int i = 0; i < mac.length; i++) {
        			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
        		}
            }else{
            	System.out.print("Cannot get the Mac Address for this interface!");
            }
    		System.out.println(sb.toString());
        }
     }
}  
