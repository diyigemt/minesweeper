import com.minesweeper.display.NaviWindow;
import com.minesweeper.display.Window;
import org.junit.Assert;
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

	@Test
	public void testNaviWindowOutOfBound() {
		NaviWindow window = new NaviWindow();
		window.setCursorText("hello", 20);
		window.setCursorText("hello hello", 30);
		window.staticShow();
		window.next();
	}

	@Test
	public void testNaviWindowShow() {
		NaviWindow window = new NaviWindow();
		window.setCursorText("hello", 5);
		window.setCursorText("hello hello", 6);
		window.staticShow();
		window.next();
	}

	@Test
	public void testNaviWindowNext() {
		NaviWindow window = new NaviWindow();
		window.setCursorText("hello", 5);
		window.setCursorText("hello hello", 6);
		window.staticShow();
		window.next();
	}

	@Test
	public void testNaviWindowNext2() {
		NaviWindow window = new NaviWindow();
		window.setCursorText("hello", 5);
		window.setCursorText("hello hello", 6);
		window.setCursorText("hello hello hello", 7);
		window.staticShow();
		window.next();
		window.next();
	}

	@Test
	public void testNaviWindowPrev() {
		NaviWindow window = new NaviWindow();
		window.setCursorText("hello", 5);
		window.setCursorText("hello hello", 6);
		boolean res = window.staticShow();
		Assert.assertTrue(res);
		res = window.next();
		Assert.assertTrue(res);
		res = window.prev();
		Assert.assertTrue(res);
		res = window.prev();
		Assert.assertFalse(res);
	}

	@Test
	public void testNaviWindowPrev2() {
		NaviWindow window = new NaviWindow();
		window.setCursorText("hello", 5);
		window.setCursorText("hello hello", 6);
		window.setCursorText("hello hello hello", 7);
		window.staticShow();
		window.next();
		window.next();
		window.prev();
		window.prev();
		window.prev();
	}
}
