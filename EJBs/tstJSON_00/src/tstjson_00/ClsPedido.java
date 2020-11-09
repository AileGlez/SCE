/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tstjson_00;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/**
 *
 * @author tabat
 */
public class ClsPedido {
    long      idClte;
    LocalDate fechaPedido;
    ClsLItems items       = new ClsLItems();
    int       numItems    = 0;
    double    montoPedido = 0.0;
    
    public ClsPedido(){}
    
    public ClsPedido( long idClte, LocalDate fecha)
    {
        this.idClte = idClte;
        this.fechaPedido = fecha;
    }

    public long getIdClte() {
        return idClte;
    }

    public void setIdClte(long idClte) {
        this.idClte = idClte;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Map<String,Object> getItems() {
        return items.colItems;
    }

    public void setItems(ClsLItems items) {
        this.items = items;
    }
    
    public double getMontoPedido()
    {
        return this.montoPedido;
    }
    
    public int getNumItems()
    {
        return this.numItems;
    }
    
    public double agregaItem(int idProd,double cant, double pu)
    {
        double monto = cant * pu;
        this.numItems++;
        this.montoPedido += monto;
        items.agregaItem(new ClsItem(idProd,cant,pu,monto));
        return monto;
    }
    
    @Override
    public String toString()
    {
        return this.getClass().getName() + ":" +
               "\n           idClte:" + this.getIdClte() +
                "\n           fecha:" + this.getFechaPedido() +
                "\n numero de items:" + this.getNumItems() +
                "\n    Monto Pedido:" + this.getMontoPedido() +
                "\n           items:" + this.items;
    }
    
    public static void main(String args[])
    {
        ClsPedido pedido = new ClsPedido( 1,LocalDate.now());
        pedido.agregaItem(1,  10.0,   5.0);
        pedido.agregaItem(2,  20.0,   8.0);
        pedido.agregaItem(10, 40.5, 100.0);
        
        System.out.println("pedido:\n" + pedido);
        
        JSONObject json = new JSONObject(pedido);
        
        String strJson = json.toString();
        System.out.println("\nstrJson:\n" + strJson);
        
       // System.out.println("\npedido.getItems().toString():\n" + pedido.getItems().toString());
       
        JSONObject jsobjItems = new JSONObject(pedido.getItems());
        System.out.println("jsonobjItems:\n" + jsobjItems.toString());
        
        JSONObject ojs = new JSONObject(strJson);
        ClsPedido op   = new ClsPedido();
        op.setIdClte(ojs.getInt("idClte"));
        String strFechaPedido = (String) ojs.get("fechaPedido");        
        java.time.LocalDate fechaPedido = java.time.LocalDate.parse(strFechaPedido);
        op.setFechaPedido(fechaPedido);
        System.out.println("op:\n" + op);
        System.out.println("---------------------");
        
        JSONObject jsonItems = (JSONObject) ojs.get("items");
        int n = jsonItems.length();
        Set<String> k = jsonItems.keySet();
        
        JSONObject jsonItem;
        int idProd;
        double unidades;
        double pu;
        double monto;
        
        for(String s:k)
        {
            System.out.println("s:"+ s);
            jsonItem = jsonItems.getJSONObject(s);
            System.out.println("jsonItem:" + jsonItem);
            // regenerar el ClsItem para guardarlo en el pedido
            idProd   = jsonItem.getInt("idProd");
            pu       = jsonItem.getDouble("precioUnitario");
            unidades = jsonItem.getDouble("unidades");
            monto    = jsonItem.getDouble("montoItem");
            op.agregaItem(idProd,unidades,pu);
        }
        System.out.println("-------------------------------------------------");
        System.out.println("perdido reconstruido:\n" + op);        
        System.out.println("-------------------------------------------------");
    }
}
