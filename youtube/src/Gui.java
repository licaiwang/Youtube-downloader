import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Gui extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static JTextArea result;
    public Gui(final String title) {
        super(title);
        this.setSize(720, 480);
        this.setLocation(100, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.WHITE));
        this.setResizable(false);
        this.add(createArea());
    }

    private JPanel createArea() { 
        Background jp = new Background(Youtube.path);
        jp.setLayout(new BorderLayout());

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center,BoxLayout.Y_AXIS));
        center.setOpaque(false);
        

        JPanel OptionArea = new JPanel();
        OptionArea.setOpaque(false);
        JPanel textArea = new JPanel();
        textArea.setOpaque(false);
        JPanel ButtonArea = new JPanel();
        ButtonArea.setOpaque(false);

        JComboBox formate = new JComboBox();
        formate.addItem("mp3");
        formate.addItem("mp4");
        JComboBox type = new JComboBox();
        type.addItem("單首");
        type.addItem("播放清單");

        JLabel title = new JLabel("輸入網址");
        title.setFont(new Font("Serif", Font.BOLD, 24));
        title.setAlignmentX(CENTER_ALIGNMENT);
        JTextField jtf = new JTextField(30);
        jtf.setAlignmentX(CENTER_ALIGNMENT);

        
        result = new JTextArea("等待網址。。。");
        result.setEditable(false);
        JScrollPane showResult = new JScrollPane(result);
        showResult.setPreferredSize(new Dimension(100,100));

      
        JButton download = new JButton("下載");
        JButton clear = new JButton("清空");
        download.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                String function = ((String)type.getSelectedItem());
                Youtube.type = ((String)formate.getSelectedItem());
                Youtube.url = jtf.getText();
                if(function == "單首")
                {
                    Youtube.DownLoadOne();  
                }else{
                    Youtube.DownLoadPlayList();
                }
			}
        });
        clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                jtf.setText("");
			}
        });

        OptionArea.add(type);
        OptionArea.add(formate);
        textArea.add(jtf);
        ButtonArea.add(download);
        ButtonArea.add(Box.createRigidArea(new Dimension(10,0)));
        ButtonArea.add(clear);

        center.add(Box.createRigidArea(new Dimension(10,20)));
        center.add(OptionArea);
        center.add(title);
        center.add(Box.createRigidArea(new Dimension(10,20)));
        center.add(textArea);
        center.add(ButtonArea);
        center.add(Box.createRigidArea(new Dimension(10,20)));
        center.add(showResult);
        center.add(Box.createRigidArea(new Dimension(10,100)));

        jp.add(Box.createRigidArea(new Dimension(300,100)),BorderLayout.WEST);
        jp.add(center);

        return jp;
    }




}