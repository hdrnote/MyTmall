package com.hdr.Bean;

public class User {
    private String password;
    private String name;
    private int id;

    public User(){}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // 获取本用户的匿名名称，在评价的时候显示用户名使用。
    public String getAnonymounsName() {
        if (name == null) return null;
        if (name.length() <= 1) return "*";
        if (name.length() == 2) return name.substring(0, 1) + "*";
        char[] cs = name.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            cs[i] = '*';
        }
        return new String(cs);
    }
}
