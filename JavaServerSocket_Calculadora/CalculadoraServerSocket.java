import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculadoraServerSocket {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket welcomeSocket;
		DataOutputStream socketOutput;
		DataInputStream socketInput;
		BufferedReader socketEntrada;

		//Intanceia um objeto calculadora
		Calculadora calc = new Calculadora();

		try {
			//Inicia um servidor socket na porta 9090
			welcomeSocket = new ServerSocket(9090);
			int i = 0; // numero de clientes

			System.out.println("Servidor no ar");
			while (true) {
				//Aceita conexoes sockets
				Socket connectionSocket = welcomeSocket.accept();
				i++;
				System.out.println("Nova conexao");

				//Recebe dados do cliente
				socketEntrada = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				
				//Quarda a operacao, convertendo de String para inteiro
				int operacao = Integer.parseInt( socketEntrada.readLine() );
				
				//Quarda a primeira varivel, convertendo de String para Double
				Double oper1 = Double.parseDouble( socketEntrada.readLine() );
				
				//Quarda a segunda varivel, convertendo de String para Double
				Double oper2 = Double.parseDouble( socketEntrada.readLine() );

				String result = "";
				
				//Verifica a operacao
				switch (operacao) {
					case 1: 
						//Executa a operacao de soma caso seja a solicitada
						result += calc.soma( oper1, oper2 );
					break;
					case 2: 
						//Executa a operacao de subtracao caso seja a solicitada
						result += calc.subtracao( oper1, oper2 );
					break;
					case 3: 
						//Executa a operacao de multiplicacao caso seja a solicitada
						result += calc.multiplicacao( oper1, oper2 );
					break;
					case 4: 
						//Executa a operacao de divisao caso seja a solicitada
						result += calc.divisao( oper1, oper2 );
					break;
				}

				//Pega o fluxo de saida de dados para o cliente
				socketOutput = new DataOutputStream(connectionSocket.getOutputStream());
				
				//Escreve no fluxo o resultado em Bytes
				socketOutput.writeBytes(result + '\n');
				
				//Printa o resultado no lado do servidor
				System.out.println(result);
				
				//Libera o fluxo
				socketOutput.flush();
				
				//Fecha a conexao com esse cliente
				socketOutput.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
