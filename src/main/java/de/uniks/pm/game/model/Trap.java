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
import de.uniks.pm.game.model.Trainer;
   /**
    * 
    * @see <a href='../../../../../../../../src/main/java/de/uniks/pm/game/test/GenModelTest.java'>GenModelTest.java</a>
 */
   public  class Trap implements SendableEntity
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
      setTrainer(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_SUCCESRATE = "succesRate";
   
   private int succesRate;

   public int getSuccesRate()
   {
      return this.succesRate;
   }
   
   public void setSuccesRate(int value)
   {
      if (this.succesRate != value) {
      
         int oldValue = this.succesRate;
         this.succesRate = value;
         this.firePropertyChange(PROPERTY_SUCCESRATE, oldValue, value);
      }
   }
   
   public Trap withSuccesRate(int value)
   {
      setSuccesRate(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getSuccesRate());
      return result.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              many                       one
    * Trap ----------------------------------- Trainer
    *              traps                   trainer
    * </pre>
    */
   
   public static final String PROPERTY_TRAINER = "trainer";

   private Trainer trainer = null;

   public Trainer getTrainer()
   {
      return this.trainer;
   }

   public boolean setTrainer(Trainer value)
   {
      boolean changed = false;
      
      if (this.trainer != value)
      {
         Trainer oldValue = this.trainer;
         
         if (this.trainer != null)
         {
            this.trainer = null;
            oldValue.withoutTraps(this);
         }
         
         this.trainer = value;
         
         if (value != null)
         {
            value.withTraps(this);
         }
         
         firePropertyChange(PROPERTY_TRAINER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Trap withTrainer(Trainer value)
   {
      setTrainer(value);
      return this;
   } 

   public Trainer createTrainer()
   {
      Trainer value = new Trainer();
      withTrainer(value);
      return value;
   } 
}
