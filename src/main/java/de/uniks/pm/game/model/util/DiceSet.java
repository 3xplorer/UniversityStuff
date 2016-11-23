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
import de.uniks.pm.game.model.Dice;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.NumberList;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.pm.game.model.util.GameSet;
import de.uniks.pm.game.model.Game;

public class DiceSet extends SimpleSet<Dice>
{
	protected Class<?> getTypClass() {
		return Dice.class;
	}

   public DiceSet()
   {
      // empty
   }

   public DiceSet(Dice... objects)
   {
      for (Dice obj : objects)
      {
         this.add(obj);
      }
   }

   public DiceSet(Collection<Dice> objects)
   {
      this.addAll(objects);
   }

   public static final DiceSet EMPTY_SET = new DiceSet().withFlag(DiceSet.READONLY);


   public DicePO createDicePO()
   {
      return new DicePO(this.toArray(new Dice[this.size()]));
   }


   public String getEntryType()
   {
      return "de.uniks.pm.game.model.Dice";
   }


   @Override
   public DiceSet getNewList(boolean keyValue)
   {
      return new DiceSet();
   }


   public DiceSet filter(Condition<Dice> condition) {
      DiceSet filterList = new DiceSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public DiceSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Dice>)value);
      }
      else if (value != null)
      {
         this.add((Dice) value);
      }
      
      return this;
   }
   
   public DiceSet without(Dice value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of Dice objects and collect a list of the value attribute values. 
    * 
    * @return List of int objects reachable via value attribute
    */
   public NumberList getValue()
   {
      NumberList result = new NumberList();
      
      for (Dice obj : this)
      {
         result.add(obj.getValue());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Dice objects and collect those Dice objects where the value attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Dice objects that match the parameter
    */
   public DiceSet filterValue(int value)
   {
      DiceSet result = new DiceSet();
      
      for (Dice obj : this)
      {
         if (value == obj.getValue())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Dice objects and collect those Dice objects where the value attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Dice objects that match the parameter
    */
   public DiceSet filterValue(int lower, int upper)
   {
      DiceSet result = new DiceSet();
      
      for (Dice obj : this)
      {
         if (lower <= obj.getValue() && obj.getValue() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Dice objects and assign value to the value attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Dice objects now with new attribute values.
    */
   public DiceSet withValue(int value)
   {
      for (Dice obj : this)
      {
         obj.setValue(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Dice objects and collect a set of the Game objects reached via game. 
    * 
    * @return Set of Game objects reachable via game
    */
   public GameSet getGame()
   {
      GameSet result = new GameSet();
      
      for (Dice obj : this)
      {
         result.with(obj.getGame());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Dice objects and collect all contained objects with reference game pointing to the object passed as parameter. 
    * 
    * @param value The object required as game neighbor of the collected results. 
    * 
    * @return Set of Game objects referring to value via game
    */
   public DiceSet filterGame(Object value)
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
      
      DiceSet answer = new DiceSet();
      
      for (Dice obj : this)
      {
         if (neighbors.contains(obj.getGame()) || (neighbors.isEmpty() && obj.getGame() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Dice object passed as parameter to the Game attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Game attributes.
    */
   public DiceSet withGame(Game value)
   {
      for (Dice obj : this)
      {
         obj.withGame(value);
      }
      
      return this;
   }

}
