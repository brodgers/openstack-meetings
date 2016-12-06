package js;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/meetings")
public class EavesdropMeetingsApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();
		
	public EavesdropMeetingsApplication() {
	}
	
	@Override
	public Set<Class<?>> getClasses() {
		classes.add(EavesdropMeetingsResource.class);
		return classes;
	}
	
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
	
}
