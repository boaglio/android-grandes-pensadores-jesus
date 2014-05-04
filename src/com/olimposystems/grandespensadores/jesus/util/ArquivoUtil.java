package com.olimposystems.grandespensadores.jesus.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.olimposystems.grandespensadores.jesus.domain.Config;
import com.olimposystems.grandespensadores.jesus.type.GrandesPensadores;

public class ArquivoUtil {

	private final String arquivo;
	private Activity activity;
	private Context context;
	private Config config;
	private static String caracterSplit = ";";

	public ArquivoUtil(String nomeDoArquivo,Activity activity,Config config) {
		arquivo = nomeDoArquivo;
		this.activity = activity;
		this.config = config;
	}

	public ArquivoUtil(String nomeDoArquivo,Activity activity) {
		arquivo = nomeDoArquivo;
		this.activity = activity;
	}

	public ArquivoUtil(String nomeDoArquivo,Context context) {
		arquivo = nomeDoArquivo;
		this.context = context;
	}

	public void grava() {

		try {

			FileOutputStream out = activity.openFileOutput(arquivo,Context.MODE_PRIVATE);

			String msg = config.getNotifica() + caracterSplit + config.getCodigoUltimaCitacao();
			out.write(msg.getBytes());
			out.close();

			Log.i(GrandesPensadores.categoriaLog.value(),msg + " - escrito com sucesso");

		} catch (FileNotFoundException e) {
			Log.e(GrandesPensadores.categoriaLog.value(),e.getMessage(),e);
		} catch (IOException e) {
			Log.e(GrandesPensadores.categoriaLog.value(),e.getMessage(),e);
		}
	}

	public Config ler() {

		Config config = new Config();
		try {
			File f = null;
			if (activity == null) {
				f = context.getFileStreamPath(arquivo);
			} else {
				f = activity.getFileStreamPath(arquivo);
			}

			Log.i(GrandesPensadores.categoriaLog.value(),"Abrindo arquivo: " + f.getAbsolutePath());

			if (f.exists()) {

				FileInputStream in = null;
				if (activity == null) {
					in = context.openFileInput(arquivo);
				} else {
					in = activity.openFileInput(arquivo);
				}

				int tamanho = in.available();
				byte bytes[] = new byte[tamanho];
				in.read(bytes);
				String s = new String(bytes);
				String[] array = s.split(caracterSplit);
				config.setNotifica(array[0]);
				Log.i(GrandesPensadores.categoriaLog.value(),"Config array.length : " + array.length);
				if (array.length > 1) {
					config.setCodigoUltimaCitacao(array[1]);
				}
				Log.i(GrandesPensadores.categoriaLog.value(),"Config lida : " + config);

			} else {
				Log.i(GrandesPensadores.categoriaLog.value(),"Arquivo n�o existe ou exclu�do");

			}
		} catch (FileNotFoundException e) {
			Log.e(GrandesPensadores.categoriaLog.value(),"Arquivo n�o encontrado: " + e.getMessage(),e);
		} catch (IOException e) {
			Log.e(GrandesPensadores.categoriaLog.value(),e.getMessage(),e);
		}

		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

}
