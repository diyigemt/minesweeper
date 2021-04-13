import com.minesweeper.display.Window;
import org.junit.Test;

public class TestWindow {
	@Test
	public void testCreateWindow() {
		Window window = new Window();
		window.setText("Hello");
		window.show();
		window.setText("Hello2", 6);
		window.show(true);
		window.setText("Hello3", 6);
		window.show(false);
	}
}
