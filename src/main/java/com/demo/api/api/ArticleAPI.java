package com.demo.api.api;

import com.demo.api.business.ArticleManager;
import com.demo.api.business.Article;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/articles")
public class ArticleAPI {
    private final ArticleManager articleManager = ArticleManager.getInstance();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Article> articles = articleManager.getAll();
        return Response.ok().entity(articles).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Article article){
        if(article.getNom().isBlank()){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Le nom de l'article ne peut pas être vide!").build();
        }
        else if(article.getPrix() == null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Le prix de l'article doit obligatoirement être défini!").build();
        }
        else  {
            articleManager.add(article);
            return Response.status(Response.Status.CREATED).entity(article).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id){
        Article article = articleManager.getById(id);
        if(article == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("404 : Article introuvable !").build();
        }
        else {
            return Response.ok().entity(article).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id){
        Article article = articleManager.getById(id);
        if(article == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Article inexistant !").build();
        }
        else {
            articleManager.delete(article);
            return Response.ok().entity("Article supprimé avec succès !").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Article article, @PathParam("id") Integer id){
        if(article == null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Article non existant !").build();
        }
        else if(id < 1 || id > articleManager.getAll().size() ||
                article.getId() < 1 || article.getId() > articleManager.getAll().size()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("ID inexistant !").build();
        }
        else if(!article.getId().equals(id)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("ID mismatch !").build();
        }
        else {
            if(!articleManager.update(article, id)) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Article non existant !").build();
            }
            return Response.ok().entity(articleManager.getById(id)).build();
        }
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response patch(Article article, @PathParam("id") Integer id){
        if(article == null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Article non existant !").build();
        }
        else if(id < 1 || id > articleManager.getAll().size() ||
                article.getId() < 1 || article.getId() > articleManager.getAll().size()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("ID inexistant !").build();
        }
        else if(!article.getId().equals(id)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("ID mismatch !").build();
        }
        else {
            if(!articleManager.patch(article, id)) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Article non existant !").build();
            }
            return Response.ok().entity(articleManager.getById(id)).build();
        }
    }
}
