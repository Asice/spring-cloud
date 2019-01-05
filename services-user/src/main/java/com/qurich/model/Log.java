package com.qurich.model;

import java.util.Date;

public class Log extends BaseModel{
	private static final long serialVersionUID = 1L;
	private int id;
	private Long tid;
	private String tname;
	private String content;
	private String ip;
	private String location;
	private String cname;
	private String uname;

	public Log( long tid, String tname, String content, String ip, String location, String cname, String uname) {
		super();
		Date now=new Date();
		this.tid = tid;
		this.tname = tname;
		this.content = content;
		this.ip = ip;
		this.location = location;
		this.setCtime(now);
		this.setUtime(now);
		this.setCname(cname);
		this.setUname(uname);
	}

	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid){
		this.tid = tid;
	}
	public String getTname(){
		return tname;
	}
	public void setTname(String tname){
		this.tname = tname;
	}
	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content = content;
	}
	public String getIp(){
		return ip;
	}
	public void setIp(String ip){
		this.ip = ip;
	}
	public String getLocation(){
		return location;
	}
	public void setLocation(String location){
		this.location = location;
	}
	public String getCname(){
		return cname;
	}
	public void setCname(String cname){
		this.cname = cname;
	}
	public String getUname(){
		return uname;
	}
	public void setUname(String uname){
		this.uname = uname;
	}
}