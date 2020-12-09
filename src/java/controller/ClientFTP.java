package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author dhanush
 */
// FTP Client


import java.net.*;
import java.io.*;


public class ClientFTP
{
    
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public static Socket soc;
    public static DataInputStream din = null;
    public static DataOutputStream dout = null;
    public static DataInputStream datadin = null;
    public static DataOutputStream dataout = null;
    public static Socket datasoc;
    public static int PortNo,dataPort;
    public static String Host;
    public static ObjectInputStream objectInput = null;
    public static ObjectOutputStream objectOutput = null;
    public String connect(String args[]) throws Exception
    {
     	
        String username="";
        String pass="";
        
        String msg="Failure";
	 	//System.out.println(args[0]);
	 	if (args.length == 4){
	 		//System.out.println(args[1]);
	 		Host=args[0];
                        PortNo=Integer.parseInt(args[1]);
                        
                        username=args[2];
                        pass=args[3];
	 		
	 	}
	 	else {
	 		PortNo=21;
	 	}
        soc=new Socket(Host,PortNo);
        dataPort=PortNo-1;
        
        System.out.println(soc.getPort());
        din=new DataInputStream(soc.getInputStream());
        dout=new DataOutputStream(soc.getOutputStream());
        
        dout.writeUTF(username);
    	dout.writeUTF(pass);
        
    	return din.readUTF();
        
        
    }


    
    

}
 