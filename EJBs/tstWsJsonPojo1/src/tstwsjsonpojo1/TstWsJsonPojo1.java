/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tstwsjsonpojo1;

import java.time.LocalDate;
import org.json.JSONObject;
import tstjson_00.ClsPersona;

/**
 *
 * @author tabat
 */
public class TstWsJsonPojo1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String strJsonPer = obtenPersona(1L); 
        System.out.println("strJsonPer: \n"+strJsonPer);
        
        ClsPersona op = new ClsPersona();
        JSONObject ojop = new JSONObject(strJsonPer); //Contruimos otro json con la cadena Json de la persona 
        //System.out.println("\n--------------------------\nojop");
        //System.out.println("ojop: "+ojop);
        op.setApMaterno(ojop.getString("apPaterno"));
        op.setApPaterno(ojop.getString("apMaterno"));
        op.setNombre(ojop.getString("nombre"));
        LocalDate ld = LocalDate.parse(ojop.getString("fechaNac"));
        op.setFechaNac(ld);
        System.out.println("op: \n" + op);
    }

    private static String obtenPersona(long idPersona) {
        wsjsonPersonaClte.WsJsonPersona_Service service = new wsjsonPersonaClte.WsJsonPersona_Service();
        wsjsonPersonaClte.WsJsonPersona port = service.getWsJsonPersonaPort();
        return port.obtenPersona(idPersona);
    }
    
}
