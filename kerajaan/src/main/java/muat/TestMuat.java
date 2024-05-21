public class TestMuat {
    public static void main(String[] args) {
        String pathFile = "../../../../../config/";
        pathFile += "gamestate.txt";
        Muat m = new Muat(pathFile);

        m.printResult();
    }
}