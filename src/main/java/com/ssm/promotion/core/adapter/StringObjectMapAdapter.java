package com.ssm.promotion.core.adapter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssm.promotion.core.entity.Org;

public class StringObjectMapAdapter extends XmlAdapter<StringObjectMapAdapter.MapConvertor, Map<String, Object>> {

    @Override
    public Map<String, Object> unmarshal(MapConvertor data) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        for (MapConvertor.Entry entry : data.getList()) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

    @Override
    public MapConvertor marshal(Map<String, Object> map) throws Exception {
    	MapConvertor data = new MapConvertor();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            data.addEntry(entry.getKey(), entry.getValue());
        }
        return data;
    }
    
    @XmlRootElement(name = "map")
    @XmlSeeAlso({Org.class})
    public static class MapConvertor {

        private List<Entry> list = new ArrayList<Entry>();

        public void addEntry(String fieldName, Object fieldValue) {
            Entry entry = new Entry();
            entry.setKey(fieldName);
            entry.setValue(fieldValue);
            list.add(entry);
        }

        public List<Entry> getList() {
            return list;
        }

        public void setList(List<Entry> list) {
            this.list = list;
        }

        public static class Entry {

            private String key;
            private Object value;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public Object getValue() {
                return value;
            }

            public void setValue(Object value) {
                this.value = value;
            }
        }
    }
}
