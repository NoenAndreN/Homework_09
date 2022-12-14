import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.StreamHandler;
import java.util.logging.SimpleFormatter;



class Animal<Name, Childs> {
    Name name;
    Childs childs;

    public Animal(Name name, Childs childs) {
        this.name = name;
        this.childs = childs;
    }
}

class CustomNumberException extends Exception {
    public CustomNumberException(String errorMessage) {
        super(errorMessage);
    }
}

public class Animals {

    static Logger LOGGER;

    static boolean checkInteger(String s) {

        boolean result = true;

        try  {
            Integer value = Integer.parseInt(s);
            if (value < 0) throw new CustomNumberException("Childs number cannot be negative");
        } catch (NumberFormatException ex) {
            System.out.println("Error! Enter integer number");
            result = false;
        } catch (CustomNumberException ex) {
            System.out.println(ex);
            result = false;
        } finally {
            return result;
        }
    }


    public static void main(String[] args) {

        try {
            String log_file = "Animals_Log.txt";

            LOGGER = Logger.getLogger(Animals.class.getName());
            FileHandler fh = new FileHandler(log_file);
            LOGGER.addHandler(fh);

            LOGGER.info("Begin work");

            System.out.println("Log file write to "+System.getProperty("user.dir")+"\\"+log_file);
        } catch(Exception ex) {
            System.out.println("Error initalize log "+ex);
        }


        Scanner in = new Scanner(System.in);

        ArrayList<Animal> arr = new ArrayList<>();

        arr.add(new Animal<String, Integer>("Dog",4));
        arr.add(new Animal<String, Integer>("Cat",2));
        arr.add(new Animal<String, Integer>("Rabbit",12));

        boolean quit = false;

        int menuItem;

        do {
            System.out.println("");
            System.out.println("Menu:");
            System.out.println("1. Show animals list");
            System.out.println("2. Add new animal to list");
            System.out.println("3. Exit");
            System.out.println("Select menu: ");

            try {
                menuItem = in.nextInt();
            } catch (Exception ex) {
                System.out.println("Error! Enter menu number");
                in.nextLine();
                continue;
            }

            switch (menuItem) {

                case 1:
                    System.out.println("List of animals:");

                    for (int i=0; i<arr.size(); i++)
                        System.out.println(arr.get(i).name+", childs: "+arr.get(i).childs);
                    break;

                case 2:
                    System.out.println("Enter animal name:");
                    String name = in.nextLine();
                    name = in.nextLine();

                    Integer childs;

                    while (true) {
                        System.out.println("Enter childs number:");
                        String s = in.nextLine();

                        if (checkInteger(s)) {
                            childs = Integer.parseInt(s);
                            break;
                        }
                    }

                    arr.add(new Animal<String, Integer>(name,childs));

                    System.out.println("Congrats! Animal " + name + " with childs " + childs + " added to list!");
                    break;

                case 3:
                    quit = true;
                    break;

                default:
                    System.out.println("Wrong menu item");
            }

        } while (!quit);

        in.close();
        System.out.println("Bye-bye");

    }
}