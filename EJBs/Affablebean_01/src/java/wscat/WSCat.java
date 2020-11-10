/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wscat;

import entidades.Category;
import frontera.CategoryFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author aalex
 */
@WebService(serviceName = "WSCat")
public class WSCat {

    @EJB
    private CategoryFacade ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "create")
    //@Oneway
    public long create(@WebParam(name = "entity") Category entity) {
        ejbRef.create(entity);
        return entity.getId(); 
    }

    @WebMethod(operationName = "edit")
    @Oneway
    public void edit(@WebParam(name = "entity") Category entity) {
        ejbRef.edit(entity);
    }

    @WebMethod(operationName = "remove")
    @Oneway
    public void remove(@WebParam(name = "entity") Category entity) {
        ejbRef.remove(entity);
    }

    @WebMethod(operationName = "find")
    public Category find(@WebParam(name = "id") Object id) {
        return ejbRef.find(id);
    }

    @WebMethod(operationName = "findAll")
    public List<Category> findAll() {
        return ejbRef.findAll();
    }

    @WebMethod(operationName = "findRange")
    public List<Category> findRange(@WebParam(name = "range") int[] range) {
        return ejbRef.findRange(range);
    }

    @WebMethod(operationName = "count")
    public int count() {
        return ejbRef.count();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "findByName")
    public List<Category> findByName(@WebParam(name = "name") String name) {
        //TODO write your implementation code here:
        return ejbRef.findByName(name);
    }
    
     /**
     * Web service operation
     */
    @WebMethod(operationName = "findByNameLike")
    public List<Category> findByNameLike(@WebParam(name = "name") String name) {
        //TODO write your implementation code here:
        return ejbRef.findByNameLike(name);
    }
}
