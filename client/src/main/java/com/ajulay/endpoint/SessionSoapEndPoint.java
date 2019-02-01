package com.ajulay.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-02-01T13:45:51.775+03:00
 * Generated source version: 3.2.7
 */
@WebService(targetNamespace = "http://endpoint.ajulay.com/", name = "SessionSoapEndPoint")
@XmlSeeAlso({ObjectFactory.class})
public interface SessionSoapEndPoint {

    @WebMethod
    @Action(input = "http://endpoint.ajulay.com/SessionSoapEndPoint/logoutRequest", output = "http://endpoint.ajulay.com/SessionSoapEndPoint/logoutResponse")
    @RequestWrapper(localName = "logout", targetNamespace = "http://endpoint.ajulay.com/", className = "com.ajulay.endpoint.Logout")
    @ResponseWrapper(localName = "logoutResponse", targetNamespace = "http://endpoint.ajulay.com/", className = "com.ajulay.endpoint.LogoutResponse")
    @WebResult(name = "return", targetNamespace = "")
    public com.ajulay.endpoint.Success logout(
            @WebParam(name = "arg0", targetNamespace = "")
                    com.ajulay.endpoint.Session arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.ajulay.com/SessionSoapEndPoint/registerRequest", output = "http://endpoint.ajulay.com/SessionSoapEndPoint/registerResponse")
    @RequestWrapper(localName = "register", targetNamespace = "http://endpoint.ajulay.com/", className = "com.ajulay.endpoint.Register")
    @ResponseWrapper(localName = "registerResponse", targetNamespace = "http://endpoint.ajulay.com/", className = "com.ajulay.endpoint.RegisterResponse")
    @WebResult(name = "return", targetNamespace = "")
    public com.ajulay.endpoint.Success register(
            @WebParam(name = "arg0", targetNamespace = "")
                    java.lang.String arg0,
            @WebParam(name = "arg1", targetNamespace = "")
                    java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.ajulay.com/SessionSoapEndPoint/getSessionAllRequest", output = "http://endpoint.ajulay.com/SessionSoapEndPoint/getSessionAllResponse")
    @RequestWrapper(localName = "getSessionAll", targetNamespace = "http://endpoint.ajulay.com/", className = "com.ajulay.endpoint.GetSessionAll")
    @ResponseWrapper(localName = "getSessionAllResponse", targetNamespace = "http://endpoint.ajulay.com/", className = "com.ajulay.endpoint.GetSessionAllResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<com.ajulay.endpoint.Session> getSessionAll(
            @WebParam(name = "arg0", targetNamespace = "")
                    com.ajulay.endpoint.Session arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.ajulay.com/SessionSoapEndPoint/loginRequest", output = "http://endpoint.ajulay.com/SessionSoapEndPoint/loginResponse", fault = {@FaultAction(className = Exception_Exception.class, value = "http://endpoint.ajulay.com/SessionSoapEndPoint/login/Fault/Exception")})
    @RequestWrapper(localName = "login", targetNamespace = "http://endpoint.ajulay.com/", className = "com.ajulay.endpoint.Login")
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "http://endpoint.ajulay.com/", className = "com.ajulay.endpoint.LoginResponse")
    @WebResult(name = "return", targetNamespace = "")
    public com.ajulay.endpoint.Session login(
            @WebParam(name = "arg0", targetNamespace = "")
                    java.lang.String arg0,
            @WebParam(name = "arg1", targetNamespace = "")
                    java.lang.String arg1
    ) throws Exception_Exception;

    @WebMethod
    @Action(input = "http://endpoint.ajulay.com/SessionSoapEndPoint/deleteSessionByIdRequest", output = "http://endpoint.ajulay.com/SessionSoapEndPoint/deleteSessionByIdResponse")
    @RequestWrapper(localName = "deleteSessionById", targetNamespace = "http://endpoint.ajulay.com/", className = "com.ajulay.endpoint.DeleteSessionById")
    @ResponseWrapper(localName = "deleteSessionByIdResponse", targetNamespace = "http://endpoint.ajulay.com/", className = "com.ajulay.endpoint.DeleteSessionByIdResponse")
    @WebResult(name = "return", targetNamespace = "")
    public com.ajulay.endpoint.Success deleteSessionById(
            @WebParam(name = "arg0", targetNamespace = "")
                    com.ajulay.endpoint.Session arg0,
            @WebParam(name = "arg1", targetNamespace = "")
                    java.lang.String arg1
    );
}
