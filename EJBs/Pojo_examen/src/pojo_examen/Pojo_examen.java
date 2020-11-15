/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo_examen;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import wscat.Category;
import wspedidoitems.OrderedProduct;
import wspedidoitems.OrderedProductPK;

/**
 *
 * @author tabat
 */
public class Pojo_examen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
       /*
        ACOPLADORES DE PAQUETES DE LOS CLIENTES DE Web Service a los BEANS de Sesión:
        wscat       CategoryFacade
        wscust      CustomerFacade
        wscustord   CustomerOrderFacade
        wsordprod   OrderedProductFacade
        wsprod      ProductFacade
        
        Las utilerías (Ver después del código del main del pojo) tienen implementadas
        las funcionalidades (XYZ es cada bean de entidad)
            count_XYZ
            create_XYZ      NOTA: el create regresa int
            findAll_XYZ
            find_XYZ
       */
        
       //
       // Nota: en lo que sigue, CU es su Clave Única
       //
       String strCU = "155999";
       // Primer Nombre de Pila, primer apellido, segundo apellido (3 cadenas)
       String strNombre[] = {"Tábata","González","Alvarado"};
       double dblCU = Double.parseDouble(strCU);
       
       XMLGregorianCalendar xmlFecha = null;
       try 
       {
          xmlFecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
       } 
       catch (DatatypeConfigurationException ex) 
       {
           System.out.println("Error al crear xmlFecha:" + ex);
           System.exit(1);
       }

       System.out.println("SCE: Segundo examen parcial de " + strCU + " " +
                           strNombre[0] + " " + strNombre[1] + " " + strNombre[2]);
       System.out.println("----------------------------------------------------");
       System.out.println("          " + xmlFecha.toString());
       System.out.println("----------------------------------------------------");
    
       //
       // Ejer 1) Crear una nueva categoría de nombre Limpieza CU
       //
       
//inicia cometario 1)
       
       int cat_id; 
       wscat.Category catnew = new wscat.Category();
       catnew.setName("Limpieza " + strCU);
      cat_id = (int) create_Category(catnew);
       catnew.setId(cat_id);
       System.out.println("Se ha dado de alta la categoría " + catnew.getId() + " ... " + catnew.getName());
       List<wscat.Category> liscat = findAll_Category();
       System.out.println("Lista de Categorías:" + ( liscat.isEmpty() ? " Lista sin elementos":"" ));
       for(wscat.Category cat:liscat)
            System.out.println("Categoría:" + cat.getId() + "->" + cat.getName());
    
//fin comentario 1) 
       
       // 
       // Ejer 2) Crear tres productos con tal categoría
       //         con precio unitario cada uno de los dígitos no cero de su clave única,
       //         empezando por la derecha
       //         con nombres 
       //                   Prod_PRIMER_NOMBRE_DE_PILA
       //                   Prod_PRIMER_APELLIDO
       //                   Prod_SEGUNDO_APELLIDO
       //         y descripciones
       //                   descripcion PRIMER_NOMBRE_DE_PILA
       //                   descripcion PRIMER_APELLIDO
       //                   descripcion SEGUNDO_APELLIDO
       
// inicia comentario 2)
       
             
       // precios unitarios de los productos
       int NUMPRODS = 3;
       List<wsprod.Product> listaProdsNuevos;
        listaProdsNuevos = new ArrayList<>();
      
       double dblPrecioUnitario;
       double dblcu = dblCU;
       String nombreProdNuevos[] = strNombre;
       
       wsprod.Product prd;
       wsprod.Category catProd = new wsprod.Category();
       catProd.setId(catnew.getId());
       catProd.setName(catnew.getName());
        
       int i = 0; 
       while(i < NUMPRODS) 
       { 
           dblPrecioUnitario = 0.0;
           while(dblPrecioUnitario == 0.0 )
           {
               dblPrecioUnitario = dblcu - 10.0 * Math.floor(dblcu/10.0);
               dblcu = Math.floor(dblcu/10.0);
           }
           
           prd = new wsprod.Product();
           prd.setCategoryId(catProd);
           prd.setDescription("descripcion " + nombreProdNuevos[i]);
           prd.setLastUpdate(xmlFecha);
           prd.setName("Prod_" + nombreProdNuevos[i]);
           prd.setPrice(new BigDecimal(dblPrecioUnitario));
           listaProdsNuevos.add(prd);
           i++;
       }
       // Dando de alta los productos
       int idProd;
       for(wsprod.Product pr:listaProdsNuevos)
       {
           idProd = (int)create_Product(pr);
           pr.setId(idProd);
       }
       // revisando la lista de productos
       List<wsprod.Product> lisprod = findAll_Product();
       System.out.println("Lista de Productos:" + ( lisprod.isEmpty() ? " Lista sin elementos":"" ));
       for(wsprod.Product p:lisprod)
            System.out.println("Producto:" + p.getId() + " " + p.getName() + " " + p.getDescription() +
                    " cat:" + p.getCategoryId().getId() + " " + p.getCategoryId().getName());
       
