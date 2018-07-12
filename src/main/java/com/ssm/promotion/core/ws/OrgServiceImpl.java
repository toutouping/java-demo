package com.ssm.promotion.core.ws;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;
import com.ssm.promotion.core.adapter.StringObjectMapAdapter.MapConvertor;

import com.ssm.promotion.core.entity.Org;

class OrgServiceImpl implements OrgService{

	@Override
	public Response getOrg(String id) {
		Org org = new Org();
		org.setParentId("1");
		System.out.println("getCategory called with category id: " + id);
		return Response.ok(org).build();
	}
	
	@Override
	public Response addOrgs(Map<String, Object> map) {
		MapConvertor data = new MapConvertor();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            data.addEntry(entry.getKey(), entry.getValue());
    		System.out.println(entry.getValue());
        }
        
		System.out.println(map);
		Org org = new Org();
		org.setParentId("1");
		return Response.ok(org).build();
	/*	System.out.println("addBooks with category id : " + org);
		if (org == null) {
			return Response.status(Status.NOT_FOUND).build();
		} else {
			return Response.ok(org).build();
		}*/
	}

	@Override
	public List<String> getStringList(List<String> strList) {
		// TODO Auto-generated method stub
		return strList;
	}

	@Override
	public List<Org> getUserList(List<Org> userList) {
		// TODO Auto-generated method stub
		return userList;
	}

	@Override
	public Map<String, Object> getMapData(Map<String, Object> data) {
		// TODO Auto-generated method stub
		return data;
	}
}
