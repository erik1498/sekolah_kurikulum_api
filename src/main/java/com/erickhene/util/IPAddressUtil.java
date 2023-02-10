package com.erickhene.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

@Slf4j
public class IPAddressUtil {
    private IPAddressUtil(){}
    private static final String localhostIpVFour = "127.0.0.1";
    private static final String localhostIPVSix = "0:0:0:0:0:0:0:1";

    public static String getClientIPAddressHttpServletRequest(HttpServletRequest request){
        try{
            String ipAddress = request.getRemoteHost();

            if (localhostIpVFour.equals(ipAddress) || localhostIPVSix.equals(ipAddress)){
                InetAddress inetAddress = InetAddress.getLocalHost();
                ipAddress = inetAddress.getHostAddress();
            }

            if (!ipAddress.isEmpty() && ipAddress.length()> 15 && (ipAddress.length() > 0 && ipAddress.indexOf(",") > 0)){
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
            return ipAddress;
        }catch (Exception e){
            log.error("Error [{}]", e.getMessage());
            return "Error get ip address";
        }
    }

    public static String getClientIPAddressReactiveServerHttpRequest(ServerHttpRequest request){
        try{
            String ipAddress = request.getRemoteAddress().getAddress().getHostAddress();

            if (localhostIpVFour.equals(ipAddress) || localhostIPVSix.equals(ipAddress)){
                InetAddress inetAddress = InetAddress.getLocalHost();
                ipAddress = inetAddress.getHostAddress();
            }

            if (!ipAddress.isEmpty() && ipAddress.length()> 15 && (ipAddress.length() > 0 && ipAddress.indexOf(",") > 0)){
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
            return ipAddress;
        }catch (NullPointerException e){
            log.error("Error NullPointerException [{}]", e.getMessage());
            return e.getMessage();
        }
        catch (Exception e){
            log.error("Error Exception [{}]", e.getMessage());
            return "Error get ip address";
        }
    }
}
