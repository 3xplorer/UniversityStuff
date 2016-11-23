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
import de.uniks.pm.game.model.Trainer;
import de.uniks.networkparser.IdMap;
import de.uniks.pm.game.model.Zombie;
import de.uniks.pm.game.model.ZombieOwner;
import de.uniks.pm.game.model.Game;
import de.uniks.pm.game.model.Ground;
import de.uniks.pm.game.model.Trap;

public class TrainerCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Trainer.PROPERTY_ZOMBIES,
      Trainer.PROPERTY_NAME,
      Trainer.PROPERTY_COLOR,
      Trainer.PROPERTY_EXPERIENCE,
      Trainer.PROPERTY_GAME,
      Trainer.PROPERTY_CURRENTGAME,
      Trainer.PROPERTY_GROUND,
      Trainer.PROPERTY_NEXT,
      Trainer.PROPERTY_PREV,
      Trainer.PROPERTY_TRAPS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Trainer();
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

      if (Trainer.PROPERTY_ZOMBIES.equalsIgnoreCase(attribute))
      {
         return ((Trainer) target).getZombies();
      }

      if (Trainer.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Trainer) target).getName();
      }

      if (Trainer.PROPERTY_COLOR.equalsIgnoreCase(attribute))
      {
         return ((Trainer) target).getColor();
      }

      if (Trainer.PROPERTY_EXPERIENCE.equalsIgnoreCase(attribute))
      {
         return ((Trainer) target).getExperience();
      }

      if (Trainer.PROPERTY_GAME.equalsIgnoreCase(attribute))
      {
         return ((Trainer) target).getGame();
      }

      if (Trainer.PROPERTY_CURRENTGAME.equalsIgnoreCase(attribute))
      {
         return ((Trainer) target).getCurrentGame();
      }

      if (Trainer.PROPERTY_GROUND.equalsIgnoreCase(attribute))
      {
         return ((Trainer) target).getGround();
      }

      if (Trainer.PROPERTY_NEXT.equalsIgnoreCase(attribute))
      {
         return ((Trainer) target).getNext();
      }

      if (Trainer.PROPERTY_PREV.equalsIgnoreCase(attribute))
      {
         return ((Trainer) target).getPrev();
      }

      if (Trainer.PROPERTY_TRAPS.equalsIgnoreCase(attribute))
      {
         return ((Trainer) target).getTraps();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Trainer.PROPERTY_EXPERIENCE.equalsIgnoreCase(attrName))
      {
         ((Trainer) target).setExperience(Integer.parseInt(value.toString()));
         return true;
      }

      if (Trainer.PROPERTY_COLOR.equalsIgnoreCase(attrName))
      {
         ((Trainer) target).setColor((String) value);
         return true;
      }

      if (Trainer.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Trainer) target).setName((String) value);
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Trainer.PROPERTY_ZOMBIES.equalsIgnoreCase(attrName))
      {
         ((Trainer) target).withZombies((Zombie) value);
         return true;
      }
      
      if ((Trainer.PROPERTY_ZOMBIES + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Trainer) target).withoutZombies((Zombie) value);
         return true;
      }

      if (Trainer.PROPERTY_GAME.equalsIgnoreCase(attrName))
      {
         ((Trainer) target).setGame((Game) value);
         return true;
      }

      if (Trainer.PROPERTY_CURRENTGAME.equalsIgnoreCase(attrName))
      {
         ((Trainer) target).setCurrentGame((Game) value);
         return true;
      }

      if (Trainer.PROPERTY_GROUND.equalsIgnoreCase(attrName))
      {
         ((Trainer) target).setGround((Ground) value);
         return true;
      }

      if (Trainer.PROPERTY_NEXT.equalsIgnoreCase(attrName))
      {
         ((Trainer) target).setNext((Trainer) value);
         return true;
      }

      if (Trainer.PROPERTY_PREV.equalsIgnoreCase(attrName))
      {
         ((Trainer) target).setPrev((Trainer) value);
         return true;
      }

      if (Trainer.PROPERTY_TRAPS.equalsIgnoreCase(attrName))
      {
         ((Trainer) target).withTraps((Trap) value);
         return true;
      }
      
      if ((Trainer.PROPERTY_TRAPS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Trainer) target).withoutTraps((Trap) value);
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
      ((Trainer) entity).removeYou();
   }
}
