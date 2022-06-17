//This class as well as the File saving class is used for storing the data in a CSV format.
//This class mainly focuses on the format of the data
package uk.ac.mmu.cnt2.Connected2EmbeddedApplication;

public class DataFile {// name of class is DataFile which is used by FileSaving class to call this class
	
	//Below is defining attributes for the data received by the application
	private String date; //Set as String as date includes slashes
	private double temp; //set as double as values are not that big and not that small
	private double motion; //set as double as values are not that big and not that small
	private double light; //set as double as values are not that big and not that small
	
	//Getters and Setters used for getting data and assigning the data so that it can be saved
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	public double getLight() {
		return light;
	}
	public void setLight(double light) {
		this.light = light;
	}
	public double getMotion() {
		return motion;
	}
	public void setMotion(double motion) {
		this.motion = motion;
	}
	@Override// Annotation used. This annotation tell the compiler that it override a method of its superclass
	public String toString() {// method created called toString
		return "Record [date=" + date + ", temp=" + temp + ", motion=" + motion + ", light=" + light + "]";
	}
}