//fin comentario 2)
       
       //
       // Ejer 3) Crear un nuevo cliente con su nombre y apellidos
       //
       
//inicia comentario 3)
       
       wscust.Customer custnew = new wscust.Customer();
       custnew.setAddress("Rio Hondo 1");
       custnew.setCcNumber(strCU);
       custnew.setCityRegion("MX");
       custnew.setEmail("tabata.gonzalez@itam.mx"); // poner su correo del itam
       custnew.setName(strNombre[0] + " " + strNombre[1] + " " + strNombre[2]);
       custnew.setPhone("5556284000"); // conmutador del itam, no cambiar
       
       int idCustNew = (int) create_Customer(custnew);
       custnew.setId(idCustNew);
       System.out.println("Se ha dado de alta el clte:" + custnew.getId() + " " +
                            custnew.getName() + " " + custnew.getAddress() + " " +
                            custnew.getCcNumber() + " " + custnew.getCityRegion() + " " +
                            custnew.getPhone() + " " + custnew.getEmail());
        
       // obteniendo el listado de los clientes:
       List<wscust.Customer> liscust = findAll_Customer();
       System.out.println("Lista de Clientes:" + ( liscust.isEmpty() ? " Lista sin elementos":"" ));
       for( wscust.Customer cust: liscust )
            System.out.println("Clte:" + cust.getId() + " " +
                            cust.getName() + " " + cust.getAddress() + " " +
                            cust.getCcNumber() + " " + cust.getCityRegion() + " " +
                            cust.getPhone() + " " + cust.getEmail());
       
