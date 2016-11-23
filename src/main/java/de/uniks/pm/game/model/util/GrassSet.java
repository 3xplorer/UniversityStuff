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
import de.uniks.pm.game.model.Grass;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import de.uniks.pm.game.model.util.ZombieSet;
import de.uniks.pm.game.model.Zombie;
import de.uniks.networkparser.list.NumberList;
import de.uniks.pm.game.model.util.GameSet;
import de.uniks.pm.game.model.Game;
import de.uniks.pm.game.model.util.TrainerSet;
import de.uniks.pm.game.model.Trainer;

public class GrassSet extends SimpleSet<Grass>
{
	protected Class<?> getTypClass() {
		return Grass.class;
	}

   public GrassSet()
   {
      // empty
   }

   public GrassSet(Grass... objects)
   {
      for (Grass obj : objects)
      {
         this.add(obj);
      }
   }

   public GrassSet(Collection<Grass> objects)
   {
      this.addAll(objects);
   }

   public static final GrassSet EMPTY_SET = new GrassSet().withFlag(GrassSet.READONLY);


   public GrassPO createGrassPO()
   {
      return new GrassPO(this.toArray(new Grass[this.size()]));
   }


   public String getEntryType()
   {
      return "de.uniks.pm.game.model.Grass";
   }


   @Override
   public GrassSet getNewList(boolean keyValue)
   {
      return new GrassSet();
   }


   public GrassSet filter(Condition<Grass> condition) {
      GrassSet filterList = new GrassSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public GrassSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Grass>)value);
      }
      else if (value != null)
      {
         this.add((Grass) value);
      }
      
      return this;
   }
   
   public GrassSet without(Grass value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of Grass objects and collect a set of the Zombie objects reached via zombies. 
    * 
    * @return Set of Zombie objects reachable via zombies
    */
   public ZombieSet getZombies()
   {
      ZombieSet result = new ZombieSet();
      
      for (Grass obj : this)
      {
         result.with(obj.getZombies());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Grass objects and collect all contained objects with reference zombies pointing to the object passed as parameter. 
    * 
    * @param value The object required as zombies neighbor of the collected results. 
    * 
    * @return Set of Zombie objects referring to value via zombies
    */
   public GrassSet filterZombies(Object value)
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
      
      GrassSet answer = new GrassSet();
      
      for (Grass obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getZombies()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Grass object passed as parameter to the Zombies attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Zombies attributes.
    */
   public GrassSet withZombies(Zombie value)
   {
      for (Grass obj : this)
      {
         obj.withZombies(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Grass object passed as parameter from the Zombies attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public GrassSet withoutZombies(Zombie value)
   {
      for (Grass obj : this)
      {
         obj.withoutZombies(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Grass objects and collect a list of the x attribute values. 
    * 
    * @return List of int objects reachable via x attribute
    */
   public NumberList getX()
   {
      NumberList result = new NumberList();
      
      for (Grass obj : this)
      {
         result.add(obj.getX());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Grass objects and collect those Grass objects where the x attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Grass objects that match the parameter
    */
   public GrassSet filterX(int value)
   {
      GrassSet result = new GrassSet();
      
      for (Grass obj : this)
      {
         if (value == obj.getX())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Grass objects and collect those Grass objects where the x attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Grass objects that match the parameter
    */
   public GrassSet filterX(int lower, int upper)
   {
      GrassSet result = new GrassSet();
      
      for (Grass obj : this)
      {
         if (lower <= obj.getX() && obj.getX() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Grass objects and assign value to the x attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Grass objects now with new attribute values.
    */
   public GrassSet withX(int value)
   {
      for (Grass obj : this)
      {
         obj.setX(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Grass objects and collect a list of the y attribute values. 
    * 
    * @return List of int objects reachable via y attribute
    */
   public NumberList getY()
   {
      NumberList result = new NumberList();
      
      for (Grass obj : this)
      {
         result.add(obj.getY());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Grass objects and collect those Grass objects where the y attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Grass objects that match the parameter
    */
   public GrassSet filterY(int value)
   {
      GrassSet result = new GrassSet();
      
      for (Grass obj : this)
      {
         if (value == obj.getY())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Grass objects and collect those Grass objects where the y attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Grass objects that match the parameter
    */
   public GrassSet filterY(int lower, int upper)
   {
      GrassSet result = new GrassSet();
      
      for (Grass obj : this)
      {
         if (lower <= obj.getY() && obj.getY() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Grass objects and assign value to the y attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Grass objects now with new attribute values.
    */
   public GrassSet withY(int value)
   {
      for (Grass obj : this)
      {
         obj.setY(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Grass objects and collect a set of the Game objects reached via game. 
    * 
    * @return Set of Game objects reachable via game
    */
   public GameSet getGame()
   {
      GameSet result = new GameSet();
      
      for (Grass obj : this)
      {
         result.with(obj.getGame());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Grass objects and collect all contained objects with reference game pointing to the object passed as parameter. 
    * 
    * @param value The object required as game neighbor of the collected results. 
    * 
    * @return Set of Game objects referring to value via game
    */
   public GrassSet filterGame(Object value)
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
      
      GrassSet answer = new GrassSet();
      
      for (Grass obj : this)
      {
         if (neighbors.contains(obj.getGame()) || (neighbors.isEmpty() && obj.getGame() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Grass object passed as parameter to the Game attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Game attributes.
    */
   public GrassSet withGame(Game value)
   {
      for (Grass obj : this)
      {
         obj.withGame(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Grass objects and collect a set of the Trainer objects reached via trainers. 
    * 
    * @return Set of Trainer objects reachable via trainers
    */
   public TrainerSet getTrainers()
   {
      TrainerSet result = new TrainerSet();
      
      for (Grass obj : this)
      {
         result.with(obj.getTrainers());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Grass objects and collect all contained objects with reference trainers pointing to the object passed as parameter. 
    * 
    * @param value The object required as trainers neighbor of the collected results. 
    * 
    * @return Set of Trainer objects referring to value via trainers
    */
   public GrassSet filterTrainers(Object value)
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
      
      GrassSet answer = new GrassSet();
      
      for (Grass obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getTrainers()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Grass object passed as parameter to the Trainers attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Trainers attributes.
    */
   public GrassSet withTrainers(Trainer value)
   {
      for (Grass obj : this)
      {
         obj.withTrainers(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Grass object passed as parameter from the Trainers attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public GrassSet withoutTrainers(Trainer value)
   {
      for (Grass obj : this)
      {
         obj.withoutTrainers(value);
      }
      
      return this;
   }

}
