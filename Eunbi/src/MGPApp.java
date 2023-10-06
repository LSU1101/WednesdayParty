import java.util.Random;
import java.util.Scanner;

abstract class Player {
    protected String bet[] = {"묵", "찌", "빠"};
    protected String name; // 선수 이름
    protected String lastBet = null; // 선수가 최근에 낸 값

    protected Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getBet() {
        return lastBet;
    }

    abstract public String next();
    // 이 메소드가 호출되면 선수가 묵, 찌, 빠 중에서 1개를 결정하여 리턴한다.
}

class Human extends Player{

    protected Human(String name) {
        super(name);
    }

    public String next(){
        Scanner scanner = new Scanner(System.in);
        System.out.print(name + ">>");
        while (true){
            this.lastBet = scanner.next();
            if(this.lastBet.equals("묵")|| this.lastBet.equals("찌") || this.lastBet.equals("빠"))
                break;
            else{
                System.out.println("묵, 찌, 빠 중에서 입력하세요");
            }
        }
        return lastBet;
    } // 이 메소드가 호출되면 선수가 묵, 찌, 빠 중
}

class Computer extends Player{
    Random r = new Random();
    protected Computer(String name) {
        super(name);
    }

    public String next(){
        System.out.println(name+">> 결정하였습니다.");
        this.lastBet = bet[r.nextInt(3)];
        return lastBet;
    } // 이 메소드가 호출되면 선수가 묵, 찌, 빠 중
}

class Game{
    Player [] players;
    Game() {

    }
    public void run(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("묵찌빠 게임을 시작합니다.");
        System.out.print("선수이름 입력 >>");
        String human = scanner.next();
        System.out.print("컴퓨터이름 입력 >>");
        String computer = scanner.next();

        players = new Player[2];
        players[0] = new Human(human);
        players[1] = new Computer(computer);

        System.out.println("2명의 선수를 생성 완료하였습니다.");
        System.out.println();
        int owner = 0; //owner를 정수 0으로 설정, 초기 owner가 human

        while(true){
            players[owner].next();          //
            players[(owner*-1)+1].next();  //owner가 0이면 1, 1이면 0
            System.out.println(players[0].getName() +" : "+ players[0].getBet()+", "+players[1].getName() +" : "+ players[1].getBet()+",");
            System.out.println();

            if (players[owner].getBet().equals(players[(owner*-1)+1].getBet())){
                System.out.println(players[owner].name+"이 이겼습니다.");
                System.out.println("게임을 종료합니다.");
                break;
            }

            else if (players[owner].getBet().equals("묵")&&players[(owner*-1)+1].getBet().equals("빠")
                    || players[owner].getBet().equals("찌")&&players[(owner*-1)+1].getBet().equals("묵")
                    || players[owner].getBet().equals("빠")&&players[(owner*-1)+1].getBet().equals("찌"))
                owner = (owner*-1)+1;
        }
    }
}

public class MGPApp {
    public static void main(String[] args){
        Game game = new Game();
        game.run();
    }
}