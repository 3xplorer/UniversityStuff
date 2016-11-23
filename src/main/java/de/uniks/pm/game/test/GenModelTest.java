package de.uniks.pm.game.test;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Parameter;

public class GenModelTest {

	@Test
	public void generateModel() throws Exception {

		ClassModel model = new ClassModel("de.uniks.pm.game.model");

		// Class modeling
		Clazz game = model.createClazz("Game").withAttribute("actionPoints", DataType.INT)
				.withMethod("checkEnd", DataType.BOOLEAN);

		Clazz dice = model.createClazz("Dice")
				.withAttribute("value", DataType.INT);

		Clazz trainer = model.createClazz("Trainer")
				.withAttribute("name", DataType.STRING)
				.withAttribute("color", DataType.STRING)
				.withAttribute("experience", DataType.INT);

		Clazz zombie = model.createClazz("Zombie")
				.withAttribute("hp", DataType.INT)
				.withAttribute("ap", DataType.INT)
				.withAttribute("name", DataType.STRING);

		Clazz ground = model.createClazz("Ground")
				.withAttribute("x", DataType.INT)
				.withAttribute("y", DataType.INT);

		Clazz trap = model.createClazz("Trap")
				.withAttribute("succesRate", DataType.INT);

		Clazz rock = model.createClazz("Rock");
		Clazz grass = model.createClazz("Grass");

		// as interface
		Clazz zombieowner = model.createClazz("ZombieOwner").enableInterface();

		// wtihMethods
		trainer.withMethod("movePlayer", DataType.VOID, new Parameter(ground));
		trainer.withMethod("attackZombie", DataType.VOID, new Parameter(zombie), new Parameter(zombie));
		trainer.withMethod("catchZombie", DataType.VOID, new Parameter(zombie), new Parameter(trap));

		// Associations game to *
		game.withBidirectional(trainer, "trainers", Cardinality.MANY, "game", Cardinality.ONE);
		game.withBidirectional(trainer, "currentTrainer", Cardinality.ONE, "currentGame", Cardinality.ONE);
		game.withBidirectional(dice, "dice", Cardinality.ONE, "game", Cardinality.ONE);
		game.withBidirectional(ground, "grounds", Cardinality.MANY, "game", Cardinality.ONE);
		game.withBidirectional(zombie, "zombies", Cardinality.MANY, "game", Cardinality.ONE);

		// Associations trainer to *
		trainer.withBidirectional(ground, "ground", Cardinality.ONE, "trainers", Cardinality.MANY);
		trainer.withBidirectional(trainer, "prev", Cardinality.ONE, "next", Cardinality.ONE);

		// Association trap to trainer
		trap.withBidirectional(trainer, "trainer", Cardinality.ONE, "traps", Cardinality.MANY);

		// Association * with ZombieOwner
//		ground.withImplements(zombieowner);
//		trainer.withImplements(zombieowner);
		ground.withSuperClazz(zombieowner);
		trainer.withSuperClazz(zombieowner);

		// Association ZombieOwner with Zombie
		zombieowner.withBidirectional(zombie, "zombies", Cardinality.MANY, "zombieowner", Cardinality.ONE);

		// Association Ground with Rock & Grass
		grass.withSuperClazz(ground);
		rock.withSuperClazz(ground);

		// generate model
		model.generate("src/main/java");

		// create diagram
		model.dumpHTML("src");
	}
}