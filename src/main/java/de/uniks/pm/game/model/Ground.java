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
import de.uniks.pm.game.model.Game;
import de.uniks.pm.game.model.util.TrainerSet;
import de.uniks.pm.game.model.Trainer;
   /**
    * 
    * @see <a href='../../../../../../../../src/main/java/de/uniks/pm/game/test/GenModelTest.java'>GenModelTest.java</a>
 */
   public  class Ground implements ZombieOwner, SendableEntity
{

   
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
      withoutTrainers(this.getTrainers().toArray(new Trainer[this.getTrainers().size()]));
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_X = "x";
   
   private int x;

   public int getX()
   {
      return this.x;
   }
   
   public void setX(int value)
   {
      if (this.x != value) {
      
         int oldValue = this.x;
         this.x = value;
         this.firePropertyChange(PROPERTY_X, oldValue, value);
      }
   }
   
   public Ground withX(int value)
   {
      setX(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getX());
      result.append(" ").append(this.getY());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_Y = "y";
   
   private int y;

   public int getY()
   {
      return this.y;
   }
   
   public void setY(int value)
   {
      if (this.y != value) {
      
         int oldValue = this.y;
         this.y = value;
         this.firePropertyChange(PROPERTY_Y, oldValue, value);
      }
   }
   
   public Ground withY(int value)
   {
      setY(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Ground ----------------------------------- Game
    *              grounds                   game
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
            oldValue.withoutGrounds(this);
         }
         
         this.game = value;
         
         if (value != null)
         {
            value.withGrounds(this);
         }
         
         firePropertyChange(PROPERTY_GAME, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Ground withGame(Game value)
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
    *              one                       many
    * Ground ----------------------------------- Trainer
    *              ground                   trainers
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

   public Ground withTrainers(Trainer... value)
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
               item.withGround(this);
               firePropertyChange(PROPERTY_TRAINERS, null, item);
            }
         }
      }
      return this;
   } 

   public Ground withoutTrainers(Trainer... value)
   {
      for (Trainer item : value)
      {
         if ((this.trainers != null) && (item != null))
         {
            if (this.trainers.remove(item))
            {
               item.setGround(null);
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
}
