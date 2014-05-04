package com.olimposystems.grandespensadores.jesus.type;

public enum GrandesPensadores {

	versao("1.0"),
	build("2014-05-04"),
	autor("Jesus Cristo"),
	database("jesus"),
	categoriaLog("gp"),
	arquivoDeconfiguracao("config.gp"),
	horaDeNotificacao("9"),
	executarAlarme("EXECUTAR_ALARME"),
	debugAtivo("n");

	GrandesPensadores(String value) {
		this.value = value;
	}

	private String value;

	public String value() {
		return value;
	}

}
