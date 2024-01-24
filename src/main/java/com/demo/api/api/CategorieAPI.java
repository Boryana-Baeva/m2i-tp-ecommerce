package com.demo.api.api;

import com.demo.api.business.Article;
import com.demo.api.business.Categorie;
import com.demo.api.business.CategorieManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/categories")
public class CategorieAPI {
    private CategorieManager categorieManager = CategorieManager.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Categorie> categories = categorieManager.getAll();
        return Response.ok().entity(categories).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Categorie categorie){
        if(categorie.getNom().isBlank()){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Le nom de la categorie ne peut pas être vide!").build();
        }
        else  {
            categorieManager.add(categorie);
            return Response.status(Response.Status.CREATED).entity(categorie).build();
        }
    }
}
