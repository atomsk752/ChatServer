package org.atomsk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

//채팅 프로그램을 OOP로 만들면 어차피 코드가 같기때문에 기능으로 구분이 가능하지
//서버 읽기 쓰기, 클라이언트 읽기 쓰기 -> 읽기 쓰기

public class OneToOneClient {

    public static void main(String[] args) {

        System.out.println("연결 고다 고");

        try(
                Socket socket = new Socket("10.10.10.94",7777);
                DataInputStream din = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                Scanner scanner = new Scanner(System.in);

                ){

            new Thread(()->{
                try {
                    while (true) {
                        String myMsg = scanner.nextLine();
                        dos.writeUTF(myMsg);
                    }

                }catch (Exception e){
                    System.out.println(e.getMessage());
                }


            }).start();



            while (true) {
                String otherMsg = din.readUTF();
                System.out.println(otherMsg);

            }

        }catch (Exception e){

            System.out.println(e.getMessage());
        }//end catch

    }

}
