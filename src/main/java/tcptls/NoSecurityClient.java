package tcptls;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/*
 * https://github.com/dordonez-ute-apdist/tcptls
 * @author dordonez@ute.edu.ec
 */
public class NoSecurityClient {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 8443);
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
