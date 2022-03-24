package com.github.fabriciolfj;

import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@GrpcService
public class PersonServiceAdapter extends PersonServiceGrpc.PersonServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(PersonServiceAdapter.class);

    @Override
    public void create(PersonRequest request, StreamObserver<PersonResponse> responseObserver) {
        var response = PersonResponse.newBuilder()
                .setCode(UUID.randomUUID().toString())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void find(PersonRequestCode request, StreamObserver<PersonResponse> responseObserver) {
        //busca na base de dados
        logger.info("Request: {}", request.toString());
        var response = PersonResponse.newBuilder()
                .setCode(request.getCode())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
