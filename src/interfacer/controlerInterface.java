package interfacer;

import java.util.ArrayList;
import java.util.List;


public class controlerInterface {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		gerente g = new gerente();
		gerente b = new gerente();
		gerente c = new gerente();
		c.autenticavel(c);
		System.out.println(g.autenticavel(g).conta());
		List <gerente> i = new ArrayList<gerente>();
		i.add(g);
		i.add(b);
		i.add(c);
		Banco banco = new Banco();
		banco.ListaGerente = i;
		int count = 1;
		for ( gerente a : banco.ListaGerente ) {
			System.out.println(count + " - " +a.conta());
			count++;
		}


	}

}
