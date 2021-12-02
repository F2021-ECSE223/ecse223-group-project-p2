package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.model.Hotel;
import ca.mcgill.ecse.climbsafe.model.Member;

public class HotelController {
  public static void assignHotel(String email, String hotelName) throws InvalidInputException{
    Hotel hotel = Hotel.getWithName(hotelName);
    if(hotel == null) {
      throw new InvalidInputException("Hotel with name "+hotelName+" does not exist");
    }
    Member member = (Member) Member.getWithEmail(email);
    if(member == null) {
      throw new InvalidInputException("Member with email "+email+" does not exist");
    }
    if(!member.isHotelRequired()) {
      throw new InvalidInputException("Member with email "+email+" did not require a hotel");
    }
    member.getAssignment().setHotel(hotel);
  }
}
