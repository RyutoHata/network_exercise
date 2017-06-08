/*
* SimpleServer.java: 受け取ったメッセージをそのまま返すサーバ
*/
import java.io.*; 
import java.net.*;
import java.util.Date;
public class TimeServer {
        public static void main (String args[]) {
            int PORT = Integer.parseInt(args[0]); 
            try {
                // ソケットを生成
                ServerSocket serverSocket = new ServerSocket(PORT);
                // 入出力ストリームを用意し，クライアントからの要求を待つ
                Socket socket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintStream writer = new PrintStream(socket.getOutputStream());

                // 現在時刻を取得し、クライアントに返す
                while (true) {
                  String line = reader.readLine();
                  //現在時刻の取得
                  Date date = new Date();
                  if(line == null)
                    break;
                  //Date型から文字列型へ変換して送る
                  System.out.println(date.toString());
                  writer.println(date.toString());
                }
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
