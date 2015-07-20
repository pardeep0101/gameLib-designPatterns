package game2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class CapitalCountry {
	/**
	 * @uml.property  name="reader"
	 */
	InputStreamReader reader;
	/**
	 * @uml.property  name="in"
	 */
	BufferedReader in;
	/**
	 * @uml.property  name="line"
	 */
	String line;
	/**
	 * @uml.property  name="url"
	 */
	URL url;
	/**
	 * @uml.property  name="i"
	 */
	int i = 0;
	/**
	 * @uml.property  name="j"
	 */
	int j;

	CapitalCountry(URL url) {
		this.url = url;

	}
	/**
	 * Give you a html with the capital information
	 * @return the source code with the capital information
	 * @throws IOException
	 * @throws NullPointerException
	 */
	public String capitalInformartion() throws IOException, NullPointerException {
		reader = new InputStreamReader(url.openStream());
		in = new BufferedReader(reader);
		String capitalInformation = null;

		
		boolean firstFlag = false;
		boolean table = false;

		while (!table) {
			if (!firstFlag) {
				if ((line = in.readLine()).contains("<td>")) {
					// tableBegin = true;
					firstFlag = true;
					capitalInformation = line;
				}
			} else if (!(line = in.readLine()).contains("</td>")) {
				capitalInformation += line;
			} else {
				table = true;
			}
			
		}
		
		return capitalInformation;
	}
/**
 * Give you the capital name.
 * @return Return the capital.
 * @throws IOException
 */
	public String getCapital() throws IOException {
		String capital = "Wrong input! You lost. Game over.";
		String capitalInfo;
		boolean capitalFound = false;
		boolean tdFoundAlone = false, cursiveFoundAlone = false;
		int k = 0;
		int m;

		capitalInfo = capitalInformartion();
		while (!capitalFound) {
			while (!tdFoundAlone
					&& !cursiveFoundAlone
					&& !(capitalInfo.substring(k, k + 1).equals("<")
							&& capitalInfo.substring(k + 1, k + 2).equals("/") && capitalInfo
							.substring(k + 2, k + 3).equals("a"))) {
				if ((capitalInfo.substring(k, k + 1).equals("<")
						&& capitalInfo.substring(k + 1, k + 2).equals("t") && capitalInfo
						.substring(k + 2, k + 3).equals("d"))
						&& capitalInfo.substring(k + 3, k + 4)
								.equalsIgnoreCase(">")
						&& !capitalInfo.substring(k + 4, k + 5).equals("<")) {
					tdFoundAlone = true;
				} else if (capitalInfo.substring(k, k + 1).equals("i")
						&& capitalInfo.substring(k + 1, k + 2).equals(">")
						&& !capitalInfo.substring(k + 2, k + 3).equals("<")) {
					cursiveFoundAlone = true;
				}

				else {
					k++;
				}

			}
			if (tdFoundAlone) {
				m = k + 3;
				while (!(capitalInfo.substring(m, m + 1).equals("<"))) {
					m++;

				}
				capitalFound = true;
				capital = capitalInfo.substring(k + 4, m);
			} else if (cursiveFoundAlone) {
				m = k + 2;
				while (!(capitalInfo.substring(m, m + 1).equals("<"))) {
					m++;
				}
				capitalFound = true;
				capital = capitalInfo.substring(k + 2, m);
			} else {
				m = k;
				if (!(capitalInfo.substring(k - 1, k).equals(">"))) {
					while (!(capitalInfo.substring(m, m + 1).equals(">"))) {
						m--;
					}
					capitalFound = true;
					capital = capitalInfo.substring(m + 1, k);
				} else {
					k++;
				}

			}
		}
		return capital;

	}

	

}
