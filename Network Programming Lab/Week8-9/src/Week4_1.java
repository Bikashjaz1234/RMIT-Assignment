import java.io.*;
import java.net.*;
import java.util.*;
import static java.lang.System.out;

public class Week4_1 
{
    public static void main(String args[]) throws SocketException {
    	//Create a interface for get ip and other information.
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        
        for (NetworkInterface netIf : Collections.list(nets)) {
        	//print the name
            out.printf("Display name: %s\n", netIf.getDisplayName());
            out.printf("Name: %s\n", netIf.getName());
            //Get the MAC address
            byte[] mac = netIf.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            //print the mac address
            out.printf("Mac Address is: ");
            //format the mac address, make sure that it is readable
            if (mac != null){
            	for (int i = 0; i < mac.length; i++) {
        			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
        		}
            }else{
            	//if get failed, print cannot get it.
            	System.out.print("Cannot get the Mac Address for this interface!");
            }
    		System.out.println(sb.toString());
    		
    		//Get the hostname
    		String hostname = "Unknown";
    		//Using try and catch to catch the error
    		try
    		{
    		    InetAddress addr;
    		    addr = InetAddress.getLocalHost();
    		    hostname = addr.getHostName();
    		    //try to get the hostname and print it.
    		    System.out.println("Hostname is: " + hostname);
    		}
    		catch (UnknownHostException ex)
    		{
    		    System.out.println("Hostname can not be resolved");
    		}
            displaySubInterfaces(netIf);
            
            //Get Host address
            //Using try and catch to catch the error
            try 
              {
            	//get the hostaddress and print it
                InetAddress me = InetAddress.getLocalHost();
                String hostAddress = me.getHostAddress();
                System.out.println("HostAddress is " + hostAddress);
              } catch (UnknownHostException e) {
            	  //if cannot get it, print error message.
                System.out.println("I'm sorry. I don't know my own address.");
              }
            out.printf("\n");
        }
    }

    //Display Sub Interface Function
    static void displaySubInterfaces(NetworkInterface netIf) throws SocketException {
        Enumeration<NetworkInterface> subIfs = netIf.getSubInterfaces();
        
        //Using for loop to get all sub interfaces
        for (NetworkInterface subIf : Collections.list(subIfs)) {
            out.printf("\tSub Interface Display name: %s\n", subIf.getDisplayName());
            out.printf("\tSub Interface Name: %s\n", subIf.getName());
          //Get the MAC address of sub interface
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
