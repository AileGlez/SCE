
package wsexponeejb;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WSExponeEJB", targetNamespace = "http://wsexponeejb/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WSExponeEJB {


    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "obtenResultado", targetNamespace = "http://wsexponeejb/", className = "wsexponeejb.ObtenResultado")
    @ResponseWrapper(localName = "obtenResultadoResponse", targetNamespace = "http://wsexponeejb/", className = "wsexponeejb.ObtenResultadoResponse")
    @Action(input = "http://wsexponeejb/WSExponeEJB/obtenResultadoRequest", output = "http://wsexponeejb/WSExponeEJB/obtenResultadoResponse")
    public String obtenResultado();

    /**
     * 
     * @param a
     * @param b
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "obtenSuma", targetNamespace = "http://wsexponeejb/", className = "wsexponeejb.ObtenSuma")
    @ResponseWrapper(localName = "obtenSumaResponse", targetNamespace = "http://wsexponeejb/", className = "wsexponeejb.ObtenSumaResponse")
    @Action(input = "http://wsexponeejb/WSExponeEJB/obtenSumaRequest", output = "http://wsexponeejb/WSExponeEJB/obtenSumaResponse")
    public int obtenSuma(
        @WebParam(name = "a", targetNamespace = "")
        int a,
        @WebParam(name = "b", targetNamespace = "")
        int b);

    /**
     * 
     * @param name
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "hello", targetNamespace = "http://wsexponeejb/", className = "wsexponeejb.Hello")
    @ResponseWrapper(localName = "helloResponse", targetNamespace = "http://wsexponeejb/", className = "wsexponeejb.HelloResponse")
    @Action(input = "http://wsexponeejb/WSExponeEJB/helloRequest", output = "http://wsexponeejb/WSExponeEJB/helloResponse")
    public String hello(
        @WebParam(name = "name", targetNamespace = "")
        String name);

}
