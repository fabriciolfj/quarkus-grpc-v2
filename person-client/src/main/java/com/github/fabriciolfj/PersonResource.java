package com.github.fabriciolfj;

import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Uni;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/api/v1/persons")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    @GrpcClient
    MutinyPersonServiceGrpc.MutinyPersonServiceStub personServiceClient;

    @GET
    @Path("/{code}")
    public Uni<PersonResponse> find(@PathParam("code") final String code) {
        return personServiceClient.find(PersonRequestCode.newBuilder()
                        .setCode(code)
                .build());
    }
}