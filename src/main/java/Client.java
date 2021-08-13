import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8088);
            System.out.println("Connect to server successfully !");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            new Thread(){
                @Override
                public void run() {
                    String rs = null;
                    try {
                        while ((rs = bufferedReader.readLine()) != null) {
                            System.out.println(rs);
                            if (rs.equalsIgnoreCase("quit")) {
                                System.out.println("Server disconnect !");
                                break;
                            }
                        }
                    } catch (Exception exception) {

                    }
                }
            }.start();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Scanner scanner = new Scanner(System.in);
            while (true){
                System.out.println("Enter message : ");
                String msg = scanner.nextLine();
                bufferedWriter.write(msg);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                if (msg.equalsIgnoreCase("quit")){
                    break;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
