package com.parminder.parser;

import java.io.IOException;

import com.parminder.bo.UserInfo;

public interface Parser {
	public UserInfo parsePage(String userName) throws IOException ;

}
