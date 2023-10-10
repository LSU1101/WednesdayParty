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
            System.out.print(getName() + ">> ");
            this.lastBet = scanner.next(); // 입력 받기

            if (this.lastBet.equals("묵") || this.lastBet.equals("찌") || this.lastBet.equals("빠")) { // 사용자가 올바르게 입력했을 경우
                return lastBet; // 입력한 값 리턴
            }

            // 올바르지 않게 입력했을 경우 실행
            System.out.println("묵, 찌, 빠 중에서 입력하세요.");
        }
    }
}

class Computer extends Player {
    public Computer(String name) {
        super(name);
    }

    @Override
    public String next() {
        int choose = (int)(Math.random() * 3); // 0 ,1 ,2 중 랜덤으로 선택
        this.lastBet = bet[choose]; // 랜덤으로 선택된 choose 값으로 묵, 찌, 빠 중 선택

        System.out.println(getName() + ">> 결정하였습니다."); // 결정 완료 메시지

        return lastBet; // 선택된 값 리턴
    }
}

class Game {
    Scanner scanner = new Scanner(System.in);
    Player[] players; // 배열 생성

    public void run() {
        players = new Player[2];
        init(players); // 선수 생성
        gamePlay();
        System.out.println("게임을 종료합니다.");
        scanner.close();
    }

    private void init(Player[] players) {
        System.out.println("묵찌빠 게임을 시작합니다.");

        System.out.print("선수이름을 입력하세요>> ");
        String human = scanner.next();
        System.out.print("컴퓨터이름을 입력하세요>> ");
        String computer = scanner.next();

        // 입력 받은 이름으로 생성
        players[0] = new Human(human);
        players[1] = new Computer(computer);

        System.out.println(players.length + " 명의 선수를 생성 완료하였습니다.\n");
    }

    private void gamePlay() {
        int owner = 0; // 0 = human, 1 = computer

        while (true) {
            // 입력 받기
            players[owner].next();
            players[(owner * -1) + 1].next();

            // 무엇을 냈는지 출력
            for (int i = 0; i < 2; i++) {
                System.out.print(players[i].getName() + ": " + players[i].getBet() + ", ");
            }
            System.out.println("\n");

            if (players[0].getBet().equals(players[1].getBet()) && owner == 0) { // owner가 0일 때 같은 것을 냈을 경우 0이 승리
                System.out.println(players[0].getName() + "이(가) 이겼습니다!");
                break;
            } else if (players[0].getBet().equals(players[1].getBet()) && owner == 1) { // owner가 1일 때 같은 것을 냈을 경우 1이 승리
                System.out.println(players[1].getName() + "이(가) 이겼습니다!");
                break;
            } else if (players[owner].getBet().equals("묵") && players[(owner * -1) + 1].getBet().equals("빠") ||
                    players[owner].getBet().equals("찌") && players[(owner * -1) + 1].getBet().equals("묵") ||
                    players[owner].getBet().equals("빠") && players[(owner * -1) + 1].getBet().equals("찌")) { // 서로 다른 것을 내고 owner가 가위바위보에서 졌을 경우 오너를 변경
                owner = owner * -1 + 1;
            }
        }
    }
}

public class MGPApp {
    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}