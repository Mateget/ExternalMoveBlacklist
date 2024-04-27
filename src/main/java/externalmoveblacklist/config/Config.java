package externalmoveblacklist.config;

import java.util.ArrayList;
import java.util.List;

public class Config {

	private String blacklistedMoveMessage;
	private List<String> blacklist = new ArrayList<>();

	public Config() {
		blacklist = new ArrayList<>();
		blacklist.add("default");
		blacklistedMoveMessage = "This external move is blacklisted";

	}

	public List<String> getBlacklist() {
		return blacklist;
	}

	public String getBlacklistedMoveMessage() {
		return blacklistedMoveMessage;
	}

}
