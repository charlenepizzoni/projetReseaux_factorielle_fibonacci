package factorielle;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import factorielle.ClientFactorielle;

public class ServeurFactorielle {

	class ClientThread extends Thread {
		Socket socket;
	    InputStream sInput;
	    OutputStream sOutput;
	    int valeur;
	    int calcul;
	    
	    ClientThread(Socket socket, int valeur) {
		    try {
		        this.socket = socket;
		        sOutput = socket.getOutputStream();
		        sInput  = socket.getInputStream();
		        this.valeur = valeur;
		    }//try
		    catch (Exception e) {}
	        }//ClientThread
	    public void run() {
            Scanner sc = new Scanner(sInput);
            while (true)
                if (sc.hasNext()) {
                    int valeurACalculer = sc.nextInt();
                    if (valeurACalculer < 1){
                    	broadcast(1, this);
                    }
                    else{
                    	ClientFactorielle clientFictif = new ClientFactorielle( (valeurACalculer-1) );
                    	broadcast((valeurACalculer*demandeCalcul(valeurACalculer)), this);
                    }
                }//if
        }//run

    }//ClientThread
	
// Server part
    
    int port;
    int valeur = 0;
    int calcul = 0;

    void ServeurFactoriel(int port) {
    	this.port = port;
    }//Chat

    synchronized void broadcast( int calcul, ClientThread client) { // le broadcast fait le calcul
		
            PrintStream output = new PrintStream(client.sOutput);
            output.println(calcul);

    }//broadcast
    
    void run() {
		try {
		    ServerSocket sServer = new ServerSocket(port);
		    while (true) {
			Socket s = sServer.accept();
			ClientThread c = new ClientThread(s, (valeur-1));;
	                System.out.println("Connection of client " + num);
	                num++;
			socks.add(c);
			c.start();
		    }//while
		}//try
		catch (Exception e) {}
	    }//run

    public static void main(String [] argv) {
		ServeurFactoriel serveur = new ServeurFactoriel(50000);
		serveur.run();
    }//main
    
    int demandeCalcul (int valACalculer){
        int reponseDuClient = 1;
        if (valACalculer < 1){ //on avait dit qu'on faisait comme Áa pour etre sur!
         return 1;
        }
        else{
        	ClientFactoriel clientFictif = new ClientFactoriel( (valACalculer-1) );
         demander_a_CF_de_calculer(valACaculer -1); //on demander au client la valeur de (valACalculer-1)!
         // reponseDuClient = il faut juste rÈcupere la reponse donnÈe par le client
         return n*reponseDuClient;
        }
       }
}