package service;
import dao.ReqDao;
import model.Requests;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
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

    @GET
    @Path("/{byName}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Requests> getRequestsByName(@PathParam("byName") String fullName) {
        return reqDao.getReqByName(fullName);
    }

    @GET
    @Path("/{byBirthYear}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Requests> getRequestsByBirthYear(@PathParam("byBirthYear") Integer birthYear) {
        return reqDao.getReqByBirthYear(birthYear);
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public String createRequest(Requests request) {
        reqDao.createRequest(request);
        return request.getBirthYear() < 2000 ?
                "Error" : request.getGender().equals("male") || request.getGender().equals("female") ?
                request.getGender().equals("male")
                        ? "Уважаемый " + request.getFullName()
                        + ", Ваш год рождения: " + request.getBirthYear() + ", вам: " +
                        (LocalDate.now().getYear() - request.getBirthYear()) + " лет" : "Уважаемая " + request.getFullName()
                        + ", Ваш год рождения: " + request.getBirthYear() + ", вам: " +
                        (LocalDate.now().getYear() - request.getBirthYear()) + " лет" : "Error!";
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public String updateReq(Requests requests){
        return reqDao.updateReq(requests);
    }

    @DELETE
    @Path("/{reqId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteReq(@PathParam("reqId") Long reqId){
        return reqDao.deleteReq(reqId);
    }
}
