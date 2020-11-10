/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsprod;

import entidades.Product;
import frontera.ProductFacade;
import java.util.ArrayList;
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
@WebService(serviceName = "WSProd")
public class WSProd {

    @EJB
    private ProductFacade ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "create")
    //@Oneway
    public long create(@WebParam(name = "entity") Product entity) {
        ejbRef.create(entity);
        return entity.getId(); 
    }

    @WebMethod(operationName = "edit")
    @Oneway
    public void edit(@WebParam(name = "entity") Product entity) {
        ejbRef.edit(entity);
    }

    @WebMethod(operationName = "remove")
    @Oneway
    public void remove(@WebParam(name = "entity") Product entity) {
        ejbRef.remove(entity);
    }

    @WebMethod(operationName = "find")
    public Product find(@WebParam(name = "id") Object id) {
        return ejbRef.find(id);
    }

    @WebMethod(operationName = "findAll")
    public List<Product> findAll() {
        return ejbRef.findAll();
    }

    @WebMethod(operationName = "findRange")
    public List<Product> findRange(@WebParam(name = "range") int[] range) {
        return ejbRef.findRange(range);
    }

    @WebMethod(operationName = "count")
    public int count() {
        return ejbRef.count();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "operation")
    public List<Product> operation() {
        Product p;
        List<Product> lista = ejbRef.findAll();
        List<Product> otraLista= new ArrayList<>();
        for (Product q:lista) {
            p= new Product();
            p.setId(q.getId());
            p.setCategoryId(null);
            p.setDescription(q.getDescription());
            p.setLastUpdate(q.getLastUpdate());
            p.setName(q.getName());
            p.setPrice(q.getPrice());
            otraLista.add(p);
            
            
        }
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "findByName")
    public List<Product> findByName(@WebParam(name = "name") String name) {
        //TODO write your implementation code here:
        return ejbRef.findByName(name);
    }

   @WebMethod(operationName = "findByNameLike")
    public List<Product> findByNameLike(@WebParam(name = "name") String name) {
        //TODO write your implementation code here:
        return ejbRef.findByNameLike(name);
    }
    
     @WebMethod(operationName = "precioPromPorCatId")
    public String precioPromPorCatId() {
        return ejbRef.precioPromPorCatId();
    }
}
