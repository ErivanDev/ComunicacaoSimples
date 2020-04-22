import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CalculadoraClientSocket {
	static String[] operacoes = { "+", "-", "*", "/" };

	public static void main(String[] args) {
		//1-somar 2-subtrair 3-dividir 4-multiplicar
		//Executa todas as operacoes
		chamar( 1, 10, 20);
		chamar( 2, 10, 20);
		chamar( 3, 10, 20);
		chamar( 4, 10, 20);
	}
	
	//Funcao "chamar" para facilitar a chamada das operacoes
	//passando como parametro a operacao e os dois valores
	public static void chamar( int operacao, double oper1, double oper2 ) {
		String result="";
        try {
        	//Conecta com o servidor usando socket
            Socket clientSocket = new Socket("localhost", 9090);
            
            //Pega o fluxo de saida dos seus dados
            DataOutputStream socketSaidaServer = new DataOutputStream(clientSocket.getOutputStream());
            
            //Escreve nesse fluxo o dado da operacao em Bytes
            socketSaidaServer.writeBytes(operacao + "\n");
            
            //Escreve nesse fluxo o dado da primeira variavel
            socketSaidaServer.writeBytes(oper1 + "\n");
            
            //Escreve nesse fluxo o dado da segunda variavel
            socketSaidaServer.writeBytes(oper2 + "\n");
            
            //Libera o fluxo
            socketSaidaServer.flush();

            //Intancia um leitor de buffer de memoria para pegar o fluxo de entrada 
            BufferedReader messageFromServer = new BufferedReader
                    (new InputStreamReader(clientSocket.getInputStream()));
            
            //Pega a String com o resultado
            result = messageFromServer.readLine();
            
            //Printa o resultado
            System.out.println("10 " + operacoes[operacao-1] + " 20 = " + result);
            
            //Fecha a conexao com o socket
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
