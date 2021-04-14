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

	@Test
	public void testGridWindowRight() {
		GridWindow window = new GridWindow();
		window.setCursorText("%sHello%s %sHello%s %sHello%s", 5);
		window.staticShow();
		Assert.assertFalse(window.left());
		Assert.assertTrue(window.right());
		Assert.assertTrue(window.right());
		Assert.assertFalse(window.right());
		Assert.assertTrue(window.left());
	}

	@Test
	public void testGridWindowNext() {
		GridWindow window = new GridWindow();
		window.setCursorText("%sHello%s %sHello%s %sHello%s", 5);
		window.setCursorText("%sHell%s %sHell%s %sHell%s", 6);
		window.setCursorText("%sDown Test%s", 7);
		window.staticShow();
		Assert.assertFalse(window.left());
		Assert.assertTrue(window.right());
		Assert.assertTrue(window.right());
		Assert.assertTrue(window.left());
		Assert.assertTrue(window.next());
		Assert.assertTrue(window.next());
	}

	@Test
	public void testGridWindowNext2() {
		GridWindow window = new GridWindow();
		window.setCursorText("%sDown Test%s", 5);
		window.setCursorText("Down Test", 6);
		window.staticShow();
		Assert.assertTrue(window.next());
	}

	@Test
	public void testGridWindowPrev() {
		GridWindow window = new GridWindow();
		window.setCursorText("%sHello%s %sHello%s %sHello%s", 5);
		window.setCursorText("%sHell%s %sHell%s %sHell%s", 6);
		window.setCursorText("%sDown Test%s", 7);
		window.staticShow();
		Assert.assertTrue(window.right());
		Assert.assertTrue(window.next());
		Assert.assertTrue(window.next());
		Assert.assertFalse(window.next());
		Assert.assertTrue(window.prev());
	}
}
