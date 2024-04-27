package externalmoveblacklist.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pixelmonmod.pixelmon.api.moveskills.MoveSkill;

import externalmoveblacklist.ExternalMoveBlacklist;

public class ConfigHandler {

	public static Config config;

	public static void readAllConfigs() {
		loadConfigFile(new File(ExternalMoveBlacklist.configDir, "config.json"));
	}

	public static void creationCheckConfig() {
		if (config == null) {
			config = new Config();
		}
	}

	public static void writeConfig() {
		writeConfigFile(new File(ExternalMoveBlacklist.configDir, "config.json"));
	}

	public static void loadConfigFile(File file) {
		try {
			if (!file.exists())
				file.createNewFile();
			Gson gson = new Gson();
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
			config = gson.fromJson(br, Config.class);
			br.close();
		} catch (Exception e) {
			ExternalMoveBlacklist.LOGGER.error("Failed to read config:\r\n" + e.getMessage());
		}
	}

	public static void writeConfigFile(File file) {
		try {
			if (!file.exists())
				file.createNewFile();
			Gson gson = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
			String json = gson.toJson(config);
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
			writer.write(json);
			writer.close();
		} catch (Exception e) {
			ExternalMoveBlacklist.LOGGER.error("Failed to save config:\r\n" + e.getMessage());
		}
	}
	
	public static List<String> configValider() {
		List<String> alerts = new ArrayList<>();
		for(String externalMoveId : config.getBlacklist()) {
			try {
				if(MoveSkill.getMoveSkillByID(externalMoveId) == null) {
					alerts.add("&cExternal move : " + externalMoveId + " do not exist");
				}
			} catch(Exception e) {
				ExternalMoveBlacklist.LOGGER.error("Error during config valider with id : " + externalMoveId);
				e.printStackTrace();
			}
			
		}
		return alerts;
	}

}
