import java.util.*;

abstract public class Player {
    protected String[] bet = { "묵", "찌", "빠" };
    protected String name; // 이름
    protected String lastBet = null; // 최근에 낸 값

    protected Player(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public String getBet() { return lastBet; }
    abstract public String next(); // 호출 시 선수가 묵찌빠 결정해서 리턴
}

class Human extends Player {
    private Human(String name) {
        super(name); // 슈퍼클래스에 접근
    }

    @Override
    public String next() { // 묵, 찌, 빠 중 입력
        Scanner scanner = new Scanner(System.in);

        System.out.println(getName() + ">>");

        while (true) {
            this.lastBet = scanner.next();

            if (this.lastBet.equals("묵") || this.lastBet.equals("찌") || this.lastBet.equals("빠")) {
                return lastBet;
            }
        }

    }
}

class Game {
    Scanner scanner = new Scanner(System.in);
    Player[] players;

    public void run() {
        players = new Player[2];


    }

    private Player[] start(Player[] players) {
        System.out.println("묵찌빠 게임을 시작합니다.");

        System.out.print("선수이름을 입력하세요>> ");
        String human = scanner.next();
        System.out.print("선수이름을 입력하세요>> ");
        String computer = scanner.next();

        players[0] = new Human(human);
    }
}

public class MGPApp {
    public static void main(String[] args) {

    }
}