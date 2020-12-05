import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ufpa.br.model.Pessoa;
import com.ufpa.br.model.SetorAtividade;

public class CamadaPersistencia {
	
	public static void main(String[] args) {		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hemopa");
		
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		//Declarando os repositórios
		SetorAtividades setorAtividades = new SetorAtividades(em);
		Pessoas pessoas = new Pessoas(em);
		
		//Buscando as informações do banco
		List<SetorAtividade> listaDeSetorAtividades =  setorAtividades.pesquisar("");
		List<Pessoa> listaDeEmpresas = pessoas.pesquisar("");
		System.out.println(listaDeEmpresas);
		
		//Criando uma empresa
		Pessoa pessoa = new Pessoa();		
		pessoa.setNome("João");
		pessoa.setCpf("41.952.519/0001-57");
		pessoa.setSobrenome("da Silva");
		pessoa.setDataNascimento(new Date());
		pessoa.setSetorAtividade(listaDeSetorAtividades.get(0));
		
		//Salvando a empresa
		pessoas.guardar(pessoa);
		
		em.getTransaction().commit();
		
		//Verificando se a inserção funcionou
		List<Pessoa> listaDePessoas2 = pessoas.pesquisar("");
		System.out.println(listaDePessoas2);
		
		
		em.close();
		emf.close();
	}

}
