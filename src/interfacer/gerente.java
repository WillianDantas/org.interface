package interfacer;

public class gerente implements funcionario{
	private int conta;
	
	public gerente autenticavel(gerente x) {
		this.conta = 10;
		return x;
	}
	
	public int conta(){
		return this.conta +=10;
	}
	
}
