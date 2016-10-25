/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.javaheart.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class RC4Util {

    private static RC4Util rc4Util = null;

    public static RC4Util getInstance() {
        if (rc4Util == null) {
            rc4Util = new RC4Util();
        }
        return rc4Util;

    }

    public String encrypt(String encryptText) {

        //System.out.println("in encrypt");
        String encryptedText = null;

        try {
            SecretKeySpec spec = getKeySpec();
            Cipher cipher = Cipher.getInstance("RC4");
            cipher.init(Cipher.ENCRYPT_MODE, spec);
            BASE64Encoder enc = new BASE64Encoder();
            encryptedText = enc.encode(cipher.doFinal(encryptText.getBytes()));
            return encryptedText;
        } catch (Exception e) {
            //log.error("Exception occured during encryption..."+e);
            e.printStackTrace();
        }

        return encryptedText;
    }

    public String decrypt(String encryptedText) {
        String decryptedText = null;

        try {
            SecretKeySpec spec = getKeySpec();
            Cipher cipher = Cipher.getInstance("RC4");
            cipher.init(Cipher.DECRYPT_MODE, spec);
            BASE64Decoder dec = new BASE64Decoder();
            byte[] decText = cipher.doFinal(dec.decodeBuffer(encryptedText));
            return decryptedText = new String(decText);

        } catch (Exception e) {
            //log.error("Exception occured during decryption..."+e);
            e.printStackTrace();
        }
        return decryptedText;
    }
    
        public static String encryptForDB(String encryptText) {

        //System.out.println("in encrypt");
        String encryptedText = null;

        try {
            SecretKeySpec spec = getKeySpec();
            Cipher cipher = Cipher.getInstance("RC4");
            cipher.init(Cipher.ENCRYPT_MODE, spec);
            BASE64Encoder enc = new BASE64Encoder();
            encryptedText = enc.encode(cipher.doFinal(encryptText.getBytes()));
            return encryptedText;
        } catch (Exception e) {
            //log.error("Exception occured during encryption..."+e);
            e.printStackTrace();
        }

        return encryptedText;
    }

    public static  String decryptForDB(String encryptedText) {
        String decryptedText = null;

        try {
            SecretKeySpec spec = getKeySpec();
            Cipher cipher = Cipher.getInstance("RC4");
            cipher.init(Cipher.DECRYPT_MODE, spec);
            BASE64Decoder dec = new BASE64Decoder();
            byte[] decText = cipher.doFinal(dec.decodeBuffer(encryptedText));
            return decryptedText = new String(decText);

        } catch (Exception e) {
            //log.error("Exception occured during decryption..."+e);
            e.printStackTrace();
        }
        return decryptedText;
    }

    public String encrypt(String encryptText, String securekey) {

        //System.out.println("in encrypt");
        String encryptedText = null;

        try {
            SecretKeySpec spec = getKeySpec(securekey);
            Cipher cipher = Cipher.getInstance("RC4");
            cipher.init(Cipher.ENCRYPT_MODE, spec);

            BASE64Encoder enc = new BASE64Encoder();
            encryptedText = enc.encode(cipher.doFinal(encryptText.getBytes()));
            return encryptedText;
        } catch (Exception e) {
            //log.error("Exception occured during encryption..."+e);
            e.printStackTrace();
        }

        return encryptedText;
    }

    public String decrypt(String encryptedText, String securekey) {
        String decryptedText = null;

        try {
            SecretKeySpec spec = getKeySpec(securekey);
            Cipher cipher = Cipher.getInstance("RC4");
            cipher.init(Cipher.DECRYPT_MODE, spec);
            BASE64Decoder dec = new BASE64Decoder();
            byte[] decText = cipher.doFinal(dec.decodeBuffer(encryptedText));
            return decryptedText = new String(decText);

        } catch (Exception e) {
            //log.error("Exception occured during decryption..."+e);
            e.printStackTrace();
        }
        return decryptedText;
    }

    public static SecretKeySpec getKeySpec() {
        SecretKeySpec spec = null;

        try {
            byte[] bytes = new byte[16];
            String pwd = "h4w6*k^";
            //String pwd = securekey;
            bytes = pwd.getBytes();

            SecretKey key = null;

            KeyGenerator kgen = KeyGenerator.getInstance("RC4");
            //	System.out.println(kgen.i);
            kgen.init(128);

            spec = new SecretKeySpec(bytes, "RC4");
            return spec;

        } catch (Exception e) {
            //log.error("Exception occured during key generation..."+e);
            e.printStackTrace();
        }

        return spec;
    }

    public static SecretKeySpec getKeySpec(String securekey) {
        SecretKeySpec spec = null;

        try {
            byte[] bytes = new byte[256];
            //String pwd = "h4w6*k^";
            String pwd = securekey;
            bytes = pwd.getBytes();

            SecretKey key = null;

            KeyGenerator kgen = KeyGenerator.getInstance("RC4");
            //	System.out.println(kgen.i);
            kgen.init(512);

            spec = new SecretKeySpec(bytes, "RC4");
            return spec;

        } catch (Exception e) {
            //log.error("Exception occured during key generation..."+e);
            e.printStackTrace();
        }

        return spec;
    }

    public static void main(String[] args) {
        try {
            RC4Util r = RC4Util.getInstance();
           // String original = "jdbc:oracle:thin:@10.10.211.227:1522:DCBEDSR";
            String original = "xxxxx";
            
            
            String enc = r.encrypt(original);
            //String dec=r.decrypt(enc,securekey);
            String dec = r.decrypt(enc);
            System.out.println("ofdriginal=" + original);
            System.out.println("enc=    " + enc);
            System.out.println("dec=" + dec);
            System.out.println(original.equals(dec));
        } catch (Exception e) {
        }
    }

}
