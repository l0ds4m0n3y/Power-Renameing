import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class PowerRenamerGUI extends JFrame {
    private final int WIDTH = 600;
    private final int LENGTH = 800;
    private final int TOP_HEIGHT = 50;
    private final int SPACER_WIDTH = 25;
    private final Font DEFAULT_FONT = new Font("Consolas", Font.BOLD, TOP_HEIGHT - 25);

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
    private Dimension topAndBottomDim = new Dimension((int) frameDim.getWidth(), TOP_HEIGHT);
    private Dimension spacerDim = new Dimension(SPACER_WIDTH, (int) frameDim.getHeight());
    private LineBorder debugBorder = new LineBorder(Color.RED, 1);
    private File sourceFolder;
    private File[] files;
    private int currentFileIndex = 0;

    PowerRenamerGUI() {

        loadFiles(choosePath());
        nextFile();

        setLayout(new BorderLayout());
        setPreferredSize(frameDim);
        setSize(getPreferredSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Spacers-----------------------------------------------------

        lspacer.setPreferredSize(spacerDim);
        rspacer.setPreferredSize(spacerDim);
        add(rspacer, BorderLayout.WEST);
        add(lspacer, BorderLayout.EAST);

        // ------------------------------------------------------------

        // TOP PANEL--------------------------------------------------

        topPanel.setPreferredSize(topAndBottomDim);
        topPanel.setLayout(new BorderLayout(0, 0));

            // LABELS---------------------------------------------------
            prefixLabel.setFont(DEFAULT_FONT);
            typeLabel.setFont(DEFAULT_FONT);
            namer.setFont(DEFAULT_FONT);

            prefixLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            typeLabel.setHorizontalAlignment(SwingConstants.LEFT);

            // ---------------------------------------------------------

        topPanel.add(prefixLabel, BorderLayout.LINE_START);
        topPanel.add(namer, BorderLayout.CENTER);
        topPanel.add(typeLabel, BorderLayout.LINE_END);
        add(topPanel, BorderLayout.NORTH);

        // -----------------------------------------------------------

        // BUTTON PANEL-------------------------------------------------

        buttonPanel.setPreferredSize(topAndBottomDim);
        buttonPanel.setLayout(new GridLayout(1, 2));

            // Buttons--------------------------------------------------
                // Delete Button----------------------------------------
                delButton.addActionListener(e -> {
                    System.out.println("delete");
                });
                // ------------------------------------------------------

                // RenameButton------------------------------------------
                renButton.addActionListener(e -> {
                    rename(files[currentFileIndex-1].toPath(), namer.getText());
                    nextFile();
                });
                // ------------------------------------------------------
            // ---------------------------------------------------------

        buttonPanel.add(delButton);
        buttonPanel.add(renButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // --------------------------------------------------------------

        // CENTER PANEL--------------------------------------------------

        add(centerPanel, BorderLayout.CENTER);

        // --------------------------------------------------------------

        // DEBUG----------------------------------------------------------

        topPanel.setBorder(debugBorder);
        buttonPanel.setBorder(debugBorder);
        centerPanel.setBorder(debugBorder);
        rspacer.setBorder(debugBorder);
        lspacer.setBorder(debugBorder);

        // -----------------------------------------------------------------

        setVisible(true);
    }

    private void loadFiles(File source){
        files = source.listFiles();
        //System.out.println(Arrays.toString(files));
    }

    private File choosePath(){
        JFileChooser choose = new JFileChooser();
        choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        choose.setCurrentDirectory(new java.io.File("."));
        choose.showOpenDialog(null);
        sourceFolder = choose.getSelectedFile();
        return sourceFolder;
    }

    private void nextFile() {
        if (files != null && currentFileIndex < files.length) {
            File currentFile = files[currentFileIndex];

            // Perform your operation on 'currentFile' here
            String FileName = currentFile.getName();
            FileName = FileName.substring(0, FileName.indexOf("."));

            String type = FileName.substring(FileName.indexOf('.') + 1);
            
            long creationDate = currentFile.lastModified();
            String formatedDate = formatDate(creationDate);

            prefixLabel.setText(type.toUpperCase() + "_" + formatedDate + "-");
            namer.setText(FileName);
            typeLabel.setText("." + type);
            
            // System.out.printf("%-10s", type);
            // System.out.printf("%-25s", name);
            // System.out.print(formatedDate);
            // System.out.println();

            currentFileIndex++;
        } else {
            System.exit(0);
        }
    }

    void rename(Path fileToRename, String name) {
        System.out.println(fileToRename.getFileName() + " " + name);
    }

    void delete(){

    }

    public static String formatDate(long creationDate) {
        Date date = new Date(creationDate);

        // Create a SimpleDateFormat object
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");

        // Format the date
        return sdf.format(date);
    }
}
