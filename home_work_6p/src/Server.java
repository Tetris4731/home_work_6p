import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {


    public static void main( String[] args ) {
        Socket clientSocket = null;
        Scanner scanner = new Scanner( System.in );

        try {
            ServerSocket serverSocket = new ServerSocket( 60080 );
            System.out.println( "Server start working" );
            clientSocket = serverSocket.accept();
            DataInputStream inputStream = new DataInputStream( clientSocket.getInputStream() );
            DataOutputStream outputStream = new DataOutputStream( clientSocket.getOutputStream() );

            Thread threadReader = new Thread( () -> {
                while (true) {
                    try {
                        outputStream.writeUTF( scanner.nextLine() );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } );
            threadReader.setDaemon( true );
            threadReader.start();
            while (true) {
                String str = inputStream.readUTF();
                if (str.equals( "quite" )) {
                    System.out.println( "Client disconnect" );
                    outputStream.writeUTF( "quite" );
                    break;
                }else {
                    System.out.println("Client " + str );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                clientSocket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}




