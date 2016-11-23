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
import de.uniks.pm.game.model.Zombie;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.NumberList;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.pm.game.model.util.GameSet;
import de.uniks.pm.game.model.Game;
import de.uniks.pm.game.model.util.ZombieOwnerSet;
import de.uniks.pm.game.model.ZombieOwner;

public class ZombieSet extends SimpleSet<Zombie>
{
	protected Class<?> getTypClass() {
		return Zombie.class;
	}

   public ZombieSet()
   {
      // empty
   }

   public ZombieSet(Zombie... objects)
   {
      for (Zombie obj : objects)
      {
         this.add(obj);
      }
   }

   public ZombieSet(Collection<Zombie> objects)
   {
      this.addAll(objects);
   }

   public static final ZombieSet EMPTY_SET = new ZombieSet().withFlag(ZombieSet.READONLY);


   public ZombiePO createZombiePO()
   {
      return new ZombiePO(this.toArray(new Zombie[this.size()]));
   }


   public String getEntryType()
   {
      return "de.uniks.pm.game.model.Zombie";
   }


   @Override
   public ZombieSet getNewList(boolean keyValue)
   {
      return new ZombieSet();
   }


   public ZombieSet filter(Condition<Zombie> condition) {
      ZombieSet filterList = new ZombieSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public ZombieSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Zombie>)value);
      }
      else if (value != null)
      {
         this.add((Zombie) value);
      }
      
      return this;
   }
   
   public ZombieSet without(Zombie value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of Zombie objects and collect a list of the hp attribute values. 
    * 
    * @return List of int objects reachable via hp attribute
    */
   public NumberList getHp()
   {
      NumberList result = new NumberList();
      
      for (Zombie obj : this)
      {
         result.add(obj.getHp());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Zombie objects and collect those Zombie objects where the hp attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Zombie objects that match the parameter
    */
   public ZombieSet filterHp(int value)
   {
      ZombieSet result = new ZombieSet();
      
      for (Zombie obj : this)
      {
         if (value == obj.getHp())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Zombie objects and collect those Zombie objects where the hp attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Zombie objects that match the parameter
    */
   public ZombieSet filterHp(int lower, int upper)
   {
      ZombieSet result = new ZombieSet();
      
      for (Zombie obj : this)
      {
         if (lower <= obj.getHp() && obj.getHp() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Zombie objects and assign value to the hp attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Zombie objects now with new attribute values.
    */
   public ZombieSet withHp(int value)
   {
      for (Zombie obj : this)
      {
         obj.setHp(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Zombie objects and collect a list of the ap attribute values. 
    * 
    * @return List of int objects reachable via ap attribute
    */
   public NumberList getAp()
   {
      NumberList result = new NumberList();
      
      for (Zombie obj : this)
      {
         result.add(obj.getAp());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Zombie objects and collect those Zombie objects where the ap attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Zombie objects that match the parameter
    */
   public ZombieSet filterAp(int value)
   {
      ZombieSet result = new ZombieSet();
      
      for (Zombie obj : this)
      {
         if (value == obj.getAp())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Zombie objects and collect those Zombie objects where the ap attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Zombie objects that match the parameter
    */
   public ZombieSet filterAp(int lower, int upper)
   {
      ZombieSet result = new ZombieSet();
      
      for (Zombie obj : this)
      {
         if (lower <= obj.getAp() && obj.getAp() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Zombie objects and assign value to the ap attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Zombie objects now with new attribute values.
    */
   public ZombieSet withAp(int value)
   {
      for (Zombie obj : this)
      {
         obj.setAp(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Zombie objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public ObjectSet getName()
   {
      ObjectSet result = new ObjectSet();
      
      for (Zombie obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Zombie objects and collect those Zombie objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Zombie objects that match the parameter
    */
   public ZombieSet filterName(String value)
   {
      ZombieSet result = new ZombieSet();
      
      for (Zombie obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Zombie objects and collect those Zombie objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Zombie objects that match the parameter
    */
   public ZombieSet filterName(String lower, String upper)
   {
      ZombieSet result = new ZombieSet();
      
      for (Zombie obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Zombie objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Zombie objects now with new attribute values.
    */
   public ZombieSet withName(String value)
   {
      for (Zombie obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Zombie objects and collect a set of the Game objects reached via game. 
    * 
    * @return Set of Game objects reachable via game
    */
   public GameSet getGame()
   {
      GameSet result = new GameSet();
      
      for (Zombie obj : this)
      {
         result.with(obj.getGame());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Zombie objects and collect all contained objects with reference game pointing to the object passed as parameter. 
    * 
    * @param value The object required as game neighbor of the collected results. 
    * 
    * @return Set of Game objects referring to value via game
    */
   public ZombieSet filterGame(Object value)
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
      
      ZombieSet answer = new ZombieSet();
      
      for (Zombie obj : this)
      {
         if (neighbors.contains(obj.getGame()) || (neighbors.isEmpty() && obj.getGame() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Zombie object passed as parameter to the Game attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Game attributes.
    */
   public ZombieSet withGame(Game value)
   {
      for (Zombie obj : this)
      {
         obj.withGame(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Zombie objects and collect a set of the ZombieOwner objects reached via zombieowner. 
    * 
    * @return Set of ZombieOwner objects reachable via zombieowner
    */
   public ZombieOwnerSet getZombieowner()
   {
      ZombieOwnerSet result = new ZombieOwnerSet();
      
      for (Zombie obj : this)
      {
         result.with(obj.getZombieowner());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Zombie objects and collect all contained objects with reference zombieowner pointing to the object passed as parameter. 
    * 
    * @param value The object required as zombieowner neighbor of the collected results. 
    * 
    * @return Set of ZombieOwner objects referring to value via zombieowner
    */
   public ZombieSet filterZombieowner(Object value)
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
      
      ZombieSet answer = new ZombieSet();
      
      for (Zombie obj : this)
      {
         if (neighbors.contains(obj.getZombieowner()) || (neighbors.isEmpty() && obj.getZombieowner() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Zombie object passed as parameter to the Zombieowner attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Zombieowner attributes.
    */
   public ZombieSet withZombieowner(ZombieOwner value)
   {
      for (Zombie obj : this)
      {
         obj.withZombieowner(value);
      }
      
      return this;
   }

}
