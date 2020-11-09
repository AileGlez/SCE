/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wscust;

import entidades.Customer;
import frontera.CustomerFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author aalex
 */
@WebService(serviceName = "WSCustomer")
public class WSCustomer {

    @EJB
    private CustomerFacade ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

   @WebMethod(operationName = "create")
    //@Oneway
    public long create(@WebParam(name = "entity") Customer entity) 
    {
        ejbRef.create(entity);
        long idNvoCustomer = entity.getId();
        Logger.getLogger(WSCustomer.class.getName()).log(Level.SEVERE, "El id del nuevo cliente es {0}", entity.toString());
        return idNvoCustomer;
    }

    @WebMethod(operationName = "edit")
    @Oneway
    public void edit(@WebParam(name = "entity") Customer entity) {
        ejbRef.edit(entity);
    }

    @WebMethod(operationName = "remove")
    @Oneway
    public void remove(@WebParam(name = "entity") Customer entity) {
        ejbRef.remove(entity);
    }

    @WebMethod(operationName = "find")
    public Customer find(@WebParam(name = "id") Integer id) {
        return ejbRef.find(id);
    }

    @WebMethod(operationName = "findAll")
    public List<Customer> findAll() {
        return ejbRef.findAll();
    }

    @WebMethod(operationName = "findRange")
    public List<Customer> findRange(@WebParam(name = "range") int[] range) {
        return ejbRef.findRange(range);
    }

    @WebMethod(operationName = "count")
    public int count() {
        return ejbRef.count();
    }
    
}
