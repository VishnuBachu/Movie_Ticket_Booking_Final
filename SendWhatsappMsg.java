import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class SendWhatsappMsg{
    public static void sendMessageDa(String tktdetails, String phno){
        try {

            StringSelection str = new StringSelection(tktdetails);
            Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
            clip.setContents(str, null);

            URI uri = new URI("https://api.whatsapp.com/send?phone=" + phno);
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    desktop.browse(uri);
                } else {
                    System.out.println("Desktop browse action is not supported.");
                }
            } else {
                System.out.println("Desktop is not supported.");
            }

            Thread.sleep(8000);
            Robot rob = new Robot();
            for(int i=0; i<12; i++){
                rob.keyPress(KeyEvent.VK_TAB);
                rob.keyRelease(KeyEvent.VK_TAB);
            }
            rob.keyPress(KeyEvent.VK_CONTROL);
            rob.keyPress(KeyEvent.VK_V);
            rob.keyRelease(KeyEvent.VK_CONTROL);
            rob.keyRelease(KeyEvent.VK_V);
            Thread.sleep(2000);
            rob.keyPress(KeyEvent.VK_TAB);
            rob.keyRelease(KeyEvent.VK_TAB);
            rob.keyPress(KeyEvent.VK_ENTER);
            rob.keyRelease(KeyEvent.VK_ENTER);


        } catch (URISyntaxException | IOException e) {}
        catch(AWTException | InterruptedException e){
            e.printStackTrace();
        }
    }
}
