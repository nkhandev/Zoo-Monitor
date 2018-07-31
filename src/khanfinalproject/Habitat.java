package khanfinalproject;

/**
 * @author Niaz
 */
public class Habitat {
   
   // default constructor
   public Habitat() {
   }
   
   // Setup the private attributes and getters and setters
   private String species;
   private String temperature;
   private String foodSource;
   private String cleanliness;
   private String habitatAlert;

   public String getSpecies() {
      return species;
   }

   public void setSpecies(String species) {
      this.species = species;
   }

   public String getTemperature() {
      return temperature;
   }

   public void setTemperature(String temperature) {
      this.temperature = temperature;
   }

   public String getFoodSource() {
      return foodSource;
   }

   public void setFoodSource(String foodSource) {
      this.foodSource = foodSource;
   }

   public String getCleanliness() {
      return cleanliness;
   }

   public void setCleanliness(String cleanliness) {
      this.cleanliness = cleanliness;
   }

   public String getHabitatAlert() {
      return habitatAlert;
   }

   public void setHabitatAlert(String habitatAlert) {
      this.habitatAlert = habitatAlert;
   }
}
