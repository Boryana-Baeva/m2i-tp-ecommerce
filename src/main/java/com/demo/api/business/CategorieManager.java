package com.demo.api.business;

import com.demo.api.data.DAO;

import java.util.List;

public class CategorieManager {
    private final DAO<Categorie> categorieDAO = new DAO<>(Categorie.class);

    private static CategorieManager categorieManager;
    public static CategorieManager getInstance() {
        if(categorieManager == null) {
            categorieManager = new CategorieManager();
        }
        return categorieManager;
    }

    private CategorieManager() {
    }

    public List<Categorie> getAll(){
        return categorieDAO.findAll();
    }

    public void add(Categorie categorie) {
        categorieDAO.save(categorie);
    }

    public Categorie getById(Integer id) {
        return categorieDAO.findById(id);
    }

    public void delete(Categorie categorie){
        categorieDAO.delete(categorie);
    }
}
