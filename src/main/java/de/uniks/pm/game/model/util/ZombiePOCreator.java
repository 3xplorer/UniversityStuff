package de.uniks.pm.game.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import de.uniks.pm.game.model.Zombie;

public class ZombiePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ZombiePO(new Zombie[]{});
      } else {
          return new ZombiePO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return de.uniks.pm.game.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
