package com.demo.api.api;

import com.demo.api.data.Article;
import com.demo.api.data.DAO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/articles")
public class ArticleAPI {
    private final DAO<Article> articleDAO = new DAO<>(Article.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Article> articles = articleDAO.findAll();
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
            articleDAO.save(article);
            return Response.status(Response.Status.CREATED).entity(article).build();
        }
    }
}
