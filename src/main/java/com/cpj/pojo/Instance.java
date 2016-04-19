package com.cpj.pojo;

import com.cpj.pojo.base.GeneralPojo;

import java.util.Date;

/**
 * Created by chenpengjiang on 2016/3/24.
 */
public class Instance extends GeneralPojo {
    private static final long serialVersionUID = 100000001L;
    private String serverid;
    private String servername;
    private String imagename;
    private String ipaddress;
    private String flavorname;
    private String keyname;
    private String state;
    private String powerstate;
    private Date createtime;

    public String getServername() {
        return servername;
    }

    public void setServername(String servername) {
        this.servername = servername;
    }

    public String getPowerstate() {
        return powerstate;
    }

    public void setPowerstate(String powerstate) {
        this.powerstate = powerstate;
    }

    public String getServerid() {
        return serverid;
    }

    public void setServerid(String serverid) {
        this.serverid = serverid;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getFlavorname() {
        return flavorname;
    }

    public void setFlavorname(String flavorname) {
        this.flavorname = flavorname;
    }

    public String getKeyname() {
        return keyname;
    }

    public void setKeyname(String keyname) {
        this.keyname = keyname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
