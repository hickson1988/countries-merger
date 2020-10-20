package com.sampleapp.countries.resource;

import com.sampleapp.countries.model.CountriesResponse;
import com.sampleapp.countries.model.Country;
import com.sampleapp.countries.rest.CountriesService;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.tuples.Tuple2;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/countries")
public class CountriesResource {

    @Inject
    @RestClient
    CountriesService countriesService;

    @GET
    @RolesAllowed("user_role")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCountries() {
        Uni<Set<Country>> countriesNameCurrency = countriesService.getCountries("name;currencies");
        Uni<Set<Country>> countriesNameCapital = countriesService.getCountries("name;capital");

        Uni<Tuple2<Set<Country>, Set<Country>>> uni = Uni.combine().all().unis(countriesNameCurrency, countriesNameCapital).asTuple();
        Set<Country> result = uni.onItem().transform(tuple ->
                Stream.concat(tuple.getItem1().stream(), tuple.getItem2().stream())
                        .collect(Collectors.toMap(Country::getName, Function.identity(),
                                (country1, country2) -> {
                                    country1.setCapital(country2.getCapital());
                                    return country1;
                                })).values().stream().collect(Collectors.toSet())
        ).await().indefinitely();
        return Response.ok(new CountriesResponse(result.size(), result)).build();
    }
}