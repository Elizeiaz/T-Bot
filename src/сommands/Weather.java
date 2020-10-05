package сommands;

public class Weather implements Command {
    @Override
    public String execute() {
        return "It's weather";
    }
}
