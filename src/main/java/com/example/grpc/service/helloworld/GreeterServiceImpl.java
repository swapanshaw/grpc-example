package com.example.grpc.service.helloworld;

import com.example.helloworld.GreeterGrpc;
import com.example.helloworld.HelloRequest;
import com.example.helloworld.HelloResponse;
import io.grpc.stub.StreamObserver;

public class GreeterServiceImpl extends GreeterGrpc.GreeterImplBase {

	@Override
	public void sayHello(HelloRequest req, StreamObserver<HelloResponse> responseObserver) {
		HelloResponse reply = HelloResponse.newBuilder().setMessage("Hello " + req.getName()).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}
}
