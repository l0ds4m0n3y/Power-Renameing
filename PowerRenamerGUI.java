import javax.sound.sampled.Line;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PowerRenamerGUI extends JFrame{
    private final int WIDTH = 600;
    private final int LENGTH = 800;
    private final int TOP_HEIGHT = 50;
    private final int SPACER_WIDTH = 25;
    private JButton delButton = new JButton("Delete");
    private JButton renButton = new JButton("Rename");
    private JLabel prefixLabel = new JLabel();
    private JLabel typeLabel = new JLabel();
    private JTextField namer = new JTextField();
    private BufferedImage centerImage;
    private JPanel topPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JPanel lspacer = new JPanel();
    private JPanel rspacer = new JPanel();
    private Dimension frameDim = new Dimension(WIDTH, LENGTH);
    private Dimension topAndBottomDim = new Dimension((int)frameDim.getWidth(), TOP_HEIGHT);
    private Dimension spacerDim = new Dimension(SPACER_WIDTH, (int) frameDim.getHeight());
    private LineBorder debugBorder = new LineBorder(Color.RED, 1);
    private Font defaulFont = new Font("Consolas", Font.BOLD, TOP_HEIGHT-25);


    PowerRenamerGUI(){
        setLayout(new BorderLayout());
        setPreferredSize(frameDim);
        setSize(getPreferredSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        //Spacers-----------------------------------------------------
        
        lspacer.setPreferredSize(spacerDim);
        rspacer.setPreferredSize(spacerDim);
        add(rspacer, BorderLayout.WEST);
        add(lspacer, BorderLayout.EAST);
        
        //------------------------------------------------------------

        
        //TOP PANEL--------------------------------------------------
        
        topPanel.setPreferredSize(topAndBottomDim);
        topPanel.setLayout(new BorderLayout(0, 0));

            //LABELS---------------------------------------------------
            prefixLabel.setFont(defaulFont);
            typeLabel.setFont(defaulFont);
            namer.setFont(defaulFont);

            prefixLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            typeLabel.setHorizontalAlignment(SwingConstants.LEFT);

            prefixLabel.setText("TXT_20240104-");
            typeLabel.setText(".txt");
            //---------------------------------------------------------

        topPanel.add(prefixLabel, BorderLayout.LINE_START);
        topPanel.add(namer, BorderLayout.CENTER);
        topPanel.add(typeLabel, BorderLayout.LINE_END);
        add(topPanel, BorderLayout.NORTH);

        //-----------------------------------------------------------


        //BUTTON PANEL-------------------------------------------------

        buttonPanel.setPreferredSize(topAndBottomDim);
        buttonPanel.setLayout(new GridLayout(1,2));

            //Buttons--------------------------------------------------
                
            //---------------------------------------------------------

        buttonPanel.add(delButton);
        buttonPanel.add(renButton);
        add(buttonPanel, BorderLayout.SOUTH);

        //--------------------------------------------------------------


        //CENTER PANEL--------------------------------------------------

        add(centerPanel, BorderLayout.CENTER);

        //--------------------------------------------------------------
        

        //DEBUG----------------------------------------------------------

        topPanel.setBorder(debugBorder);
        buttonPanel.setBorder(debugBorder);
        centerPanel.setBorder(debugBorder);
        rspacer.setBorder(debugBorder);
        lspacer.setBorder(debugBorder);

        //-----------------------------------------------------------------

        setVisible(true);
    }
}
