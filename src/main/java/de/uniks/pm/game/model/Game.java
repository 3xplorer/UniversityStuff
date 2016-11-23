/*
   Copyright (c) 2016 baeant
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package de.uniks.pm.game.model;

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import de.uniks.pm.game.model.util.TrainerSet;
import de.uniks.pm.game.model.Trainer;
import de.uniks.pm.game.model.Dice;
import de.uniks.pm.game.model.Grass;
import de.uniks.pm.game.model.Rock;
import de.uniks.pm.game.model.util.GroundSet;
import de.uniks.pm.game.model.Ground;
import de.uniks.pm.game.model.util.ZombieSet;
import de.uniks.pm.game.model.Zombie;
   /**
    * 
    * @see <a href='../../../../../../../../src/main/java/de/uniks/pm/game/test/GenModelTest.java'>GenModelTest.java</a>
 */
   public  class Game implements SendableEntity
{

   
   //==========================================================================
   public boolean checkEnd(  )
   {
      return false;
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = null;
   
   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(listener);
   	return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(propertyName, listener);
   	return true;
   }
   
   public boolean removePropertyChangeListener(PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners.removePropertyChangeListener(listener);
   	}
   	listeners.removePropertyChangeListener(listener);
   	return true;
   }

   public boolean removePropertyChangeListener(String propertyName,PropertyChangeListener listener) {
   	if (listeners != null) {
   		listeners.removePropertyChangeListener(propertyName, listener);
   	}
   	return true;
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
      withoutTrainers(this.getTrainers().toArray(new Trainer[this.getTrainers().size()]));
      setCurrentTrainer(null);
      setDice(null);
      withoutGrounds(this.getGrounds().toArray(new Ground[this.getGrounds().size()]));
      withoutZombies(this.getZombies().toArray(new Zombie[this.getZombies().size()]));
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_ACTIONPOINTS = "actionPoints";
   
   private int actionPoints;

   public int getActionPoints()
   {
      return this.actionPoints;
   }
   
   public void setActionPoints(int value)
   {
      if (this.actionPoints != value) {
      
         int oldValue = this.actionPoints;
         this.actionPoints = value;
         this.firePropertyChange(PROPERTY_ACTIONPOINTS, oldValue, value);
      }
   }
   
   public Game withActionPoints(int value)
   {
      setActionPoints(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getActionPoints());
      return result.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              one                       many
    * Game ----------------------------------- Trainer
    *              game                   trainers
    * </pre>
    */
   
   public static final String PROPERTY_TRAINERS = "trainers";

   private TrainerSet trainers = null;
   
   public TrainerSet getTrainers()
   {
      if (this.trainers == null)
      {
         return TrainerSet.EMPTY_SET;
      }
   
      return this.trainers;
   }

   public Game withTrainers(Trainer... value)
   {
      if(value==null){
         return this;
      }
      for (Trainer item : value)
      {
         if (item != null)
         {
            if (this.trainers == null)
            {
               this.trainers = new TrainerSet();
            }
            
            boolean changed = this.trainers.add (item);

            if (changed)
            {
               item.withGame(this);
               firePropertyChange(PROPERTY_TRAINERS, null, item);
            }
         }
      }
      return this;
   } 

   public Game withoutTrainers(Trainer... value)
   {
      for (Trainer item : value)
      {
         if ((this.trainers != null) && (item != null))
         {
            if (this.trainers.remove(item))
            {
               item.setGame(null);
               firePropertyChange(PROPERTY_TRAINERS, item, null);
            }
         }
      }
      return this;
   }

   public Trainer createTrainers()
   {
      Trainer value = new Trainer();
      withTrainers(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Game ----------------------------------- Trainer
    *              currentGame                   currentTrainer
    * </pre>
    */
   
   public static final String PROPERTY_CURRENTTRAINER = "currentTrainer";

   private Trainer currentTrainer = null;

   public Trainer getCurrentTrainer()
   {
      return this.currentTrainer;
   }

   public boolean setCurrentTrainer(Trainer value)
   {
      boolean changed = false;
      
      if (this.currentTrainer != value)
      {
         Trainer oldValue = this.currentTrainer;
         
         if (this.currentTrainer != null)
         {
            this.currentTrainer = null;
            oldValue.setCurrentGame(null);
         }
         
         this.currentTrainer = value;
         
         if (value != null)
         {
            value.withCurrentGame(this);
         }
         
         firePropertyChange(PROPERTY_CURRENTTRAINER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Game withCurrentTrainer(Trainer value)
   {
      setCurrentTrainer(value);
      return this;
   } 

   public Trainer createCurrentTrainer()
   {
      Trainer value = new Trainer();
      withCurrentTrainer(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Game ----------------------------------- Dice
    *              game                   dice
    * </pre>
    */
   
   public static final String PROPERTY_DICE = "dice";

   private Dice dice = null;

   public Dice getDice()
   {
      return this.dice;
   }

   public boolean setDice(Dice value)
   {
      boolean changed = false;
      
      if (this.dice != value)
      {
         Dice oldValue = this.dice;
         
         if (this.dice != null)
         {
            this.dice = null;
            oldValue.setGame(null);
         }
         
         this.dice = value;
         
         if (value != null)
         {
            value.withGame(this);
         }
         
         firePropertyChange(PROPERTY_DICE, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Game withDice(Dice value)
   {
      setDice(value);
      return this;
   } 

   public Dice createDice()
   {
      Dice value = new Dice();
      withDice(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Game ----------------------------------- Ground
    *              game                   grounds
    * </pre>
    */
   
   public static final String PROPERTY_GROUNDS = "grounds";

   private GroundSet grounds = null;
   
   public GroundSet getGrounds()
   {
      if (this.grounds == null)
      {
         return GroundSet.EMPTY_SET;
      }
   
      return this.grounds;
   }

   public Game withGrounds(Ground... value)
   {
      if(value==null){
         return this;
      }
      for (Ground item : value)
      {
         if (item != null)
         {
            if (this.grounds == null)
            {
               this.grounds = new GroundSet();
            }
            
            boolean changed = this.grounds.add (item);

            if (changed)
            {
               item.withGame(this);
               firePropertyChange(PROPERTY_GROUNDS, null, item);
            }
         }
      }
      return this;
   } 

   public Game withoutGrounds(Ground... value)
   {
      for (Ground item : value)
      {
         if ((this.grounds != null) && (item != null))
         {
            if (this.grounds.remove(item))
            {
               item.setGame(null);
               firePropertyChange(PROPERTY_GROUNDS, item, null);
            }
         }
      }
      return this;
   }

   public Ground createGrounds()
   {
      Ground value = new Ground();
      withGrounds(value);
      return value;
   } 

   public Grass createGroundsGrass()
   {
      Grass value = new Grass();
      withGrounds(value);
      return value;
   } 

   public Rock createGroundsRock()
   {
      Rock value = new Rock();
      withGrounds(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Game ----------------------------------- Zombie
    *              game                   zombies
    * </pre>
    */
   
   public static final String PROPERTY_ZOMBIES = "zombies";

   private ZombieSet zombies = null;
   
   public ZombieSet getZombies()
   {
      if (this.zombies == null)
      {
         return ZombieSet.EMPTY_SET;
      }
   
      return this.zombies;
   }

   public Game withZombies(Zombie... value)
   {
      if(value==null){
         return this;
      }
      for (Zombie item : value)
      {
         if (item != null)
         {
            if (this.zombies == null)
            {
               this.zombies = new ZombieSet();
            }
            
            boolean changed = this.zombies.add (item);

            if (changed)
            {
               item.withGame(this);
               firePropertyChange(PROPERTY_ZOMBIES, null, item);
            }
         }
      }
      return this;
   } 

   public Game withoutZombies(Zombie... value)
   {
      for (Zombie item : value)
      {
         if ((this.zombies != null) && (item != null))
         {
            if (this.zombies.remove(item))
            {
               item.setGame(null);
               firePropertyChange(PROPERTY_ZOMBIES, item, null);
            }
         }
      }
      return this;
   }

   public Zombie createZombies()
   {
      Zombie value = new Zombie();
      withZombies(value);
      return value;
   }
   
   public void init(Trainer... trainers) {

		// set assocs game -trainer
		for (Trainer trainer : trainers) {
			trainer.withGame(this);
		}

		Trainer first = this.getTrainers().get(0);
		Trainer second = this.getTrainers().get(1);

		// set traps- each trainer gets 17 traps of various type of success
		// rates
		setTrapsWithTrainer(trainers);

		// set dice
		this.withDice(new Dice());

		// set field -
		setGameWithGround();

		// place first trainer at coordinate (0,0)
		GroundSet groundSet = this.getGrounds();
		first.withGround(groundSet.get(0));

		// place second trainer at coordinate (4,5)
		second.withGround(groundSet.get(groundSet.size() - 1));

		// set next-prev assocs
		first.withNext(second);
		second.withNext(first);

		// set first trainer as current trainer
		this.withCurrentTrainer(first);

		// set 3 action points
		this.withActionPoints(3);
	}

	private void setGameWithGround() {
		for (int y = 0; y < 6; y++) {
			for (int x = 0; x < 5; x++) {

				if ((x == 1 && y == 1) || (x == 1 && y == 3) || (x == 3 && y == 2)) {
					this.withGrounds(createGrounds().withX(x).withY(y));
				} else if ((x == 3 && y == 3)) {
					this.withGrounds(createGroundsRock().withX(x).withY(y));
				} else {
					this.withGrounds(createGroundsGrass().withX(x).withY(y));
				}
			}
		}
	}

	private void setTrapsWithTrainer(Trainer... trainers) {
		for (Trainer trainer : trainers) {

			for (int i = 0; i < 10; i++) {
				trainer.withTraps(trainer.createTraps().withSuccesRate(10));
			}
			for (int i = 0; i < 5; i++) {
				trainer.withTraps(trainer.createTraps().withSuccesRate(50));
			}
			trainer.withTraps(trainer.createTraps().withSuccesRate(100));
			trainer.withTraps(trainer.createTraps().withSuccesRate(125));
		}
	}

}