//fin comentario 3) 
       
       // Ejer 4) Crear dos pedidos con los tres productos dados de alta de
       //         la nueva categoría, con 5 unidades de cada uno, 
       //         el segundo con 10 unidades. 
       //
       

       
       // convirtiendo el Cliente custnew a wscustord
       wspedidoheader.Customer custco = new wspedidoheader.Customer();
       custco.setId(custnew.getId());
       custco.setAddress(custnew.getAddress());
       custco.setCcNumber(custnew.getCcNumber());
       custco.setCityRegion(custnew.getCityRegion());
       custco.setEmail(custnew.getEmail());
       custco.setName(custnew.getName());
       custco.setPhone(custnew.getPhone());
       
       // cambiando la categoría catnew a wsordprod
       
       wspedidoitems.Category catParaItems = new wspedidoitems.Category();
       catParaItems.setId(catnew.getId());
       catParaItems.setName(catnew.getName());
       
       // convirtiendo los productos a wsprodord
       List<wspedidoitems.Product> lisProdParaItems = new ArrayList<>();
       wspedidoitems.Product prodParaItems;
       for(wsprod.Product pr:listaProdsNuevos)
       {
          prodParaItems = new wspedidoitems.Product();
          prodParaItems.setCategoryId(catParaItems);
          prodParaItems.setDescription(pr.getDescription());
          prodParaItems.setId(pr.getId());
          prodParaItems.setLastUpdate(pr.getLastUpdate());
          prodParaItems.setName(pr.getName());
          prodParaItems.setPrice(pr.getPrice());
          lisProdParaItems.add(prodParaItems);
       }
       
       //
       //   4.1) Definir los items en una lista y obtener el total
       //
       //  NOTA: SE IDENTIFICAN CON 0 y 1 (para que coincidan con los índices 
       //        de los arreglos y posiciones en las listas) 
       //
         

       

       
       List<List<wspedidoitems.OrderedProduct>> lisLisItems = new ArrayList<>();
       // listas de items para ambos pedidos
       List<wspedidoitems.OrderedProduct> lisItemsCO0 = new ArrayList<>();
       List<wspedidoitems.OrderedProduct> lisItemsCO1 = new ArrayList<>();
       double sm0 = 0.0, sm1 = 0.0;
       wspedidoitems.OrderedProduct op0,op1;
       for( wspedidoitems.Product prop: lisProdParaItems)
       {
           op0 = new wspedidoitems.OrderedProduct();
           op0.setProduct(prop);
           op0.setQuantity((short)5.0);
           sm0 += op0.getQuantity() * prop.getPrice().doubleValue();
           lisItemsCO0.add(op0);
           
           op1 = new wspedidoitems.OrderedProduct();
           op1.setProduct(prop);
           op1.setQuantity((short)10.0);
           sm1 += op1.getQuantity() * prop.getPrice().doubleValue();
           lisItemsCO1.add(op1);
       }
       lisLisItems.add(lisItemsCO0);
       lisLisItems.add(lisItemsCO1);
       //
       //   4.2) Dar de alta los encabezados (CustomerOrder)
       //
       

       // primer encabezado
       wspedidoheader.CustomerOrder co0 = new wspedidoheader.CustomerOrder();
       co0.setAmount(new BigDecimal(sm0));
       co0.setConfirmationNumber(1);
       co0.setCustomerId(custco);
       co0.setDateCreated(xmlFecha);
       int intNumPedido0 = (int)create_CustomerOrder(co0); // alta del primer encabezado
       co0.setId(intNumPedido0);
       
       // segundo encabezado
       wspedidoheader.CustomerOrder co1 = new wspedidoheader.CustomerOrder();
       co1.setAmount(new BigDecimal(sm1));
       co1.setConfirmationNumber(2);
       co1.setCustomerId(custco);
       co1.setDateCreated(xmlFecha);
       int intNumPedido1 = (int) create_CustomerOrder(co1); // alta del segundo encabezado
       co1.setId(intNumPedido1);
       
       // y el custco a wsordprod.Customer
       wspedidoitems.Customer custcoop = new wspedidoitems.Customer();
       custcoop.setAddress(custco.getAddress());
       custcoop.setCcNumber(custco.getCcNumber());
       custcoop.setCityRegion(custco.getCityRegion());
       custcoop.setEmail(custco.getEmail());
       custcoop.setId(custco.getId());
       custcoop.setName(custco.getName());
       custcoop.setPhone(custco.getPhone());
       
       // convierto los co0 y co1 a wsordprod       
       
       wspedidoitems.CustomerOrder arr_coop[] = new wspedidoitems.CustomerOrder[2];
       arr_coop[0] = new wspedidoitems.CustomerOrder();
       arr_coop[1] = new wspedidoitems.CustomerOrder();
       
       arr_coop[0].setAmount(co0.getAmount());
       arr_coop[0].setConfirmationNumber(co0.getConfirmationNumber());
       arr_coop[0].setCustomerId(custcoop);
       arr_coop[0].setDateCreated(co0.getDateCreated());
       arr_coop[0].setId(co0.getId());
       
       arr_coop[1].setAmount(co1.getAmount());
       arr_coop[1].setConfirmationNumber(co1.getConfirmationNumber());
       arr_coop[1].setCustomerId(custcoop);
       arr_coop[1].setDateCreated(co1.getDateCreated());
       arr_coop[1].setId(co1.getId());
       

       // 
       //   4.3) Dar de alta los items de cada pedido 
       //
       
       // antes se actualiza el customerorder id de cada item
       List<wspedidoitems.OrderedProduct> lisop;
       wspedidoitems.OrderedProductPK oppkv; 
       for( i = 0; i<=1;i++)
       {
           lisop = lisLisItems.get(i);
           for(wspedidoitems.OrderedProduct item:lisop)
           {
               item.setCustomerOrder(arr_coop[i]);
               oppkv = new wspedidoitems.OrderedProductPK();
               oppkv.setCustomerOrderId(arr_coop[i].getId());
               oppkv.setProductId(item.getProduct().getId());
               item.setOrderedProductPK(oppkv);
               create_OrderedProduct(item);
           }  
       }
       // Obtener la lista de los encabezados (CustomerOrder)
       List<wspedidoheader.CustomerOrder> lisco = findAll_CustomerOrder();
       System.out.println("Lista de Encabezados de Pedidos:" + ( lisco.isEmpty() ? " Lista sin elementos":"" ));
       for(wspedidoheader.CustomerOrder co : lisco)
            System.out.println("CustomerOrder:" +
                                " Id:" + co.getId() + 
                                " Fecha:"     + co.getDateCreated() +
                                " Clte:"     + co.getCustomerId().getId() +
                                " "          + co.getCustomerId().getName() +
                                " Num. conf:" + co.getConfirmationNumber() +
                                " Monto:"    + co.getAmount());
       
       
       
       // Obtener la lista de los items (OrderedProducts)
       List<wspedidoitems.OrderedProduct> lisitems = findAll_OrderedProduct();
       System.out.println("Lista de Items de Pedidos:" + ( lisitems.isEmpty() ? " Lista sin elementos":"" ));
       //int numPedido;
       //wsordprod.CustomerOrder itmco;
       for(wspedidoitems.OrderedProduct itm : lisitems)
       {
            //itmco = itm.getCustomerOrder();
            //numPedido = itmco.getId().intValue();
            System.out.println("CustomerOrder:" +
                                " No.Pedido:"   + itm.getCustomerOrder().getId().intValue() + 
                                " Producto:"    + itm.getProduct().getId() +
                                " "             + itm.getProduct().getName() +
                               " Cantidad:"     + itm.getQuantity());
       }
       


       // 
       // Ejer 5)  Obtener cada encabezado con sus items.
       //
       
      
       //
       // Ejer 6) Obtener el monto total de los pedidos (Amount de CustomerOrder)
       //
       
     long total = 0;
       for (wspedidoitems.OrderedProduct itm2:lisitems){
           total = itm2.getCustomerOrder().getAmount().longValue() + total; 
       }
        System.out.println("\n EL Monto total de  los pedidos es: " + total);
        System.out.println("El monto con query es: " );
       
       //
       // ======================  FIN del main() ===========================
       //     
    }
    //-----------------------------------------------------------------------
    // -------------------------- UTILERIAS ---------------------------------
    //-----------------------------------------------------------------------
    // =======================================================================
    //   WSCategory
    // =======================================================================

    
    // =======================================================================
    //   WSProduct
    // =======================================================================
