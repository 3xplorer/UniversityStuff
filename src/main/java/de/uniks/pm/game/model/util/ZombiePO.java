package de.uniks.pm.game.model.util;

import org.sdmlib.models.pattern.PatternObject;
import de.uniks.pm.game.model.Zombie;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import de.uniks.pm.game.model.util.GamePO;
import de.uniks.pm.game.model.Game;
import de.uniks.pm.game.model.util.ZombiePO;
import de.uniks.pm.game.model.util.ZombieOwnerPO;
import de.uniks.pm.game.model.ZombieOwner;

public class ZombiePO extends PatternObject<ZombiePO, Zombie>
{

    public ZombieSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ZombieSet matches = new ZombieSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Zombie) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ZombiePO(){
      newInstance(null);
   }

   public ZombiePO(Zombie... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public ZombiePO(String modifier)
   {
      this.setModifier(modifier);
   }
   public ZombiePO createHpCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Zombie.PROPERTY_HP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ZombiePO createHpCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Zombie.PROPERTY_HP)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ZombiePO createHpAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Zombie.PROPERTY_HP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getHp()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Zombie) getCurrentMatch()).getHp();
      }
      return 0;
   }
   
   public ZombiePO withHp(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Zombie) getCurrentMatch()).setHp(value);
      }
      return this;
   }
   
   public ZombiePO createApCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Zombie.PROPERTY_AP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ZombiePO createApCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Zombie.PROPERTY_AP)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ZombiePO createApAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Zombie.PROPERTY_AP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getAp()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Zombie) getCurrentMatch()).getAp();
      }
      return 0;
   }
   
   public ZombiePO withAp(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Zombie) getCurrentMatch()).setAp(value);
      }
      return this;
   }
   
   public ZombiePO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Zombie.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ZombiePO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Zombie.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ZombiePO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Zombie.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Zombie) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public ZombiePO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Zombie) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public GamePO createGamePO()
   {
      GamePO result = new GamePO(new Game[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Zombie.PROPERTY_GAME, result);
      
      return result;
   }

   public GamePO createGamePO(String modifier)
   {
      GamePO result = new GamePO(new Game[]{});
      
      result.setModifier(modifier);
      super.hasLink(Zombie.PROPERTY_GAME, result);
      
      return result;
   }

   public ZombiePO createGameLink(GamePO tgt)
   {
      return hasLinkConstraint(tgt, Zombie.PROPERTY_GAME);
   }

   public ZombiePO createGameLink(GamePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Zombie.PROPERTY_GAME, modifier);
   }

   public Game getGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Zombie) this.getCurrentMatch()).getGame();
      }
      return null;
   }

   public ZombieOwnerPO createZombieownerPO()
   {
      ZombieOwnerPO result = new ZombieOwnerPO(new ZombieOwner[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Zombie.PROPERTY_ZOMBIEOWNER, result);
      
      return result;
   }

   public ZombieOwnerPO createZombieownerPO(String modifier)
   {
      ZombieOwnerPO result = new ZombieOwnerPO(new ZombieOwner[]{});
      
      result.setModifier(modifier);
      super.hasLink(Zombie.PROPERTY_ZOMBIEOWNER, result);
      
      return result;
   }

   public ZombiePO createZombieownerLink(ZombieOwnerPO tgt)
   {
      return hasLinkConstraint(tgt, Zombie.PROPERTY_ZOMBIEOWNER);
   }

   public ZombiePO createZombieownerLink(ZombieOwnerPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Zombie.PROPERTY_ZOMBIEOWNER, modifier);
   }

   public ZombieOwner getZombieowner()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Zombie) this.getCurrentMatch()).getZombieowner();
      }
      return null;
   }

}
