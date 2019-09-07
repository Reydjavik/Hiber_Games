/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.main;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author aymeric.allazzetta
 */
@Named(value = "gameController")
@SessionScoped
public class GameController implements Serializable {

    int startId;
    int endId;
    DataModel gameTitles;
    GameHelper helper;
    private int recordCount = 1000;
    private int pageSize = 10;
        
    private Jeux current;
    private int selectedItemIndex;
    
    
    /**
     * Creates a new instance of GameController
     */
    public GameController() {
        
        helper = new GameHelper();
        startId = 1;
        endId = 10;
        
    }
    
    public GameController(int startId, int endId) {
        
        helper = new GameHelper();
        this.startId=startId;
        this.endId=endId;
    }
    
    public Jeux getSelected() {
        
        if(current==null){
            current = new Jeux();
            selectedItemIndex =-1;
        }
        return current;
    }
    
    public DataModel getGameTitles(){
        
        if(gameTitles==null){
            gameTitles = new ListDataModel(helper.getGameTitles(startId, endId));
        }
        return gameTitles;
    }
    
    void recreateModel() {
        gameTitles=null;
    }
    
    public boolean isHasNextPage() {
        
        if (endId + pageSize <= recordCount) {
            return true;
        }
        return false;
    }
    
    public boolean isHasPreviousPage() {
        
        if (startId - pageSize > 0) {
            return true;
        }
        return false;
    }
    
    public String next() {
        
        startId = endId=1;
        endId = endId + pageSize;
        recreateModel();
        return "index";
    }
    
    public String previous() {
        startId = startId - pageSize;
        endId = endId - pageSize;
        recreateModel();
        return "index";
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public String prepareView() {
        current = (Jeux) getGameTitles().getRowData();
        return "browse";
    }
    
    public String prepareList() {
        recreateModel();
        return "index";
    }
}
