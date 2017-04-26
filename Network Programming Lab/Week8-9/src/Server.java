import java.util.*; 
import java.io.*; 
import java.net.*; 
public class Server { 

DataInputStream netIn; 
DataOutputStream netOut; 
int GuanKa=0; //关卡 
ServerSocket serverscoket; 
Socket socket[]=new Socket[100]; //最多可以连接100个客户端 
int TopNumber[]=new int[10]; //排行榜数值 
String TopName[]=new String[10]; //排行榜名字 
int POST=444; //端口 
int numbegin=0,numend=100; //随机数开始值和结束值 
int number=0; //客户端猜的数值 
int Fen=100; //定义客户端得分 
int i=0; //连接数量 
String OptionNum=""; //客户端的菜单选择 

Server(){ 
Login(); 
} 
/** 
* 主方法 
*/ 
public static void main(String[] args) { 
// TODO Auto-generated method stub 
Server s=new Server(); 
s.Login(); 


} 
/** 
* 连接,并对客户端传来的数据进行操作。 
*/ 
public void Login(){ 

try { 
serverscoket=new ServerSocket(POST); 
System.out.println("服务器开启..."); 
InetAddress host=InetAddress.getLocalHost(); 
System.out.println("本机IP："+host.getHostAddress().toString()); 
socket[i]=serverscoket.accept(); 
String ip=socket[i].getInetAddress().getHostName(); 
System.out.println(ip+"连入服务器"); 
netIn=new DataInputStream(socket[i].getInputStream()); 
netOut=new DataOutputStream(socket[i].getOutputStream()); 
OptionNum=netIn.readUTF(); //获取客户端的菜单选择 
System.out.println("对方选择："+OptionNum); 
/* 
* 如果对方选择"5"即退出,否则保持连接 
*/ 
while(!OptionNum.equals("5")) 
{ 
if(OptionNum.equals("1")){ 
ServerOut(); 
} 
socket[i]=serverscoket.accept(); 
netIn=new DataInputStream(socket[i].getInputStream()); 
netOut=new DataOutputStream(socket[i].getOutputStream()); 
OptionNum=netIn.readUTF(); //获取对方选择信息 
System.out.println("对方选择："+OptionNum); 
} 

} catch (IOException e) { 
// TODO Auto-generated catch block 
System.out.println("连接断开或数据错误！"); 
} 
} 
/** 
* 服务器反馈 
*/ 
public void ServerOut(){ 
int quess=10; //定义猜测次数 
int NumBer=RomDom(numbegin,numend); //生成从numbegin-numend的随机数 
System.out.println("生成的随机数为："+NumBer); 
try { 
netOut.writeUTF("请输入"+numbegin+"到"+numend+"之间的数！\n您有"+quess+"次猜测！"); 
/* 
* 如果客户端猜了五次或猜对了,则退出,否则继续循环. 
*/ 
for(int j=1;j<=quess&&number!=NumBer;j++){ 
number=Integer.parseInt(netIn.readUTF()); 
netOut.writeUTF(ShuZiChaoZuo(NumBer,number)); //向客户端反馈结果 
if(ShuZiChaoZuo(NumBer,number).equals("恭喜您猜对了！")){ 
socket[i].close(); //关闭本次连接 
} 
if(j==quess) 
netOut.writeUTF("这个随机数是："+NumBer); 
} 


} catch (IOException e) { 
// TODO Auto-generated catch block 
System.out.println("连接断开！"); 
} 

} 
/** 
* 数字操作，即两个数比较,该方法需要添加两个整型参数，第一个是被比较的数，第二个是比较的数。 
*/ 
public String ShuZiChaoZuo(int random,int x){ 
String str=""; 
if(x>numend||x<numbegin){ 
str="您猜的数字超出了范围，请输入"+numbegin+"-"+numend; 
}else{ 
if(random==x){ 
str="恭喜您猜对了！"; 
}else 
if(random>x){ 
str="您猜的数字过小！请重新输入！"; 
}else 
str="您猜的数字过大！请重新输入！"; 
} 
return str; 

} 
/** 
* 生成随机数字 
*/ 
public int RomDom(int begin,int end){ 
int romdom=0; 
numbegin=begin; 
numend=end; 
Random random=new Random(); 
romdom=random.nextInt(end-begin)+begin; 
return romdom; 
} 

} 