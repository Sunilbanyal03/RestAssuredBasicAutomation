package POJOLearning;

public class GetCourses {

	private String instructor;
	private String url;
	private String services;
	private String linkedIn;
	private Courses courses;
	private String expertise;
	
	
	public String getExpertise() {
		return expertise;
	}
	
	public void setExpertise(String expertise) {
		
		this.expertise=expertise;
	}
	
	
	public Courses getCourses() {
		return courses;
	}

	public void setCourses(Courses courses) {
		this.courses = courses;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getLinkedIn() {
		return linkedIn;
	}

	public void setLinkedin(String linkedIn) {
		this.linkedIn = linkedIn;
	}


	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		
		this.url=url;
	}
}
