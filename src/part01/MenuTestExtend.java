package part01;

public class MenuTestExtend extends Menu {
    public MenuTestExtend(String title, String options[]) {
        super(title, options);
        this.setExtraDetails("Hello!\n");
        this.getChoice();
    }

    public static void main(String[] args) {
        String title = "Test Menu";
        String options[] = {"Hello", "Goodbye"};
        
        MenuTestExtend myMenu = new MenuTestExtend(title, options);
        
    }
}