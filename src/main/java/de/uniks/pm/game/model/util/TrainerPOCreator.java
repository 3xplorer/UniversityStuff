package de.uniks.pm.game.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import de.uniks.pm.game.model.Trainer;

public class TrainerPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new TrainerPO(new Trainer[]{});
      } else {
          return new TrainerPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return de.uniks.pm.game.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
