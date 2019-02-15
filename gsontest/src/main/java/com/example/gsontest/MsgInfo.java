package com.example.gsontest;

import java.util.List;

public class MsgInfo {
    private String message;
    private String flag;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}

class ComOpen {
    private Org org;
    private List<BizObject> biz;

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    public List<BizObject> getBiz() {
        return biz;
    }

    public void setBiz(List<BizObject> biz) {
        this.biz = biz;
    }
}

class Org {
    private String orgId;
    private String orgName;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}

class BizObject {
    private int appcode;
    private String subscode;

    public String getSubscode() {
        return subscode;
    }

    public void setSubscode(String subscode) {
        this.subscode = subscode;
    }

    public int getAppcode() {
        return appcode;
    }

    public void setAppcode(int appcode) {
        this.appcode = appcode;
    }
}
