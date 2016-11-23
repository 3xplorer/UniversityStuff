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
import de.uniks.pm.game.model.Trainer;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.pm.game.model.Ground;
import de.uniks.pm.game.model.Zombie;
import de.uniks.pm.game.model.Trap;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import de.uniks.pm.game.model.util.ZombieSet;
import de.uniks.networkparser.list.NumberList;
import de.uniks.pm.game.model.util.GameSet;
import de.uniks.pm.game.model.Game;
import de.uniks.pm.game.model.util.GroundSet;
import de.uniks.pm.game.model.util.TrainerSet;
import de.uniks.pm.game.model.util.TrapSet;

public class TrainerSet extends SimpleSet<Trainer>
{
	protected Class<?> getTypClass() {
		return Trainer.class;
	}

   public TrainerSet()
   {
      // empty
   }

   public TrainerSet(Trainer... objects)
   {
      for (Trainer obj : objects)
      {
         this.add(obj);
      }
   }

   public TrainerSet(Collection<Trainer> objects)
   {
      this.addAll(objects);
   }

   public static final TrainerSet EMPTY_SET = new TrainerSet().withFlag(TrainerSet.READONLY);


   public TrainerPO createTrainerPO()
   {
      return new TrainerPO(this.toArray(new Trainer[this.size()]));
   }


   public String getEntryType()
   {
      return "de.uniks.pm.game.model.Trainer";
   }


   @Override
   public TrainerSet getNewList(boolean keyValue)
   {
      return new TrainerSet();
   }


