package de.uniks.pm.game.model.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = new IdMap().withSessionId(sessionID);
      jsonIdMap.with(new GameCreator());
      jsonIdMap.with(new GamePOCreator());
      jsonIdMap.with(new DiceCreator());
      jsonIdMap.with(new DicePOCreator());
      jsonIdMap.with(new TrainerCreator());
      jsonIdMap.with(new TrainerPOCreator());
      jsonIdMap.with(new ZombieCreator());
      jsonIdMap.with(new ZombiePOCreator());
      jsonIdMap.with(new GroundCreator());
      jsonIdMap.with(new GroundPOCreator());
      jsonIdMap.with(new TrapCreator());
      jsonIdMap.with(new TrapPOCreator());
      jsonIdMap.with(new RockCreator());
      jsonIdMap.with(new RockPOCreator());
      jsonIdMap.with(new GrassCreator());
      jsonIdMap.with(new GrassPOCreator());
      jsonIdMap.with(new ZombieOwnerCreator());
      jsonIdMap.with(new ZombieOwnerPOCreator());
      return jsonIdMap;
   }
}
