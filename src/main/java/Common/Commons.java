package Common;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class Commons {
	public MainConfig mconfig;

	public static Commons load(String path){
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		try {
			Commons config = mapper.readValue(Commons.class.getResource(path), Commons.class);
			return config;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static double streamDouble(double pixRate) {
		double newPixRate = (double)Math.round(pixRate * 100) / 100;
		return newPixRate;
	}
}