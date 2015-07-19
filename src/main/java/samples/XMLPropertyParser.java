package samples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

/**
 * Simple Example of reading xml properties file that has hierarchy 
 *
 */
public class XMLPropertyParser {

	private static String fileName = "hiearchicalproperties.xml";

	public XMLPropertyParser() {

	}

	public void run() throws ConfigurationException {
		String line = null;
		URL url = this.getClass().getClassLoader().getResource(fileName);
		System.out.println(url);
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(fileName)))) {
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			System.out.println("START OF OUTPUT");
			XMLConfiguration xmlConfiguration = new XMLConfiguration(url);
			@SuppressWarnings("unchecked")
			List<Object> testVar = xmlConfiguration.getList("job.command");
			int count = testVar.size();
			for (int i = 0; i < count; i++) {
				System.out.println("Way ONE: " + xmlConfiguration.getString("job(" + i + ").command"));
				HierarchicalConfiguration config = xmlConfiguration.configurationAt("job(" + i + ")");
				System.out.println("WAY2: " + config.getString("command"));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		XMLPropertyParser parser = new XMLPropertyParser();
		try {
			parser.run();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

}
