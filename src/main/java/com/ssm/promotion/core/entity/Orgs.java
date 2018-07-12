package com.ssm.promotion.core.entity;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SapOrgs")
public class Orgs {
	private List<Org> list;
	 
    private Org[] array;
 
    private Map<String, Org> map;
 
    public List<Org> getList() {
        return list;
    }
 
    public void setList(List<Org> list) {
        this.list = list;
    }
 
    public Org[] getArray() {
        return array;
    }
 
    public void setArray(Org[] array) {
        this.array = array;
    }
 
    public Map<String, Org> getMap() {
        return map;
    }
 
    public void setMap(Map<String, Org> map) {
        this.map = map;
    }

}
