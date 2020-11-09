/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tstwsprodpojo;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import wscust.Customer;
import wsprod.Product;

/**
 *
 * @author aalex
 */
public class Tstwsprodpojo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         XMLGregorianCalendar fecha = null;
        boolean primero            = true;
        wsprod.Category cat        = new wsprod.Category();
        
        System.out.println("Son " + count_Product() + " productos");
        System.out.println("--------------Product.findAll() ----------------------------");
        java.util.List<wsprod.Product> lisprod = findAll_Product();
        for(wsprod.Product p:lisprod)
        {
            System.out.println(p.getId() + " " + p.getName() + " " + p.getDescription() + " " +
                    p.getPrice() + " " + p.getLastUpdate() + " Cat:" +
                    p.getCategoryId().getId() + " " + p.getCategoryId().getName());
            if(primero)
            {
              //cat   = p.getCategoryId(); // se obtiene la categoría
              cat.setId(p.getCategoryId().getId());
              cat.setName(p.getCategoryId().getName());
              fecha = p.getLastUpdate();
              primero = false;
            }
        }
        
        //Encontrar un producto con su id
        int id = 1;
        System.out.println("-------------------------- id = " + id + " --------------------------");
        System.out.println("El producto con id " + id + " es:");
        wsprod.Product p = find_Product(id);
        System.out.println(p.getId() + " " + p.getName() + " " + p.getDescription() + " " +
                    p.getPrice() + " " + p.getLastUpdate() + " Cat:" +
                    p.getCategoryId().getId() + " " + p.getCategoryId().getName());
        
        //Encontrar todos los productos que contengan ----- 
        System.out.println("------------------ findLikeName() ------------------------");
        java.util.List<wsprod.Product> producto = findByNameLike("seed");
        for(wsprod.Product prodLike:producto)
        {
            System.out.print("PRODUCTO: " + prodLike.getId()+ " " + prodLike.getName() + " " + prodLike.getDescription() + " "
                + prodLike.getPrice()+ " " +prodLike.getLastUpdate()+ " \n");
        }
        
        
        String strByName = "chocolate cookies";
        System.out.println("------------------ name = " + strByName + " ------------------------");
        java.util.List<wsprod.Product> prodsName = findByName(strByName);
        prodsName.forEach((pr) -> {
            System.out.println(pr.getId() + " " + pr.getName() + " " + pr.getDescription() + " " +
                    pr.getPrice() + " " + pr.getLastUpdate() + " Cat:" +
                    pr.getCategoryId().getId() + " " + pr.getCategoryId().getName());
        });
        
        //Crear un nuevo producto 
        wsprod.Product prod = new wsprod.Product();
        prod.setCategoryId(cat);
        prod.setId(0); //Auto entonces se pone el siguiente número 
        prod.setName("Nuevo");
        prod.setDescription("Descripcion del producto fase 3");
        prod.setPrice(new BigDecimal(55.0));
            //Fecha 
        XMLGregorianCalendar xmlFecha = null;
        try {
            xmlFecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(Tstwsprodpojo.class.getName()).log(Level.SEVERE, null, ex);
        }
        prod.setLastUpdate(  xmlFecha);
            //Se crea el producto con todas las variables que inicializamos arriba 
        //create_Product(prod);
     
        // --------------------------------- Customer --------------------------------  
        wscust.Customer cust = new wscust.Customer();
     
        cust.setCcNumber("ccNumber");
        cust.setCityRegion("MT");
        cust.setEmail("rgamboa@itam.mx");
        cust.setAddress("Rio Hondo 1");
        cust.setPhone("5556284061");
        cust.setName("Rafael Gregorio");
       
        long lngIdCustomer = create_customer(cust);
        System.out.println("==================================================");
        System.out.println("El nuevo cliente tiene clave:" + lngIdCustomer);
        System.out.println("==================================================");
        System.out.println("---------------- Customer.findAll() ----------------------------");
        java.util.List<wscust.Customer> liscust = findAll_Customers();
        for(wscust.Customer c:liscust)
        {
            System.out.println(c.getId() + " " + c.getName() + " " + c.getAddress() + " " +
                    c.getCcNumber() + " " + c.getCityRegion() + " " + c.getEmail() + " " + c.getPhone());
        }
        
        //Traemos a un cliente recien creado para asignarle un pedido
        //en este caso usaremos el cliente que acabamos de crear
        wscust.Customer clte = find_Customer(lngIdCustomer); //Busca el customer 
        
        //Tenemos que hacer todas las relaciones necesarias para poder agregar el pedido
        //Customer order se liga con customer por su id por lo que es necesario asignar todo para que sepa a que cliente corresponde. 
        wspedidoheader.Customer phclte = new wspedidoheader.Customer();
        phclte.setAddress(clte.getAddress());
        phclte.setCcNumber(clte.getCcNumber());
        phclte.setCityRegion(clte.getCityRegion());
        phclte.setEmail(clte.getEmail());
        phclte.setId(clte.getId());
        phclte.setName(clte.getName());
        phclte.setPhone(clte.getPhone());
        
        //OrderedProduct tiene una relacion con customer order y este con cliente
        //Primero hacemos el enlace con de custromer con customer order a traves del ordered product
        wspedidoitems.Customer itclte = new wspedidoitems.Customer (); 
        itclte.setAddress(clte.getAddress());
        itclte.setCcNumber(clte.getCcNumber());
        itclte.setCityRegion(clte.getCityRegion());
        itclte.setEmail(clte.getEmail());
        itclte.setId(clte.getId());
        itclte.setName(clte.getName());
        itclte.setPhone(clte.getPhone());
        
        // ahora hacemos la relacion del orderes product con el customer order. 
        wspedidoheader.CustomerOrder oh = new wspedidoheader.CustomerOrder(); 
        oh.setCustomerId(phclte); // se asigna el customer ordered que creamos al principio 
        oh.setAmount(new BigDecimal(100.75));
        oh.setConfirmationNumber(10);
        oh.setDateCreated(xmlFecha);
        
        //Tenemos que crear el pedido header que corresponde al customer ordered
      
    }

    private static int count_Product() {
        wsprod.WSProd_Service service = new wsprod.WSProd_Service();
        wsprod.WSProd port = service.getWSProdPort();
        return port.count();
    }

    private static java.util.List<wsprod.Product> findAll_Product() {
        wsprod.WSProd_Service service = new wsprod.WSProd_Service();
        wsprod.WSProd port = service.getWSProdPort();
        return port.findAll();
    }
    
    private static java.util.List<wscust.Customer> findAll_Customers() {
        wscust.WSCustomer_Service service = new wscust.WSCustomer_Service();
        wscust.WSCustomer port = service.getWSCustomerPort();
        return port.findAll();
    }

    private static java.util.List<wsprod.Product> findByName(java.lang.String name) {
        wsprod.WSProd_Service service = new wsprod.WSProd_Service();
        wsprod.WSProd port = service.getWSProdPort();
        return port.findByName(name);
    }

    
    private static void create_Product(wsprod.Product entity) {
        wsprod.WSProd_Service service = new wsprod.WSProd_Service();
        wsprod.WSProd port = service.getWSProdPort();
        port.create(entity);
    }
    
    private static long create_customer(wscust.Customer entity) {
        wscust.WSCustomer_Service service = new wscust.WSCustomer_Service();
        wscust.WSCustomer port = service.getWSCustomerPort();
        return port.create(entity);
    }

    private static Product find_Product(java.lang.Object id) {
        wsprod.WSProd_Service service = new wsprod.WSProd_Service();
        wsprod.WSProd port = service.getWSProdPort();
        return port.find(id);
    }
    
    private static Customer find_Customer(java.lang.Object id) {
        wscust.WSCustomer_Service service = new wscust.WSCustomer_Service();
        wscust.WSCustomer port = service.getWSCustomerPort();
        return port.find(id);
    }

    private static java.util.List<wsprod.Product> findByNameLike(java.lang.String name) {
        wsprod.WSProd_Service service = new wsprod.WSProd_Service();
        wsprod.WSProd port = service.getWSProdPort();
        return port.findByNameLike(name);
    }


    

    

    
}
