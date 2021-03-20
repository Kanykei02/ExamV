package service;

import dao.DataBase;
import dao.ReqDao;
import model.Requests;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/requests")
public class ReqSer {

    private ReqDao reqDao = new ReqDao();
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Requests> getAllRequests(){
        return reqDao.getAllRequests();
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Requests getUserById(@PathParam("userId") int userId){
        return reqDao.getUserById(userId);
    }
}
