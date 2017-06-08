/*
* SimpleClient.java: 送ったメッセージをそのまま受信するクライアント
*/
import java.io.*; import java.net.*;
public class PhttpClient {

        public static void main (String args[]) {
          
            int PORT = Integer.parseInt(args[1]);

            try {
                // ソケットを作成
                Socket socket = new Socket(args[0], PORT);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
	    
                // サーバにメッセージを送る
                // HTTPの仕様通りの文字列を送れば
                // あとはwebサーバがよしなにしてくれる
                writer.writeBytes("GET / HTTP1.1 \r\n Host:"+args[0] + args[1]+ "\r\n\r\n");
	    
                //サーバからメッセージを受け取る
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("Server: " + line);
                }
                writer.close();
                reader.close();
                socket.close();
            } catch (UnknownHostException e) {
                System.err.println("Host not found");
                System.exit(-1);
            } catch (SocketException e) {
                System.err.println("Socket error");
                System.exit(-1);
            } catch (IOException e) {
                System.err.println("IO error");
                System.exit(-1);
            }
        }
}
