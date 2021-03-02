package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	public RedesController()
	{
		super();
	}

	public String verificaOS()
	{
		String os = System.getProperty("os.name");

		return os;
	}

	public void ip(String os)	
	{
		String ipconfig = " ";

		if (os.contains("Windows"))
		{
			try
			{
				Process p = Runtime.getRuntime().exec("ipconfig");
				InputStream fluxo = p.getInputStream();


				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);


				String linha = buffer.readLine();
				System.out.println(linha);

				while (linha != null)
				{
					if (linha.contains("Ethernet:") || linha.contains("VirtualBox") || linha.contains("Windows"))
					{
						ipconfig += linha + "\n";
					}

					if (linha.contains("IPv4"))
					{
						ipconfig += linha + "\n";						
					}
					linha = buffer.readLine();
				}

				buffer.close();
				leitor.close();
				fluxo.close();

			}
			catch(IOException err)
			{
				ipconfig = err.getMessage();
			}
		}
		else if (os.contains("Linux"))
		{
			try
			{
				Process p = Runtime.getRuntime().exec("ifconfig");
				InputStream fluxo = p.getInputStream();


				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);


				String linha = buffer.readLine();

				while (linha != null)
				{
					if (linha.contains("flags:"))
					{
						ipconfig += linha + "\n";
					}

					if (linha.contains("netmask"))
					{
						ipconfig += linha + "\n";						
					}
					System.out.println(linha);
					linha = buffer.readLine();
				}

				buffer.close();
				leitor.close();
				fluxo.close();

			}
			catch(IOException err)
			{
				ipconfig = err.getMessage();
			}
		}
		else
		{
			ipconfig = "OS não identificado";
		}

		System.out.println(ipconfig);
	}

	public void ping(String oS)
	{
		double soma = 0;
		double media = 0f;

		if (oS.contains("Windows"))
		{
			try
			{
				Process p = Runtime.getRuntime().exec("ping -n 10 www.google.com.br");
				InputStream fluxo = p.getInputStream();


				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);


				String linha = buffer.readLine();
				System.out.println(linha);

				while (linha != null)
				{
					if (linha.contains("tempo"))
					{
						int inicioValor = linha.indexOf("o="); 
						int finalValor = linha.indexOf("ms");

						soma += Double.parseDouble(linha.substring(inicioValor + 2, finalValor));
					}

					linha = buffer.readLine();
				}

				media = soma / 10;

				System.out.println(media);

				buffer.close();
				leitor.close();
				fluxo.close();

			}
			catch(IOException err)
			{

				System.out.println(err.getMessage());
			}
		}

		else if (oS.contains("Linux"))
		{
			try
			{
				Process p = Runtime.getRuntime().exec("ping -c 10 www.google.com.br");
				InputStream fluxo = p.getInputStream();


				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);


				String linha = buffer.readLine();
				System.out.println(linha);

				while (linha != null)
				{
					if (linha.contains("seq"))
					{
						int inicioValor = linha.indexOf("e="); 
						int finalValor = linha.indexOf(" ms");

						soma += Double.parseDouble(linha.substring(inicioValor + 2, finalValor));
					}

					linha = buffer.readLine();
				}

				media = soma / 10;

				System.out.println(media);

				buffer.close();
				leitor.close();
				fluxo.close();

			}
			catch(IOException err)
			{

				System.out.println(err.getMessage());
			}
		}
		else
		{
			System.out.println("OS não identificado");
		}

	}
}