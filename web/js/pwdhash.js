/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function RC4(Key, Data) {
     KeyBytes=new Array(255);
  CypherBytes=new Array(255);
  for(i=0; i<256; ++i) {
    KeyBytes[i]=Key.charCodeAt(i % Key.length);
    CypherBytes[i]=i;
//document.write(i+" KeyBytes[i]="+ KeyBytes[i]+" CypherBytes[i]"+ CypherBytes[i]+"<BR>");

  }
  Jump=0;
  for(i=0; i<256; ++i) {
    Jump=(Jump+CypherBytes[i]+KeyBytes[i]) & 0xFF;
    Tmp=CypherBytes[i]; // Swap:
    CypherBytes[i]=CypherBytes[Jump];
    CypherBytes[Jump]=Tmp;
	//document.write(i+"Jump="+Jump+"<>Tmp="+Tmp+" CypherBytes[i]="+ CypherBytes[i]+" CypherBytes[Jump]"+ CypherBytes[Jump]+"<BR>");
  }
  i=0;
  Jump=0;
  Result="";
  for(X=0; X < Data.length; ++X) {
    i=(i+1) & 0xFF;
    Tmp=CypherBytes[i];
    Jump=(Jump+Tmp) & 0xFF;
    T=(Tmp+CypherBytes[Jump]) & 0xFF;
    CypherBytes[i]=CypherBytes[Jump]; // Swap:
    CypherBytes[Jump]=Tmp;
    Result+=String.fromCharCode(Data.charCodeAt(X)^CypherBytes[T]);
//document.write("i="+i+"<>Tmp="+Tmp+"<>Jump="+Jump+"<>T="+T+"<>CypherBytes[i]="+CypherBytes[i]+"<> CypherBytes[Jump]="+ CypherBytes[Jump]+"<>Data.charCodeAt(X)="+Data.charCodeAt(X)+"<>CypherBytes[T]="+CypherBytes[T]+"<>Result="+Result+"<>(Data.charCodeAt(X)^CypherBytes[T]=)"+(Data.charCodeAt(X)^CypherBytes[T])+"<br>");
  }
  return Result; 
}
function Data2ASCIIhex(S) {
//document.write("S="+S+"<BR>");
  var Result="";
  var Chars="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
  for(i=0; i<S.length; ++i) {
    Byte=S.charCodeAt(i);
	
	Result+=pad(Byte,3);
    //lo=Byte & 0x0F;
    //hi=Byte >> 4;
    //Result+=Chars.charAt(hi)+Chars.charAt(lo);
	//document.write(i+"<>Byte="+Byte+"<>lo="+lo+"<>hi="+hi+"<>Chars.charAt(lo)="+Chars.charAt(lo)+"<>Chars.charAt(hi)="+Chars.charAt(hi)+"<>Result="+Result+"<br>");
  }
  return Result;
}
 function pad(num, size) {
    var s = num+"";
    while (s.length < size) s = "0" + s;
    return s;
}
