import javax.swing.*;
import java.awt.*;

public class WestGridFrame extends JFrame {
    public WestGridFrame() {
        try { // CrossPlatform. Windows, MacOS와 같이 다양한 OS에서 같은 결과물을 보이기 위한 예외처리
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("West Grid 프레임"); // 타이틀 지정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = getContentPane(); // 컨텐트팬 알아내기
        container.setLayout(new BorderLayout()); // BorderLayout 사용 (동, 서, 남, 북, 중앙으로 분할)

        container.add(new LeftColorButtons(), BorderLayout.WEST); // 컬러 버튼을 서쪽에 부착
        container.add(new CenterRandomNumbers(), BorderLayout.CENTER); // 랜덤 출력 숫자를 중앙에 부착

        setSize(300, 300); // 창 크기를 300 X 300으로 지정
        setVisible(true); // 화면에 출력
    }

    class LeftColorButtons extends JPanel {
        public LeftColorButtons() {
            Color[] colors = {Color.RED, Color.GRAY, Color.BLUE, Color.YELLOW, Color.CYAN,
                    Color.DARK_GRAY, Color.PINK, Color.GREEN, Color.ORANGE, Color.MAGENTA }; // for 문을 위해 Color를 배열로 저장

            setLayout(new GridLayout(10, 1)); // 1열, 10행

            for (int i = 0; i < 10; i++) {
                JButton jbutton = new JButton();
                jbutton.setBackground(colors[i]); //  색 지정
                add(jbutton); // 버튼 부착
            }
        }
    }

    class CenterRandomNumbers extends JPanel {
        public CenterRandomNumbers() {
            setLayout(null); // 랜덤 배치를 위해 배치관리자 제거

            for (int i = 0; i < 10; i++) {
                int x = (int)(Math.random() * 150) + 50; // 50 ~ 200
                int y = (int)(Math.random() * 150) + 50; // 50 ~ 200

                JLabel jLabel = new JLabel(Integer.toString(i));
                jLabel.setSize(10, 10); // 크기 지정
                jLabel.setLocation(x, y); // 위치 지정
                jLabel.setForeground(Color.RED); // 색을 RED로 지정
                add(jLabel); // 부착
            }
        }
    }

    public static void main(String[] args) {
        new WestGridFrame();
    }
}