package сommands;

public class unexpectedCommand implements Command{
    @Override
    public String execute() {
        return getInfo();
    }

    private String getInfo(){
        return "Несуществующая команда\nДля получения справки введите /help";
    }
}
