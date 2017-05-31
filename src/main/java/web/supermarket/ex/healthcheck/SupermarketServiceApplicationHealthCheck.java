package web.supermarket.ex.healthcheck;

import com.codahale.metrics.health.HealthCheck;

/**
 * Created by gperry on 31/05/2017.
 */
public class SupermarketServiceApplicationHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}

