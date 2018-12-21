package com.example.grpc.client;

import com.example.grpc.streaming.PersonRequest;
import com.example.grpc.streaming.PersonResponse;
import com.example.grpc.streaming.PersonServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.logging.Logger;

public class PersonClient {

	private static final String HOST = "localhost";
	private static final Integer PORT = 8080;

	private static final Logger LOGGER = Logger.getLogger(PersonClient.class.getName());
	public static void main(String... args) {
		final ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, PORT).usePlaintext().build();

		final PersonServiceGrpc.PersonServiceBlockingStub blockingStub = PersonServiceGrpc.newBlockingStub(channel);

		final PersonRequest personRequest = PersonRequest.newBuilder().setId("1").build();
		PersonResponse response = blockingStub.fetchPerson(personRequest);


		LOGGER.info("reponse from server " + response);


	}
}
