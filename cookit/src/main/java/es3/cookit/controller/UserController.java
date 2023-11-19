package es3.cookit.controller;

import es3.cookit.UserUtilsService;
import es3.cookit.dto.UserDto;
import es3.cookit.entities.Food;
import es3.cookit.entities.User;
import es3.cookit.services.UserService;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("userManager")
//@Authenticated
public class UserController {

    @Inject
    UserService userService;

    @GET
    @Path("{id}")
    public Response listUser(@PathParam("id") Long id) {
        User user = userService.listUserById(id);
        return Response.ok(user).build();
    }

    @POST
    @Path("/login")
    public Response registerUser(UserDto dto) {
        User loggedUser = new User();
        loggedUser = userService.logginUser(dto);
        return Response.ok(loggedUser).status(201).build();
    }

    @Path("/logout/{id}")
    @GET
    public Response unsignUser(@PathParam("id") Long id) {
        userService.loggoutUser(id);
        return Response.status(204).build();
    }

    @PUT
    @Path("{id}")
    public Response updateUser(@PathParam("id") Long id, UserDto dto) {
        userService.updateUser(id, dto);
        return Response.status(204).build();
    } 

}
