package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ShuD extends JFrame {
    //序列化字段
    private static final long serialVersionUID = 5952689219411916553L;
    //存储输入后的两位数组
    static int ans[][] = new int[9][9];
    //存储文本框中的数字
    private static JTextField a[][] = new JTextField[9][9];
    public int rightans[][];
    GameCore example = new GameCore();
    public int right[][] = example.generatePuzzleMatrix();

    public ShuD() {

        Container c = getContentPane();
//边框布局
        c.setLayout(new BorderLayout(2, 1));
//定义菜单
        JMenuItem jmiOk = new JMenuItem("提交");

        JMenuItem jmiExplain = new JMenuItem("详情");

        JMenuItem jmiMessage = new JMenuItem("信息");
//定义一个容器
        JPanel panel = new JPanel();
//将菜单在容器内显示
        panel.add(jmiOk);

        panel.add(jmiExplain);

        panel.add(jmiMessage);
//定义9行9列的网格布局
        JPanel p1 = new JPanel(new GridLayout(9, 9, 5, 5));
//将菜单放置在北面
        add(panel, BorderLayout.NORTH);
//将数字放置在正中间
        add(p1, BorderLayout.CENTER);

        rightans = Wk(right);

        for (int k = 0; k < 9; k++) {

            for (int n = 0; n < 9; n++) {

                if (rightans[k][n] != 0) {

                    a[k][n] = new JTextField("" + rightans[k][n]);
                    //将数字水平居中
                    a[k][n].setHorizontalAlignment(JTextField.CENTER);
                    //只可显示不可修改
                    a[k][n].setEditable(false);
                    //添加文本框
                    p1.add(a[k][n]);

                } else {

                    a[k][n] = new JTextField();

                    a[k][n].setHorizontalAlignment(JTextField.CENTER);

                    p1.add(a[k][n]);

                }

            }

        }
//将数字面板显示在容器里
        add(p1);
//匿名创建事件监听器
        jmiOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (gettext() == 1) {

                    if (judge() == true) {

                        JOptionPane.showMessageDialog(null, "Your answer is right!", "Result", JOptionPane.INFORMATION_MESSAGE);

                    } else {

                        JOptionPane.showMessageDialog(null, "Your answer is wrong!", "Result", JOptionPane.INFORMATION_MESSAGE);

                    }

                }

            }

        });

        explainListenerClass listener2 = new explainListenerClass();

        jmiExplain.addActionListener(listener2);

        messageListenerClass listener3 = new messageListenerClass();

        jmiMessage.addActionListener(listener3);

    }

    //获取文本框的文字
    static int gettext() {

        int i, j;

        for (i = 0; i < 9; i++) {

            for (j = 0; j < 9; j++) {

                ans[i][j] = 0;

            }

        }

        for (int k = 0; k < 9; k++) {

            for (int n = 0; n < 9; n++) {
                //异常处理
                try {

                    ans[k][n] = Integer.parseInt(a[k][n].getText());

//将答案类型转换之后传给ans

                } catch (NumberFormatException nfe) {

                    JOptionPane.showMessageDialog(null, "数据中包括非数字，请重新输入");

                    return 0;

                }

            }

        }

        return 1;

    }

    //判断输入的答案是否正确
    public static boolean judge() {

        int i, j, k;

        int[][] answer = ans;

        for (i = 0; i < 9; i++) {
            //判断每列是否有重复数字
            if (judge9(answer[i]) == false) {
                return false;
            }

        }
//判断每行是否有重复数字
        for (j = 0; j < 9; j++) {

            int[] newAnswerColumn = new int[9];

            for (i = 0; i < 9; i++) {
                newAnswerColumn[i] = answer[i][j];
            }

            if (judge9(newAnswerColumn) == false) {
                return false;
            }

        }
//判断每个小九宫格内是否有重复数字
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                k = 0;
                int[] newAnswer = new int[9];

                for (int m = i * 3; m < i * 3 + 3; m++) {
                    for (int n = j * 3; n < j * 3 + 3; n++) {
                        newAnswer[k] = answer[m][n];
                        k++;
                    }
                }
                if (judge9(newAnswer) == false) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean judge9(int[] answer) {

        int i, j;

        for (i = 0; i < 9; i++) {

            for (j = 0; j < 9; j++) {
                if (i == j) {
                    continue;
                }
                //如果有重复的数字，返回false
                if (answer[i] == answer[j]) {

                    return false;

                }

            }

        }
//没有重复数字，返回true
        return true;

    }

    public static void main(String[] args) {

        JFrame frame = new ShuD();

        frame.setTitle("SuDoku");

        frame.setSize(600, 900);

        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

    }

    //挖空
    private int[][] Wk(int a[][]) {

        Random r = new Random();

        int a1, a2;

        a1 = r.nextInt(9);

        a2 = r.nextInt(9);

        for (int i = 0; i < 100; i++) {

            a[a1][a2] = 0;

            a1 = r.nextInt(9);

            a2 = r.nextInt(9);

        }

        return a;

    }

}

//事件监听器
class explainListenerClass implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "填入数字保证每行每列及每个小的九宫格内数字无重复", "Explain", JOptionPane.INFORMATION_MESSAGE);

    }

}

class messageListenerClass implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "made by wyx", "Message", JOptionPane.INFORMATION_MESSAGE);

    }

}