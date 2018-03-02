/**
 * BchtRminfoImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bcht.its.webserviceclient;

public class BchtRminfoImplServiceLocator extends org.apache.axis.client.Service implements BchtRminfoImplService {

    public BchtRminfoImplServiceLocator() {
    }


    public BchtRminfoImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public BchtRminfoImplServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BchtRminfoImplPort
    private String BchtRminfoImplPort_address = "http://10.166.194.52:9999/service/bchtRminf";

    public String getBchtRminfoImplPortAddress() {
        return BchtRminfoImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String BchtRminfoImplPortWSDDServiceName = "BchtRminfoImplPort";

    public String getBchtRminfoImplPortWSDDServiceName() {
        return BchtRminfoImplPortWSDDServiceName;
    }

    public void setBchtRminfoImplPortWSDDServiceName(String name) {
        BchtRminfoImplPortWSDDServiceName = name;
    }

    public BchtRminfo getBchtRminfoImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BchtRminfoImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBchtRminfoImplPort(endpoint);
    }

    public BchtRminfo getBchtRminfoImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
           BchtRminfoImplServiceSoapBindingStub _stub = new BchtRminfoImplServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getBchtRminfoImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBchtRminfoImplPortEndpointAddress(String address) {
        BchtRminfoImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (BchtRminfo.class.isAssignableFrom(serviceEndpointInterface)) {
            BchtRminfoImplServiceSoapBindingStub _stub = new BchtRminfoImplServiceSoapBindingStub(new java.net.URL(BchtRminfoImplPort_address), this);
                _stub.setPortName(getBchtRminfoImplPortWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("BchtRminfoImplPort".equals(inputPortName)) {
            return getBchtRminfoImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://impl.webservice.its.bcht.com/", "BchtRminfoImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://impl.webservice.its.bcht.com/", "BchtRminfoImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("BchtRminfoImplPort".equals(portName)) {
            setBchtRminfoImplPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
