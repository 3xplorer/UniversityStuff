package de.uniks.pm.game.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import de.uniks.pm.game.model.Grass;

public class GrassPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new GrassPO(new Grass[]{});
      } else {
          return new GrassPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return de.uniks.pm.game.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
