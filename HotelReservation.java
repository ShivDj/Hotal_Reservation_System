package hotel_management;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;


public class HotelReservation {
	static long totalDays,totalWeekDays,totalWeekEndDays;
	
	static Scanner input = new Scanner(System.in);
	
	public ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
	
	public void addHotel(String customerType) {
		Hotel hotel1 = null;
		Hotel hotel2 = null;
		Hotel hotel3 = null;
			
			if(customerType.equals("Regular")) {
				hotel1 = new Hotel("LakeWood", 110, 90, 4, "Regular");
				hotel2 = new Hotel("BridgeWood", 150, 50, 3, "Regular");
				hotel3 = new Hotel("RidgeWood" , 220, 150, 5, "Regular");
			}
			else if(customerType.equals("Reward")){
				hotel1 = new Hotel("LakeWood", 80, 80, 4, "Reward");
				hotel2 = new Hotel("BridgeWood", 110, 50, 3, "Reward");
				hotel3 = new Hotel("RidgeWood" , 100, 40, 5, "Reward");
			}
			else {
				System.out.println("Invalid Customer Type");
			}
		
		hotelList.add(hotel1);
		hotelList.add(hotel2);
		hotelList.add(hotel3);
		System.err.println(hotelList);
	}
	public void getInput(String customerType) {
		LocalDate[] localDate = new LocalDate[2];
		String inputDate = "";
		inputDate = input.nextLine();
		//Split the string to get the dates
		String[] dates = inputDate.split(":|,");
		customerType = dates[0];
		for(int iteration = 0; iteration<=1 ; iteration++) {
			//Convert dates to standard format
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy", Locale.ENGLISH);
				LocalDate date = LocalDate.parse(dates[iteration], formatter);
				localDate[iteration-1] = date;
			}
			catch(DateTimeException exception) {
				System.out.println("Invalid Date Entry");

			}
		}
		LocalDate start = localDate[0];
		LocalDate end = localDate[1];
		totalDays = ChronoUnit.DAYS.between(start, end);
		totalDays = totalDays + 1;
		totalWeekEndDays = getTotalWeekEndDays(start, end); 
		totalWeekDays = totalDays - totalWeekEndDays;
	}
		
	//private long getTotalWeekEndDays(LocalDate start, LocalDate end) {
		// TODO Auto-generated method stub
		//return 0;
	//}
	
	public ArrayList<Hotel> findCheapestHotels() {
		HashMap<Hotel,Integer> hotelMap = (HashMap<Hotel, Integer>)hotelList.stream().collect(Collectors.toMap(hotel -> hotel, hotel -> (int)(hotel.getWeekDayRate() * totalWeekDays + hotel.getWeekEndRate() * totalWeekEndDays)));
		int minimumRate = hotelMap.values().stream().min(Integer :: compare).get() ;
		ArrayList<Hotel> cheapestHotel = (ArrayList<Hotel>)hotelMap.entrySet().stream().filter(data -> data.getValue() == minimumRate).map(data -> data.getKey()).collect(Collectors.toList());
		return cheapestHotel;
	}
	
	public int getTotalWeekEndDays(LocalDate start, LocalDate end) {
		long weekEndDays = 0;
		LocalDate next = start.minusDays(1);
		//iterate from start date to end date
		while ((next = next.plusDays(1)).isBefore(end.plusDays(1))) {
			if(next.getDayOfWeek().toString().equals("SATURDAY") || next.getDayOfWeek().toString().equals("SUNDAY")) {
				totalWeekEndDays++;
			}
		}
		return (int)totalWeekEndDays;	
	}
	public static void main(String[] args) {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("*******************Welcome to Hotel Reservation System*******************");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("enter the type of customer you are(Regular,Reward)=");
		String customerType=input.next();
		HotelReservation hotel = new HotelReservation();
		hotel.addHotel(customerType);
		
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("the hotel is diplayed according to the cost :");
		ArrayList<Hotel> cheapest = hotel.findCheapestHotels();
		cheapest.forEach(System.out :: println);
	
	}
}