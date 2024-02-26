package org.mypro.entity;

public class Scenicspot {
    private Integer id;

    private String name;

    private String pic;

    private String introduce;

    private String orgurl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getOrgurl() {
        return orgurl;
    }

    public void setOrgurl(String orgurl) {
        this.orgurl = orgurl == null ? null : orgurl.trim();
    }
}