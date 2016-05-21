package org.cuike.netdemo.tcpbio.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private int port = 7956;
	ServerSocket serverSocket = new ServerSocket(port);

	public Server() throws IOException {

	}

	public void service() throws IOException {
		Socket socket = null;
		while (true) {
			try {
				socket = serverSocket.accept();
				new Thread(new EchoServerHandler(socket)).start();
			} finally {
				if (socket != null) {
					socket.close();
					System.out.println("Server has closed");
					socket = null;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.service();
	}
}
