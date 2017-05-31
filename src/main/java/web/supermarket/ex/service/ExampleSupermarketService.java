package web.supermarket.ex.service;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.jersey.errors.ErrorMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import web.supermarket.ex.client.mongo.MongoClient;
import web.supermarket.ex.client.mongo.MongoClientConfiguration;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by gperry on 31/05/2017.
 */
@Path("/journal")
@Api(value = "Journal Metadata API")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExampleSupermarketService {

    private MongoClient mongoClient;

    public ExampleSupermarketService(MongoClientConfiguration mongoClientConfiguration) {
        this.mongoClient = new MongoClient(mongoClientConfiguration);
    }

    @GET
    @Timed
    @ExceptionMetered
    @ApiOperation(value = "Retrieve all journals")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Journals found", responseContainer = "List", response = String.class),
            @ApiResponse(code = 422, message = "Unprocessable entity", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ErrorMessage.class)})
    public String returnYes () {

        return "yes";
    }
}
