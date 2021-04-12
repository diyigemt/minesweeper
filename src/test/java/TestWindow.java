import com.minesweeper.display.Window;
import org.junit.Test;

public class TestWindow {
  @Test
  public void testCreateWindow() {
    Window window = new Window();
    window.setText("Hello");
    window.show();
  }
}
