/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontera;

import entidades.OrderedProduct;
import entidades.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author aalex
 */
@Stateless
public class OrderedProductFacade extends AbstractFacade<OrderedProduct> {

    @PersistenceContext(unitName = "Affablebean_01PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderedProductFacade() {
        super(OrderedProduct.class);
    }
    
    public java.util.List<OrderedProduct> findOrderedProductByIdCustomerOrder(int id)
    {
        em = getEntityManager();
        Query query = em.createNamedQuery("OrderedProduct.findOrderedProductByIdCustomerOrder", OrderedProduct.class);
        query.setParameter("customerOrderID", id);
        java.util.List<OrderedProduct> lista= query.getResultList();
        return lista;
    }
   
}
