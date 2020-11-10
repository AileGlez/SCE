/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wspedidoheader;

import entidades.CustomerOrder;
import frontera.CustomerOrderFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import wscust.WSCustomer;

/**
 *
 * @author tabat
 */
@WebService(serviceName = "wsPedidoHeader")
public class wsPedidoHeader {

    @EJB
    private CustomerOrderFacade ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "create")
    //@Oneway
    public long create(@WebParam(name = "entity") CustomerOrder entity) {
        ejbRef.create(entity);
        long idNvoCustomerOrder= entity.getId();
        return idNvoCustomerOrder;
    }

    @WebMethod(operationName = "edit")
    @Oneway
    public void edit(@WebParam(name = "entity") CustomerOrder entity) {
        ejbRef.edit(entity);
    }

    @WebMethod(operationName = "remove")
    @Oneway
    public void remove(@WebParam(name = "entity") CustomerOrder entity) {
        ejbRef.remove(entity);
    }

    @WebMethod(operationName = "find")
    public CustomerOrder find(@WebParam(name = "id") Object id) {
        return ejbRef.find(id);
    }

    @WebMethod(operationName = "findAll")
    public List<CustomerOrder> findAll() {
        return ejbRef.findAll();
    }

    @WebMethod(operationName = "findRange")
    public List<CustomerOrder> findRange(@WebParam(name = "range") int[] range) {
        return ejbRef.findRange(range);
    }

    @WebMethod(operationName = "count")
    public int count() {
        return ejbRef.count();
    }
    
}
