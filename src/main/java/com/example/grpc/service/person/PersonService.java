package com.example.grpc.service.person;

import com.example.grpc.streaming.PersonRequest;
import com.example.grpc.streaming.PersonResponse;
import com.example.grpc.streaming.PersonServiceGrpc;
import io.grpc.stub.StreamObserver;

public class PersonService extends PersonServiceGrpc.PersonServiceImplBase {

	@Override
	public void fetchPerson(final PersonRequest request, final StreamObserver<PersonResponse> responseObserver) {

		final PersonResponse response = PersonResponse.newBuilder().setId(request.getId())
			.setName("John").setAge(20).build();

		responseObserver.onNext(response);
		responseObserver.onCompleted();

	}

}
