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

	public String verificaOs()
	{
		String oS = System.getProperty("os.name");

		return oS;
	}

	public String ip(String oS)	
	{
		String ipconfig = " ";

		if (oS.contains("Windows"))
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
		else if (oS.contains("Linux"))
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
			ipconfig = "Sistema Operacional não identificado";
		}

		return ipconfig;
	}

	public void ping(String oS)
	{
		double soma = 0;
		double media = 0f;
		int c = 0;

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
				
				System.out.println("'pingando' o Google...");

				while (linha != null)
				{
					if (linha.contains("tempo"))
					{
						int inicioValor = linha.indexOf("o="); 
						int finalValor = linha.indexOf("ms");

						soma += Double.parseDouble(linha.substring(inicioValor + 2, finalValor));
						c ++;
					}

					linha = buffer.readLine();
				}

				media = soma / c;

				System.out.println("O tempo médio de PING, considerando "+ c +" pacotes enviados"
						+ " para www.google.com.br foi de: " + media + "ms");

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

				System.out.println("'pingando' o Google...");
				
				while (linha != null)
				{
					if (linha.contains("seq"))
					{
						int inicioValor = linha.indexOf("e="); 
						int finalValor = linha.indexOf(" ms");

						soma += Double.parseDouble(linha.substring(inicioValor + 2, finalValor));
						c ++;
					}

					linha = buffer.readLine();
				}

				media = soma / c;

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