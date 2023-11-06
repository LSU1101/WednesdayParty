import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CalculatorGUI extends JFrame {
    private final JTextField northInput = new JTextField();
    private final JTextField southOutput = new JTextField();

    public CalculatorGUI() {
        setTitle("자바 스윙 계산기"); // 타이틀 지정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = getContentPane(); // 컨텐트팬 알아내기

        setLayout(new BorderLayout()); // BorderLayout 사용 (동, 서, 남, 북, 중앙으로 분할)
        container.add(new NorthInputPanel(), BorderLayout.NORTH); // 입력창을 북쪽에 부착
        container.add(new CenterButtonPanel(), BorderLayout.CENTER); // 숫자 버튼을 센터에 부착
        container.add(new SouthResultPanel(), BorderLayout.SOUTH); // 결과창을 남쪽에 부착

        setSize(300, 300); // 창 크기 300 X 300
        setVisible(true); // 화면에 출력
    }

    class NorthInputPanel extends JPanel {
        public NorthInputPanel() {
            setBackground(Color.LIGHT_GRAY);
            setLayout(new FlowLayout()); // FlowLayout 사용

            add(new JLabel("수식")); // "수식" 글 부착
            northInput.setColumns(20);
            add(northInput); // 입력창 부착
        }
    }

    class CenterButtonPanel extends JPanel {
        private String[] centerButtonsArray = { // 부착할 숫자 배열
                "C", "UN", "BK", "/",
                "7", "8", "9", "x",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "0", ".", "=", "%"
        };
        public CenterButtonPanel() {
            setBackground(Color.WHITE);
            setLayout(new GridLayout(5, 4, 5, 5)); // 4열 5행 간격 5px

            for (int i = 0; i < 20; i++) { // 배열 버튼 삽입
                JButton button = new JButton();
                button.setText(centerButtonsArray[i]);
                if (button.getText().equals("=")) {
                    button.setBackground(Color.CYAN);
                }
                button.addActionListener(new Calculate());
                add(button);
            }
        }
    }

    class SouthResultPanel extends JPanel {
        public SouthResultPanel() {
            setBackground(Color.YELLOW); // 배경색 노란색
            setLayout(new FlowLayout(FlowLayout.LEFT)); // 왼쪽 정렬

            add(new JLabel("계산 결과"));
            southOutput.setColumns(18);
            add(southOutput);
        }
    }

    class Calculate implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JButton pressedButton = (JButton)event.getSource();
            String pressed = pressedButton.getText();

            switch (pressed) {
                case "C":
                    northInput.setText("");
                    break;
                case "BK":
                    back();
                    break;
                case "/":
                    append("/");
                    break;
                case "%":
                    append("%");
                    break;
                case "x":
                    append("x");
                    break;
                case "-":
                    append("-");
                    break;
                case "+":
                    append("+");
                    break;
                case ".":
                    append(".");
                    break;
                case "=":
                    outputResult();
                    break;
            }

            for (int i = 0; i <= 9; i++) {
                if (Integer.parseInt(pressed) == i) {
                    append(Integer.toString(i));
                }
            }
        }

        private void back() {
            int length = northInput.getText().length(); // 입력되어 있는 글자의 길이 알아내
            if (length > 0) { // 글자가 있으면
                String text = northInput.getText().substring(0, length - 1); // 맨 뒤 글자를 뺴고 가져오기
                northInput.setText(text); // 맨 뒤 글자가 삭제된 텍스트를 넣기
            }
        }

        private void append(String input) {
            String text = northInput.getText().concat(input);
            northInput.setText(text);
        }

        private void outputResult() {
            String text = northInput.getText();
            StringTokenizer st = new StringTokenizer(text, "+-/%x", true); // 연산자도 토큰에 포함
            var inputText = new Vector<String>();
            double result = 0.0;

            while (st.hasMoreTokens()) {
                inputText.add(st.nextToken());
            }

            if (inputText.size() > 3) {
                southOutput.setText("한 개의 연산만 가능합니다.");
                return;
            }
            if (inputText.get(0).equals("+") || inputText.get(0).equals("-") || inputText.get(0).equals("x") ||inputText.get(0).equals("/") ||inputText.get(0).equals("%")) {
                southOutput.setText("올바른 식을 입력하세요.");
            }
            if (inputText.get(2).equals("+") || inputText.get(2).equals("-") || inputText.get(2).equals("x") ||inputText.get(2).equals("/") ||inputText.get(2).equals("%")) {
                southOutput.setText("올바른 식을 입력하세요.");
            }

            double firstNum = Double.parseDouble(inputText.get(0));
            double secondNum = Double.parseDouble(inputText.get(2));

            if (inputText.get(1).equals("+")) {
                result = firstNum + secondNum;
            } else if (inputText.get(1).equals("-")) {
                result = firstNum - secondNum;
            } else if (inputText.get(1).equals("x")) {
                result = firstNum * secondNum;
            } else if (inputText.get(1).equals("/")) {
                if (secondNum == 0) {
                    southOutput.setText("0으로는 나눌 수 없습니다.");
                    return;
                }
                result = firstNum / secondNum;
            } else if (inputText.get(1).equals("%")) {
                if (secondNum == 0) {
                    southOutput.setText("0으로는 나눌 수 없습니다.");
                    return;
                }
                result = firstNum % secondNum;
            }

            southOutput.setText(Double.toString(result));
        }
    }

    public static void main(String[] args) {
        try { // Windows, MacOS와 같이 다양한 OS에서 같은 결과물을 보이기 위한 예외처리. Mac OS의 경우 Mac OS LookAndFill을 Cross-platform LookAndFill로 변경
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) { }

        new CalculatorGUI();
    }
}