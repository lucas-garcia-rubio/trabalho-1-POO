//trabalho POO
import java.util.Scanner;
import java.io.Serializable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class email implements Serializable
{
	private String de;
	private String para;
	private String assunto;
	private String mensagem;

	//Método Construtor

	public void email(String de, String para, String assunto, String mensagem)
	{
		this.de = de;
		this.para = para;
		this.assunto = assunto;
		this.mensagem = mensagem;
	}

	//Método getters e setters

	public void setDe(String de)
	{
		this.de = de;
	}

	public void setPara(String para)
	{
		this.para = para;
	}

	public void setAssunto(String assunto)
	{
		this.assunto = assunto;
	}

	public void setMensagem(String mensagem)
	{
		this.mensagem = mensagem;
	}

	public String getDe()
	{
		return de;
	}

	public String getPara()
	{
		return para;
	}

	public String getAssunto()
	{
		return assunto;
	}

	public String getMensagem()
	{
		return mensagem;
	}

	//Método principal
	public void imprimirDados()
	{
		System.out.println("De: " + getDe());
		System.out.println("Para: " + getPara());
		System.out.println("Assunto: " + getAssunto());
		System.out.println("Mensagem: " + getMensagem());
	}
}

class outsee
{
	public static void main(String args[])
	{
		//criando vetor de emails
		email[] vetor = new email[10];

		vetor[0] = new email();
		vetor[1] = new email();
		vetor[2] = new email();
		vetor[3] = new email();
		vetor[4] = new email();
		vetor[5] = new email();
		vetor[6] = new email();
		vetor[7] = new email();
		vetor[8] = new email();
		vetor[9] = new email();

		//variável que conta o número de email dentro do vetor: vai de 1 a 10
		int n=0;

		//variável que lê informações do teclado
		Scanner teclado = new Scanner(System.in);

		//criando menu
		//--------------------------Início Menu-------------------------------//
		int opcao = 1;

		System.out.printf("Usuário: ");
		String user = teclado.nextLine();

		while(opcao == 1)
		{
			int opcao2;

			System.out.println("1 - Escrever um email");
			System.out.println("2 - Listar minhas correspondências");
			System.out.println("3 - Ler email");
			System.out.println("4 - Gravar emails em um arquivo");
			System.out.println("5 - Ler emails de um arquivo");
			System.out.println("6 - Fazer logout");

			opcao2 = teclado.nextInt();

			while(opcao2 != 6)
			{
				if(opcao2 == 1)
				{
					if(n == 10)
						System.out.println("Limite máximo de emails atingido");
					else
					{
						vetor[n].setDe(user);

						teclado.nextLine();
						System.out.println("Para: ");
						String p = teclado.nextLine();
						vetor[n].setPara(p);


						System.out.println("Assunto: ");
						String a = teclado.nextLine();
						vetor[n].setAssunto(a);

						System.out.println("Mensagem: ");
						String m = teclado.nextLine();
						vetor[n].setMensagem(m);

						n++;
					}
				}

				if(opcao2 == 2)
				{
					int i; //vai percorrer os elementos do vetor
					boolean entrou = false;
					for(i=0; i<n; i++)
						if(vetor[i].getPara().compareTo(user) == 0)
						{
							System.out.println("De: " + vetor[i].getDe());
							System.out.println("Assunto: " + vetor[i].getAssunto());
							entrou = true;
						}

					if(entrou == false)
						System.out.println("Não há emails para você");
				}

				if(opcao2 == 3)
				{
					teclado.nextLine();
					System.out.printf("Ler email de: ");
					String remetente = teclado.nextLine(); //variável para o usuário escolher o email de quem ele quer ler

					int i;
					boolean entrou = false;

					for(i=0; i<n; i++)
						if(vetor[i].getDe().compareTo(remetente) == 0 && vetor[i].getPara().compareTo(user) == 0)
						{
							System.out.println("Mensagem de " + vetor[i].getDe() + ":");
							System.out.println(vetor[i].getMensagem());
							entrou = true;
						}

					if(entrou == false)
						System.out.println("Não há emails desse usuário para você");
				}

				if(opcao2 == 4)
				{
					File arquivo = new File("vetor.dat");
					try
					{
						FileOutputStream fout = new FileOutputStream(arquivo);
						ObjectOutputStream oos = new ObjectOutputStream(fout);

						oos.writeObject(vetor);

						oos.flush();
						oos.close();
						fout.close();
					}
					catch (Exception ex)
					{
						System.err.println("Erro: " + ex.toString());
					}
				}

				if(opcao2 == 5)
				{
					File arquivo = new File ("vetor.dat");
					try
					{
						FileInputStream fin = new FileInputStream(arquivo);
						ObjectInputStream oin = new ObjectInputStream(fin);

						email[] vetorArq = (email[]) oin.readObject();
						oin.close();
						fin.close();

						for(email p : vetorArq)
							if(p.getDe() != null)
								p.imprimirDados();
					}
					catch(Exception ex)
					{
						System.err.println("Erro: " + ex.toString());
					}
				}

				System.out.println("1 - Escrever um email");
				System.out.println("2 - Listar minhas correspondências");
				System.out.println("3 - Ler email");
				System.out.println("4 - Gravar emails em um arquivo");
				System.out.println("5 - Ler emails de um arquivo");
				System.out.println("6 - Fazer logout");

				opcao2 = teclado.nextInt();
			}

			teclado.nextLine();
			System.out.printf("Usuário: ");
			user = teclado.nextLine();
		}
		//----------------------------Fim Menu--------------------------------//
	}
}