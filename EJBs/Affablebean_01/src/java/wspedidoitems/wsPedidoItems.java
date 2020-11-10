/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wspedidoitems;

import entidades.OrderedProduct;
import frontera.OrderedProductFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author tabat
 */
@WebService(serviceName = "wsPedidoItems")
public class wsPedidoItems {

    @EJB
    private OrderedProductFacade ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "create")
    //@Oneway
    public void create(@WebParam(name = "entity") OrderedProduct entity) {
        ejbRef.create(entity);
    }

    @WebMethod(operationName = "edit")
    //@Oneway
    public void edit(@WebParam(name = "entity") OrderedProduct entity) {
        ejbRef.edit(entity);
    }

    @WebMethod(operationName = "remove")
    @Oneway
    public void remove(@WebParam(name = "entity") OrderedProduct entity) {
        ejbRef.remove(entity);
    }

    @WebMethod(operationName = "find")
    public OrderedProduct find(@WebParam(name = "id") Object id) {
        return ejbRef.find(id);
    }

    @WebMethod(operationName = "findAll")
    public List<OrderedProduct> findAll() {
        return ejbRef.findAll();
    }

    @WebMethod(operationName = "findRange")
    public List<OrderedProduct> findRange(@WebParam(name = "range") int[] range) {
        return ejbRef.findRange(range);
    }

    @WebMethod(operationName = "count")
    public int count() {
        return ejbRef.count();
    }
    
}
