package org.mypro.entity;

public class History {
    private Integer id;

    private String scenicspot;

    private Integer scenicspotid;

    private String user;

    private Integer userid;

    private String addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScenicspot() {
        return scenicspot;
    }

    public void setScenicspot(String scenicspot) {
        this.scenicspot = scenicspot == null ? null : scenicspot.trim();
    }

    public Integer getScenicspotid() {
        return scenicspotid;
    }

    public void setScenicspotid(Integer scenicspotid) {
        this.scenicspotid = scenicspotid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime == null ? null : addtime.trim();
    }
}