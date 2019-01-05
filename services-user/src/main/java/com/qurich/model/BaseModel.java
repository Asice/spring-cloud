package com.qurich.model;

import java.io.Serializable;
import java.util.Date;

public class BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 14445485255511001L;
	
	private Date ctime; //创建时间
	private Date utime;//更新时间
	private String cname; //创建人
	private String uname; //更新人
	private int is_del; //是否删除。0否，非0是

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getIs_del() {
        return is_del;
    }

    public void setIs_del(int is_del) {
        this.is_del = is_del;
    }
}
