package web.supermarket.ex.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.glassfish.jersey.filter.LoggingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.supermarket.ex.exception.BadRequestExceptionMapper;
import web.supermarket.ex.healthcheck.SupermarketServiceApplicationHealthCheck;
import web.supermarket.ex.service.ExampleSupermarketService;

import static java.util.logging.Logger.getLogger;

public class SupermarketServiceApplication extends Application<SupermarketServiceApplicationConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(SupermarketServiceApplication.class);

    public static void main(String[] args) throws Exception {
        new SupermarketServiceApplication().run(args);

        logger.info("*** Journal Metadata Service started ***");
    }

    @Override
    public String getName() {
        return "journal-metadata-service";
    }

    @Override
    public void initialize(Bootstrap<SupermarketServiceApplicationConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<SupermarketServiceApplicationConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
                    SupermarketServiceApplicationConfiguration configuration) {
                return configuration.getSwaggerBundleConfiguration();
            }
        });

        logger.info("** Bootstrap initialised **");
    }

    @Override
    public void run(SupermarketServiceApplicationConfiguration configuration, Environment environment) throws Exception {
        environment.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

        ExampleSupermarketService exampleSupermarketService = new ExampleSupermarketService(configuration.getMongoClientConfiguration());

        environment.jersey().register(exampleSupermarketService);
        environment.jersey().register(BadRequestExceptionMapper.class);
        environment.jersey().register(new LoggingFilter(getLogger(LoggingFilter.class.getName()), true));

        environment.healthChecks().register("healthcheck", new SupermarketServiceApplicationHealthCheck());

        logger.info("** Environment initialised **");
    }
}
