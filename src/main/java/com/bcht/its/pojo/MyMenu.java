package com.bcht.its.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${zhaojinglong} on 2017/4/7 0007.
 */
@Getter
@Setter
public class MyMenu {
    private String myName;
    private String myRole;
    private String myAvatar="../../../temp/user/userAvatar.jpg";
    private String myNotifi = "56";
    private List<Menuchild> menu = new ArrayList<Menuchild>();
 }
