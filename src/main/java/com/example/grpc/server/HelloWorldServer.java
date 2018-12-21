package com.example.grpc.server;

import com.example.grpc.service.helloworld.GreeterServiceImpl;
import com.example.grpc.service.person.PersonService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Logger;

public class HelloWorldServer {
	private static final Logger logger = Logger.getLogger(HelloWorldServer.class.getName());
	private static final Integer PORT = 8080;
	Server server;

	public static void main(String... args) throws InterruptedException {
		try {
			final HelloWorldServer helloWorldServer = new HelloWorldServer();
			helloWorldServer.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void start() throws IOException, InterruptedException {
		ServerBuilder.forPort(PORT)
			//.addService(new GreeterServiceImpl())
			.addService(new PersonService())
			.build()
			.start()
			.awaitTermination();
		logger.info("Server started, listening on " + PORT);
		/*Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				// Use stderr here since the logger may have been reset by its JVM shutdown hook.
				System.err.println("*** shutting down gRPC server since JVM is shutting down");
				HelloWorldServer.this.stop();
				System.err.println("*** server shut down");
			}
		});*/
	}

	private void stop() {
		if (server != null) {
			server.shutdown();
		}
	}

	/**
	 * Await termination on the main thread since the grpc library uses daemon threads.
	 */
	private void blockUntilShutdown() throws InterruptedException {
		if (server != null) {
			server.awaitTermination();
		}
	}
}
