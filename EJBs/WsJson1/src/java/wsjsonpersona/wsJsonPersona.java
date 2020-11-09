/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsjsonpersona;

import java.time.LocalDate;
import java.time.Month;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.json.JSONObject;
import tstjson_00.ClsPersona;

/**
 *
 * @author tabat
 */
@WebService(serviceName = "wsJsonPersona")
public class wsJsonPersona {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "obtenPersona")
    public String obtenPersona(@WebParam(name = "idPersona") long idPersona) {
        //Construimos a la persona
        tstjson_00.ClsPersona per = new ClsPersona("Tábata","González","Alvarado",LocalDate.of(1996, Month.DECEMBER, 27));
        JSONObject jsonObject = new JSONObject(per); 
        return jsonObject.toString();
    }
}
