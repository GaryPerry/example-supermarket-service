package web.supermarket.ex.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import web.supermarket.ex.client.mongo.MongoClientConfiguration;


/**
 * Created by gperry on 31/05/2017.
 */

public class SupermarketServiceApplicationConfiguration extends Configuration {

    @JsonProperty("swagger")
    private SwaggerBundleConfiguration swaggerBundleConfiguration;

    @JsonProperty("mongo")
    private MongoClientConfiguration mongoClientConfiguration;

    public SwaggerBundleConfiguration getSwaggerBundleConfiguration() {
        return swaggerBundleConfiguration;
    }

    public MongoClientConfiguration getMongoClientConfiguration() {
        return mongoClientConfiguration;
    }
}

