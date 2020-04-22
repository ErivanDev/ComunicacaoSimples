import java.rmi.Remote;
import java.rmi.RemoteException;

//Interface ICalculadora com as assinaturas das funcoes de operacoes
//Estende da interface Remote, necessario para que 
//seja criado objetos remotos a partir dessa classe (ICalculadora)
public interface ICalculadora extends Remote {
	public double soma(double a, double b) throws RemoteException;
	public double subtracao(double a, double b) throws RemoteException;
	public double multiplicacao(double a, double b) throws RemoteException;
	public double divisao(double a, double b) throws RemoteException;
}
