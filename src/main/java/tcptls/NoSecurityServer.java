package tcptls;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/*
 * https://github.com/dordonez-ute-apdist/tcptls
 * @author dordonez@ute.edu.ec
 */
public class NoSecurityServer {

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(8443);
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
