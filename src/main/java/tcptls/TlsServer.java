package tcptls;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.net.ssl.SSLServerSocketFactory;

/*
 * https://github.com/dordonez-ute-apdist/tcptls
 * @author dordonez@ute.edu.ec
 */
public class TlsServer {

	public static void main(String[] args) {
		// Carga la KeyStore con las claves del servidor
	    System.setProperty("javax.net.ssl.keyStore", "ServerKeyStore.pkcs12");
	    System.setProperty("javax.net.ssl.keyStorePassword", "password");		

		try {
			ServerSocket serverSocket = SSLServerSocketFactory.getDefault().createServerSocket(8443);
			System.out.println("Esperando Cliente");
			Socket socket = serverSocket.accept();
			System.out.println("Cliente conectado");
			Scanner scan = new Scanner(socket.getInputStream());
			PrintWriter write = new PrintWriter(socket.getOutputStream(), true);
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				System.out.println("Recibido: " + line);
				write.println("ECHO: " + line);
				if(line.equalsIgnoreCase("EXIT")) break;
			}
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
