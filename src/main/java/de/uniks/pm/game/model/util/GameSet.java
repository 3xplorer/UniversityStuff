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
import de.uniks.pm.game.model.Game;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.NumberList;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import de.uniks.pm.game.model.util.TrainerSet;
import de.uniks.pm.game.model.Trainer;
import de.uniks.pm.game.model.util.DiceSet;
import de.uniks.pm.game.model.Dice;
import de.uniks.pm.game.model.util.GroundSet;
import de.uniks.pm.game.model.Ground;
import de.uniks.pm.game.model.util.ZombieSet;
import de.uniks.pm.game.model.Zombie;

public class GameSet extends SimpleSet<Game>
{
	protected Class<?> getTypClass() {
		return Game.class;
	}

   public GameSet()
   {
      // empty
   }

   public GameSet(Game... objects)
   {
      for (Game obj : objects)
      {
         this.add(obj);
      }
   }

   public GameSet(Collection<Game> objects)
   {
      this.addAll(objects);
   }

   public static final GameSet EMPTY_SET = new GameSet().withFlag(GameSet.READONLY);


   public GamePO createGamePO()
   {
      return new GamePO(this.toArray(new Game[this.size()]));
   }


   public String getEntryType()
   {
      return "de.uniks.pm.game.model.Game";
   }


   @Override
   public GameSet getNewList(boolean keyValue)
   {
      return new GameSet();
   }


