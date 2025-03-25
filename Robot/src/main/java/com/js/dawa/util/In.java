package com.js.dawa.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * 
 * Copyright Dassault Aviation.
 * Common DKM Projects.
 * 
 * @author Dassault Aviation
 * 
 *         Common Transversal
 *         Open/read file with BuffereReader
 * 
 */

public class In implements AutoCloseable {

	BufferedReader mIn;

	FileReader mFileReader;

	public static final String UTF8 = "UTF8";

	public static final String CP1252 = "Cp1252";

	/**
	 *
	 * 
	 * 
	 * @param p_path - p_path
	 * 
	 * @throws DawaException DASSAULT exception
	 * 
	 */

	public void open(String p_path) throws DawaException {

		open(p_path, UTF8);

	}

	/**
	 *
	 * 
	 * 
	 * @param p_path    - p_path
	 * 
	 * @param pEncoding - pEncoding
	 * 
	 * @throws DawaException DASSAULT exception
	 * 
	 */

	public void open(String p_path, String pEncoding) throws DawaException {

		verify();

		try {

			File lFile = new File(p_path);

			mIn = new BufferedReader(new InputStreamReader(new FileInputStream(lFile), pEncoding));

		} catch (FileNotFoundException | UnsupportedEncodingException le) {

			throw new DawaException("Erreur In Open", le);

		}

	}

	public void openFromRessourceAsStream(String pPath) throws DawaException {

		verify();

		InputStream li = getClass().getClassLoader().getResourceAsStream(pPath);

		if (li != null) {
			try {
				mIn = new BufferedReader(new InputStreamReader(li, UTF8));
			}

			catch (UnsupportedEncodingException le) {
				throw new DawaException("Error open in  File", le);
			}

		} else {
			throw new DawaException("Ressource " + pPath + " not exits");

		}

	}

	void verify() throws DawaException {
		if (mIn != null)

			throw new DawaException("Error already open");

	}

	/**
	 * 
	 * @return A string value
	 * 
	 * @throws DawaException DASSAULT exception
	 * 
	 */

	public String readLine() throws DawaException {

		String l_res = null;

		try {

			if (mIn != null) {

				l_res = mIn.readLine();

			}

		} catch (IOException le) {

			throw new DawaException("Erreur Read", le);

		}

		return l_res;

	}

	@Override

	public void close() throws DawaException {

		try {

			if (mIn != null) {
				mIn.close();
				mIn = null;

			}

			if (mFileReader != null) {
				mFileReader.close();
				mFileReader = null;

			}

		} catch (IOException le) {

			throw new DawaException("Erreur In Close", le);

		}

	}

}
