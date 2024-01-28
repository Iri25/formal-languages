import logic.Program;
import ui.UserInterface;

public class Main {


    public static void main(String[] args) {
        Program program = new Program();

        UserInterface userInterface = new UserInterface(program);
        userInterface.start();

    }
}
