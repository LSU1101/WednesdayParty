import java.util.*;

abstract class Player {
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
    public Human(String name) {
        super(name); // 슈퍼클래스에 접근
    }

    @Override
    public String next() { // 묵, 찌, 빠 중 입력
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(getName() + ">>");
            this.lastBet = scanner.next();

            if (this.lastBet.equals("묵") || this.lastBet.equals("찌") || this.lastBet.equals("빠")) {
                return lastBet;
            }
            System.out.println("묵, 찌, 빠 중에서 입력하세요.");
            scanner.next();
        }
    }
}

class Computer extends Player {
    public Computer(String name) {
        super(name);
    }

    @Override
    public String next() {
        int choose = (int)(Math.random() * 3);
        this.lastBet = bet[choose];

        System.out.println(getName() + ">> 결정하였습니다.");

        return lastBet;
    }
}

class Game {
    Scanner scanner = new Scanner(System.in);
    Player[] players;

    public void run() {
        players = new Player[2];
        init(players);


    }

    private void init(Player[] players) {
        System.out.println("묵찌빠 게임을 시작합니다.");

        System.out.print("선수이름을 입력하세요>> ");
        String human = scanner.next();
        System.out.print("선수이름을 입력하세요>> ");
        String computer = scanner.next();

        players[0] = new Human(human);
        players[1] = new Computer(computer);

        System.out.println("두 명의 선수를 생성 완료하였습니다.");
    }
}

public class MGPApp {
    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}