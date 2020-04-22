import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

//Classe Calculadora do cliente
public class CalculadoraCliente {
	
	public static void main(String[] args) {
		Registry reg = null;
		ICalculadora calc;		
		try {
			//Retorna a instancia do objeto remoto Registry para a variavel reg
			reg = LocateRegistry.getRegistry(1099);
		
			//Retorna a referencia remota do objeto calculadora pelo nome "calculadora"
			calc = (ICalculadora) reg.lookup("calculadora");
			
			//Executa a funcao soma e printa o seu resultado
			System.out.println("3 + 2:");
			System.out.println(calc.soma(3,2));
			
			//Executa a funcao subtracao e printa o seu resultado
			System.out.println("3 - 2:");
			System.out.println(calc.subtracao(3,2));
			
			//Executa a funcao multiplicacao e printa o seu resultado
			System.out.println("3 * 2:");
			System.out.println(calc.multiplicacao(3,2));
			
			//Executa a funcao divisao e printa o seu resultado
			System.out.println("3 / 2:");
			System.out.println(calc.divisao(3,2));
		} catch (RemoteException | NotBoundException e) {
				System.out.println(e);
				System.exit(0);
		}
	}		

}
