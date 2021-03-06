package br.edu.ufba.softvis.visminerweb.util;

import java.io.File;
import java.io.FileOutputStream;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FileUtils {

	public static final String TMP_PATH = "/tmp";
	public static final String CONFIG_PATH = "/WEB-INF";
	public static final String DBCONFIG_FILE = CONFIG_PATH
			+ "/dbconfig.properties";

	public static String generateJSONFileName(String prefix) {
		String json = "";

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		String id = session.getId();
		json = id + "." + prefix + ".json";

		return json;
	}

	public static String getTempPath() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath(TMP_PATH);
	}

	public static String getContextPath() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context
				.getExternalContext().getRequest();
		String url = req.getRequestURL().toString();
		url = url.substring(0, url.lastIndexOf("/"));

		return url;
	}

	public static synchronized void writeFile(String filePath, String content)
			throws Exception {
		File f = new File(filePath);
		if (f.exists()) {
			f.delete();
		}

		byte dataToWrite[] = content.getBytes();
		FileOutputStream out = new FileOutputStream(filePath);
		out.write(dataToWrite);
		out.close();
	}

	public static String getDBConfigFilePath() {
		String path = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath(DBCONFIG_FILE);

		return path;
	}

}
