/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.javaheart.util;

/**
 *
 * @author clover
 */
public class RC4 {

    public static String RC4(String Key, String Data) {
        String Result = "";
        try {

            int[] KeyBytes = new int[256];
            int[] CypherBytes = new int[256];
            for (int i = 0; i < 256; ++i) {
                //System.out.println(i+"i % Key.length()="+(i % Key.length()));
                KeyBytes[i] = Key.charAt(i % Key.length());
                CypherBytes[i] = i;
                // System.out.println(i+" KeyBytes[i]="+ KeyBytes[i]+" CypherBytes[i]"+ CypherBytes[i]+"<BR>");
            }
            int Jump = 0;
            for (int i = 0; i < 256; ++i) {
                Jump = (Jump + CypherBytes[i] + KeyBytes[i]) & 0xFF;
                int Tmp = CypherBytes[i]; // Swap:
                CypherBytes[i] = CypherBytes[Jump];
                CypherBytes[Jump] = Tmp;
                //System.out.println(i+"Jump="+Jump+"<>Tmp="+Tmp+" CypherBytes[i]="+ CypherBytes[i]+" CypherBytes[Jump]"+ CypherBytes[Jump]+"<BR>");
            }
            int i = 0;
            Jump = 0;

            for (int X = 0; X < Data.length(); ++X) {
                i = (i + 1) & 0xFF;
                int Tmp = CypherBytes[i];
                Jump = (Jump + Tmp) & 0xFF;
                int T = (Tmp + CypherBytes[Jump]) & 0xFF;
                CypherBytes[i] = CypherBytes[Jump]; // Swap:
                CypherBytes[Jump] = Tmp;
                int temp = (int) Data.charAt(X);
                int temp1 = temp ^ CypherBytes[T];
                //Result+=String.valueOf();
                Result += Character.toString((char) temp1);
                //System.out.println("i="+i+"<>Tmp="+Tmp+"<>Jump="+Jump+"<>T="+T+"<>CypherBytes[i]="+CypherBytes[i]+"<> CypherBytes[Jump]="+ CypherBytes[Jump]+"<>Data.charAt(X)="+Data.charAt(X)+"<>CypherBytes[T]="+CypherBytes[T]+"<>Result="+Result+"<>(Data.charAt(X)^CypherBytes[T]=)"+(Data.charAt(X)^CypherBytes[T])+"<>temp="+temp);
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return Result;
    }

    private static String Data2ASCIIhex(String S) {
        //System.out.println("S="+S);
        String Result = "";
        String Chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < S.length(); ++i) {
            int Byte = (int) S.charAt(i);
            int lo = Byte & 0x0F;
            int hi = Byte >> 4;
            Result += String.format("%03d", Byte) + "";
   // Result+=Chars.charAt(hi)+""+Chars.charAt(lo);
            //System.out.println(i+"<>Byte="+Byte+"<>lo="+lo+"<>hi="+hi+"<>Chars.charAt(lo)="+Chars.charAt(lo)+"<>Chars.charAt(hi)="+Chars.charAt(hi)+"<>Result="+Result);
        }
        return Result;
    }

    private static String Data2ASCIIhex1(String S) {
        //System.out.println("S="+S);
        String Result = "";
        String Chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < S.length(); ++i) {
            int Byte = (int) S.charAt(i);
            int lo = Byte & 0x0F;
            int hi = Byte >> 4;
            Result += Chars.charAt(hi) + "" + Chars.charAt(lo);
            //System.out.println(i+"<>Byte="+Byte+"<>lo="+lo+"<>hi="+hi+"<>Chars.charAt(lo)="+Chars.charAt(lo)+"<>Chars.charAt(hi)="+Chars.charAt(hi)+"<>Result="+Result);
        }
        return Result;
    }

    public static String ASCIIhex2Data(String S) {
        // System.out.println("S="+S);
        String Result = "";
        String Chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < S.length();) {
            int start = i;
            int end = i + 3;
            i = end;
            String x = S.substring(start, end);
      //System.out.println("start="+start+"<>end="+end+"<>i="+i+"<>x="+x);

    //int lo=Chars.indexOf(S.charAt(++i));
            //System.out.println("byte1="+byte1+">>"+Character.toString((char)byte1));
            //int byte1=hi<< 4;
            //int byte2=lo ^ 0x0F;
            //System.out.println("hi="+hi+"<>lo="+lo+"<>byte1="+byte1+"<>byte2="+byte2);
            //int lo=Byte & 0x0F;
            //int hi=Byte >> 4;
            Result += Character.toString((char) Integer.parseInt(x));
            //System.out.println(i+"<>Byte="+Byte+"<>lo="+lo+"<>hi="+hi+"<>Chars.charAt(lo)="+Chars.charAt(lo)+"<>Chars.charAt(hi)="+Chars.charAt(hi)+"<>Result="+Result);
        }
        return Result;
    }

    public static void main(String[] args) {
        String temp = RC4("oiasdf234", "pravin@123");
        String t1 = Data2ASCIIhex(temp);

        System.out.println(">>>"+temp);
        System.out.println(">>>"+t1);
        System.out.println("____"+ASCIIhex2Data(t1));
         String dec=RC4("oiasdf234",temp);
        System.out.println(">>>"+dec);
    }

    public static void main2(String[] args) {
        try {
            int a = 0x0F;
            int b = 34;
            int c = a & b;
            System.out.println(c);
            int d = c ^ a;
            System.out.println(d);
            d = c | a;
            System.out.println(d);
        } catch (Exception e) {
        }
    }

}
