//Classe Calculadora com as funcoes das operacoes basicas
public class Calculadora {
    public String sayHello(String nome, String sobrenome) {
        return "Fala "+ nome + " " + sobrenome;
    }
    
    //Funcao de soma que retorna um double como resultado da operacao
    public double soma(double a, double b) {
		System.out.println("Método soma chamado ");
		return a + b;
	}
    
    //Funcao de subtracao que retorna um double como resultado da operacao
	public double subtracao(double a, double b) {
		System.out.println("Método subtracao chamado ");
		return a - b;
	}
	
	//Funcao de multiplicacao que retorna um double como resultado da operacao
	public double multiplicacao(double a, double b) {
		System.out.println("Método multiplicacao chamado ");
		return a * b;
	}
	
	//Funcao de divisao que retorna um double como resultado da operacao
	public double divisao(double a, double b) {
		System.out.println("Método soma chamado ");
		return a / b;
	}
}