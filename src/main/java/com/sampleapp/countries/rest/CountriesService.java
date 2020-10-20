package com.sampleapp.countries.rest;

import com.sampleapp.countries.model.Country;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.Set;

@Path("/v2")
@RegisterRestClient(configKey="country-api")
public interface CountriesService {
    @GET
    @Path("/all")
    @Produces("application/json")
    Uni<Set<Country>> getCountries(@QueryParam("fields") String fields);
}
