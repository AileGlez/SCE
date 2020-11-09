/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontera;

import entidades.Product;
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
public class ProductFacade extends AbstractFacade<Product> {

    @PersistenceContext(unitName = "Affablebean_01PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }
    
     public java.util.List<Product> findByName(String nombre)
    {
        em = getEntityManager();
        TypedQuery<Product> queryCatByName = em.createNamedQuery("Product.findByName", Product.class);
        queryCatByName.setParameter("name", nombre);
        java.util.List<Product> lista= queryCatByName.getResultList();

        return lista;
    }
     
     public java.util.List<Product> findByNameLike(String nombre)
    {
        em = getEntityManager();
        TypedQuery<Product> queryProdByName = em.createNamedQuery("Product.findByNameLike", Product.class);
        queryProdByName.setParameter("name", nombre);
        java.util.List<Product> lista= queryProdByName.getResultList();

        return lista;
    }
     
    public String precioPromPorCatId() 
    {
        String strRes = "\n";
        em = getEntityManager();
        
        Query query = em.createQuery("SELECT p.categoryId, AVG(p.price), COUNT(p.categoryId) FROM Product p GROUP BY p.categoryId");
        System.err.println(query.getResultList());
        java.util.List<Object> lista = query.getResultList();
       
	    entidades.Category cat;
	   
        for(Object obj:lista)
        {
          Object[] x = (Object[])obj;
          
         cat = (entidades.Category)x[0];
         strRes += cat.getId() + " " + cat.getName() + "... Cantidad de productos: "+ x[2].toString()+
                  " ... Precio Promedio:"+ x[1].toString() + '\n';
        }
        return strRes;
    }
   
      
}
