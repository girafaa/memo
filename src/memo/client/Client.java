package memo.client;

import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		new inputWindow();
		String ip = "localhost";
		int port = 20000;
		try (
			Socket socket = new Socket(ip, port);
		){
			System.out.println("접속 성공");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
