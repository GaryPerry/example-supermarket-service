package web.supermarket.ex.exception;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import io.dropwizard.jersey.validation.ConstraintMessage;
import io.dropwizard.jersey.validation.ValidationErrorMessage;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by gperry on 31/05/2017.
 */
public class BadRequestExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        List<String> errors = exception.getConstraintViolations().stream()
                .map(ConstraintMessage::getMessage)
                .collect(toList());

        if (errors.size() == 0) {
            errors = ImmutableList.of(Strings.nullToEmpty(exception.getMessage()));
        }

        return Response.status(400).entity(new ValidationErrorMessage(ImmutableList.copyOf(errors))).build();
    }
}