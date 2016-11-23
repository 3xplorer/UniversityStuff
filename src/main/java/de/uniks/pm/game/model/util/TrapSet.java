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
   
package de.uniks.pm.game.model.util;

import de.uniks.networkparser.list.SimpleSet;
import de.uniks.pm.game.model.Trap;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.NumberList;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.pm.game.model.util.TrainerSet;
import de.uniks.pm.game.model.Trainer;

public class TrapSet extends SimpleSet<Trap>
{
	protected Class<?> getTypClass() {
		return Trap.class;
	}

   public TrapSet()
   {
      // empty
   }

   public TrapSet(Trap... objects)
   {
      for (Trap obj : objects)
      {
         this.add(obj);
      }
   }

   public TrapSet(Collection<Trap> objects)
   {
      this.addAll(objects);
   }

   public static final TrapSet EMPTY_SET = new TrapSet().withFlag(TrapSet.READONLY);


   public TrapPO createTrapPO()
   {
      return new TrapPO(this.toArray(new Trap[this.size()]));
   }


   public String getEntryType()
   {
      return "de.uniks.pm.game.model.Trap";
   }


   @Override
   public TrapSet getNewList(boolean keyValue)
   {
      return new TrapSet();
   }


   public TrapSet filter(Condition<Trap> condition) {
      TrapSet filterList = new TrapSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public TrapSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Trap>)value);
      }
      else if (value != null)
      {
         this.add((Trap) value);
      }
      
      return this;
   }
   
   public TrapSet without(Trap value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of Trap objects and collect a list of the succesRate attribute values. 
    * 
    * @return List of int objects reachable via succesRate attribute
    */
   public NumberList getSuccesRate()
   {
      NumberList result = new NumberList();
      
      for (Trap obj : this)
      {
         result.add(obj.getSuccesRate());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Trap objects and collect those Trap objects where the succesRate attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Trap objects that match the parameter
    */
   public TrapSet filterSuccesRate(int value)
   {
      TrapSet result = new TrapSet();
      
      for (Trap obj : this)
      {
         if (value == obj.getSuccesRate())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Trap objects and collect those Trap objects where the succesRate attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Trap objects that match the parameter
    */
   public TrapSet filterSuccesRate(int lower, int upper)
   {
      TrapSet result = new TrapSet();
      
      for (Trap obj : this)
      {
         if (lower <= obj.getSuccesRate() && obj.getSuccesRate() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Trap objects and assign value to the succesRate attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Trap objects now with new attribute values.
    */
   public TrapSet withSuccesRate(int value)
   {
      for (Trap obj : this)
      {
         obj.setSuccesRate(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Trap objects and collect a set of the Trainer objects reached via trainer. 
    * 
    * @return Set of Trainer objects reachable via trainer
    */
   public TrainerSet getTrainer()
   {
      TrainerSet result = new TrainerSet();
      
      for (Trap obj : this)
      {
         result.with(obj.getTrainer());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Trap objects and collect all contained objects with reference trainer pointing to the object passed as parameter. 
    * 
    * @param value The object required as trainer neighbor of the collected results. 
    * 
    * @return Set of Trainer objects referring to value via trainer
    */
   public TrapSet filterTrainer(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      TrapSet answer = new TrapSet();
      
      for (Trap obj : this)
      {
         if (neighbors.contains(obj.getTrainer()) || (neighbors.isEmpty() && obj.getTrainer() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Trap object passed as parameter to the Trainer attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Trainer attributes.
    */
   public TrapSet withTrainer(Trainer value)
   {
      for (Trap obj : this)
      {
         obj.withTrainer(value);
      }
      
      return this;
   }

}
