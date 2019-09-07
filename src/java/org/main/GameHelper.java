/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.main;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author aymeric.allazzetta
 */
public class GameHelper {
    
    Session session = null;
    
    public GameHelper() {
        
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public List getGameTitles (int startId, int endId) {
        
        List<Jeux> jeuxList = null;
        
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery ("from Jeux as jeux where jeux.idGame between'"+startId+"' and '"+endId+"'");
            jeuxList = (List<Jeux>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jeuxList;
    }
    
}
