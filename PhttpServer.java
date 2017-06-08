/*
* SimpleServer.java: 受け取ったメッセージをそのまま返すサーバ
*/
import java.io.*; 
import java.net.*;
import java.util.Date;
public class PhttpServer {
        public static void main (String args[]) {
            int PORT = Integer.parseInt(args[0]); 
            Date date = new Date();
            try {
                // ソケットを生成
                ServerSocket serverSocket = new ServerSocket(PORT);
                // 入出力ストリームを用意し，クライアントからの要求を待つ
                Socket socket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintStream writer = new PrintStream(socket.getOutputStream());

                String line = reader.readLine();
                StringBuilder header = new StringBuilder();
                // データを読み込み，そのまま返す
                
               while (line != null && !line.isEmpty()){
                  header.append(line + "\n");
                  line = reader.readLine();
                  System.out.println(header);
               }


                writer.println("Hello!");
                writer.close();
                reader.close();
                socket.close();
                serverSocket.close();
            } catch (SocketException e) {
                System.err.println("Socket error");
                System.exit(-1);
            } catch (IOException e) {
                System.err.println("IO error");
                System.exit(-1);
            }
        }
}
