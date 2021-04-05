package com.app.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ResffulServices {

	public static String getServices(String URL) throws IOException {

		URL url;
		StringBuilder textBuilder = new StringBuilder();

		url = new URL(URL);

		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		try (InputStream in = new BufferedInputStream(urlConnection.getInputStream());) {

			try (Reader reader = new BufferedReader(
					new InputStreamReader(in, Charset.forName(StandardCharsets.UTF_8.name())))) {
				int c = 0;
				while ((c = reader.read()) != -1) {
					textBuilder.append((char) c);
				}
			}
		} catch (Exception e) {
			throw new IOException(e);
		} finally {
			urlConnection.disconnect();
		}

		return textBuilder.toString();

	}
}
