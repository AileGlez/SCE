/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontera;

import entidades.CustomerOrder;
import entidades.OrderedProduct;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author aalex
 */
@Stateless
public class CustomerOrderFacade extends AbstractFacade<CustomerOrder> {

    @PersistenceContext(unitName = "Affablebean_01PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerOrderFacade() {
        super(CustomerOrder.class);
    }
    
    public double countAmount()
    {
        em = getEntityManager();
        Query query = em.createNativeQuery("SELECT SUM(c.AMOUNT) FROM Customer_Order c "); 
        Object result = query.getSingleResult(); 
        return Double.parseDouble(result.toString());
    }
    
     
}
