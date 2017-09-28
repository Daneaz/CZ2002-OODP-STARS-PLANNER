package Class;

import java.io.Serializable;
import java.util.Date;
/**
 * Each lesson represents a type of lesson in a schedule
 * @author Guo sha, Wu WeiJie, Jerrold Seet, Joseph Ng HengQi, Zhang YuanCe
 *
 */
public class Lesson implements Serializable{
	
	private Date starttime;
	private Date endtime;
	private String venue;
	private String indexid;
	/**
	 * Day in a week
	 */
	private int day;
	/**
	 * Constructor to add a lesson to a schedule
	 * @param day
	 * @param st
	 * @param et
	 * @param venue
	 * @param indexid
	 */
	public Lesson(int day, Date st, Date et, String venue, String indexid )
	{
		this.day = day;
		this.starttime =st;
		this.endtime = et;
		this.venue = venue;
		this.indexid = indexid;
	}
	
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getIndexID() {
		return indexid;
	}
	public void setIndexID(String indexid) {
		this.indexid = indexid;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	
}
