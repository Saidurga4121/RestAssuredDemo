package OAUTH;

public class GetCourses 
{
	private String instructor;
	private String url;
	private String services;
	private String expertise;
	private Courses courses;
	private String linkedIn;
	//
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
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public Courses getCourses() {
		return courses;
	}
	public void setCourses(Courses courses) {
		this.courses = courses;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	
	
	// entire response 
//	{
//	    "instructor": "RahulShetty",
//	    "url": "rahulshettycademy.com",
//	    "services": "projectSupport",
//	    "expertise": "Automation",
//	    "courses": {
//	        "WebAutomation": [
//	            {
//	                "courseTitle": "Selenium Webdriver Java",
//	                "price": "50"
//	            },
//	            {
//	                "courseTitle": "Cypress",
//	                "price": "40"
//	            },
//	            {
//	                "courseTitle": "Protractor",
//	                "price": "40"
//	            }
//	        ],
//	        "api": [
//	            {
//	                "courseTitle": "Rest Assured Automation using Java",
//	                "price": "50"
//	            },
//	            {
//	                "courseTitle": "SoapUI Webservices testing",
//	                "price": "40"
//	            }
//	        ],
//	        "mobile": [
//	            {
//	                "courseTitle": "Appium-Mobile Automation using Java",
//	                "price": "50"
//	            }
//	        ]
//	    },
//	    "linkedIn": "https://www.linkedin.com/in/rahul-shetty-trainer/"
//	}
	

}
