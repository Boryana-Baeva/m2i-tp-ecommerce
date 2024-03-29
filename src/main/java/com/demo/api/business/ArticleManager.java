package com.demo.api.business;

import com.demo.api.data.DAO;

import java.util.List;

public class ArticleManager {
    private final DAO<Article> articleDAO = new DAO<>(Article.class);

    private static ArticleManager articleManager;
    public static ArticleManager getInstance() {
        if(articleManager == null) {
            articleManager = new ArticleManager();
        }
        return articleManager;
    }

    private ArticleManager() {
    }

    public List<Article> getAll(){
        return articleDAO.findAll();
    }

    public void add(Article article) {
        articleDAO.save(article);
    }

    public Article getById(Integer id) {
        return articleDAO.findById(id);
    }

    public void delete(Article article){
        articleDAO.delete(article);
    }

    public boolean update(Article articleNew, int id) {
        Article articleExisting = articleDAO.findById(id);

        if(articleExisting != null) {
            articleDAO.update(articleNew);
            return true;
        }

        return false;
    }

    public boolean patch(Article article, Integer id) {
        Article articleExisting = articleDAO.findById(id);

        if(articleExisting != null) {
            if(article.getNom() != null)
                articleExisting.setNom(article.getNom());
            if(article.getPrix() != null)
                articleExisting.setPrix(article.getPrix());
            if(article.getCategorie() != null)
                articleExisting.setCategorie(article.getCategorie());

            articleDAO.patch(articleExisting);
            return true;
        }
        return false;
    }
}
