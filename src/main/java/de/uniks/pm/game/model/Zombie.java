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
import de.uniks.networkparser.EntityUtil;
import de.uniks.pm.game.model.Game;
import de.uniks.pm.game.model.ZombieOwner;
   /**
    * 
    * @see <a href='../../../../../../../../src/main/java/de/uniks/pm/game/test/GenModelTest.java'>GenModelTest.java</a>
 */
   public  class Zombie implements SendableEntity
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

   
   //==========================================================================
   
   
   public void removeYou()
   {
      setGame(null);
      setZombieowner(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_HP = "hp";
   
   private int hp;

   public int getHp()
   {
      return this.hp;
   }
   
   public void setHp(int value)
   {
      if (this.hp != value) {
      
         int oldValue = this.hp;
         this.hp = value;
         this.firePropertyChange(PROPERTY_HP, oldValue, value);
      }
   }
   
   public Zombie withHp(int value)
   {
      setHp(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getHp());
      result.append(" ").append(this.getAp());
      result.append(" ").append(this.getName());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_AP = "ap";
   
   private int ap;

   public int getAp()
   {
      return this.ap;
   }
   
   public void setAp(int value)
   {
      if (this.ap != value) {
      
         int oldValue = this.ap;
         this.ap = value;
         this.firePropertyChange(PROPERTY_AP, oldValue, value);
      }
   }
   
   public Zombie withAp(int value)
   {
      setAp(value);
      return this;
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
   
   public Zombie withName(String value)
   {
      setName(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Zombie ----------------------------------- Game
    *              zombies                   game
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
            oldValue.withoutZombies(this);
         }
         
         this.game = value;
         
         if (value != null)
         {
            value.withZombies(this);
         }
         
         firePropertyChange(PROPERTY_GAME, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Zombie withGame(Game value)
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
    *              many                       one
    * Zombie ----------------------------------- ZombieOwner
    *              zombies                   zombieowner
    * </pre>
    */
   
   public static final String PROPERTY_ZOMBIEOWNER = "zombieowner";

   private ZombieOwner zombieowner = null;

   public ZombieOwner getZombieowner()
   {
      return this.zombieowner;
   }

   public boolean setZombieowner(ZombieOwner value)
   {
      boolean changed = false;
      
      if (this.zombieowner != value)
      {
         ZombieOwner oldValue = this.zombieowner;
         
         
         this.zombieowner = value;
         
         
         firePropertyChange(PROPERTY_ZOMBIEOWNER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Zombie withZombieowner(ZombieOwner value)
   {
      setZombieowner(value);
      return this;
   } 
}