/*
    private static int count_Product() {
        wsprod.WSProduct_Service service = new wsprod.WSProduct_Service();
        wsprod.WSProduct port = service.getWSProductPort();
        return port.count();
    }

    private static int create_Product(wsprod.Product entity) {
        wsprod.WSProduct_Service service = new wsprod.WSProduct_Service();
        wsprod.WSProduct port = service.getWSProductPort();
        return port.create(entity);
    }
    private static Product find_Product(int id) {
        wsprod.WSProduct_Service service = new wsprod.WSProduct_Service();
        wsprod.WSProduct port = service.getWSProductPort();
        return port.find(id);
    }
    private static java.util.List<wsprod.Product> findAll_Product() {
        wsprod.WSProduct_Service service = new wsprod.WSProduct_Service();
        wsprod.WSProduct port = service.getWSProductPort();
        return port.findAll();
    }
*/
    // =======================================================================
    //   WSCustomer
    // =======================================================================
/*
    private static int count_Customer() {
        wscust.WSCustomer_Service service = new wscust.WSCustomer_Service();
        wscust.WSCustomer port = service.getWSCustomerPort();
        return port.count();
    }
    private static int create_Customer(wscust.Customer entity) {
        wscust.WSCustomer_Service service = new wscust.WSCustomer_Service();
        wscust.WSCustomer port = service.getWSCustomerPort();
        return port.create(entity);
    }
    private static Customer find_Customer(int id) {
        wscust.WSCustomer_Service service = new wscust.WSCustomer_Service();
        wscust.WSCustomer port = service.getWSCustomerPort();
        return port.find(id);
    }

    private static java.util.List<wscust.Customer> findAll_Customer() {
        wscust.WSCustomer_Service service = new wscust.WSCustomer_Service();
        wscust.WSCustomer port = service.getWSCustomerPort();
        return port.findAll();
    }
*/
    // =======================================================================
    //   WSCustomerOrder
    // =======================================================================
/*
    private static int count_CustomerOrder() {
        wscustord.WSCustomerOrder_Service service = new wscustord.WSCustomerOrder_Service();
        wscustord.WSCustomerOrder port = service.getWSCustomerOrderPort();
        return port.count();
    }
    private static int create_CustomerOrder(wscustord.CustomerOrder entity) {
        wscustord.WSCustomerOrder_Service service = new wscustord.WSCustomerOrder_Service();
        wscustord.WSCustomerOrder port = service.getWSCustomerOrderPort();
        return port.create(entity);
    }
    private static CustomerOrder find_CustomerOrder(int id) {
        wscustord.WSCustomerOrder_Service service = new wscustord.WSCustomerOrder_Service();
        wscustord.WSCustomerOrder port = service.getWSCustomerOrderPort();
        return port.find(id);
    }
    private static java.util.List<wscustord.CustomerOrder> findAll_CustomerOrder() {
        wscustord.WSCustomerOrder_Service service = new wscustord.WSCustomerOrder_Service();
        wscustord.WSCustomerOrder port = service.getWSCustomerOrderPort();
        return port.findAll();
    }
*/
    // =======================================================================
    //   WSOrderedProduct
    // =======================================================================
