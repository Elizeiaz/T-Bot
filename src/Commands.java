public class Commands {
    public Object getCommand(Object command) {
        return command;
    }

    public String start() {
        return "Привет, я бот!";
    }

    public String help() {
        return "Help";
    }

    public String notExist() {
        return "This command isn't exist";
    }

    public String weather() {
        return "+12";
    }
}