   public GameSet filter(Condition<Game> condition) {
      GameSet filterList = new GameSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public GameSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Game>)value);
      }
      else if (value != null)
      {
         this.add((Game) value);
      }
      
      return this;
   }
   
   public GameSet without(Game value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public de.uniks.networkparser.list.BooleanList checkEnd()
   {
      
      de.uniks.networkparser.list.BooleanList result = new de.uniks.networkparser.list.BooleanList();
      
      for (Game obj : this)
      {
         result.add( obj.checkEnd() );
      }
      return result;
   }


   /**
    * Loop through the current set of Game objects and collect a list of the actionPoints attribute values. 
    * 
    * @return List of int objects reachable via actionPoints attribute
    */
   public NumberList getActionPoints()
   {
      NumberList result = new NumberList();
      
      for (Game obj : this)
      {
         result.add(obj.getActionPoints());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Game objects and collect those Game objects where the actionPoints attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Game objects that match the parameter
    */
   public GameSet filterActionPoints(int value)
   {
      GameSet result = new GameSet();
      
      for (Game obj : this)
      {
         if (value == obj.getActionPoints())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Game objects and collect those Game objects where the actionPoints attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Game objects that match the parameter
    */
   public GameSet filterActionPoints(int lower, int upper)
   {
      GameSet result = new GameSet();
      
      for (Game obj : this)
      {
         if (lower <= obj.getActionPoints() && obj.getActionPoints() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Game objects and assign value to the actionPoints attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Game objects now with new attribute values.
    */
   public GameSet withActionPoints(int value)
   {
      for (Game obj : this)
      {
         obj.setActionPoints(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Game objects and collect a set of the Trainer objects reached via trainers. 
    * 
    * @return Set of Trainer objects reachable via trainers
    */
   public TrainerSet getTrainers()
   {
      TrainerSet result = new TrainerSet();
      
      for (Game obj : this)
      {
         result.with(obj.getTrainers());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Game objects and collect all contained objects with reference trainers pointing to the object passed as parameter. 
    * 
    * @param value The object required as trainers neighbor of the collected results. 
    * 
    * @return Set of Trainer objects referring to value via trainers
    */
   public GameSet filterTrainers(Object value)
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
      
      GameSet answer = new GameSet();
      
      for (Game obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getTrainers()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Game object passed as parameter to the Trainers attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Trainers attributes.
    */
   public GameSet withTrainers(Trainer value)
   {
      for (Game obj : this)
      {
         obj.withTrainers(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Game object passed as parameter from the Trainers attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public GameSet withoutTrainers(Trainer value)
   {
      for (Game obj : this)
      {
         obj.withoutTrainers(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Game objects and collect a set of the Trainer objects reached via currentTrainer. 
    * 
    * @return Set of Trainer objects reachable via currentTrainer
    */
   public TrainerSet getCurrentTrainer()
   {
      TrainerSet result = new TrainerSet();
      
      for (Game obj : this)
      {
         result.with(obj.getCurrentTrainer());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Game objects and collect all contained objects with reference currentTrainer pointing to the object passed as parameter. 
    * 
    * @param value The object required as currentTrainer neighbor of the collected results. 
    * 
    * @return Set of Trainer objects referring to value via currentTrainer
    */
   public GameSet filterCurrentTrainer(Object value)
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
      
      GameSet answer = new GameSet();
      
      for (Game obj : this)
      {
         if (neighbors.contains(obj.getCurrentTrainer()) || (neighbors.isEmpty() && obj.getCurrentTrainer() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Game object passed as parameter to the CurrentTrainer attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their CurrentTrainer attributes.
    */
   public GameSet withCurrentTrainer(Trainer value)
   {
      for (Game obj : this)
      {
         obj.withCurrentTrainer(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Game objects and collect a set of the Dice objects reached via dice. 
    * 
    * @return Set of Dice objects reachable via dice
    */
   public DiceSet getDice()
   {
      DiceSet result = new DiceSet();
      
      for (Game obj : this)
      {
         result.with(obj.getDice());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Game objects and collect all contained objects with reference dice pointing to the object passed as parameter. 
    * 
    * @param value The object required as dice neighbor of the collected results. 
    * 
    * @return Set of Dice objects referring to value via dice
    */
   public GameSet filterDice(Object value)
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
      
      GameSet answer = new GameSet();
      
      for (Game obj : this)
      {
         if (neighbors.contains(obj.getDice()) || (neighbors.isEmpty() && obj.getDice() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Game object passed as parameter to the Dice attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Dice attributes.
    */
   public GameSet withDice(Dice value)
   {
      for (Game obj : this)
      {
         obj.withDice(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Game objects and collect a set of the Ground objects reached via grounds. 
    * 
    * @return Set of Ground objects reachable via grounds
    */
   public GroundSet getGrounds()
   {
      GroundSet result = new GroundSet();
      
      for (Game obj : this)
      {
         result.with(obj.getGrounds());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Game objects and collect all contained objects with reference grounds pointing to the object passed as parameter. 
    * 
    * @param value The object required as grounds neighbor of the collected results. 
    * 
    * @return Set of Ground objects referring to value via grounds
    */
   public GameSet filterGrounds(Object value)
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
      
      GameSet answer = new GameSet();
      
      for (Game obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getGrounds()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Game object passed as parameter to the Grounds attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Grounds attributes.
    */
   public GameSet withGrounds(Ground value)
   {
      for (Game obj : this)
      {
         obj.withGrounds(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Game object passed as parameter from the Grounds attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public GameSet withoutGrounds(Ground value)
   {
      for (Game obj : this)
      {
         obj.withoutGrounds(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Game objects and collect a set of the Zombie objects reached via zombies. 
    * 
    * @return Set of Zombie objects reachable via zombies
    */
   public ZombieSet getZombies()
   {
      ZombieSet result = new ZombieSet();
      
      for (Game obj : this)
      {
         result.with(obj.getZombies());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Game objects and collect all contained objects with reference zombies pointing to the object passed as parameter. 
    * 
    * @param value The object required as zombies neighbor of the collected results. 
    * 
    * @return Set of Zombie objects referring to value via zombies
    */
   public GameSet filterZombies(Object value)
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
      
      GameSet answer = new GameSet();
      
      for (Game obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getZombies()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Game object passed as parameter to the Zombies attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Zombies attributes.
    */
   public GameSet withZombies(Zombie value)
   {
      for (Game obj : this)
      {
         obj.withZombies(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Game object passed as parameter from the Zombies attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public GameSet withoutZombies(Zombie value)
   {
      for (Game obj : this)
      {
         obj.withoutZombies(value);
      }
      
      return this;
   }

}
