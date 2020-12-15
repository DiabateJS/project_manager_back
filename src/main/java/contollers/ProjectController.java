package contollers;

import models.Project;
import services.ProjectService;

import javax.inject.Inject;
import javax.json.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/projects")
public class ProjectController {
    @Inject
    private ProjectService projectService;

    @Path("/all")
    @GET
    @Produces("application/json")
    public JsonObject getAll(){
        JsonObjectBuilder builder = Json.createObjectBuilder();
        for (Project p : projectService.getAllProjects()){
            builder.add("id",1);
        }
        return builder.build();
    }
}
