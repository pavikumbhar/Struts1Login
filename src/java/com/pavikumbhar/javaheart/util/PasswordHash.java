/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.javaheart.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author clover
 */
public class PasswordHash {

    public static String generateMD5(String message) throws HashException {
        return hashString(message, "MD5");
    }

    public static String generateSHA1(String message) throws HashException {
        return hashString(message, "SHA-1");
    }

    public static String generateSHA256(String message) throws HashException {
        return hashString(message, "SHA-256");
    }

    public static String generateSHA512(String message) throws HashException {
        return hashString(message, "SHA-512");
    }

    private static String hashString(String message, String algorithm)
            throws HashException {

        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));

            return convertByteArrayToHexString(hashedBytes);
        } catch (NoSuchAlgorithmException ex) {
            HashException h = new HashException();
            h.setStackTrace(ex.getStackTrace());
        } catch (UnsupportedEncodingException ex) {
            HashException h = new HashException();
            h.setStackTrace(ex.getStackTrace());
        }

        return "";
    }

    private static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayBytes.length; i++) {
            stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return stringBuffer.toString();
    }

}
