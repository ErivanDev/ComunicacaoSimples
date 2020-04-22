import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

//Classe calculadora do cliente usando HTTP
public class CalculadoraClientHTTP {

	public static void main(String[] args) {
		//Executa todas as operacoes
		chamar(15,15,1);
		chamar(15,15,2);
		chamar(15,15,3);
		chamar(15,15,4);
	}
	
	static void chamar( double oper1, double oper2, double operacao ) {
		String result = "";
		try {
			//Objeto para representar a localizacao de um recurso na web
			//nesse caso do servidor PHP
			URL url = new URL("https://double-nirvana-273602.appspot.com/?hl=pt-BR");
			
			//Cria uma conexao para o servidor PHP
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			
			//Define o read timeout para 10000 milisegundos, 
			//ou seja o tempo maximo apos estabelecer a conexao e retornar a resposta
			conn.setReadTimeout(10000);
			
			//Define o connect timeout para 15000 milisegundos,
			//ou seja o tempo maximo para que se estabeleza a conexao é 15 segundos
			conn.setConnectTimeout(15000);
			
			//Define o metodo de requisicao para POST, 
			//sendo os parametros enviados pelo corpo da mensagem
			conn.setRequestMethod("POST");
			
			//Usa a conexao url para aceitar inputs
			conn.setDoInput(true);
			
			//Usa a conexao url para aceitar output
			conn.setDoOutput(true);

			//Pega uma instancia do fluxo de saida da conexao
			OutputStream os = conn.getOutputStream();
			
			//Objeto para escrever os dados utilizando um buffer de memoria, para depois passar para o fluxo da conexao 
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			
			//Escreve uma String contendo dois valores e um operador
			writer.write("oper1="+ oper1 + "&oper2=" + oper2 + "&operacao=" + operacao + ""); // 1-somar 2-subtrair 3-dividir 4-multiplicar
			
			//Libera o fluxo
			writer.flush();
			
			//Fecha o fluxo
			writer.close();
			
			//Fecha o fluxo da conexao
			os.close();

			//Pega o codigo da resposta
			int responseCode = conn.getResponseCode();

			//Verifica se deu certo a conexao
			if (responseCode == HttpsURLConnection.HTTP_OK) {
				//Instanceia um leitor de buffer, usando o fluxo de input da conexao
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
				
				//Instanceia um objeto para amarzenar uma sequencia de caracteres
				StringBuilder response = new StringBuilder();
				
				//Adiciona cada linha da resposta do servidor ao objeto "response"
				String responseLine = null;
				while ((responseLine = br.readLine()) != null) {
					response.append(responseLine.trim());
				}
				
				//Converte o objeto que armazenou as linhas em uma String
				result = response.toString();
				
				//Printa a String com o resultado
				System.out.println("Resposta do Servidor PHP = " + result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
