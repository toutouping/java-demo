package com.ssm.promotion.core.ws;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.ssm.promotion.core.adapter.StringObjectMapAdapter;
import com.ssm.promotion.core.entity.Org;


@Path("")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) /* 定义了服务类和方法生产内容的类型 */
public interface OrgService {

	@GET
	@Path("/org/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getOrg(@PathParam("id") String id);

	@POST
	@Path("/addOrgs")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response addOrgs(@XmlJavaTypeAdapter(StringObjectMapAdapter.class) Map<String, Object> fieldMap);

	@POST
	@Path("/getStringList")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<String> getStringList(List<String> strList);  

	@POST
	@Path("/getUserList")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Org> getUserList(List<Org> userList);

	@POST
	@Path("/getMapData")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @XmlJavaTypeAdapter(StringObjectMapAdapter.class)
    public Map<String,Object> getMapData(@XmlJavaTypeAdapter(StringObjectMapAdapter.class) Map<String,Object> data);  
}
