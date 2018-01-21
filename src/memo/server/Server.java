package memo.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try(
				ServerSocket server = new ServerSocket(20000);
				Socket socket = server.accept();
			)
		{
			System.out.println(socket.toString());//Á¤º¸ Âï±â
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
