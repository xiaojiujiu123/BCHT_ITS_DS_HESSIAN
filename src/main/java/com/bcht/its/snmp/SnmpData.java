package com.bcht.its.snmp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 演示： GET单个OID值
 *
 * blog http://www.micmiu.com
 *
 * @author Michael
 */
public class SnmpData {
    private static Logger logger = LoggerFactory.getLogger(SnmpData.class);
    public static final int DEFAULT_VERSION = SnmpConstants.version2c;
    public static final String DEFAULT_PROTOCOL = "udp";
    public static final int DEFAULT_PORT = 161;
    public static final long DEFAULT_TIMEOUT = 3 * 1000L;
    public static final int DEFAULT_RETRY = 3;

    /**
     * 创建对象communityTarget，用于返回target
     *
     * @param ip 服务器的IP
     * @param community 身份陷井名称
     * @return CommunityTarget
     */
    public static CommunityTarget createDefault(String ip, String community) {
        Address address = GenericAddress.parse(DEFAULT_PROTOCOL + ":" + ip + "/" + DEFAULT_PORT);
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(community));
        target.setAddress(address);
        target.setVersion(DEFAULT_VERSION);
        target.setTimeout(DEFAULT_TIMEOUT); // milliseconds
        target.setRetries(DEFAULT_RETRY);
        return target;
    }

    /**
     * 根据OID，获取单条消息
     * @param ip 服务器名称
     * @param community 身份陷井名称
     * @param oid OID类型
     * @retun 返回单条记录
     */
    public static Map<OID,Variable> snmpGet(String ip, String community, String oid) {

        CommunityTarget target = createDefault(ip, community);
        Snmp snmp = null;
        Map<OID,Variable> result = null;
        try {
            PDU pdu = new PDU();
            // pdu.add(new VariableBinding(new OID(new int[]
            // {1,3,6,1,2,1,1,2})));
            pdu.add(new VariableBinding(new OID(oid)));

            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
            logger.debug("-------> 发送PDU <-------");
            pdu.setType(PDU.GET);
            ResponseEvent respEvent = snmp.send(pdu, target);
            logger.debug("PeerAddress:" + respEvent.getPeerAddress());
            PDU response = respEvent.getResponse();

            if (response == null) {
                logger.debug("response is null, request time out");
            } else {
                logger.debug("response pdu size is " + response.size());
                result = new HashMap<OID,Variable>();
                for (int i = 0; i < response.size(); i++) {
                    VariableBinding vb = response.get(i);
                    logger.debug(vb.getOid() + " = " + vb.getVariable());
                    result.put(vb.getOid(),vb.getVariable());
                }

            }
            logger.debug("SNMP GET one OID value finished !");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("SNMP Get Exception:" + e);
            return null;
        } finally {
            if (snmp != null) {
                try {
                    snmp.close();
                } catch (IOException ex1) {
                    snmp = null;
                }
            }
        }
    }
    /*根据OID列表，一次获取多条OID数据，并且以List形式返回*/
    public static void snmpGetList(String ip, String community, List<String> oidList)
    {
        CommunityTarget target = createDefault(ip, community);
        Snmp snmp = null;
        try {
            PDU pdu = new PDU();

            for(String oid:oidList)
            {
                pdu.add(new VariableBinding(new OID(oid)));
            }

            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
            logger.debug("-------> 发送PDU <-------");
            pdu.setType(PDU.GET);
            ResponseEvent respEvent = snmp.send(pdu, target);
            logger.debug("PeerAddress:" + respEvent.getPeerAddress());
            PDU response = respEvent.getResponse();

            if (response == null) {
                logger.debug("response is null, request time out");
            } else {

                logger.debug("response pdu size is " + response.size());
                for (int i = 0; i < response.size(); i++) {
                    VariableBinding vb = response.get(i);
                    logger.debug(vb.getOid() + " = " + vb.getVariable());
                }

            }
            logger.debug("SNMP GET one OID value finished !");
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("SNMP Get Exception:" + e);
        } finally {
            if (snmp != null) {
                try {
                    snmp.close();
                } catch (IOException ex1) {
                    snmp = null;
                }
            }

        }
    }
    /*根据OID列表，采用异步方式一次获取多条OID数据，并且以List形式返回*/
    public static void snmpAsynGetList(String ip, String community,List<String> oidList)
    {
        CommunityTarget target = createDefault(ip, community);
        Snmp snmp = null;
        try {
            PDU pdu = new PDU();

            for(String oid:oidList)
            {
                pdu.add(new VariableBinding(new OID(oid)));
            }

            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
            logger.debug("-------> 发送PDU <-------");
            pdu.setType(PDU.GET);
            ResponseEvent respEvent = snmp.send(pdu, target);
            logger.debug("PeerAddress:" + respEvent.getPeerAddress());
            PDU response = respEvent.getResponse();

			/*异步获取*/
            final CountDownLatch latch = new CountDownLatch(1);
            ResponseListener listener = new ResponseListener() {
                public void onResponse(ResponseEvent event) {
                    ((Snmp) event.getSource()).cancel(event.getRequest(), this);
                    PDU response = event.getResponse();
                    PDU request = event.getRequest();
                    logger.debug("[request]:" + request);
                    if (response == null) {
                        logger.debug("[ERROR]: response is null");
                    } else if (response.getErrorStatus() != 0) {
                        logger.debug("[ERROR]: response status"
                                + response.getErrorStatus() + " Text:"
                                + response.getErrorStatusText());
                    } else {
                        logger.debug("Received response Success!");
                        for (int i = 0; i < response.size(); i++) {
                            VariableBinding vb = response.get(i);
                            logger.debug(vb.getOid() + " = "
                                    + vb.getVariable());
                        }
                        logger.debug("SNMP Asyn GetList OID finished. ");
                        latch.countDown();
                    }
                }
            };

            pdu.setType(PDU.GET);
            snmp.send(pdu, target, null, listener);
            logger.debug("asyn send pdu wait for response...");

            boolean wait = latch.await(30, TimeUnit.SECONDS);
            logger.debug("latch.await =:" + wait);

            snmp.close();

            logger.debug("SNMP GET one OID value finished !");
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("SNMP Get Exception:" + e);
        } finally {
            if (snmp != null) {
                try {
                    snmp.close();
                } catch (IOException ex1) {
                    snmp = null;
                }
            }

        }
    }
    /*根据targetOID，获取树形数据*/
    public static Map<OID,Variable> snmpWalk(String ip, String community, String targetOid)
    {
        CommunityTarget target = createDefault(ip, community);
        TransportMapping transport = null;
        Snmp snmp = null;
        try {
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            transport.listen();

            PDU pdu = new PDU();
            OID targetOID = new OID(targetOid);
            pdu.add(new VariableBinding(targetOID));

            boolean finished = false;
            logger.debug("----> demo start <----");
            Map<OID,Variable> result = new HashMap<OID, Variable>();
            while (!finished) {
                VariableBinding vb = null;
                ResponseEvent respEvent = snmp.getNext(pdu, target);

                PDU response = respEvent.getResponse();

                if (null == response) {
                    logger.debug("responsePDU == null");
                    finished = true;
                    break;
                } else {
                    vb = response.get(0);
                }
                // check finish
                finished = checkWalkFinished(targetOID, pdu, vb);
                if (!finished) {
                    logger.debug("==== walk each vlaue :");
                    logger.debug(vb.getOid() + " = " + vb.getVariable());
                    result.put(vb.getOid(),vb.getVariable());
                    // Set up the variable binding for the next entry.
                    pdu.setRequestID(new Integer32(0));
                    pdu.set(0, vb);
                } else {
                    logger.debug("SNMP walk OID has finished.");
                    snmp.close();
                }
            }
            logger.debug("----> demo end <----");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("SNMP walk Exception: " + e);
            return null;
        } finally {
            if (snmp != null) {
                try {
                    snmp.close();
                } catch (IOException ex1) {
                    snmp = null;
                }
            }
        }
    }

    private static boolean checkWalkFinished(OID targetOID, PDU pdu,
                                             VariableBinding vb) {
        boolean finished = false;
        if (pdu.getErrorStatus() != 0) {
            logger.debug("[true] responsePDU.getErrorStatus() != 0 ");
            logger.debug(pdu.getErrorStatusText());
            finished = true;
        } else if (vb.getOid() == null) {
            logger.debug("[true] vb.getOid() == null");
            finished = true;
        } else if (vb.getOid().size() < targetOID.size()) {
            logger.debug("[true] vb.getOid().size() < targetOID.size()");
            finished = true;
        } else if (targetOID.leftMostCompare(targetOID.size(), vb.getOid()) != 0) {
            logger.debug("[true] targetOID.leftMostCompare() != 0");
            finished = true;
        } else if (Null.isExceptionSyntax(vb.getVariable().getSyntax())) {
            System.out
                    .println("[true] Null.isExceptionSyntax(vb.getVariable().getSyntax())");
            finished = true;
        } else if (vb.getOid().compareTo(targetOID) <= 0) {
            logger.debug("[true] Variable received is not " + "lexicographic successor of requested " + "one:");
            logger.debug(vb.toString() + " <= " + targetOID);
            finished = true;
        }
        return finished;

    }
    /*根据targetOID，异步获取树形数据*/
    public static void snmpAsynWalk(String ip, String community, String oid)
    {
        final CommunityTarget target = createDefault(ip, community);
        Snmp snmp = null;
        try {
            logger.debug("----> demo start <----");

            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();

            final PDU pdu = new PDU();
            final OID targetOID = new OID(oid);
            final CountDownLatch latch = new CountDownLatch(1);
            pdu.add(new VariableBinding(targetOID));

            ResponseListener listener = new ResponseListener() {
                public void onResponse(ResponseEvent event) {
                    ((Snmp) event.getSource()).cancel(event.getRequest(), this);

                    try {
                        PDU response = event.getResponse();
                        // PDU request = event.getRequest();
                        // logger.debug("[request]:" + request);
                        if (response == null) {
                            logger.debug("[ERROR]: response is null");
                        } else if (response.getErrorStatus() != 0) {
                            logger.debug("[ERROR]: response status"
                                    + response.getErrorStatus() + " Text:"
                                    + response.getErrorStatusText());
                        } else {
                            System.out.println("Received Walk response value :");
                            VariableBinding vb = response.get(0);

                            boolean finished = checkWalkFinished(targetOID, pdu, vb);
                            if (!finished) {
                                logger.debug(vb.getOid() + " = " + vb.getVariable());
                                pdu.setRequestID(new Integer32(0));
                                pdu.set(0, vb);
                                ((Snmp) event.getSource()).getNext(pdu, target, null, this);
                            } else {
                                System.out.println("SNMP Asyn walk OID value success !");
                                latch.countDown();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        latch.countDown();
                    }

                }
            };

            snmp.getNext(pdu, target, null, listener);
            logger.debug("pdu 已发送,等到异步处理结果...");

            boolean wait = latch.await(30, TimeUnit.SECONDS);
            logger.debug("latch.await =:" + wait);
            snmp.close();

            logger.debug("----> demo end <----");
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("SNMP Asyn Walk Exception:" + e);
        }
    }

    /**
     * 根据OID和指定string来设置设备的数据
     * @param ip
     * @param community
     * @param oid
     * @param val
     * @throws IOException
     */
    public static void setPDU(String ip,String community,String oid,String val) throws IOException
    {
        CommunityTarget target = createDefault(ip, community);
        Snmp snmp = null;
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(oid),new OctetString(val)));
        pdu.setType(PDU.SET);

        DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
        snmp = new Snmp(transport);
        snmp.listen();
        logger.debug("-------> 发送PDU <-------");
        snmp.send(pdu, target);
        snmp.close();
    }

    public static void main(String[] args){

       /* while(true){
            try {
                String ip = "127.0.0.1";
                String community = "public";
                String targetOid = ".1.3.6.1.2.1.25.2.3.1.3";
                Map<OID,Variable> result = SnmpData.snmpWalk(ip, community, targetOid);
                Iterator<OID> iterator = result.keySet().iterator();
                Iterator<Variable> values = result.values().iterator();
                int index = 0;
                int cpuValue = 0;
                while(values.hasNext()){
                    cpuValue += values.next().toInt();
                    index++;
                }
                System.out.println(cpuValue / index);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
        }*/

        String ip = "10.10.10.7";
        String community = "public";
        String targetOid = "1.3.6.1.2.1.25.2.3.1.5.0";

        Map<OID,Variable> result = SnmpData.snmpGet(ip, community, targetOid);
        System.out.println(result);

        //1.3.6.1.2.1.25.2.3.1.3
        //1.3.6.1.2.1.25.2.3.1.6
    }
}