/*
    private static int count_OrderedProduct() {
        wsordprod.WSOrderedProduct_Service service = new wsordprod.WSOrderedProduct_Service();
        wsordprod.WSOrderedProduct port = service.getWSOrderedProductPort();
        return port.count();
    }
    private static OrderedProductPK create_OrderedProduct(wsordprod.OrderedProduct entity) {
        wsordprod.WSOrderedProduct_Service service = new wsordprod.WSOrderedProduct_Service();
        wsordprod.WSOrderedProduct port = service.getWSOrderedProductPort();
        return port.create(entity);
    }
    private static OrderedProduct find_OrderedProduct(int custord_id, int prod_id) {
        wsordprod.WSOrderedProduct_Service service = new wsordprod.WSOrderedProduct_Service();
        wsordprod.WSOrderedProduct port = service.getWSOrderedProductPort();
        return port.find(custord_id,prod_id);
    }
    private static java.util.List<wsordprod.OrderedProduct> findAll_OrderedProduct() {
        wsordprod.WSOrderedProduct_Service service = new wsordprod.WSOrderedProduct_Service();
        wsordprod.WSOrderedProduct port = service.getWSOrderedProductPort();
        return port.findAll();
    }
    */

    private static long create_Category(wscat.Category entity) {
        wscat.WSCat_Service service = new wscat.WSCat_Service();
        wscat.WSCat port = service.getWSCatPort();
        return port.create(entity);
    }

    private static Category find_Category(java.lang.Object id) {
        wscat.WSCat_Service service = new wscat.WSCat_Service();
        wscat.WSCat port = service.getWSCatPort();
        return port.find(id);
    }

    private static java.util.List<wscat.Category> findAll_Category() {
        wscat.WSCat_Service service = new wscat.WSCat_Service();
        wscat.WSCat port = service.getWSCatPort();
        return port.findAll();
    }

    private static long create_Product(wsprod.Product entity) {
        wsprod.WSProd_Service service = new wsprod.WSProd_Service();
        wsprod.WSProd port = service.getWSProdPort();
        return port.create(entity);
    }

    private static java.util.List<wsprod.Product> findAll_Product() {
        wsprod.WSProd_Service service = new wsprod.WSProd_Service();
        wsprod.WSProd port = service.getWSProdPort();
        return port.findAll();
    }

    private static long create_Customer(wscust.Customer entity) {
        wscust.WSCustomer_Service service = new wscust.WSCustomer_Service();
        wscust.WSCustomer port = service.getWSCustomerPort();
        return port.create(entity);
    }

    private static java.util.List<wscust.Customer> findAll_Customer() {
        wscust.WSCustomer_Service service = new wscust.WSCustomer_Service();
        wscust.WSCustomer port = service.getWSCustomerPort();
        return port.findAll();
    }

    private static long create_CustomerOrder(wspedidoheader.CustomerOrder entity) {
        wspedidoheader.WsPedidoHeader_Service service = new wspedidoheader.WsPedidoHeader_Service();
        wspedidoheader.WsPedidoHeader port = service.getWsPedidoHeaderPort();
        return port.create(entity);
    }

    private static java.util.List<wspedidoheader.CustomerOrder> findAll_CustomerOrder() {
        wspedidoheader.WsPedidoHeader_Service service = new wspedidoheader.WsPedidoHeader_Service();
        wspedidoheader.WsPedidoHeader port = service.getWsPedidoHeaderPort();
        return port.findAll();
    }

    private static java.util.List<wspedidoitems.OrderedProduct> findAll_OrderedProduct() {
        wspedidoitems.WsPedidoItems_Service service = new wspedidoitems.WsPedidoItems_Service();
        wspedidoitems.WsPedidoItems port = service.getWsPedidoItemsPort();
        return port.findAll();
    }

   /* private static void create_OrderedProduct(OrderedProduct item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    private static OrderedProductPK create_OrderedProduct(wspedidoitems.OrderedProduct entity) {
        wspedidoitems.WsPedidoItems_Service service = new wspedidoitems.WsPedidoItems_Service();
        wspedidoitems.WsPedidoItems port = service.getWsPedidoItemsPort();
        return port.createPK(entity);
    }

    
    
    
}