/**
 * BchtRminfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bcht.its.webserviceclient;

public interface BchtRminfo extends java.rmi.Remote {
    public String queryObjectOut(String xtlb, String jkxlh, String jkid, String queryXmlDoc) throws java.rmi.RemoteException;
    public String writeObjectOut(String xtlb, String jkxlh, String jkid, String writeXmlDoc) throws java.rmi.RemoteException;
}
