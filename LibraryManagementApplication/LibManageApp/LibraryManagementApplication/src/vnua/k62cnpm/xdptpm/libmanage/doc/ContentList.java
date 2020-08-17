/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vnua.k62cnpm.xdptpm.libmanage.doc;

/**
 *
 * @author Minh
 */
public class ContentList {
    private String name, id;

    public ContentList(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return name;
    }
}
