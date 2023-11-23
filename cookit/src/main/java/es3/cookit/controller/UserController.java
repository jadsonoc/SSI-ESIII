package es3.cookit.controller;

import java.util.List;

import es3.cookit.dto.UserDto;
import es3.cookit.entities.Recipe;
import es3.cookit.entities.User;
import es3.cookit.services.UserService;
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

    @GET
    @Path("/favourite/{id}")
    public Response listUsersFavouritesRecipes(@PathParam("id") Long idUser) {
        List<Recipe> recipes = userService.listUsersFavouritesRecipesById(idUser);
        return Response.ok(recipes).build();
    }

    @GET
    @Path("/isFavourite/{idUser}/{idRecipe}")
    public Response isFavouriteRecipe(@PathParam("idUser") Long idUser, @PathParam("idRecipe") Long idRecipe) {
        boolean isFavouriteUsersRecipe = userService.isFavouriteUsersRecipe(idUser, idRecipe);
        return Response.ok(isFavouriteUsersRecipe).build();
    }

    @POST
    @Path("/login")
    public Response registerUser(UserDto dto) {
        User loggedUser = new User();
        loggedUser = userService.logginUser(dto);
        return Response.ok(loggedUser).status(201).build();
    }

    @PUT
    @Path("/favourite/{idUser}/{idRecipe}")
    public Response favouriteRecipe(@PathParam("idUser") Long idUser, @PathParam("idRecipe") Long idRecipe) {
        userService.favouriteRecipe(idUser, idRecipe);
        return Response.status(204).build();
    }

    @PUT
    @Path("/unfavourite/{idUser}/{idRecipe}")
    public Response unfavouriteRecipe(@PathParam("idUser") Long idUser, @PathParam("idRecipe") Long idRecipe) {
        userService.unfavouriteRecipe(idUser, idRecipe);
        return Response.status(204).build();
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
