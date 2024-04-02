package com.example.demo.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.City;
import com.example.demo.Entity.Complex;
import com.example.demo.Entity.Reservation;
import com.example.demo.Entity.Stadium;
import com.example.demo.Entity.TimeSlot;
import com.example.demo.Entity.User;
import com.example.demo.Reposotory.CategoryRepo;
import com.example.demo.Reposotory.CityRepo;
import com.example.demo.Reposotory.ComplexRepo;
import com.example.demo.Reposotory.ReservationRepo;
import com.example.demo.Reposotory.StadiumRepo;
import com.example.demo.Reposotory.TimeSlotRepo;
import com.example.demo.Reposotory.UserRepo;

@Service
@Transactional
public class ComplexInitServiceImp implements IComplexInitService {
	@Autowired
	private CityRepo cityrepo ; 
	@Autowired
	private ComplexRepo complexRepo ;  
	@Autowired
	private StadiumRepo stadiumRepo; 
	@Autowired
	private CategoryRepo categoryRepo; 
	@Autowired
	private UserRepo userRepo; 
	@Autowired
	private TimeSlotRepo timeSlotRepo ; 
	@Autowired
	private ReservationRepo reservationRepo ; 



	
	


	
	@Override
	public void initCity() {
	Stream.of("Tunis","Sousse","Ariana","Ben arous","Bizart","Monastir")
	.forEach(nameCity->
	{
		City city = new City(); 
		city.setName(nameCity);	
		cityrepo.save(city); 
		
	});

	}
	

	@Override
    public void initComplex() {
        cityrepo.findAll().forEach(city -> {
            Stream.of("ooredoo", "Complex Lac1", "Ariana Complex", "Complex Nechba", "Footbal Complex", "Big Complex", "Ariana Complex", "Tunis Complex")
                    .forEach(nameComplex -> {
                        if (!complexRepo.existsByName(nameComplex)) {
                            Complex complex = new Complex();
                            complex.setName(nameComplex);
                            complex.setStadiumNumber(4 + (int) (Math.random() * 4));
                            complex.setPhoto(nameComplex.replaceAll(" ", "") + ".jpg");

                         // Fetch a random category ID
                            Long randomCategoryId = categoryRepo.findRandomCategoryId();
                            if (randomCategoryId != null) {
                                Category randomCategory = new Category();
                                randomCategory.setId(randomCategoryId);
                                complex.setCategory(randomCategory);
                            }
                            complex.setCity(city);
                            complexRepo.save(complex);
                        }
                    });
        });
    }






	




	
	@Override
	public void initStadium() {
	    List<Integer> priceList = Arrays.asList(60, 70, 75, 80, 90);
	    List<Integer> numberOfPlacesList = Arrays.asList(20, 18, 16, 14, 12, 10, 8);
	    List<Integer> rankCountList = Arrays.asList(1, 2, 3, 4, 5);
	    List<String> stadiumPhotoNames = Arrays.asList("stadium1", "stadium11", "stadium12", "stadium13");
	    List<String> DiscriptionList = Arrays.asList("Discription 1 of the stadium siza,location and the options...", "Discription 2 of the stadium  siza,location and the options...", "Discription 3 of the stadium siza,location and the options...", "Discription 4 of the stadium siza,location and the options...", "Discription 5 of the stadium siza,location and the options...");
	    complexRepo.findAll().forEach(complex -> {
	        for (int i = 0; i < complex.getStadiumNumber(); i++) {
	            Stadium stadium = new Stadium();
	            stadium.setName("Stadium " + (i + 1));
	            stadium.setComplex(complex);
	            // Set the 'price' field to a random value from priceList
	            int randomPriceIndex = (int) (Math.random() * priceList.size());
	            stadium.setPrice(priceList.get(randomPriceIndex));
	            // Set the 'numberOfPlaces' field to a random value from numberOfPlacesList
	            int randomPlacesIndex = (int) (Math.random() * numberOfPlacesList.size());
	            stadium.setNumberOfPlaces(numberOfPlacesList.get(randomPlacesIndex));
	            // Set the 'rankCount' field to a random value from rankCountList
	            int randomRankCountIndex = (int) (Math.random() * rankCountList.size());
	            //stadium.setRankCount(rankCountList.get(randomRankCountIndex));
	            // Set the 'photos' field to a list of random photo names (e.g., 1 to 3 photos per stadium)
	            int numPhotos = (int) (1 + Math.random() * 3); // Adjust the number of photos as needed
	            List<String> stadiumPhotos = new ArrayList<>();
	            for (int j = 0; j < numPhotos; j++) {
	                int randomPhotoIndex = (int) (Math.random() * stadiumPhotoNames.size());
	                stadiumPhotos.add(stadiumPhotoNames.get(randomPhotoIndex) + ".jpg");
	            }
	            stadium.setPhotos(stadiumPhotos);
	         // Set the 'description' field to a random value from DiscriptionList
	            int randomDescriptionIndex = (int) (Math.random() * DiscriptionList.size());
	            stadium.setDiscription(DiscriptionList.get(randomDescriptionIndex));   
	            stadiumRepo.save(stadium);
	        }
	    });
	}

	
	
	
	@Override
	public void initcategory() 
	{
	    Stream.of("FOOTBALL","BASKETBALL","HANDBALL","TENNIS","SWIMMING","GOLF")
	        .forEach(cat-> 
	        {
	            Category category = new Category(); 
	            category.setCategoryName(cat) ; 
	            categoryRepo.save(category); 
	        });
	}
	
