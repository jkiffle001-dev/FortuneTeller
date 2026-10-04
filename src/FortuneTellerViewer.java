import javax.swing.JFrame;

/**
 * FortuneTellerViewer is the java main class. It creates the
 * FortuneTellerFrame and sets the frame stats.
 */
public class FortuneTellerViewer
{
    public static void main(String[] args)
    {
        FortuneTellerFrame frame = new FortuneTellerFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
