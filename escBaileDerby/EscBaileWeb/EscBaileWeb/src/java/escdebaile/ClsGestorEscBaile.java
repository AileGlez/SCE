package escdebaile;

/*
 * ClsGestorEscBaile.java
 */

/**
 *
 * @author  RGGH
 */
public class ClsGestorEscBaile
{
    ClsConexion conexion;
// ---------------------------------------------------------------------------    
    public ClsGestorEscBaile ()
    {
       //Conexión a access
       //String strBD = "C:\\Users\\aalex\\Documents\\NetBeansProjects\\SCE\\todoEscBaile\\EscBaileWeb\\EscBaileWeb\\EscDeBaile";
       //Conexión a Derby
       String strBD="jdbc:derby://localhost:1527/EscDeBaile";             
       conexion = new ClsConexion(strBD);
    }
// ---------------------------------------------------------------------------        
    public boolean conectaBD(String strUsuario, String strContrasenha )
    {
        return conexion.conectate (strUsuario, strContrasenha );
    }
// ---------------------------------------------------------------------------        
    public boolean conectado() 
    {
        return conexion.conectado();
    } 
// --------------------------------------------------------------------------- 
   public void desconecta()
   {
     conexion.cierraCon();  
   }
// --------------------------------------------------------------------------- 
    public java.sql.ResultSet obtenAlumnos()
    {
      return conexion.obtenRS ("tblAlumnos");
    }
// ---------------------------------------------------------------------------
   public MiModelo obtenModeloAlumno( String clvAlumno )
   {
      java.sql.ResultSet rs = conexion.obtenRegSelect("Select * from tblAlumnos where clvAlumno='" + clvAlumno + "'"); 
      MiModelo elModelo = new MiModelo(rs);
      return elModelo;
   }
// ---------------------------------------------------------------------------
   public MiModelo obtenModeloAlumnos()
   {
      java.sql.ResultSet rs = conexion.obtenRegSelect("Select * from tblAlumnos"); 
      MiModelo elModelo = new MiModelo(rs);
      return elModelo;
   }
// ---------------------------------------------------------------------------
                   
// ---------------------------------------------------------------------------
    public java.sql.ResultSet obtenProfesores()
    {
        return conexion.obtenRS ("tblProfesores");
    }
// ---------------------------------------------------------------------------    
    public java.sql.ResultSet obtenBailes()
    {
        return conexion.obtenRS ("tblBailes");
    }
// ---------------------------------------------------------------------------    
    public java.sql.ResultSet obtenNiveles()
    {
        return conexion.obtenRS ("tblNiveles");
    }
// ---------------------------------------------------------------------------    
    public java.sql.ResultSet obtenGrupos()
    {
        return conexion.obtenRS ("tblGrupos");
    }
// ---------------------------------------------------------------------------    
    public java.sql.ResultSet obtenAlumnosPorGrupo(String numGpo )
    { 
       return null;
    }
// ---------------------------------------------------------------------------
//                           Obtener un solo campo
// ---------------------------------------------------------------------------
   public java.sql.ResultSet obtenCampoParaLista(String strTV, String strCampo )
   {
     String strSelect = "Select " + strCampo + " From " + strTV; 
     return conexion.obtenRegSelect(strSelect);  
   }
// ---------------------------------------------------------------------------    
//                            Altas de registros 
// ---------------------------------------------------------------------------
    public boolean altaAlumno(String arr_nomCampos[], String arr_datos[])
    {
      int i,n;
              
      // se obtiene la colección de campos de la tabla de los alumnos
      java.util.TreeMap<String,ClsCampoBD> colCampos = conexion.obtenMapaCampos(conexion.obtenRS("tblAlumnos"));
      
      n = arr_nomCampos.length;
      
      // se rellena el valor para cada campo
      for( i = 0; i < n; i++ )
       ((ClsCampoBD) colCampos.get(arr_nomCampos[i])).valor = arr_datos[i];
      
      // se solicita al objeto conexión que inserte el registro y se espera el resultado
      return conexion.insertaReg("tblAlumnos", colCampos);
    }
// ---------------------------------------------------------------------------
// ---------------------------------------------------------------------------
    public boolean bajaAlumno(String arr_nomCampos[], String arr_datos[],int tipo)
    {
      int i,n;
              
      // se obtiene la colección de campos de la tabla de los alumnos
      java.util.TreeMap<String,ClsCampoBD> colCampos = conexion.obtenMapaCampos(conexion.obtenRS("tblAlumnos"));
      
      n = arr_nomCampos.length;
      
      // se rellena el valor para cada campo
      for( i = 0; i < n; i++ )
       ((ClsCampoBD) colCampos.get(arr_nomCampos[i])).valWhere = arr_datos[i];
      
      // se solicita al objeto conexión que elimine el registro y se espera el resultado
      return conexion.eliminaRegs("tblAlumnos", colCampos, tipo);
    }    
    public boolean modificaAlumno( MiModelo mm)
    {
        int col,colCve = -1;
        String strCve = null; 
        
        int numCampos = mm.getColumnCount();
        String[] strNomDatos = new String[numCampos];
        String[] strDatos    = new String[numCampos];
        
        for( col = 0; col < numCampos; col++ )
        {
           strNomDatos[col] = mm.getColumnName(col);
           strDatos[col]    = (String) mm.getValueAt(0, col);
           System.out.println(col + " -> " + strNomDatos[col] + ":" + strDatos[col]);
           if( strNomDatos[col].toLowerCase().startsWith("clv"))
           {
               colCve = col;
               strCve = strDatos[col];
           }
        }
        java.util.TreeMap<String,ClsCampoBD> colCampos = 
                conexion.obtenMapaCampos(conexion.obtenRegSelect("Select * from tblAlumnos where clvAlumno ='" + strCve + "';"));
        
        for( col = 0; col < numCampos; col++ )
        {
            colCampos.get(strNomDatos[col]).valor = strDatos[col];
        }
        colCampos.get(strNomDatos[colCve]).valWhere = strCve;
       
        return conexion.modificaReg("tblAlumnos", colCampos);
        
    }
    
    
// ---------------------------------------------------------------------------    
    public boolean altaProfesor()
    {
     return true;   
    }  
// ---------------------------------------------------------------------------    
    public boolean altaBaile()
    {
     return true;   
    }
// ---------------------------------------------------------------------------    
    public boolean altaNivel()
    {
     return true;   
    }
// ---------------------------------------------------------------------------    
    public boolean altaGrupo()
    {
     return true;   
    }    
// ---------------------------------------------------------------------------    
    public boolean altaAlumnoEnGrupo(String numGpo, String clvAlumno )
    {
     return true;   
    }    
// ---------------------------------------------------------------------------    
// ---------------------------------------------------------------------------    
// ---------------------------------------------------------------------------    
// ---------------------------------------------------------------------------    
    public static void main(String[] args){
        ClsGestorEscBaile gestor = new ClsGestorEscBaile();
        MiModelo elModelo;
        int nReng, nCol;
        try{
             if( gestor.conectaBD("rafa", "rafa" )){
                String res="";
                elModelo= new MiModelo(gestor.obtenAlumnos());
                nReng=elModelo.getRowCount();
                nCol=elModelo.getColumnCount();
                for(int i=0;i<nReng;i++){
                    res=res+"(";
                    for (int j=0;j<nCol;j++){
                        res=res+elModelo.getValueAt(i, j)+",";
                    }
                    res=res.substring(0,res.length()-1)+"),\n";
                }
                res=res.substring(0, res.length()-3)+";";
                System.out.println(res);
          }
          else
          {
             
              System.out.println("No se pudo establecer la conexión a la base de datos...");              
          }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}