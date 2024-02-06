package com.nataliasep.listacompra.DataBase.sqlite.dao;

import com.nataliasep.listacompra.Interfaces.DAO;

import java.util.List;
import java.util.Map;

public class ItemDAO extends DAO {
    public ItemDAO(String tableName) {
        super(tableName);
    }

    @Override
    public Object findById(int id) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public boolean update(Object e) {
        return false;
    }

    @Override
    public boolean insert(Object e) {
        return false;
    }

    @Override
    public boolean delete(Object e) {
        return false;
    }

    @Override
    public List findBy(Map condition) {
        return null;
    }
}
