/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.dao;

import com.esprit.entity.Employe;
import java.util.List;

/**
 *
 * @author saif
 */
public interface EmpIdao {
    public void insert(Employe o);
    public void delete(Employe o);
    public List<Employe> displayAll();
    public Employe displayById(int id);
    
    public boolean update(Employe os);
    
}
