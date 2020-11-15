/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tstjson_02;

import java.util.GregorianCalendar;
import org.json.JSONUtil;
import org.json.JsonObject;

/**
 *
 * @author tabat
 */
public class ClsPersona 
{
    //Bean ClsPersona
    String nombre = null; 
    String apPat = null; 
    String apMat = null; 
    GregorianCalendar fechaNac = new GregorianCalendar (); 

    public ClsPersona() {
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPat() {
        return apPat;
    }

    public void setApPat(String apPat) {
        this.apPat = apPat;
    }

    public String getApMat() {
        return apMat;
    }

    public void setApMat(String apMat) {
        this.apMat = apMat;
    }

    public GregorianCalendar getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(GregorianCalendar fechaNac) {
        this.fechaNac = fechaNac;
    }

   
    public String toStrong() {
        return this.getClass().getName() + ": " + "nombre=" + this.nombre + ", apPat=" + this.apPat 
                + ", apMat=" + this.apMat + ", fechaNac=" + this.fechaNac;
    }
    
    public static void main(String args[])
    {
        ClsPersona persona = new ClsPersona(); 
        persona.setNombre("Tabata");
        persona.setApPat("Gonzalez");
        persona.setApMat("Alvarado");
        //impresion con sout 
        System.out.println(persona);
        //Impresion con json
        JsonObject json = new JsonObject(); 
        //String jsonStr = JSONUtil.toJSON(persona);
        //System.out.println(jsonStr);
        json.add("nombre", persona.getNombre());
        json.add("apPat", persona.getApPat());
        json.add("apMat", persona.getApMat());
        json.add("fechaNac", persona.getFechaNac());
        String cadJson = json.toJSON(); 
        System.out.println(json.toString());
        
    }
   
}
