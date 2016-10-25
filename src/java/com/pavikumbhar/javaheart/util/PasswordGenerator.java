/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.javaheart.util;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Random;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author clover
 */
public class PasswordGenerator {

    Character c[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'z', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    private long generateRandomNumber() {
        Random r = new Random(10000000999l);
        long l1 = r.nextLong();
        long l2 = r.nextLong();
        long l3 = r.nextLong();
        return l1 & l2 & l3 * Calendar.getInstance().getTimeInMillis();
    }

    public char[] f1(Character[] a) {
        char[] temp = new char[a.length];

        for (int i = 0; i < a.length; i++) {
            temp[i] = a[i];
        }
        return temp;
    }

    public String generatePassword() {
        return generatePassword(8);
    }

    public String generatePasswordHash() {
        return generatePassword(10);
    }

    public String generateLoginpagetoken() {
        return generatePassword(10);
    }

    public String generateTempPasswordForMobile() {
        return generatePassword(8);
    }

    private String generatePassword(int length) {
        int count = c.length;
        LinkedList<Character> s = new LinkedList<Character>();
        LinkedList<Character> s1 = new LinkedList<Character>();
        Collections.addAll(s, c);
        while (count > 0) {
            long n = generateRandomNumber();
            int position = (int) (n % count);
            s1.add(s.remove(position));
            count--;
        }
        String pwd = null;
        //System.out.println(">>"+s1.toString()); 
        Character[] c1 = new Character[s1.size()];
        c1 = s1.toArray(c1);
        String s2 = new String(f1(c1));
               //System.out.println(">>"+s2); 

        return RandomStringUtils.random(length, s2);

    }

    public static void main(String[] args) {

        try {

            PasswordGenerator p = new PasswordGenerator();
            String pwd = p.generatePassword();
            String pwdHash = p.generatePasswordHash();
            System.out.println(pwd);
            System.out.println(pwdHash);
        //System.out.println(PasswordHash.generateMD5(x));
            //System.out.println(PasswordHash.generateSHA1(x));
            //System.out.println(PasswordHash.generateSHA256(x));
            System.out.println(PasswordHash.generateSHA512(pwd));
            System.out.println(PasswordHash.generateSHA512(pwd + pwdHash));

            System.out.println(">>final>>=" + PasswordHash.generateSHA512("password" + "pass"));

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }
}
