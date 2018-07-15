package battleship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Class for testing battle ship. Uses Junits.
 * @author Parker
 *
 */
public class TestBattleShip {

	/**
	 * Test the constructor for GUI.
	 */
	@Test
	public void testConstructor() {
		GUI g  = new GUI(false);
		Board b1 = g.getBoard(0);
		b1.setReady(true);
		
	}
	
	/**
	 * Test board class.
	 */
	@Test
	public void testBoard() {
		Board b = new Board("parker");
		assertEquals(b.getStatus(), "Place Your Battleships");
		
		
		Ship s = new Ship("boat", 4);
		Ship s2 = new Ship("boat", 4);
		Ship s3 = new Ship("boat", 4);

		
		assertFalse(b.placeShip(0, 2, 0, 0, s));
		assertFalse(b.placeShip(2, 0, 0, 0, s));
		assertTrue(b.placeShip(0, 3, 0, 0, s));
		assertFalse(b.placeShip(0, 0, 0, 3, s2));

		assertTrue(b.placeShip(8, 8, 8, 5, s2));
		assertFalse(b.placeShip(8, 5, 8, 8, s3));
		
		assertFalse(b.placeShip(9, 9, 9, 9, s3));
		assertFalse(b.placeShip(0, 7, 6, 2, s3));
		assertTrue(b.hitMiss(0, 0));
		b.hitMiss(1, 0);
		b.hitMiss(2, 0);
		b.hitMiss(3, 0);
		
		b.getSub().doClick();
		b.getButton(0, 4).doClick();
		b.getButton(0, 2).doClick();
		//b.getButton().doClick();
		
		assertFalse(b.hitMiss(9, 9));
		b.setShipCount(0);
		assertEquals(b.getShipCount(), 0);
		assertFalse(b.isLost());
		b.setLost(true);
		b.disableButtons();
	}
	
	/**
	 * Test opponent board.
	 */
	@Test 
	public void testOppBoard() {
		OppBoard o = new OppBoard("parker");
		o.getFire().doClick();
		o.enableFire();
		
		o.markSquare(false, 0, 0);
		o.markSquare(true, 0, 1);
		
		o.getFire().doClick();
		o.getButton(9, 9).doClick();
		o.getButton(8, 9).doClick();
		o.getFire().doClick();
		
		o.updateStatus("hi");
		o.updateStatus("You lose");
		assertEquals("You Win!", o.getStatus());
	}
	

	/**
	 * Test the win panel class.
	 */
	@Test
	public void testWinPanel() {
		WinPanel w = new WinPanel("Parker");
		w.getRe().doClick();
		assertTrue(w.isRematch());
	}
	
	/**
	 * Test switch panel class.
	 */
	@Test
	public void testSwitchPanel() {
		SwitchPanel s = new SwitchPanel("Parker");
		s.getReadyB().doClick();
		assertTrue(s.isReady());
		s.setpName("Hi");
		assertEquals(s.getpName(), "Hi");
		s.setReady(false);
		assertFalse(s.isReady());
	}
	
	/**
	 * Test ship class.
	 */
	@Test
	public void testShip() {
		Ship s = new Ship("boat", 5);
		s.setName("yacht");
		assertEquals(s.getName(), "yacht");
		s.setSSize(4);
		assertEquals(s.getSSize(), 4);
		s.setUsed(true);
		assertTrue(s.isUsed());
		s.hit();
		assertFalse(s.isDestroyed());
		s.hit();
		s.hit();
		s.hit();
		assertTrue(s.isDestroyed());
		
	}
	
	/**
	 * Test battleButton class.
	 */
	@Test
	public void testBattleButton() {
		BattleButton b = new BattleButton();
		b.setHasShip(false);
		b.setHasShip(true);
		b.revertColor();
		assertTrue(b.isHasShip());
		b.setHasShip(false);
		
		b.setHit(true);
		assertTrue(b.isHit());
		b.revertColor();
		b.setHit(false);
		
		b.setMiss(true);
		assertTrue(b.isMiss());
		b.revertColor();
		b.setMiss(false);
		b.revertColor();
		
		Ship s = new Ship("boat", 3);
		b.setShip(s);
		assertEquals(b.getShip(), s);
	}
}
