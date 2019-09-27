package com.parminder.parser;

import java.io.IOException;

import org.json.JSONObject;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.parminder.bo.UserInfo;

public class FacebookParser implements Parser{

	public UserInfo parsePage(String userName) throws IOException {

		Document doc = Jsoup.connect("https://www.facebook.com/" + userName + "/about").get();
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

					if (jsonObject.has("address") && jsonObject.getJSONObject("address").has("addressLocality")) {
						userInfo.setAddress(jsonObject.getJSONObject("address").getString("addressLocality"));
					}
				}
			}
		}
		try {
			doc = Jsoup.connect("https://www.facebook.com/pg/" + userName + "/about").get();

			Elements classes = doc.getElementsByClass("_50f4");
			for (Element element : classes) {
				for (Node node : element.childNodes()) {
					String data = node.toString().trim();
					if (data.startsWith("https://twitter.com/")) {
						userInfo.setTwitterUrl(data);
					}
					if (data.startsWith("https://twitter.com/")) {
						userInfo.setTwitterUrl(data);
					}
					if (data.startsWith("https://www.instagram.com/")) {
						userInfo.setInstagramUrl(data);
					}
					if (data.startsWith("https://www.pinterest.com/")) {
						userInfo.setPinterestUrl(data);
					}
				}
			}
			Element div = doc.getElementById("u_0_p");

			for (Node node : div.childNodes()) {

				for (Node urlNode : node.childNodes()) {

					userInfo.setUrl(urlNode.toString().trim());

				}
			}

		} catch (HttpStatusException e) {

		}

		return userInfo;
	}

}
