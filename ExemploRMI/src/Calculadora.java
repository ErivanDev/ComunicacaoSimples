import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

//Classe Calculadora (do servidor) que implementa a interface ICalculadora
public class Calculadora  implements ICalculadora {
	//Variavel que conta a quantidades de chamadas ao servidor
	private static int chamadas = 0;

	//Funcao soma
	public double soma(double a, double b) throws RemoteException {
		System.out.println("Método soma chamado " + chamadas++);
		return a + b;
	}
	
	//Funcao subtracao
	public double subtracao(double a, double b) throws RemoteException {
		System.out.println("Método subtracao chamado " + chamadas++);
		return a - b;
	}
	
	//Funcao multiplicacao
	public double multiplicacao(double a, double b) throws RemoteException {
		System.out.println("Método multiplicacao chamado " + chamadas++);
		return a * b;
	}
	
	//Funcao divisao
	public double divisao(double a, double b) throws RemoteException {
		System.out.println("Método soma chamado " + chamadas++);
		return a / b;
	}

	//Funcao principal
	public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException  {
		//Instanceia a classe Calculadora
		Calculadora calculadora = new Calculadora();		
		
		Registry reg = null;
		
		//Instancia o objeto do servidor e a sua stub 
		ICalculadora stub = (ICalculadora) UnicastRemoteObject.exportObject(calculadora, 1100);
		
		try {
			//Se uma instancia do Registry ainda nao foi criado
			//ele é criado para receber requisicoes na porta 1099
			reg = LocateRegistry.createRegistry(1099);
			System.out.println("Registry Created");
		} catch (Exception e) {
			try {
				//Se uma instancia do Registry ja tenha sido criado 
				//ela é pega e adicionada a variavel reg
				reg = LocateRegistry.getRegistry(1099);
			} catch (Exception e1) {
				System.exit(0);
			}
		}

		//Faz ou refaz uma ligacao usando o nome "calculadora" para a referencia remota fornecida (stub)
		reg.rebind("calculadora", stub);
	}
}
