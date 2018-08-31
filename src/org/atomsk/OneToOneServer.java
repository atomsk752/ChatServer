package org.atomsk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class OneToOneServer {

    public static void main(String[] args) {


        System.out.println("SERVER RUN>>>");
        try(ServerSocket serverSocket = new ServerSocket(7777);
            Socket socket = serverSocket.accept();
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream din = new DataInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);
        ){
            System.out.println("한판뜨자");

            new Thread(()->{ //중간에 실하나 더 만들어서 여기다 배분
                try {
                    while(true) {
                        //read and then write 여기서 블로킹
                        String otherMsg = din.readUTF();
                        System.out.println(otherMsg);
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }).start();


            while (true) {


                String myMsg = scanner.nextLine();
                dos.writeUTF(myMsg);
                dos.flush();


            }



        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


}
