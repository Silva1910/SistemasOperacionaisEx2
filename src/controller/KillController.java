package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class KillController {

	public static void ListaProcessos() {

		String os = System.getProperty("os.name").toLowerCase();

		if (os.contains("win")) {
			ListaProcessosWindows();
		} else if (os.contains("nix") || os.contains("nux")) {
			ListaProcessosUnix();
		} else {
			JOptionPane.showMessageDialog(null, "Sistema operacional não suportado.");
		}

	}

	private static void ListaProcessosWindows() {
		try {
			String command = "TASKLIST /FO TABLE";
			Process process = Runtime.getRuntime().exec(command);

			InputStream inputStream = process.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void ListaProcessosUnix() {
		try {
			String command = "ps -ef";
			Process process = Runtime.getRuntime().exec(command);

			InputStream inputStream = process.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int MataProcessoPID(int pid) {

		String os = System.getProperty("os.name").toLowerCase();

		if (os.contains("win")) {
			MataProcessoWindows(pid);
		} else if (os.contains("nix") || os.contains("nux")) {
			MataProcessoUnix(pid);
		} else {
			JOptionPane.showMessageDialog(null, "Sistema operacional não suportado.");
		}
		return 0;
	}

	private static void MataProcessoWindows(int pid) {
		try {
			String command = " TASKKILL /PID " + pid;
			Process process = Runtime.getRuntime().exec(command);

			int exitCode = process.waitFor();
			if (exitCode == 0) {
				JOptionPane.showMessageDialog(null, "Processo com PID " + pid + " foi encerrado.");
			} else {
				JOptionPane.showMessageDialog(null, "Não foi possível encerrar o processo com PID " + pid + ".");
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void MataProcessoUnix(int pid) {
		try {
			String command = "kill -9 " + pid;
			Process process = Runtime.getRuntime().exec(command);

			int exitCode = process.waitFor();
			if (exitCode == 0) {
				JOptionPane.showMessageDialog(null, "Processo com PID " + pid + " foi encerrado.");
			} else {
				JOptionPane.showMessageDialog(null, "Não foi possível encerrar o processo com PID " + pid + ".");
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static int MataProcessoNome(String nome) {

		String os = System.getProperty("os.name").toLowerCase();

		if (os.contains("win")) {
			MataProcessoWindowsNome(nome);
		} else if (os.contains("nix") || os.contains("nux")) {
			MataProcessoUnixNome(nome);
		} else {
			JOptionPane.showMessageDialog(null, "Sistema operacional não suportado.");
		}
		return 0;
	}

	private static void MataProcessoWindowsNome(String nome) {
		try {
			String command = "TASKKILL /IM " + nome;
			Process process = Runtime.getRuntime().exec(command);

			int exitCode = process.waitFor();
			if (exitCode == 0) {
				JOptionPane.showMessageDialog(null, "Processo com PID " + nome + " foi encerrado.");
			} else {
				JOptionPane.showMessageDialog(null, "Não foi possível encerrar o processo com PID " + nome + ".");
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void MataProcessoUnixNome(String nome) {
		try {
			String command = "pkill -f " + nome;
			Process process = Runtime.getRuntime().exec(command);

			int exitCode = process.waitFor();
			if (exitCode == 0) {
				JOptionPane.showMessageDialog(null, "Processo com PID " + nome + " foi encerrado.");
			} else {
				JOptionPane.showMessageDialog(null, "Não foi possível encerrar o processo com PID " + nome + ".");
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
