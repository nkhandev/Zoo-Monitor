package khanfinalproject;

/**
 * @author Niaz
 */
public class Animal {

   // Default constructor
   public Animal() {
   }
   
   // Setup the private attributes and getters and setters
   private String species;
   private String name;
   private int age;
   private String healthConcerns;
   private String feedingSchedule;
   private String animalAlert;

   public String getSpecies() {
      return species;
   }

   public void setSpecies(String species) {
      this.species = species;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getAge() {
      return age;
   }

   public void setAge(int age) {
      this.age = age;
   }

   public String getHealthConcerns() {
      return healthConcerns;
   }

   public void setHealthConcerns(String healthConcerns) {
      this.healthConcerns = healthConcerns;
   }

   public String getFeedingSchedule() {
      return feedingSchedule;
   }

   public void setFeedingSchedule(String feedingSchedule) {
      this.feedingSchedule = feedingSchedule;
   }
   
   public String getAnimalAlert() {
      return animalAlert;
   }

   public void setAnimalAlert(String animalAlert) {
      this.animalAlert = animalAlert;
   }
}
