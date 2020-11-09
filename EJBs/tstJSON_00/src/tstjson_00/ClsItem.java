/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tstjson_00;

/**
 *
 * @author tabat
 */
public class ClsItem {
    int idProd;
       double unidades;
       double precioUnitario;
       double montoItem;
       
       public ClsItem(){}
       
       public ClsItem(int idProd, double unidades, double pu, double monto)
       {
           this.idProd         = idProd;
           this.unidades       = unidades;
           this.precioUnitario = pu;
           this.montoItem      = monto;
       }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public double getUnidades() {
        return unidades;
    }

    public void setUnidades(double unidades) {
        this.unidades = unidades;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getMontoItem() {
        return montoItem;
    }

    public void setMontoItem(double montoItem) {
        this.montoItem = montoItem;
    }
       
       @Override
    public String toString()
    {
        return this.getClass().getName() + ":"     +
                "\n         idProd:" + this.getIdProd()   + 
                "\n       unidades:" + this.getUnidades() +
                "\nprecio Unitario:" + this.getPrecioUnitario() +
                "\n          monto:" + this.getMontoItem() + '\n';
    }
}
