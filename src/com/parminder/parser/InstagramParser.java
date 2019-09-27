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

public class InstagramParser implements Parser{

	public UserInfo parsePage(String userName)  throws IOException {
		Document doc = Jsoup.connect("https://www.instagram.com/" + userName).get();
		Elements scriptElements = doc.getElementsByTag("script");
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(userName);

		for (Element element : scriptElements) {
			if (element.attr("type").equals("application/ld+json")) {
				for (DataNode node : element.dataNodes()) {

					JSONObject jsonObject = new JSONObject(node.getWholeData());

					if (jsonObject.has("name")) {
						userInfo.setName(jsonObject.getString("name"));
					}
					if (jsonObject.has("@type")) {
						userInfo.setType(
								jsonObject.getString("@type").equals("Organization") ? Type.Organization : Type.Person);
					}

					if (jsonObject.has("description")) {
						userInfo.setDescription(jsonObject.getString("description"));
					}

					if (jsonObject.has("url")) {
						userInfo.setUrl(jsonObject.getString("url"));
					}
					if (jsonObject.has("address") && jsonObject.getJSONObject("address").has("addressLocality")) {
						userInfo.setAddress(jsonObject.getJSONObject("address").getString("addressLocality"));
					}
				}
			}
		}
		return userInfo;	

	}
}
