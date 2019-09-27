package com.parminder.parser;

import java.io.IOException;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.parminder.bo.UserInfo;
import com.parminder.bo.UserInfo.Type;

public class TwitterParser implements Parser{

	public UserInfo parsePage(String userName) throws IOException {
		Document doc = Jsoup.connect("https://twitter.com/" + userName).get();
		Elements scriptElements = doc.getElementsByTag("input");
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(userName);

		for (Element element : scriptElements) {
			if (element.attr("id").equals("init-data")) {

				JSONObject jsonObject = new JSONObject(element.attr("value"));
				if (jsonObject.has("profile_user")) {

					JSONObject profileJson = jsonObject.getJSONObject("profile_user");
					if (profileJson.has("name")  && !profileJson.isNull("name")) {
						userInfo.setName(profileJson.getString("name"));
					}
					if (profileJson.has("location")  && !profileJson.isNull("location")) {
						userInfo.setAddress(profileJson.getString("location"));
					}

					if (profileJson.has("description") &&   !profileJson.isNull("description")) {
						userInfo.setDescription(profileJson.getString("description"));
					}
					if (profileJson.has("url") && !profileJson.isNull("url")) {
						userInfo.setUrl(profileJson.getString("url"));
					}
				}
			}

		}
		return userInfo;

	}
}
