import com.minesweeper.display.GridWindow;
import org.junit.Assert;
import org.junit.Test;

public class TestGridWindow {
	@Test
	public void testGridWindowSetText() {
		GridWindow window = new GridWindow();
		window.setCursorText("%sHello%s %sHello%s %sHello%s", 5);
		window.staticShow();
	}

	@Test
	public void testGridWindowLeft() {
		GridWindow window = new GridWindow();
		window.setCursorText("%sHello%s %sHello%s %sHello%s", 5);
		window.staticShow();
		Assert.assertFalse(window.left());
		Assert.assertTrue(window.right());
		Assert.assertTrue(window.left());
	}
}
