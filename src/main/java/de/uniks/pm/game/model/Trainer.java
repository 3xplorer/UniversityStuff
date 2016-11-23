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

import de.uniks.pm.game.model.ZombieOwner;
import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import de.uniks.pm.game.model.util.ZombieSet;
import de.uniks.pm.game.model.Zombie;
import de.uniks.networkparser.EntityUtil;
import de.uniks.pm.game.model.Game;
import de.uniks.pm.game.model.Ground;
import de.uniks.pm.game.model.util.TrainerSet;
import de.uniks.pm.game.model.util.TrapSet;
import de.uniks.pm.game.model.Trap;
   /**
    * 
    * @see <a href='../../../../../../../../src/main/java/de/uniks/pm/game/test/GenModelTest.java'>GenModelTest.java</a>
 */
   public  class Trainer implements ZombieOwner, SendableEntity
{

   
   //==========================================================================
   public void movePlayer( Ground p0 )
   {
      
   }

   
   //==========================================================================
   public void attackZombie( Zombie p0, Zombie p1 )
   {
      
   }

   
   //==========================================================================
   public void catchZombie( Zombie p0, Trap p1 )
   {
      
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

   
   /********************************************************************
    * <pre>
    *              one                       many
    * ZombieOwner ----------------------------------- Zombie
    *              zombieowner                   zombies
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

   public ZombieOwner withZombies(Zombie... value)
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
               item.withZombieowner(this);
               firePropertyChange(PROPERTY_ZOMBIES, null, item);
            }
         }
      }
      return this;
   } 

   public ZombieOwner withoutZombies(Zombie... value)
   {
      for (Zombie item : value)
      {
         if ((this.zombies != null) && (item != null))
         {
            if (this.zombies.remove(item))
            {
               item.setZombieowner(null);
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

   
   //==========================================================================
   
   
   public void removeYou()
   {
      setGame(null);
      setCurrentGame(null);
      setGround(null);
      setNext(null);
      setPrev(null);
      withoutTraps(this.getTraps().toArray(new Trap[this.getTraps().size()]));
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_NAME = "name";
   
   private String name;

   public String getName()
   {
      return this.name;
   }
   
   public void setName(String value)
   {
      if ( ! EntityUtil.stringEquals(this.name, value)) {
      
         String oldValue = this.name;
         this.name = value;
         this.firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }
   
   public Trainer withName(String value)
   {
      setName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getName());
      result.append(" ").append(this.getColor());
      result.append(" ").append(this.getExperience());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_COLOR = "color";
   
   private String color;

   public String getColor()
   {
      return this.color;
   }
   
   public void setColor(String value)
   {
      if ( ! EntityUtil.stringEquals(this.color, value)) {
      
         String oldValue = this.color;
         this.color = value;
         this.firePropertyChange(PROPERTY_COLOR, oldValue, value);
      }
   }
   
   public Trainer withColor(String value)
   {
      setColor(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_EXPERIENCE = "experience";
   
   private int experience;

   public int getExperience()
   {
      return this.experience;
   }
   
   public void setExperience(int value)
   {
      if (this.experience != value) {
      
         int oldValue = this.experience;
         this.experience = value;
         this.firePropertyChange(PROPERTY_EXPERIENCE, oldValue, value);
      }
   }
   
   public Trainer withExperience(int value)
   {
      setExperience(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Trainer ----------------------------------- Game
    *              trainers                   game
    * </pre>
    */
   
   public static final String PROPERTY_GAME = "game";

   private Game game = null;

   public Game getGame()
   {
      return this.game;
   }

   public boolean setGame(Game value)
   {
      boolean changed = false;
      
      if (this.game != value)
      {
         Game oldValue = this.game;
         
         if (this.game != null)
         {
            this.game = null;
            oldValue.withoutTrainers(this);
         }
         
         this.game = value;
         
         if (value != null)
         {
            value.withTrainers(this);
         }
         
         firePropertyChange(PROPERTY_GAME, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Trainer withGame(Game value)
   {
      setGame(value);
      return this;
   } 

   public Game createGame()
   {
      Game value = new Game();
      withGame(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Trainer ----------------------------------- Game
    *              currentTrainer                   currentGame
    * </pre>
    */
   
   public static final String PROPERTY_CURRENTGAME = "currentGame";

   private Game currentGame = null;

   public Game getCurrentGame()
   {
      return this.currentGame;
   }

   public boolean setCurrentGame(Game value)
   {
      boolean changed = false;
      
      if (this.currentGame != value)
      {
         Game oldValue = this.currentGame;
         
         if (this.currentGame != null)
         {
            this.currentGame = null;
            oldValue.setCurrentTrainer(null);
         }
         
         this.currentGame = value;
         
         if (value != null)
         {
            value.withCurrentTrainer(this);
         }
         
         firePropertyChange(PROPERTY_CURRENTGAME, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Trainer withCurrentGame(Game value)
   {
      setCurrentGame(value);
      return this;
   } 

   public Game createCurrentGame()
   {
      Game value = new Game();
      withCurrentGame(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Trainer ----------------------------------- Ground
    *              trainers                   ground
    * </pre>
    */
   
   public static final String PROPERTY_GROUND = "ground";

   private Ground ground = null;

   public Ground getGround()
   {
      return this.ground;
   }

   public boolean setGround(Ground value)
   {
      boolean changed = false;
      
      if (this.ground != value)
      {
         Ground oldValue = this.ground;
         
         if (this.ground != null)
         {
            this.ground = null;
            oldValue.withoutTrainers(this);
         }
         
         this.ground = value;
         
         if (value != null)
         {
            value.withTrainers(this);
         }
         
         firePropertyChange(PROPERTY_GROUND, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Trainer withGround(Ground value)
   {
      setGround(value);
      return this;
   } 

   public Ground createGround()
   {
      Ground value = new Ground();
      withGround(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Trainer ----------------------------------- Trainer
    *              prev                   next
    * </pre>
    */
   
   public static final String PROPERTY_NEXT = "next";

   private Trainer next = null;

   public Trainer getNext()
   {
      return this.next;
   }
   public TrainerSet getNextTransitive()
   {
      TrainerSet result = new TrainerSet().with(this);
      return result.getNextTransitive();
   }


   public boolean setNext(Trainer value)
   {
      boolean changed = false;
      
      if (this.next != value)
      {
         Trainer oldValue = this.next;
         
         if (this.next != null)
         {
            this.next = null;
            oldValue.setPrev(null);
         }
         
         this.next = value;
         
         if (value != null)
         {
            value.withPrev(this);
         }
         
         firePropertyChange(PROPERTY_NEXT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Trainer withNext(Trainer value)
   {
      setNext(value);
      return this;
   } 

   public Trainer createNext()
   {
      Trainer value = new Trainer();
      withNext(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Trainer ----------------------------------- Trainer
    *              next                   prev
    * </pre>
    */
   
   public static final String PROPERTY_PREV = "prev";

   private Trainer prev = null;

   public Trainer getPrev()
   {
      return this.prev;
   }
   public TrainerSet getPrevTransitive()
   {
      TrainerSet result = new TrainerSet().with(this);
      return result.getPrevTransitive();
   }


   public boolean setPrev(Trainer value)
   {
      boolean changed = false;
      
      if (this.prev != value)
      {
         Trainer oldValue = this.prev;
         
         if (this.prev != null)
         {
            this.prev = null;
            oldValue.setNext(null);
         }
         
         this.prev = value;
         
         if (value != null)
         {
            value.withNext(this);
         }
         
         firePropertyChange(PROPERTY_PREV, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Trainer withPrev(Trainer value)
   {
      setPrev(value);
      return this;
   } 

   public Trainer createPrev()
   {
      Trainer value = new Trainer();
      withPrev(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Trainer ----------------------------------- Trap
    *              trainer                   traps
    * </pre>
    */
   
   public static final String PROPERTY_TRAPS = "traps";

   private TrapSet traps = null;
   
   public TrapSet getTraps()
   {
      if (this.traps == null)
      {
         return TrapSet.EMPTY_SET;
      }
   
      return this.traps;
   }

   public Trainer withTraps(Trap... value)
   {
      if(value==null){
         return this;
      }
      for (Trap item : value)
      {
         if (item != null)
         {
            if (this.traps == null)
            {
               this.traps = new TrapSet();
            }
            
            boolean changed = this.traps.add (item);

            if (changed)
            {
               item.withTrainer(this);
               firePropertyChange(PROPERTY_TRAPS, null, item);
            }
         }
      }
      return this;
   } 

   public Trainer withoutTraps(Trap... value)
   {
      for (Trap item : value)
      {
         if ((this.traps != null) && (item != null))
         {
            if (this.traps.remove(item))
            {
               item.setTrainer(null);
               firePropertyChange(PROPERTY_TRAPS, item, null);
            }
         }
      }
      return this;
   }

   public Trap createTraps()
   {
      Trap value = new Trap();
      withTraps(value);
      return value;
   } 
}
