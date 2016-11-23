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
import de.uniks.pm.game.model.Ground;
import de.uniks.networkparser.IdMap;
import de.uniks.pm.game.model.Zombie;
import de.uniks.pm.game.model.ZombieOwner;
import de.uniks.pm.game.model.Game;
import de.uniks.pm.game.model.Trainer;

public class GroundCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Ground.PROPERTY_ZOMBIES,
      Ground.PROPERTY_X,
      Ground.PROPERTY_Y,
      Ground.PROPERTY_GAME,
      Ground.PROPERTY_TRAINERS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Ground();
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

      if (Ground.PROPERTY_ZOMBIES.equalsIgnoreCase(attribute))
      {
         return ((Ground) target).getZombies();
      }

      if (Ground.PROPERTY_X.equalsIgnoreCase(attribute))
      {
         return ((Ground) target).getX();
      }

      if (Ground.PROPERTY_Y.equalsIgnoreCase(attribute))
      {
         return ((Ground) target).getY();
      }

      if (Ground.PROPERTY_GAME.equalsIgnoreCase(attribute))
      {
         return ((Ground) target).getGame();
      }

      if (Ground.PROPERTY_TRAINERS.equalsIgnoreCase(attribute))
      {
         return ((Ground) target).getTrainers();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Ground.PROPERTY_Y.equalsIgnoreCase(attrName))
      {
         ((Ground) target).setY(Integer.parseInt(value.toString()));
         return true;
      }

      if (Ground.PROPERTY_X.equalsIgnoreCase(attrName))
      {
         ((Ground) target).setX(Integer.parseInt(value.toString()));
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Ground.PROPERTY_ZOMBIES.equalsIgnoreCase(attrName))
      {
         ((Ground) target).withZombies((Zombie) value);
         return true;
      }
      
      if ((Ground.PROPERTY_ZOMBIES + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Ground) target).withoutZombies((Zombie) value);
         return true;
      }

      if (Ground.PROPERTY_GAME.equalsIgnoreCase(attrName))
      {
         ((Ground) target).setGame((Game) value);
         return true;
      }

      if (Ground.PROPERTY_TRAINERS.equalsIgnoreCase(attrName))
      {
         ((Ground) target).withTrainers((Trainer) value);
         return true;
      }
      
      if ((Ground.PROPERTY_TRAINERS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Ground) target).withoutTrainers((Trainer) value);
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
      ((Ground) entity).removeYou();
   }
}
