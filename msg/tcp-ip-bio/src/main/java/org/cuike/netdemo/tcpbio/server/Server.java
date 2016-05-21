package org.cuike.netdemo.tcpbio.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	private int port = 7956;
	ServerSocket serverSocket = new ServerSocket(port);

	public Server() throws IOException {

	}

	public void service() throws IOException {
		Socket socket = null;
		ExecutorService connectionsPool = Executors.newFixedThreadPool(50);
		try {
			while (true) {
				socket = serverSocket.accept();
				connectionsPool.execute(new EchoServerHandler(socket));
			}
		} finally {
			if (serverSocket != null) {
				serverSocket.close();
				System.out.println("Server has closed");
				serverSocket = null;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.service();
	}
}