	@Override
	public List<Stadium> getStadiumsByComplexId(Long complexId) {
        // Fetch all stadiums from the repository
        List<Stadium> allStadiums = stadiumRepo.findAll();

        // Filter stadiums by the complex ID
        List<Stadium> stadiumsByComplexId = allStadiums.stream()
                .filter(stadium -> stadium.getComplex() != null && stadium.getComplex().getId().equals(complexId))
                .collect(Collectors.toList());

        return stadiumsByComplexId;
    }
	


	
	
	public void initTimeSlots() {
        List<LocalTime> startTimes = Arrays.asList(LocalTime.parse("09:00"), LocalTime.parse("10:30"), LocalTime.parse("12:00"), LocalTime.parse("13:30"), LocalTime.parse("15:00"), LocalTime.parse("16:30"), LocalTime.parse("18:00"), LocalTime.parse("19:30"), LocalTime.parse("20:00"));
        List<LocalTime> endTimes = Arrays.asList(LocalTime.parse("10:30"), LocalTime.parse("12:00"), LocalTime.parse("13:30"), LocalTime.parse("15:00"), LocalTime.parse("16:30"), LocalTime.parse("18:00"), LocalTime.parse("19:30"), LocalTime.parse("20:00"), LocalTime.parse("21:30"));

        if (startTimes.size() != endTimes.size()) {
            // Handle mismatched start and end times
            return;
        }
        List<TimeSlot> timeSlots = new ArrayList<>();

        Random random = new Random();
        long maxStadiumId = 100;
        for (int i = 0; i < startTimes.size(); i++) {
            TimeSlot timeSlot = new TimeSlot();
            timeSlot.setStartTime(startTimes.get(i));
            timeSlot.setEndTime(endTimes.get(i));
         // Generate a random stadium ID
            long randomStadiumId = random.nextInt((int) maxStadiumId) + 1;
            // Fetch a random stadium from the database using the repository
            Optional<Stadium> randomStadium = stadiumRepo.findById(randomStadiumId);
            if (randomStadium.isPresent()) {
                timeSlot.setStadium(randomStadium.get());
                timeSlots.add(timeSlot);
            }
            
            timeSlots.add(timeSlot);
        }
        timeSlotRepo.saveAll(timeSlots);
    }
	
	
	public void initUser() {
        Random random = new Random();
        List<String> names = Arrays.asList("anissa", "marwen", "baya", "hamed", "nechba","kimo","miro","zinouba","sitlkol","l8ali");
        List<Character> accountTypes = Arrays.asList('A', 'U', 'P'); 
        List<String> emailDomains = Arrays.asList("outlook.fr", "gmail.com");

       Stream.of(names.toArray(new String[0]))
                .forEach(name -> {
                    User user = new User();
                    user.setName(name);
                    user.setLastName("LastName"); // Set a constant last name or generate it randomly
                    //user.setStatusAcount(random.nextBoolean());
                    user.setAcount(accountTypes.get(random.nextInt(accountTypes.size()))); 
                    user.setEmail(name + "@" + emailDomains.get(random.nextInt(emailDomains.size())));
                    //user.setPhoneNumber(generateRandomPhoneNumber());
                    String randomPhoto = "https://bootdey.com/img/Content/avatar/avatar" + (random.nextInt(10) + 1) + ".png";
                    user.setPhoto(randomPhoto);
                    user.setPassword("admin"); // Set a default password
                   

                    userRepo.save(user);
                });
    }
	
	public void initreservation() {
        List<Character> statusReservations = Arrays.asList('A', 'D', 'P');
        Random random = new Random();

        // Get all available stadiums, users, and time slots
        List<Stadium> stadiums = stadiumRepo.findAll();
        List<User> users = userRepo.findAll();
        List<TimeSlot> timeSlots = timeSlotRepo.findAll();
        List<Complex> complexes = complexRepo.findAll();

        // Check if there are stadiums, users, and time slots available
        if (stadiums.isEmpty() || users.isEmpty() || timeSlots.isEmpty()) {
            throw new IllegalStateException("No stadiums, users, or time slots available for reservations.");
        }

        // Generate random reservations
        Stream.of(1, 2, 3,4,5,6,7,8,9,10) // Change the number of reservations you want to generate
            .forEach(index -> {
                Reservation reservation = new Reservation();
                reservation.setStatusreservation(statusReservations.get(random.nextInt(statusReservations.size())));
                reservation.setStadium(stadiums.get(random.nextInt(stadiums.size())));
                reservation.setUser(users.get(random.nextInt(users.size())));
                reservation.setTimeSlot(timeSlots.get(random.nextInt(timeSlots.size())));
                reservation.setComplex(complexes.get(random.nextInt(complexes.size()))); // Assign a random complex

               reservationRepo.save(reservation);
            });
    }
}

	
	
	




	

	




