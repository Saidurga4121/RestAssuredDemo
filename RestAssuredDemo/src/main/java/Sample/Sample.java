package Sample;

import java.util.List;

public class Sample 
{
	
	private String firstname;
	private String lastname;
	private String email;
	private List<String> courses;
	//
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getCourses() {
		return courses;
	}
	public void setCourses(List<String> courses) {
		this.courses = courses;
	}
	
	
	
	
	
//	{
//		  "firstName":"sai",
//		  "lastName":"durga",
//		  "email":"sai@gmail.com",
//		  "courses":[
//		              "c++",
//		              "java"
//		            ] 
//		}

}
