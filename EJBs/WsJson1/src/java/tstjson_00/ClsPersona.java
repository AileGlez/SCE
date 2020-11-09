/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tstjson_00;

import java.time.LocalDate;
import java.time.Month;
import org.json.JSONObject;

/**
 *
 * @author aalex
 */
public class ClsPersona {
    private String apMaterno;
    private String apPaterno;
    private String nombre;
    private LocalDate localDate;
    
    public ClsPersona(){
        
    }
    
    public ClsPersona(String nombre, String apPaterno, String apMaterno, LocalDate localDate){
     this.nombre=nombre;
     this.apMaterno=apMaterno;
     this.apPaterno=apPaterno;
     this.localDate=localDate;
    }
    
    public String getApMaterno() {
        return apMaterno;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public LocalDate getFechaNac() {
        return localDate;
    }

    public String getNombre(){
        return nombre;
    }
    
    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    
    public void setFechaNac(LocalDate fechaNac) {
        this.localDate = fechaNac;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getName());
        sb.append("\n Nombre: ");
        sb.append(this.getNombre());
        sb.append("\n Ap. Paterno: ");
        sb.append(this.getApPaterno());
        sb.append("\n Ap. Materno: ");
        sb.append(this.getApMaterno());
        sb.append("\n Fecha Nacimiento: ");
        sb.append(this.getFechaNac());

        return sb.toString();
    }
    public static void main(String[] args) {
        ClsPersona p= new ClsPersona("Tábata","González","Alvarado",LocalDate.of(1996, Month.DECEMBER, 27));
        System.out.println("----------------------------\nper: " + p);
        
        JSONObject jsonObject = new JSONObject(p);
        System.out.println("\n----------------------------\nJSONPer: ");
        String strJsonPer= jsonObject.toString(); //cadena JSON 
        System.out.println("strJsonPer: "+strJsonPer);
        
        ClsPersona op = new ClsPersona();
        JSONObject ojop = new JSONObject(strJsonPer); //Contruimos otro json con la cadena Json de la persona 
        //System.out.println("\n--------------------------\nojop");
        //System.out.println("ojop: "+ojop);
        op.setApMaterno(ojop.getString("apPaterno"));
        op.setApPaterno(ojop.getString("apMaterno"));
        op.setNombre(ojop.getString("nombre"));
        LocalDate ld = LocalDate.parse(ojop.getString("fechaNac"));
        op.setFechaNac(ld);
        
    }
}
