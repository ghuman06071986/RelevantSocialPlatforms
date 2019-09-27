package com.parminder.parser;

import java.io.IOException;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.parminder.bo.UserInfo;

public class PinterestParser implements Parser{

	public UserInfo parsePage(String userName)  throws IOException {

		Document doc = Jsoup.connect("https://in.pinterest.com/" + userName + "/").ignoreContentType(true)
				.header("accept", "application/json").get();
		Elements scriptElements = doc.getElementsByTag("script");
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(userName);

		for (Element element : scriptElements) {
			if (element.attr("id").equals("initial-state")) {
				for (DataNode node : element.dataNodes()) {
					JSONObject jsonObject = new JSONObject(node.getWholeData());

					if (jsonObject.has("resources") && jsonObject.getJSONObject("resources").has("data") && jsonObject
							.getJSONObject("resources").getJSONObject("data").has("UnauthReactUserProfileResource")) {

						JSONObject UnauthReactUserProfileResource = jsonObject.getJSONObject("resources")
								.getJSONObject("data").getJSONObject("UnauthReactUserProfileResource");
						UnauthReactUserProfileResource.keySet().forEach((action) -> {
							JSONObject dataObject = UnauthReactUserProfileResource.getJSONObject(action + "");
							if (dataObject.has("data") && dataObject.getJSONObject("data").has("profile")) {
								JSONObject profileObject = dataObject.getJSONObject("data").getJSONObject("profile");
								if (profileObject.has("full_name") && !profileObject.isNull("full_name")) {
									userInfo.setName(profileObject.getString("full_name"));
								}
								if (profileObject.has("about") && !profileObject.isNull("about")) {
									userInfo.setDescription(profileObject.getString("about"));
								}
								if (profileObject.has("domain_url") && !profileObject.isNull("domain_url")) {
									userInfo.setUrl(profileObject.getString("domain_url"));
								}
							}

						});

					}
				}
			}
		}
		return userInfo;

	}
}
