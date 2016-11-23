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

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.pm.game.model.Zombie;
import de.uniks.networkparser.IdMap;
import de.uniks.pm.game.model.Game;
import de.uniks.pm.game.model.ZombieOwner;

public class ZombieCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Zombie.PROPERTY_HP,
      Zombie.PROPERTY_AP,
      Zombie.PROPERTY_NAME,
      Zombie.PROPERTY_GAME,
      Zombie.PROPERTY_ZOMBIEOWNER,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Zombie();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (Zombie.PROPERTY_HP.equalsIgnoreCase(attribute))
      {
         return ((Zombie) target).getHp();
      }

      if (Zombie.PROPERTY_AP.equalsIgnoreCase(attribute))
      {
         return ((Zombie) target).getAp();
      }

      if (Zombie.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Zombie) target).getName();
      }

      if (Zombie.PROPERTY_GAME.equalsIgnoreCase(attribute))
      {
         return ((Zombie) target).getGame();
      }

      if (Zombie.PROPERTY_ZOMBIEOWNER.equalsIgnoreCase(attribute))
      {
         return ((Zombie) target).getZombieowner();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Zombie.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Zombie) target).setName((String) value);
         return true;
      }

      if (Zombie.PROPERTY_AP.equalsIgnoreCase(attrName))
      {
         ((Zombie) target).setAp(Integer.parseInt(value.toString()));
         return true;
      }

      if (Zombie.PROPERTY_HP.equalsIgnoreCase(attrName))
      {
         ((Zombie) target).setHp(Integer.parseInt(value.toString()));
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Zombie.PROPERTY_GAME.equalsIgnoreCase(attrName))
      {
         ((Zombie) target).setGame((Game) value);
         return true;
      }

      if (Zombie.PROPERTY_ZOMBIEOWNER.equalsIgnoreCase(attrName))
      {
         ((Zombie) target).setZombieowner((ZombieOwner) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return de.uniks.pm.game.model.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((Zombie) entity).removeYou();
   }
}
