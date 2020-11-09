/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tstjson_00;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author tabat
 */
public class ClsLItems {
    Map<String,Object> colItems = new LinkedHashMap<>();
    
    public ClsLItems(){}
    
    public void agregaItem(ClsItem item)
    {
        colItems.put("" + item.getIdProd(),item);
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getName());
        sb.append(":\n");
        sb.append(colItems.toString());
        return sb.toString();
    }
    
    public static void main(String args[])
    {
        ClsLItems items = new ClsLItems();
        
        items.agregaItem(new ClsItem(1,  10.0,   5.0, 10.0 * 5));
        items.agregaItem(new ClsItem(2,  20.0,   8.0, 20.0 * 8.0));
        items.agregaItem(new ClsItem(10, 40.5, 100.0, 40.5 * 100));

        System.out.println("items:\n" + items + "\n -----------------------");
        
        org.json.JSONObject jsoitems = new org.json.JSONObject( items.colItems);
        
        System.out.println("jsoitems:\n" + jsoitems.toString());
        
    }
}
