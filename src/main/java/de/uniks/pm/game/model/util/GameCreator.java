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
import de.uniks.pm.game.model.Game;
import de.uniks.networkparser.IdMap;
import de.uniks.pm.game.model.Trainer;
import de.uniks.pm.game.model.Dice;
import de.uniks.pm.game.model.Ground;
import de.uniks.pm.game.model.Zombie;

public class GameCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Game.PROPERTY_ACTIONPOINTS,
      Game.PROPERTY_TRAINERS,
      Game.PROPERTY_CURRENTTRAINER,
      Game.PROPERTY_DICE,
      Game.PROPERTY_GROUNDS,
      Game.PROPERTY_ZOMBIES,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Game();
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

      if (Game.PROPERTY_ACTIONPOINTS.equalsIgnoreCase(attribute))
      {
         return ((Game) target).getActionPoints();
      }

      if (Game.PROPERTY_TRAINERS.equalsIgnoreCase(attribute))
      {
         return ((Game) target).getTrainers();
      }

      if (Game.PROPERTY_CURRENTTRAINER.equalsIgnoreCase(attribute))
      {
         return ((Game) target).getCurrentTrainer();
      }

      if (Game.PROPERTY_DICE.equalsIgnoreCase(attribute))
      {
         return ((Game) target).getDice();
      }

      if (Game.PROPERTY_GROUNDS.equalsIgnoreCase(attribute))
      {
         return ((Game) target).getGrounds();
      }

      if (Game.PROPERTY_ZOMBIES.equalsIgnoreCase(attribute))
      {
         return ((Game) target).getZombies();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Game.PROPERTY_ACTIONPOINTS.equalsIgnoreCase(attrName))
      {
         ((Game) target).setActionPoints(Integer.parseInt(value.toString()));
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Game.PROPERTY_TRAINERS.equalsIgnoreCase(attrName))
      {
         ((Game) target).withTrainers((Trainer) value);
         return true;
      }
      
      if ((Game.PROPERTY_TRAINERS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Game) target).withoutTrainers((Trainer) value);
         return true;
      }

      if (Game.PROPERTY_CURRENTTRAINER.equalsIgnoreCase(attrName))
      {
         ((Game) target).setCurrentTrainer((Trainer) value);
         return true;
      }

      if (Game.PROPERTY_DICE.equalsIgnoreCase(attrName))
      {
         ((Game) target).setDice((Dice) value);
         return true;
      }

      if (Game.PROPERTY_GROUNDS.equalsIgnoreCase(attrName))
      {
         ((Game) target).withGrounds((Ground) value);
         return true;
      }
      
      if ((Game.PROPERTY_GROUNDS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Game) target).withoutGrounds((Ground) value);
         return true;
      }

      if (Game.PROPERTY_ZOMBIES.equalsIgnoreCase(attrName))
      {
         ((Game) target).withZombies((Zombie) value);
         return true;
      }
      
      if ((Game.PROPERTY_ZOMBIES + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Game) target).withoutZombies((Zombie) value);
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
      ((Game) entity).removeYou();
   }
}
