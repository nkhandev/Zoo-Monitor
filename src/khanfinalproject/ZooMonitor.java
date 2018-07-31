package khanfinalproject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * @author Niaz Khan
 */
public class ZooMonitor {

   // Create global use arrays
   static Animal[] animalArray;
   static Habitat[] habitatArray;
   
   public static void main(String[] args) {
      // Process the data files
      // if files are not processed, exit the program
      if(!processFiles()) {
         return;
      }   
      
      // Setup Menu variables
      Object[] outerButtons = {"Animal", "Habitat", "Exit"};
      String outerMenuTitle = " Zoo Monitoring System ";
      String outerMenuInstructions = "Choose to monitor an animal, a habitat or Exit.";
      int outerMenuChoice = -1;
      Object[] innerAnimalMenuOptions = new Object[animalArray.length + 1];
      String innerAnimalMenuTitle = " Zoo Monitoring System - Animal Details Menu ";
      String innerAnimalMenuInstructions = "Choose which animal to monitor.";
      int innerAnimalMenuChoice = -1;
      Object[] innerHabitatMenuOptions = new Object[habitatArray.length + 1];
      String innerHabitatMenuTitle = " Zoo Monitoring System - Habitat Details Menu ";
      String innerHabitatMenuInstructions = "Choose which habitat to monitor.";
      int innerHabitatMenuChoice = -1;
      int innerMenuChoice = -1;
      
      // Build out options for inner animal menu from array
      for(int counter=0; counter < animalArray.length; ++counter) {
         innerAnimalMenuOptions[counter] = animalArray[counter].getSpecies() + " details";
      }
      innerAnimalMenuOptions[animalArray.length] = "Exit to Previous Menu";
      
      // Build out options for inner habitat menu from array
      for(int counter=0; counter < habitatArray.length; ++counter) {
         innerHabitatMenuOptions[counter] = habitatArray[counter].getSpecies() + " habitat";
      }
      innerHabitatMenuOptions[habitatArray.length] = "Exit to Previous Menu";
      
      // Loop until the user exits
      while(outerMenuChoice != 2) {
         innerAnimalMenuChoice = -1;
         innerHabitatMenuChoice = -1;
         // Show the outer menu and obtain user input
         outerMenuChoice = JOptionPane.showOptionDialog(null, 
              outerMenuInstructions, outerMenuTitle, 
              JOptionPane.YES_NO_OPTION, 
              JOptionPane.PLAIN_MESSAGE, 
              null, 
              outerButtons, outerButtons[0]);
        
         if (outerMenuChoice == 0) {
            // Animal Branch
            // Loop inner animal menu until user chooses to exit to previous menu
            while(innerAnimalMenuChoice != animalArray.length){
               // Show Animal inner menu and obtain user input
               innerAnimalMenuChoice = JOptionPane.showOptionDialog(null, 
                  innerAnimalMenuInstructions, innerAnimalMenuTitle, 
                  JOptionPane.YES_NO_OPTION, 
                  JOptionPane.PLAIN_MESSAGE, 
                  null, 
                  innerAnimalMenuOptions, innerAnimalMenuOptions[0]);
               
               // Display chosen animal information from animal object
               if(innerAnimalMenuChoice < animalArray.length) {
                  String animalAlert = "** Alert for " + animalArray[innerAnimalMenuChoice].getName() +
                          " the " + animalArray[innerAnimalMenuChoice].getSpecies() + 
                          " ** \n\n" + animalArray[innerAnimalMenuChoice].getAnimalAlert() + "\n";
                  String animalInfo = "Animal: " + animalArray[innerAnimalMenuChoice].getSpecies() + "\n" +
                          "Name: " + animalArray[innerAnimalMenuChoice].getName() + "\n" +
                          "Age: " + animalArray[innerAnimalMenuChoice].getAge() + "\n" + 
                          "Health Concerns: " + animalArray[innerAnimalMenuChoice].getHealthConcerns() + "\n" +
                          "Feeding Schedule: " + animalArray[innerAnimalMenuChoice].getFeedingSchedule();
                  String animalTitle = " Zoo Monitoring System - " + 
                          animalArray[innerAnimalMenuChoice].getSpecies() + 
                          " Information";
                  // Show any alert information
                  if(animalArray[innerAnimalMenuChoice].getAnimalAlert() != null){
                     JOptionPane.showMessageDialog(null,
                        animalAlert,
                        " ** ALERT ** (Zoo Monitoring System)",
                        JOptionPane.WARNING_MESSAGE);
                     }
                  JOptionPane.showMessageDialog(null,
                     animalInfo,
                     animalTitle,
                     JOptionPane.PLAIN_MESSAGE);
               }
            }
         } else if(outerMenuChoice == 1) {
            // Habitat Branch
            // Loop inner habitat menu until user chooses to exit to previous menu
            while(innerHabitatMenuChoice != habitatArray.length){
               // Show Habitat inner menu and obtain user input
               innerHabitatMenuChoice = JOptionPane.showOptionDialog(null, 
                  innerHabitatMenuInstructions, innerHabitatMenuTitle, 
                  JOptionPane.YES_NO_OPTION, 
                  JOptionPane.PLAIN_MESSAGE, 
                  null, 
                  innerHabitatMenuOptions, innerHabitatMenuOptions[0]);
               
               // Display chosen habitat information from habitat object
               if(innerHabitatMenuChoice < habitatArray.length) {
                  String habitatAlert = "** Alert for " + habitatArray[innerHabitatMenuChoice].getSpecies() +
                          " habitat " + 
                          " ** \n\n" + habitatArray[innerHabitatMenuChoice].getHabitatAlert() + "\n";
                  String habitatInfo = "Habitat: " + habitatArray[innerHabitatMenuChoice].getSpecies() + "\n" +
                          "Temperature: " + habitatArray[innerHabitatMenuChoice].getTemperature() + "\n" +
                          "Food Source: " + habitatArray[innerHabitatMenuChoice].getFoodSource() + "\n" + 
                          "Cleanliness: " + habitatArray[innerHabitatMenuChoice].getCleanliness() + "\n";
                  String habitatTitle = " Zoo Monitoring System - " + 
                          habitatArray[innerHabitatMenuChoice].getSpecies() + 
                          " Information";
                  // Show any alert information
                  if(habitatArray[innerHabitatMenuChoice].getHabitatAlert() != null){
                     JOptionPane.showMessageDialog(null,
                        habitatAlert,
                        " ** ALERT ** (Zoo Monitoring System)",
                        JOptionPane.WARNING_MESSAGE);
                     }
                  JOptionPane.showMessageDialog(null,
                     habitatInfo,
                     habitatTitle,
                     JOptionPane.PLAIN_MESSAGE);
               }
            }
         }
      }
   }
   
   
   // Method for reading and processing data flat files
   // returns true if successful and false if not
   public static boolean processFiles() {
      
      // Working method variables
      String inputString;
      int numberOfAnimals = 0;
      int loadedAnimalCounter = 0;
      int linesInFilePerAnimal = 4;
      int lineCounter = 0;
      int numberOfHabitats = 0;
      int loadedHabitatCounter = 0;
      int linesInFilePerHabitat = 3;
      
      // Setup file input stream for animals.txt file
      FileInputStream animalsFileByteStream;
      try {
         animalsFileByteStream = new FileInputStream("animals.txt");
      } catch (IOException error) {
         // Could not open the animals data file so display a proper message and return false
         JOptionPane.showMessageDialog(null, "The file animals.txt was not found.  Exiting Program.");
         return false;
      }
      Scanner animalsArrayCounterScanner = new Scanner(animalsFileByteStream);
    
      // Read animals file to determine how large the animal array needs to be
      while(animalsArrayCounterScanner.hasNextLine()) {
         inputString = animalsArrayCounterScanner.nextLine();
         if(inputString.startsWith("Details on")) {
            ++numberOfAnimals;
         }
      }

      // Reset file input stream for animals.txt
      try {
         animalsFileByteStream = new FileInputStream("animals.txt");
      } catch (IOException error) {
         // Could not open the animals data file so display a proper message and return false
         JOptionPane.showMessageDialog(null, "The file animals.txt was not found.  Exiting Program.");
         return false;
      }
      Scanner animalsObjectScanner = new Scanner(animalsFileByteStream);
      
      // Setup array of Animal objects
      animalArray = new Animal[numberOfAnimals];
      
      // Setup Animal objects from file and load animalArray
      while(loadedAnimalCounter < numberOfAnimals) {
         inputString = animalsObjectScanner.nextLine();
         
         if(inputString.startsWith("Animal -")) {
            // Build animal object
            animalArray[loadedAnimalCounter] = new Animal();
            animalArray[loadedAnimalCounter].setSpecies(inputString.substring(9));
            for(lineCounter = 1; lineCounter <= linesInFilePerAnimal; lineCounter++) {
               inputString = animalsObjectScanner.nextLine();
               switch(lineCounter){
                  case 1: 
                     // import name
                     animalArray[loadedAnimalCounter].setName(inputString.substring(6));
                     break;
                  case 2:
                     // import age
                     animalArray[loadedAnimalCounter].setAge(Integer.parseInt(inputString.substring(5)));
                     break;
                  case 3:
                     // import health concerns
                     if(inputString.startsWith("*****")){
                        animalArray[loadedAnimalCounter].setHealthConcerns(inputString.substring(22));
                        animalArray[loadedAnimalCounter].setAnimalAlert(inputString.substring(5));
                     } else {
                        animalArray[loadedAnimalCounter].setHealthConcerns(inputString.substring(17));
                     }
                     break;
                  case 4:
                     // import feeding schedule
                     if(inputString.startsWith("*****")){
                        animalArray[loadedAnimalCounter].setFeedingSchedule(inputString.substring(23));
                        animalArray[loadedAnimalCounter].setAnimalAlert(inputString.substring(5));
                     } else {
                        animalArray[loadedAnimalCounter].setFeedingSchedule(inputString.substring(18));
                     }
                     break;
               }
            }
            ++loadedAnimalCounter;
         }
      }
      
      // close the animals.txt file
      try {
         animalsFileByteStream.close();
      } catch (IOException error) {
         // Could not close the animals data file so display a proper message and return false
         JOptionPane.showMessageDialog(null, "The file animals.txt could not be closed.  Exiting Program.");
         return false;
      }
       
      // Setup file input stream for habitats.txt file
      FileInputStream habitatsFileByteStream;
      try {
         habitatsFileByteStream = new FileInputStream("habitats.txt");
      } catch (IOException error) {
         // Could not open the habitats data file so display a proper message and return false
         JOptionPane.showMessageDialog(null, "The file habitats.txt was not found.  Exiting Program.");
         return false;
      }
      Scanner habitatsArrayCounterScanner = new Scanner(habitatsFileByteStream);
    
      // Read habitats file to determine how large the habitat array needs to be
      while(habitatsArrayCounterScanner.hasNextLine()) {
         inputString = habitatsArrayCounterScanner.nextLine();
         if(inputString.startsWith("Details on")) {
            ++numberOfHabitats;
         }
      }

      // Reset file input stream for habitats.txt
      try {
         habitatsFileByteStream = new FileInputStream("habitats.txt");
      } catch (IOException error) {
         // Could not open the habitats data file so display a proper message and return false
         JOptionPane.showMessageDialog(null, "The file habitats.txt was not found.  Exiting Program.");
         return false;
      }
      Scanner habitatsObjectScanner = new Scanner(habitatsFileByteStream);
      
      // Setup array of Habitat objects
      habitatArray = new Habitat[numberOfHabitats];
      
      // Setup Habitat objects from file and load habitatArray
      while(loadedHabitatCounter < numberOfHabitats) {
         inputString = habitatsObjectScanner.nextLine();
         
         if(inputString.startsWith("Habitat -")) {
            // Build habitat object
            habitatArray[loadedHabitatCounter] = new Habitat();
            habitatArray[loadedHabitatCounter].setSpecies(inputString.substring(10));
            for(lineCounter = 1; lineCounter <= linesInFilePerHabitat; lineCounter++) {
               inputString = habitatsObjectScanner.nextLine();
               switch(lineCounter){
                  case 1: 
                     // import Temperature
                     habitatArray[loadedHabitatCounter].setTemperature(inputString.substring(13));
                     break;
                  case 2:
                     // import Food Source
                     if(inputString.startsWith("*****")){
                        habitatArray[loadedHabitatCounter].setFoodSource(inputString.substring(18));
                        habitatArray[loadedHabitatCounter].setHabitatAlert(inputString.substring(5));
                     } else {
                        habitatArray[loadedHabitatCounter].setFoodSource(inputString.substring(13));
                     }
                     break;
                  case 3:
                     // import Cleanliness
                     if(inputString.startsWith("*****")){
                        habitatArray[loadedHabitatCounter].setCleanliness(inputString.substring(18));
                        habitatArray[loadedHabitatCounter].setHabitatAlert(inputString.substring(5));
                     } else {
                        habitatArray[loadedHabitatCounter].setCleanliness(inputString.substring(13));
                     }
                     break;
               }
            }
            ++loadedHabitatCounter;
         }
      }
      
      // close the habitats.txt file
      try {
         habitatsFileByteStream.close();
      } catch (IOException error) {
         // Could not close the habitats data file so display a proper message and return false
         JOptionPane.showMessageDialog(null, "The file habitats.txt could not be closed.  Exiting Program.");
         return false;
      }
      
      // return true to signify the files were opened and processed successfully
      return true;
   }
   
}
