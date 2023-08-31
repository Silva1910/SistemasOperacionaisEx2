package view;

import javax.swing.JOptionPane;
import controller.KillController;

public class Main {

	public static void main(String[] args) {

		String menu = "----------------------------------------------------------------------------- \n"
				+ "Selecione uma opção:\n" + "1. DIGITE 1 PARA SABER SEU SISTEMA OPERACIONAL \n"
				+ "----------------------------------------------------------------------------- \n"
				+ "2. DIGITE 2 PARA SABER OS PROCESSOS ATIVOS \n"
				+ "----------------------------------------------------------------------------- \n"
				+ "3. MATAR PROCESSO POR PID   \n "
				+ "----------------------------------------------------------------------------- \n"
				+ "4. MATAR PROCESSO POR NOME   \n "
				+ "----------------------------------------------------------------------------- \n" + "0. Sair\n"
				+ "----------------------------------------------------------------------------- \n";

		KillController kill = new KillController();

		int opc;
		String temp;
		do {
			opc = Integer.parseInt(JOptionPane.showInputDialog(null, menu));
			switch (opc) {
			case 1:
				JOptionPane.showMessageDialog(null,
						" O NOME DO SISTEMA OPERACIONAL E ==>: " + getOperatingSystemName());
				break;
			case 2:
				JOptionPane.showMessageDialog(null, " A SAIDA SERA NO CONSOLE DE UMA OLHADA ");
				kill.ListaProcessos();
				break;
			case 3:
				int pid = Integer.parseInt(JOptionPane.showInputDialog(null, "DIGITE O PID PROCESSO QUE QUER MATAR"));
				kill.MataProcessoPID(pid);
				break;
			case 4:
				String nome = JOptionPane.showInputDialog(null, "DIGITE O NOME DO PROCESSO QUE QUER MATAR");
				kill.MataProcessoNome(nome);
				break;
			case 0:
				JOptionPane.showMessageDialog(null, "Este exercicio vale 1 ponto na media.");
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opção inválida. Escolha novamente.");
			}

			if (opc != 0) {
				int voltarAoMenu = JOptionPane.showConfirmDialog(null, "Deseja voltar ao menu anterior?",
						"Voltar ao Menu", JOptionPane.YES_NO_OPTION);
				if (voltarAoMenu != JOptionPane.YES_OPTION) {
					opc = 0; // Sair do loop se não quiser voltar ao menu
				}
			}
		} while (opc != 0);
	}

	private static String getOperatingSystemName() {
		String osName = System.getProperty("os.name");
		return osName;
	}
}
