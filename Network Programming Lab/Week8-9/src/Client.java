import java.net.*; 
import java.io.*; 
public class Client { 

DataInputStream netIn; 
DataOutputStream netOut; 
InetAddress hostress; 
Socket socket; 
int POST=444; 
private String TGNum=""; //关卡选择 
/** 
* @param args 
*/ 
public static void main(String[] args) { 
// TODO Auto-generated method stub 
Client cn=new Client(); 
cn.connecttoserver(); 


} 
/** 
* 连接服务器 
*/ 
public void connecttoserver(){ 
BufferedReader hostname=new BufferedReader(new InputStreamReader(System.in)); 
try { 
System.out.println("请输入主机IP或主机名："); 
String hostip=hostname.readLine(); //输入主机名 
socket=new Socket(hostip,POST); //建立连接 
if(socket.isConnected()){ 
System.out.println("连接成功！"); 
} 
netIn=new DataInputStream(socket.getInputStream()); 
netOut=new DataOutputStream(socket.getOutputStream()); 
String XZ=""; 
/* 
* 如果用户选择"5"则退出,否则继续. 
*/ 
while(!XZ.equals("5")){ 
XZ=Option(); 
if(XZ.equals("1")) 
{ 
NumberInput(); //输入数字并接收服务器的反馈信息 
}else 
if(XZ.equals("4")) 
{ 
System.out.println("本程序代码由B004班向启燮编写，QQ：358801989"); 
}else 
if(XZ.equals("5")) 
{ 
System.out.println("退出程序！"); 
}else 
if(XZ.equals("2")){ 
System.out.println("此功能待开发！"); 
TollGate(); //关卡选择 
} 
socket=new Socket(hostip,POST); 
netIn=new DataInputStream(socket.getInputStream()); 
netOut=new DataOutputStream(socket.getOutputStream()); 
} 
} catch (IOException e) { 
// TODO Auto-generated catch block 
System.out.println("服务器连接失败，请确认您的输入是否正确！"); 
} 
} 
/** 
* 输入数字并接收服务器的反馈结果 
*/ 
public void NumberInput(){ 
String FK; 
BufferedReader shu=new BufferedReader(new InputStreamReader(System.in)); 
try { 
FK=netIn.readUTF(); //获得数字的范围 
System.out.println(FK); 
for(int i=0;i<5;i++){ 
String number=shu.readLine(); //输入猜的数 
netOut.writeUTF(number); 
FK=netIn.readUTF(); //获得猜测结果 
System.out.println(FK); 
if(FK.equals("恭喜您猜对了！")){ 
break; 
}else if(i==4){ 
FK=netIn.readUTF(); //获得服务器生成的随机数 
System.out.println(FK); 
System.out.println("您五次都没猜对，连接断开！"); 
} 
} 
} catch (IOException e) { 
// TODO Auto-generated catch block 
e.printStackTrace(); 
} 
} 

/** 
* 选项 
*/ 
public String Option(){ 
System.out.println(); 
System.out.println("1、开始游戏"); 
System.out.println("2、关 卡"); 
System.out.println("3、排 行 榜"); 
System.out.println("4、关 于"); 
System.out.println("5、退 出"); 
BufferedReader xz=new BufferedReader(new InputStreamReader(System.in)); 
String xzstr = null; 
System.out.println(); 
System.out.print("选择："); 
try { 
xzstr = xz.readLine(); //用户选择 
netOut.writeUTF(xzstr); //把选择结果发送到服务器 
} catch (IOException e) { 
// TODO Auto-generated catch block 
e.printStackTrace(); 
} 
return xzstr; 
} 
/** 
* 关卡 
*/ 
public void TollGate(){ 
BufferedReader TGIn=new BufferedReader(new InputStreamReader(System.in)); 
try { 
System.out.println("1、第一关"); 
System.out.println("2、第二关"); 
System.out.println("3、第三关"); 
System.out.println("4、第四关"); 
System.out.println("5、第五关"); 
System.out.println("6、返　回"); 
System.out.print("选择："); 
TGNum=TGIn.readLine(); 
} catch (IOException e) { 
// TODO Auto-generated catch block 
e.printStackTrace(); 
} 
} 
} 