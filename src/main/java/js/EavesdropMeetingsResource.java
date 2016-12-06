package js;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import services.EavesdropMeetingsService;

@Path("")
public class EavesdropMeetingsResource {
	private EavesdropMeetingsService eavesdropMeetingsService;
	
	public EavesdropMeetingsResource() {
		this.eavesdropMeetingsService = new EavesdropMeetingsService();
	}
	
	@GET
	@Path("/{project}")
	@Produces("text/html")
	public String getMeetingCounts(@PathParam("project") String project) {
		List<Integer> counts = eavesdropMeetingsService.getMeetingCounts(project);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < counts.size(); i++) {
			sb.append(counts.get(i));
			sb.append(" ");
		}
		return sb.toString();
	}
}
