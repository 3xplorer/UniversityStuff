package de.uniks.pm.game.tests;

import static org.junit.Assert.*;


import org.junit.Test;

import de.uniks.pm.game.model.Game;
import de.uniks.pm.game.model.Grass;
import de.uniks.pm.game.model.Ground;
import de.uniks.pm.game.model.Trainer;
import de.uniks.pm.game.model.Trap;
import de.uniks.pm.game.model.util.GroundSet;

public class TestModelCreation {

	@Test
	public void testTrapsWithTrainer() throws Exception {

		Game game = new Game();
		Trainer firstTrainer = new Trainer()
				.withName("Harry")
				.withColor("GREEN");
		Trainer secondTrainer = new Trainer()
				.withName("Michael")
				.withColor("RED");
		
		game.init(firstTrainer, secondTrainer);

		assertNotNull(firstTrainer.getTraps());
		assertEquals(firstTrainer.getTraps().size(), 17);
		for (Trap trap : firstTrainer.getTraps()) {
			assertNotNull(trap);
		}
	}
	
	@Test
	public void testDice() throws Exception {
		
		Game game = new Game();
		Trainer firstTrainer = new Trainer()
				.withName("Harry")
				.withColor("GREEN");
		Trainer secondTrainer = new Trainer()
				.withName("Michael")
				.withColor("RED");
		
		game.init(firstTrainer, secondTrainer);
		
		assertNotNull(game.getDice());
		assertNotNull(game.getDice().getGame());
	}
	
	@Test
	public void testFieldSetup() throws Exception {
		
		Game game = new Game();
		Trainer firstTrainer = new Trainer()
				.withName("Harry")
				.withColor("GREEN");
		Trainer secondTrainer = new Trainer()
				.withName("Michael")
				.withColor("RED");
		
		game.init(firstTrainer, secondTrainer);
		
		assertEquals(30, game.getGrounds().size());
		assertNotNull(game.getGrounds());
		assertNotNull(game.getGrounds().getGame());
		for (Ground ground : game.getGrounds()) {
			assertNotNull(ground.getGame());
		}

		for (Ground ground : game.getGrounds()) {

			isInstanceofGround(ground);
			isInstanceOfRock(ground);
			isInstanceOfGrass(ground);
		}
	}

	private void isInstanceOfGrass(Ground ground) {
		if (ground.getX() == 0 && ground.getY() == 0) {
			assertTrue(ground instanceof Grass);
		}
	}

	private void isInstanceOfRock(Ground ground) {
		if (ground.getX() == 3 && ground.getY() == 3) {
			assertTrue(ground instanceof Ground);
		}
	}

	private void isInstanceofGround(Ground ground) {
		if (ground.getX() == 1 && ground.getY() == 1) {
			assertTrue(ground instanceof Ground);
		}
		if (ground.getX() == 1 && ground.getY() == 3) {
			assertTrue(ground instanceof Ground);
		}
		if (ground.getX() == 3 && ground.getY() == 2) {
			assertTrue(ground instanceof Ground);
		}
	}

	@Test
	public void testNextPrevTrainer() throws Exception {
		
		Game game = new Game();
		Trainer firstTrainer = new Trainer()
				.withName("Harry")
				.withColor("GREEN");
		Trainer secondTrainer = new Trainer()
				.withName("Michael")
				.withColor("RED");
		
		game.init(firstTrainer, secondTrainer);
		assertEquals(firstTrainer.getNext(), secondTrainer);
		assertEquals(secondTrainer.getPrev(), firstTrainer);
		assertEquals(firstTrainer.getPrev(), secondTrainer);
		assertEquals(secondTrainer.getNext(), firstTrainer);
	}
	
	@Test
	public void testFirstTrainerOnField() throws Exception {
		
		Game game = new Game();
		Trainer firstTrainer = new Trainer()
				.withName("Harry")
				.withColor("GREEN");
		Trainer secondTrainer = new Trainer()
				.withName("Michael")
				.withColor("RED");
		
		game.init(firstTrainer, secondTrainer);
		
		GroundSet grounds = game.getGrounds();
		assertEquals(firstTrainer.getGround().getX(), grounds.get(0).getX());
		assertEquals(firstTrainer.getGround().getY(), grounds.get(0).getY());
	}
	
	@Test
	public void testSecondPlayerOnField() throws Exception {
		
		Game game = new Game();
		Trainer firstTrainer = new Trainer()
				.withName("Harry")
				.withColor("GREEN");
		Trainer secondTrainer = new Trainer()
				.withName("Michael")
				.withColor("RED");
		
		game.init(firstTrainer, secondTrainer);
		
		GroundSet grounds = game.getGrounds();
		assertEquals(secondTrainer.getGround().getX(), grounds.get(grounds.size()-1).getX());
		assertEquals(secondTrainer.getGround().getY(), grounds.get(grounds.size()-1).getY());
	}
	
	@Test
	public void testCurrentTrainer() throws Exception {
		
		Game game = new Game();
		Trainer firstTrainer = new Trainer()
				.withName("Harry")
				.withColor("GREEN");
		Trainer secondTrainer = new Trainer()
				.withName("Michael")
				.withColor("RED");
		
		game.init(firstTrainer, secondTrainer);
		
		assertNotNull(game.getCurrentTrainer());
		assertEquals(firstTrainer, game.getCurrentTrainer());
		assertNotEquals(secondTrainer, game.getCurrentTrainer());
		assertNull(secondTrainer.getCurrentGame());
		
		game.setCurrentTrainer(firstTrainer.getNext());
		
		assertNotNull(game.getCurrentTrainer());
		assertEquals(secondTrainer, game.getCurrentTrainer());
		assertNotEquals(firstTrainer, game.getCurrentTrainer());
		assertNull(firstTrainer.getCurrentGame());
	}
	
	@Test
	public void testActionPoints() throws Exception {
		
		Game game = new Game();
		Trainer firstTrainer = new Trainer()
				.withName("Harry")
				.withColor("GREEN");
		Trainer secondTrainer = new Trainer()
				.withName("Michael")
				.withColor("RED");
		
		game.init(firstTrainer, secondTrainer);
		
		assertNotNull(game.getActionPoints());
		assertEquals(3, game.getActionPoints());
	}
}
