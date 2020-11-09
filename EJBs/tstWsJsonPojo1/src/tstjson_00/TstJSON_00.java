/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tstjson_00;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import org.json.JSONObject;

/**
 *
 * @author aalex
 */
public class TstJSON_00 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Integer x = 5;
        int[] a = {1,1,2,3,5,8,13};
        Map<String,Object> myData = new LinkedHashMap<>();
        myData.put("x", x);
        myData.put("a", a);
        //myData.put("bundle", ResourceBundle.getBundle("some.bundle.name", locale));
        myData.put("status", "success");
        String jsonStr = new JSONObject(myData).toString();    
        System.out.println(jsonStr.toString());
    }

}
