package de.uniks.pm.game.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import de.uniks.pm.game.model.Ground;

public class GroundPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new GroundPO(new Ground[]{});
      } else {
          return new GroundPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return de.uniks.pm.game.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
