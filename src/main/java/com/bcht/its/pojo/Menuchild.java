package com.bcht.its.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${zhaojinglong} on 2017/4/6 0006.
 */
@Getter
@Setter
public class Menuchild {
    private String name;
    private String cname;
    private String url;
    private String icon;
    private List<Menuchild> sub = new ArrayList<Menuchild>();
    private Boolean checked = false;

    public Menuchild(String _name, String _cname, String _url, String _icon, Boolean checked) {
        this.name = _name;
        this.cname = _cname;
        this.url = _url;
        this.icon = _icon;
        this.checked = checked;
    }

    public Menuchild(String _name, String _cname, String _url, String _icon) {
        this.name = _name;
        this.cname = _cname;
        this.url = _url;
        this.icon = _icon;
    }

    public String getId() {
        return this.cname;
    }

    public String getText() {
        return this.name;
    }

    public List<Menuchild> getChildren() {
        return this.sub;
    }


}
