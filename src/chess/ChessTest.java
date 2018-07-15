package chess;

import org.junit.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ChessTest {
	
	@Before
	public void createModel() {
		
	}
	
	@Test
	public void testMove() {
		Model model = new Model();
		Move move = new Move();
		move.fromRow = 1;
		move.fromColumn = 0;
		move.toRow = 3;
		move.toColumn = 0;
		assertTrue(model.isValidMove(move));
	}
	
	@Test
	public void testWinner() {
		
	}
}
