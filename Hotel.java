package hotel_management;
public class Hotel {
private String hotelName;
	public int weekDayRate;
	public int weekEndRate;
	public int hotelRatings;
	public String customerType;
	public Hotel(String hotelName, int weekDayRate, int weekEndRate, int hotelRatings, String customerType) {
		this.hotelName = hotelName;
		this.weekDayRate = weekDayRate;
		this.weekEndRate = weekEndRate;
		this.hotelRatings = hotelRatings;
		this.customerType = customerType;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public int getWeekDayRate() {
		return weekDayRate;
	}	
	public void setWeekDayRate(int weekDayRate) {
		this.weekDayRate = weekDayRate;
	}
	public int getWeekEndRate() {
		return weekEndRate;
	}
	public void setWeekEndRate(int weekEndRate) {
		this.weekEndRate = weekEndRate;
	}
	public int getHotelRatings() {
		return hotelRatings;
	}
	public void setHotelRatings(int hotelRatings) {
		this.hotelRatings = hotelRatings;
	}
	@Override
	public String toString() {
		String hotel = "Hotel : " + this.getHotelName() + "\n" + "Customer Type : " +this.getCustomerType() + "\n" + "WeekDayRate : " +
	                   this.getWeekDayRate() + "\n" + "WeekEndRate : " + this.getWeekEndRate() +"\n"+ "Hotel Ratings: " + this.getHotelRatings() + "\n";
		return hotel;
	}
	}