   public TrainerSet filter(Condition<Trainer> condition) {
      TrainerSet filterList = new TrainerSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public TrainerSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Trainer>)value);
      }
      else if (value != null)
      {
         this.add((Trainer) value);
      }
      
      return this;
   }
   
   public TrainerSet without(Trainer value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public TrainerSet movePlayer(Ground p0)
   {
      return TrainerSet.EMPTY_SET;
   }

   
   //==========================================================================
   
   public TrainerSet attackZombie(Zombie p0, Zombie p1)
   {
      return TrainerSet.EMPTY_SET;
   }

   
   //==========================================================================
   
   public TrainerSet catchZombie(Zombie p0, Trap p1)
   {
      return TrainerSet.EMPTY_SET;
   }

   /**
    * Loop through the current set of Trainer objects and collect a set of the Zombie objects reached via zombies. 
    * 
    * @return Set of Zombie objects reachable via zombies
    */
   public ZombieSet getZombies()
   {
      ZombieSet result = new ZombieSet();
      
      for (Trainer obj : this)
      {
         result.with(obj.getZombies());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Trainer objects and collect all contained objects with reference zombies pointing to the object passed as parameter. 
    * 
    * @param value The object required as zombies neighbor of the collected results. 
    * 
    * @return Set of Zombie objects referring to value via zombies
    */
   public TrainerSet filterZombies(Object value)
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
      
      TrainerSet answer = new TrainerSet();
      
      for (Trainer obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getZombies()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Trainer object passed as parameter to the Zombies attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Zombies attributes.
    */
   public TrainerSet withZombies(Zombie value)
   {
      for (Trainer obj : this)
      {
         obj.withZombies(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Trainer object passed as parameter from the Zombies attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public TrainerSet withoutZombies(Zombie value)
   {
      for (Trainer obj : this)
      {
         obj.withoutZombies(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Trainer objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public ObjectSet getName()
   {
      ObjectSet result = new ObjectSet();
      
      for (Trainer obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Trainer objects and collect those Trainer objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Trainer objects that match the parameter
    */
   public TrainerSet filterName(String value)
   {
      TrainerSet result = new TrainerSet();
      
      for (Trainer obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Trainer objects and collect those Trainer objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Trainer objects that match the parameter
    */
   public TrainerSet filterName(String lower, String upper)
   {
      TrainerSet result = new TrainerSet();
      
      for (Trainer obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Trainer objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Trainer objects now with new attribute values.
    */
   public TrainerSet withName(String value)
   {
      for (Trainer obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Trainer objects and collect a list of the color attribute values. 
    * 
    * @return List of String objects reachable via color attribute
    */
   public ObjectSet getColor()
   {
      ObjectSet result = new ObjectSet();
      
      for (Trainer obj : this)
      {
         result.add(obj.getColor());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Trainer objects and collect those Trainer objects where the color attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Trainer objects that match the parameter
    */
   public TrainerSet filterColor(String value)
   {
      TrainerSet result = new TrainerSet();
      
      for (Trainer obj : this)
      {
         if (value.equals(obj.getColor()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Trainer objects and collect those Trainer objects where the color attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Trainer objects that match the parameter
    */
   public TrainerSet filterColor(String lower, String upper)
   {
      TrainerSet result = new TrainerSet();
      
      for (Trainer obj : this)
      {
         if (lower.compareTo(obj.getColor()) <= 0 && obj.getColor().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Trainer objects and assign value to the color attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Trainer objects now with new attribute values.
    */
   public TrainerSet withColor(String value)
   {
      for (Trainer obj : this)
      {
         obj.setColor(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Trainer objects and collect a list of the experience attribute values. 
    * 
    * @return List of int objects reachable via experience attribute
    */
   public NumberList getExperience()
   {
      NumberList result = new NumberList();
      
      for (Trainer obj : this)
      {
         result.add(obj.getExperience());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Trainer objects and collect those Trainer objects where the experience attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Trainer objects that match the parameter
    */
   public TrainerSet filterExperience(int value)
   {
      TrainerSet result = new TrainerSet();
      
      for (Trainer obj : this)
      {
         if (value == obj.getExperience())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Trainer objects and collect those Trainer objects where the experience attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Trainer objects that match the parameter
    */
   public TrainerSet filterExperience(int lower, int upper)
   {
      TrainerSet result = new TrainerSet();
      
      for (Trainer obj : this)
      {
         if (lower <= obj.getExperience() && obj.getExperience() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Trainer objects and assign value to the experience attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Trainer objects now with new attribute values.
    */
   public TrainerSet withExperience(int value)
   {
      for (Trainer obj : this)
      {
         obj.setExperience(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Trainer objects and collect a set of the Game objects reached via game. 
    * 
    * @return Set of Game objects reachable via game
    */
   public GameSet getGame()
   {
      GameSet result = new GameSet();
      
      for (Trainer obj : this)
      {
         result.with(obj.getGame());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Trainer objects and collect all contained objects with reference game pointing to the object passed as parameter. 
    * 
    * @param value The object required as game neighbor of the collected results. 
    * 
    * @return Set of Game objects referring to value via game
    */
   public TrainerSet filterGame(Object value)
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
      
      TrainerSet answer = new TrainerSet();
      
      for (Trainer obj : this)
      {
         if (neighbors.contains(obj.getGame()) || (neighbors.isEmpty() && obj.getGame() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Trainer object passed as parameter to the Game attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Game attributes.
    */
   public TrainerSet withGame(Game value)
   {
      for (Trainer obj : this)
      {
         obj.withGame(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Trainer objects and collect a set of the Game objects reached via currentGame. 
    * 
    * @return Set of Game objects reachable via currentGame
    */
   public GameSet getCurrentGame()
   {
      GameSet result = new GameSet();
      
      for (Trainer obj : this)
      {
         result.with(obj.getCurrentGame());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Trainer objects and collect all contained objects with reference currentGame pointing to the object passed as parameter. 
    * 
    * @param value The object required as currentGame neighbor of the collected results. 
    * 
    * @return Set of Game objects referring to value via currentGame
    */
   public TrainerSet filterCurrentGame(Object value)
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
      
      TrainerSet answer = new TrainerSet();
      
      for (Trainer obj : this)
      {
         if (neighbors.contains(obj.getCurrentGame()) || (neighbors.isEmpty() && obj.getCurrentGame() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Trainer object passed as parameter to the CurrentGame attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their CurrentGame attributes.
    */
   public TrainerSet withCurrentGame(Game value)
   {
      for (Trainer obj : this)
      {
         obj.withCurrentGame(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Trainer objects and collect a set of the Ground objects reached via ground. 
    * 
    * @return Set of Ground objects reachable via ground
    */
   public GroundSet getGround()
   {
      GroundSet result = new GroundSet();
      
      for (Trainer obj : this)
      {
         result.with(obj.getGround());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Trainer objects and collect all contained objects with reference ground pointing to the object passed as parameter. 
    * 
    * @param value The object required as ground neighbor of the collected results. 
    * 
    * @return Set of Ground objects referring to value via ground
    */
   public TrainerSet filterGround(Object value)
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
      
      TrainerSet answer = new TrainerSet();
      
      for (Trainer obj : this)
      {
         if (neighbors.contains(obj.getGround()) || (neighbors.isEmpty() && obj.getGround() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Trainer object passed as parameter to the Ground attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Ground attributes.
    */
   public TrainerSet withGround(Ground value)
   {
      for (Trainer obj : this)
      {
         obj.withGround(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Trainer objects and collect a set of the Trainer objects reached via next. 
    * 
    * @return Set of Trainer objects reachable via next
    */
   public TrainerSet getNext()
   {
      TrainerSet result = new TrainerSet();
      
      for (Trainer obj : this)
      {
         result.with(obj.getNext());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Trainer objects and collect all contained objects with reference next pointing to the object passed as parameter. 
    * 
    * @param value The object required as next neighbor of the collected results. 
    * 
    * @return Set of Trainer objects referring to value via next
    */
   public TrainerSet filterNext(Object value)
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
      
      TrainerSet answer = new TrainerSet();
      
      for (Trainer obj : this)
      {
         if (neighbors.contains(obj.getNext()) || (neighbors.isEmpty() && obj.getNext() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow next reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of Trainer objects reachable via next transitively (including the start set)
    */
   public TrainerSet getNextTransitive()
   {
      TrainerSet todo = new TrainerSet().with(this);
      
      TrainerSet result = new TrainerSet();
      
      while ( ! todo.isEmpty())
      {
         Trainer current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getNext()))
            {
               todo.with(current.getNext());
            }
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the Trainer object passed as parameter to the Next attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Next attributes.
    */
   public TrainerSet withNext(Trainer value)
   {
      for (Trainer obj : this)
      {
         obj.withNext(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Trainer objects and collect a set of the Trainer objects reached via prev. 
    * 
    * @return Set of Trainer objects reachable via prev
    */
   public TrainerSet getPrev()
   {
      TrainerSet result = new TrainerSet();
      
      for (Trainer obj : this)
      {
         result.with(obj.getPrev());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Trainer objects and collect all contained objects with reference prev pointing to the object passed as parameter. 
    * 
    * @param value The object required as prev neighbor of the collected results. 
    * 
    * @return Set of Trainer objects referring to value via prev
    */
   public TrainerSet filterPrev(Object value)
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
      
      TrainerSet answer = new TrainerSet();
      
      for (Trainer obj : this)
      {
         if (neighbors.contains(obj.getPrev()) || (neighbors.isEmpty() && obj.getPrev() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow prev reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of Trainer objects reachable via prev transitively (including the start set)
    */
   public TrainerSet getPrevTransitive()
   {
      TrainerSet todo = new TrainerSet().with(this);
      
      TrainerSet result = new TrainerSet();
      
      while ( ! todo.isEmpty())
      {
         Trainer current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getPrev()))
            {
               todo.with(current.getPrev());
            }
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the Trainer object passed as parameter to the Prev attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Prev attributes.
    */
   public TrainerSet withPrev(Trainer value)
   {
      for (Trainer obj : this)
      {
         obj.withPrev(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Trainer objects and collect a set of the Trap objects reached via traps. 
    * 
    * @return Set of Trap objects reachable via traps
    */
   public TrapSet getTraps()
   {
      TrapSet result = new TrapSet();
      
      for (Trainer obj : this)
      {
         result.with(obj.getTraps());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Trainer objects and collect all contained objects with reference traps pointing to the object passed as parameter. 
    * 
    * @param value The object required as traps neighbor of the collected results. 
    * 
    * @return Set of Trap objects referring to value via traps
    */
   public TrainerSet filterTraps(Object value)
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
      
      TrainerSet answer = new TrainerSet();
      
      for (Trainer obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getTraps()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Trainer object passed as parameter to the Traps attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Traps attributes.
    */
   public TrainerSet withTraps(Trap value)
   {
      for (Trainer obj : this)
      {
         obj.withTraps(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Trainer object passed as parameter from the Traps attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public TrainerSet withoutTraps(Trap value)
   {
      for (Trainer obj : this)
      {
         obj.withoutTraps(value);
      }
      
      return this;
   }

}
