package de.uniks.pm.game.model.util;

import org.sdmlib.models.pattern.PatternObject;
import de.uniks.pm.game.model.ZombieOwner;
import de.uniks.pm.game.model.util.ZombiePO;
import de.uniks.pm.game.model.Zombie;
import de.uniks.pm.game.model.util.ZombieOwnerPO;
import de.uniks.pm.game.model.util.ZombieSet;

public class ZombieOwnerPO extends PatternObject<ZombieOwnerPO, ZombieOwner>
{

    public ZombieOwnerSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ZombieOwnerSet matches = new ZombieOwnerSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ZombieOwner) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ZombieOwnerPO(){
      newInstance(null);
   }

   public ZombieOwnerPO(ZombieOwner... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public ZombieOwnerPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public ZombiePO createZombiesPO()
   {
      ZombiePO result = new ZombiePO(new Zombie[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ZombieOwner.PROPERTY_ZOMBIES, result);
      
      return result;
   }

   public ZombiePO createZombiesPO(String modifier)
   {
      ZombiePO result = new ZombiePO(new Zombie[]{});
      
      result.setModifier(modifier);
      super.hasLink(ZombieOwner.PROPERTY_ZOMBIES, result);
      
      return result;
   }

   public ZombieOwnerPO createZombiesLink(ZombiePO tgt)
   {
      return hasLinkConstraint(tgt, ZombieOwner.PROPERTY_ZOMBIES);
   }

   public ZombieOwnerPO createZombiesLink(ZombiePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, ZombieOwner.PROPERTY_ZOMBIES, modifier);
   }

   public ZombieSet getZombies()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ZombieOwner) this.getCurrentMatch()).getZombies();
      }
      return null;
   }

}
