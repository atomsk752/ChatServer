package org.atomsk.multi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class ServerAgent extends Thread {

    private Socket socket;
    private DataInputStream din;
    private DataOutputStream dos;
    private String ip;

    public ServerAgent(Socket socket) throws Exception{
        this.socket = socket;
        this.din = new DataInputStream(socket.getInputStream());
        this.dos = new DataOutputStream(socket.getOutputStream());
        this.ip = socket.getInetAddress().getHostAddress();

    }

    @Override
    public  void run(){
        try{
            read();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    //쓰레드는 메인에서 이미 whi;e 돌리고 있기 때문에
    public void read() throws Exception{
        while (true){
            String msg = din.readUTF();
//            System.out.println(msg);
            String [] arr = msg.split(":");
            System.out.println(Arrays.toString(arr));

            String oper = arr[0];

            switch (oper) {
                case "A":
                    MultiServer2.ONE.broadcast(ip + ":"+msg);
                    break;
                case "S":
                    MultiServer2.ONE.whisper(ip, arr[1], arr[2]);

            }


        }
    }

    public void write(String msg) throws Exception{

        dos.writeUTF(msg);

    }
}
