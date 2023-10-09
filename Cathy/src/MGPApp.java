import java.util.*;

abstract class Player {

    protected String [] bet = {"묵", "찌", "빠" };
    protected String name; // 선수 이름
    protected String lastBet = null; // 선수가 최근에 낸 값
    protected Player(String name) { this.name = name; }
    public String getName() { return name; }
    public String getBet() { return lastBet; }
    abstract public String next(); // 호출 시 선수가 묵찌빠 결정해서 리턴
}

class Human extends Player {

    protected Human(String name) {
        super(name); // 슈퍼클래스 멤버에 접근
    }

    @Override // 오버라이딩
    public String next() { // human 사용자에게 묵찌빠 입력 받기

        Scanner scanner = new Scanner(System.in);

        System.out.print(name +">>");

        while(true) {

            this.lastBet = scanner.next();

            if(this.lastBet.equals("묵")|| this.lastBet.equals("찌") || this.lastBet.equals("빠"))
                return lastBet;

            System.out.println("경고!! 묵, 찌, 빠 중에 입력하세요.");
            System.out.println();
            System.out.print(getName() + ">>");
        }
    }
}

class Computer extends Player {

    public Computer(String name) {
        super(name);
    }

    @Override // 오버라이딩
    public String next() { // 랜덤으로 묵찌빠 저장하기

        System.out.println(name +">> 결정하였습니다.");
        int n = (int)(Math.random() * 3);
        this.lastBet = bet[n];

        return lastBet;
    }
}

class Game {

    Player[] players;

    public void run() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("묵찌빠 게임을 시작합니다.");

        players = new Player[2];

        System.out.print("선수이름 입력 >>");
        String human = scanner.next();
        System.out.print("컴퓨터이름 입력 >>");
        String computer = scanner.next();

        players[0] = new Human(human);
        players[1] = new Computer(computer);

        System.out.println("2명의 선수를 생성 완료하였습니다.");
        System.out.println();

        int owner = 0; // 0이면 human , 1이면 computer
        boolean again = true; // 비김 판단

        while(true) {

            players[owner].next(); // owner 먼저 결정
            players[(owner * (-1)) + 1].next();  // owner 가 0이면 1, 1이면 0으로 변경되는 식

            // players 각자 낸 것 출력
            System.out.println(players[0].getName() + " : " + players[0].getBet() + ", " + players[1].getName() + " : " + players[1].getBet());
            System.out.println();

            // 둘다 낸 것이 같으면
            if (players[owner].getBet().equals(players[(owner * (-1)) + 1].getBet())){

                if (again) { // 처음이 비긴 것이면 다시 내도록 하기

                    System.out.println("비김!! 같은 '" + players[owner].getBet() + "' 입니다. 다시 내세요.");
                    System.out.println();
                }

                else { // 처음이 아닌데 비긴 것이면 owner 의 승리로 종료

                    System.out.println(players[owner].name + "이(가) 이겼습니다.");
                    System.out.println("게임을 종료합니다.");

                    break;
                }
            }

            // owner 가 졌다면
            else if (players[owner].getBet().equals("묵") && players[(owner * (-1)) + 1].getBet().equals("빠") ||
                    players[owner].getBet().equals("찌") && players[(owner * (-1)) + 1].getBet().equals("묵") ||
                    players[owner].getBet().equals("빠") && players[(owner * (-1)) + 1].getBet().equals("찌")) {

                owner = (owner * (-1)) + 1; // owner 변경
                again = false; // 비겼을 시 승자가 나오도록 변경
            }

            // owner 가 이겼다면
            else
                again = false; // 비겼을 시 승자가 나오도록 변경
        }
    }
}

public class MGPApp {

    public static void main(String[] args) {

        Game game = new Game();
        game.run();
    }
}