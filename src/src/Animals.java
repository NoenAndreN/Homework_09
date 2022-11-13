import java.util.ArrayList;
import java.util.Scanner;



class Animal<Name, Childs> {
    Name name;
    Childs childs;

    public Animal(Name name, Childs childs) {
        this.name = name;
        this.childs = childs;
    }
}

public class Animals {

    static boolean checkInteger(String s) {
        try  {
            Integer value = Integer.parseInt(s);
            if (value < 0) return false;
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static void main(String[] args) {
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


            menuItem = in.nextInt();

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
                        } else {
                            System.out.println("Error! Enter integer number");
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