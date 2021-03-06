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
import de.uniks.pm.game.model.ZombieOwner;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import de.uniks.pm.game.model.util.ZombieSet;
import de.uniks.pm.game.model.Zombie;

public class ZombieOwnerSet extends SimpleSet<ZombieOwner>
{
	protected Class<?> getTypClass() {
		return ZombieOwner.class;
	}

   public ZombieOwnerSet()
   {
      // empty
   }

   public ZombieOwnerSet(ZombieOwner... objects)
   {
      for (ZombieOwner obj : objects)
      {
         this.add(obj);
      }
   }

   public ZombieOwnerSet(Collection<ZombieOwner> objects)
   {
      this.addAll(objects);
   }

   public static final ZombieOwnerSet EMPTY_SET = new ZombieOwnerSet().withFlag(ZombieOwnerSet.READONLY);


   public ZombieOwnerPO createZombieOwnerPO()
   {
      return new ZombieOwnerPO(this.toArray(new ZombieOwner[this.size()]));
   }


   public String getEntryType()
   {
      return "de.uniks.pm.game.model.ZombieOwner";
   }


   @Override
   public ZombieOwnerSet getNewList(boolean keyValue)
   {
      return new ZombieOwnerSet();
   }


   public ZombieOwnerSet filter(Condition<ZombieOwner> condition) {
      ZombieOwnerSet filterList = new ZombieOwnerSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public ZombieOwnerSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ZombieOwner>)value);
      }
      else if (value != null)
      {
         this.add((ZombieOwner) value);
      }
      
      return this;
   }
   
   public ZombieOwnerSet without(ZombieOwner value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of ZombieOwner objects and collect a set of the Zombie objects reached via zombies. 
    * 
    * @return Set of Zombie objects reachable via zombies
    */
   public ZombieSet getZombies()
   {
      ZombieSet result = new ZombieSet();
      
      for (ZombieOwner obj : this)
      {
         result.with(obj.getZombies());
      }
      
      return result;
   }

   /**
    * Loop through the current set of ZombieOwner objects and collect all contained objects with reference zombies pointing to the object passed as parameter. 
    * 
    * @param value The object required as zombies neighbor of the collected results. 
    * 
    * @return Set of Zombie objects referring to value via zombies
    */
   public ZombieOwnerSet filterZombies(Object value)
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
      
      ZombieOwnerSet answer = new ZombieOwnerSet();
      
      for (ZombieOwner obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getZombies()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the ZombieOwner object passed as parameter to the Zombies attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Zombies attributes.
    */
   public ZombieOwnerSet withZombies(Zombie value)
   {
      for (ZombieOwner obj : this)
      {
         obj.withZombies(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the ZombieOwner object passed as parameter from the Zombies attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public ZombieOwnerSet withoutZombies(Zombie value)
   {
      for (ZombieOwner obj : this)
      {
         obj.withoutZombies(value);
      }
      
      return this;
   }

}
