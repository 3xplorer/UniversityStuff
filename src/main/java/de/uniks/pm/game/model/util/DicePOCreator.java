package de.uniks.pm.game.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import de.uniks.pm.game.model.Dice;

public class DicePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new DicePO(new Dice[]{});
      } else {
          return new DicePO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return de.uniks.pm.game.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
