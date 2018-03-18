package org.joker.tv.model.front.web;

import java.io.Serializable;

public class ServerDto implements Serializable {

	private static final long serialVersionUID = -610919731942427521L;

	private String serverid;
	private String port;
	private String host;

	public String getServerid() {
		return serverid;
	}

	public void setServerid(String serverid) {
		this.serverid = serverid;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

}
