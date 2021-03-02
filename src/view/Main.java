package view;

import controller.RedesController;

public class Main
{

	public static void main(String[] args)
	{
		RedesController redController = new RedesController();

		String oS = redController.verificaOS();

		redController.ip(oS);

		redController.ping(oS);
	}
}
