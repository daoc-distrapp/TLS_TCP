package tcptls;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/*
 * https://github.com/dordonez-ute-apdist/tcptls
 * @author dordonez@ute.edu.ec
 */
public class TlsClient {

	public static void main(String[] args) {
		//Carga una TrustStore personalizada
		System.setProperty( "javax.net.ssl.trustStore", "ClientTrustStore.jks" );
		System.setProperty( "javax.net.ssl.trustStorePassword", "password" );		
		
		try {
			SSLSocket socket = (SSLSocket) SSLSocketFactory.getDefault().createSocket("localhost", 8443);
			socket.startHandshake();
			System.out.println("Conectado con servidor");
			Scanner scan = new Scanner(socket.getInputStream());
			PrintWriter write = new PrintWriter(socket.getOutputStream(), true);
			write.println("Hola !");
			System.out.println("Respuesta: " + scan.nextLine());
			write.println("exit");
			System.out.println("Respuesta: " + scan.nextLine());
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
