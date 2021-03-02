package view;

import java.util.Scanner;
import controller.RedesController;

public class Main
{

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner (System.in);

		RedesController redController = new RedesController();

		int opc = 0;

		String oS = redController.verificaOS();

		while (opc != 9) {

			System.out.println("Opções\n 1 - Nome do adaptador e IPV4\n 2 - Média de PING com Google\n 9 - Finalizar");
			opc = scanner.nextInt();

			switch (opc)
			{
			case 1:
				System.out.println(redController.ip(oS));
				break;

			case 2:
				redController.ping(oS);
				break;

			case 9:
				System.out.println("Finalizando");
				break;

			default:
				System.out.println("Opção Invalida");
				break;
			}
		}
		scanner.close();
	}
}