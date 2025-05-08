public class Main {
    public static void main(String[] args) {

        MenuUI menu = new MenuUI();
        while(MenuUI.running){
            menu.displayMenu();
        }
    }
}
