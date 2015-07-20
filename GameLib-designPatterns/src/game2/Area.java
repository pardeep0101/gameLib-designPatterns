package game2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Area {

	
	InputStreamReader reader;
	
	BufferedReader in;
	/**
	 * @uml.property  name="url"
	 */
	URL url;


	Area(URL url) {
		this.url = url;
	}
/**
 * GIve you the line where is the area word.
 * @return the line of the area word.
 * @throws IOException
 * @throws NullPointerException
 */
	private String areaLine() throws IOException, NullPointerException {
		reader = new InputStreamReader(url.openStream());
		in = new BufferedReader(reader);
		String areaLine = null;

		boolean aLineFound = false;

		// boolean table = false;

		while (!aLineFound) {
			if ((areaLine = in.readLine()).contains("km<sup><small>2")&& areaLine.contains("<td>")) {
				aLineFound = true;
				// table = true;
			}
		}
		return areaLine;
	}
/**
 * Clean the area line and give you the area.
 * @return The area.
 * @throws NullPointerException
 * @throws IOException
 */
	private String cleanAreaLine() throws NullPointerException, IOException {
		String area = areaLine();
		boolean areaFound = false;
		int k = 4, m = k;

		while (!areaFound) {
			// if()
			if ((area.substring(m, m + 1).equals("&")
					&& area.substring(m + 1, m + 2).equals("#")
					&& area.substring(m + 2, m + 3).equals("1")
					&& area.substring(m + 3, m + 4).equals("6")
					&& area.substring(m + 4, m + 5).equals("0"))||area.substring(m, m+1).equals("<")) {
				
				areaFound = true;
			} else {
				m++;
			}

		}
		area = area.substring(k, m);
		return area;

	}
/**
 * Give you the area round with no comma.
 * @return return the int of the area rounded.
 * @throws NullPointerException
 * @throws IOException
 */
	public int areaRound() throws NullPointerException, IOException {
		String cleanLine = cleanAreaLine();
		String otherInformation;
		String numberRemoveComa = cleanLine.replaceAll(",", "");
		int index = 0;
		while(index < numberRemoveComa.length() &&Character.isDigit(numberRemoveComa.charAt(index))){
			index++;
		}
		cleanLine = numberRemoveComa.substring(0, index);
		otherInformation = numberRemoveComa.substring(index);
		int number = Integer.parseInt(cleanLine);
		double roundNumber = number / 10000.0;
		int numberRound;
		if ((roundNumber - (int) roundNumber) > .5) {
			roundNumber = (int) roundNumber + 1;
		}
		numberRound = (int) roundNumber;

		return numberRound;

	}
/**
 * Give you a String of the with the area.
 * @return A String with the area with comma.
 * @throws NullPointerException
 * @throws IOException
 */
	public String areaRoundWithComma() throws NullPointerException,
			IOException {
		String areaRound = Integer.toString(areaRound());
		if (areaRound.length() > 3) {
			for (int i = 0; i < areaRound.length(); i = i + 3) {
				areaRound = areaRound.substring(0, 1)
						+ ","
						+ areaRound
								.substring(areaRound.length() - 3);
			}
		}
		return areaRound;
	}

}
