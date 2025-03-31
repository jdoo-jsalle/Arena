package com.js.dawa.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

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



	/**
	 *
	 * 
	 * 
	 * @param p_path - p_path
	 * 
	 * @throws DawaException DASSAULT exception
	 * 
	 */

	public void open(String pPath) throws DawaException {

		open(pPath, StandardCharsets.UTF_8);

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

	public void open(String pPath, Charset pEncoding) throws DawaException {

		verify();

		try {

			File lFile = new File(pPath);

			mIn = new BufferedReader(new InputStreamReader(new FileInputStream(lFile), pEncoding));

		} catch (FileNotFoundException  le) {

			throw new DawaException("Erreur In Open", le);

		}

	}

	public void openFromRessourceAsStream(String pPath) throws DawaException {

		verify();

		InputStream li = getClass().getClassLoader().getResourceAsStream(pPath);

		if (li != null) {
			mIn = new BufferedReader(new InputStreamReader(li, StandardCharsets.UTF_8));
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

		String lRes = null;

		try {

			if (mIn != null) {

				lRes = mIn.readLine();

			}

		} catch (IOException le) {

			throw new DawaException("Erreur Read", le);

		}

		return lRes;

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
