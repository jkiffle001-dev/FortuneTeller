import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

/**
 * FortuneTellerFrame is the main window of the Fortune Teller application.
 * Top: title label with an image. Middle: scrolling text area of fortunes.
 * Bottom: "Read My Fortune!" and "Quit" buttons.
 */
public class FortuneTellerFrame extends JFrame
{
    private JPanel mainPnl;
    private JPanel topPnl;
    private JPanel middlePnl;
    private JPanel bottomPnl;

    private JLabel titleLbl;
    private ImageIcon icon;

    private JTextArea fortuneTA;
    private JScrollPane scroller;

    private JButton readBtn;
    private JButton quitBtn;

    private final Font titleFont = new Font("Serif", Font.BOLD | Font.ITALIC, 48);
    private final Font fortuneFont = new Font("SansSerif", Font.PLAIN, 18);
    private final Font buttonFont = new Font("SansSerif", Font.BOLD, 16);

    private final ArrayList<String> fortunes = new ArrayList<>();
    private final Random rnd = new Random();
    private int lastIndex = -1;   // index of the last fortune shown

    /**
     * Builds the frame: sets the size to 3/4 of the screen width, centers it,
     * loads the fortunes, and creates the three panels.
     */
    public FortuneTellerFrame()
    {
        super("Fortune Teller");

        loadFortunes();

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createTopPanel();
        mainPnl.add(topPnl, BorderLayout.NORTH);

        createMiddlePanel();
        mainPnl.add(middlePnl, BorderLayout.CENTER);

        createBottomPanel();
        mainPnl.add(bottomPnl, BorderLayout.SOUTH);

        add(mainPnl);

        // size the frame to 3/4 of the screen width and center it
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = screenWidth * 3 / 4;
        int frameHeight = screenHeight * 3 / 4;
        setSize(frameWidth, frameHeight);
        setLocation((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2);
    }

    /**
     * Fills the list with at least 12 humorous fortunes.
     */
    private void loadFortunes()
    {
        fortunes.add("You will find a bug in your code... and three more while fixing it.");
        fortunes.add("A semicolon you forgot will be found in the most unexpected place.");
        fortunes.add("Your next cup of coffee will be the best one yet.");
        fortunes.add("Today the compiler will agree with you. Enjoy it while it lasts.");
        fortunes.add("Beware of null pointers lurking in the shadows.");
        fortunes.add("You will soon have an excellent idea at 2 AM. Write it down.");
        fortunes.add("A pizza with your name on it is in your near future.");
        fortunes.add("Your Wi-Fi will be strong and your battery will be full.");
        fortunes.add("Someone will ask you to fix their printer. Run.");
        fortunes.add("The answer you seek is on Stack Overflow, third reply down.");
        fortunes.add("You will press Ctrl+S right before the power goes out.");
        fortunes.add("Good news: your code works. Bad news: nobody knows why.");
        fortunes.add("An unexpected snack will brighten your afternoon.");
        fortunes.add("You will learn that infinite loops are, in fact, quite long.");
    }

    /**
     * Top panel: a JLabel with the title text shown below the ImageIcon.
     */
    private void createTopPanel()
    {
        topPnl = new JPanel();
        topPnl.setBackground(new Color(40, 20, 70));

        icon = new ImageIcon("fortuneteller.png");
        titleLbl = new JLabel("Fortune Teller", icon, SwingConstants.CENTER);
        titleLbl.setFont(titleFont);
        titleLbl.setForeground(new Color(255, 215, 120));
        // place the text below the image
        titleLbl.setVerticalTextPosition(SwingConstants.BOTTOM);
        titleLbl.setHorizontalTextPosition(SwingConstants.CENTER);

        topPnl.add(titleLbl);
    }

    /**
     * Middle panel: a JTextArea inside a JScrollPane for the fortunes.
     */
    private void createMiddlePanel()
    {
        middlePnl = new JPanel(new BorderLayout());
        middlePnl.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        fortuneTA = new JTextArea(10, 50);
        fortuneTA.setFont(fortuneFont);
        fortuneTA.setEditable(false);
        fortuneTA.setLineWrap(true);
        fortuneTA.setWrapStyleWord(true);

        scroller = new JScrollPane(fortuneTA);
        middlePnl.add(scroller, BorderLayout.CENTER);
    }

    /**
     * Bottom panel: the "Read My Fortune!" and "Quit" buttons, using lambda
     * expressions for their action listeners.
     */
    private void createBottomPanel()
    {
        bottomPnl = new JPanel();

        readBtn = new JButton("Read My Fortune!");
        readBtn.setFont(buttonFont);
        readBtn.addActionListener((ae) -> fortuneTA.append(nextFortune() + "\n"));

        quitBtn = new JButton("Quit");
        quitBtn.setFont(buttonFont);
        quitBtn.addActionListener((ae) -> System.exit(0));

        bottomPnl.add(readBtn);
        bottomPnl.add(quitBtn);
    }

    /**
     * Picks a random fortune that is never the same as the previous one.
     *
     * @return the next fortune to display
     */
    private String nextFortune()
    {
        int index;
        do
        {
            index = rnd.nextInt(fortunes.size());
        } while (index == lastIndex);

        lastIndex = index;
        return fortunes.get(index);
    }
}
