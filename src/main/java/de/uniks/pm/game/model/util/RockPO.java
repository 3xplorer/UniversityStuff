package de.uniks.pm.game.model.util;

import org.sdmlib.models.pattern.PatternObject;
import de.uniks.pm.game.model.Rock;
import de.uniks.pm.game.model.util.ZombiePO;
import de.uniks.pm.game.model.Zombie;
import de.uniks.pm.game.model.util.RockPO;
import de.uniks.pm.game.model.util.ZombieSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import de.uniks.pm.game.model.util.GamePO;
import de.uniks.pm.game.model.Game;
import de.uniks.pm.game.model.util.TrainerPO;
import de.uniks.pm.game.model.Trainer;
import de.uniks.pm.game.model.util.TrainerSet;

public class RockPO extends PatternObject<RockPO, Rock>
{

    public RockSet allMatches()
   {
      this.setDoAllMatches(true);
      
      RockSet matches = new RockSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Rock) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public RockPO(){
      newInstance(null);
   }

   public RockPO(Rock... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public RockPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public ZombiePO createZombiesPO()
   {
      ZombiePO result = new ZombiePO(new Zombie[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Rock.PROPERTY_ZOMBIES, result);
      
      return result;
   }

   public ZombiePO createZombiesPO(String modifier)
   {
      ZombiePO result = new ZombiePO(new Zombie[]{});
      
      result.setModifier(modifier);
      super.hasLink(Rock.PROPERTY_ZOMBIES, result);
      
      return result;
   }

   public RockPO createZombiesLink(ZombiePO tgt)
   {
      return hasLinkConstraint(tgt, Rock.PROPERTY_ZOMBIES);
   }

   public RockPO createZombiesLink(ZombiePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Rock.PROPERTY_ZOMBIES, modifier);
   }

   public ZombieSet getZombies()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Rock) this.getCurrentMatch()).getZombies();
      }
      return null;
   }

   public RockPO createXCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Rock.PROPERTY_X)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public RockPO createXCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Rock.PROPERTY_X)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public RockPO createXAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Rock.PROPERTY_X)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getX()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Rock) getCurrentMatch()).getX();
      }
      return 0;
   }
   
   public RockPO withX(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Rock) getCurrentMatch()).setX(value);
      }
      return this;
   }
   
   public RockPO createYCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Rock.PROPERTY_Y)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public RockPO createYCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Rock.PROPERTY_Y)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public RockPO createYAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Rock.PROPERTY_Y)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getY()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Rock) getCurrentMatch()).getY();
      }
      return 0;
   }
   
   public RockPO withY(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Rock) getCurrentMatch()).setY(value);
      }
      return this;
   }
   
   public GamePO createGamePO()
   {
      GamePO result = new GamePO(new Game[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Rock.PROPERTY_GAME, result);
      
      return result;
   }

   public GamePO createGamePO(String modifier)
   {
      GamePO result = new GamePO(new Game[]{});
      
      result.setModifier(modifier);
      super.hasLink(Rock.PROPERTY_GAME, result);
      
      return result;
   }

   public RockPO createGameLink(GamePO tgt)
   {
      return hasLinkConstraint(tgt, Rock.PROPERTY_GAME);
   }

   public RockPO createGameLink(GamePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Rock.PROPERTY_GAME, modifier);
   }

   public Game getGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Rock) this.getCurrentMatch()).getGame();
      }
      return null;
   }

   public TrainerPO createTrainersPO()
   {
      TrainerPO result = new TrainerPO(new Trainer[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Rock.PROPERTY_TRAINERS, result);
      
      return result;
   }

   public TrainerPO createTrainersPO(String modifier)
   {
      TrainerPO result = new TrainerPO(new Trainer[]{});
      
      result.setModifier(modifier);
      super.hasLink(Rock.PROPERTY_TRAINERS, result);
      
      return result;
   }

   public RockPO createTrainersLink(TrainerPO tgt)
   {
      return hasLinkConstraint(tgt, Rock.PROPERTY_TRAINERS);
   }

   public RockPO createTrainersLink(TrainerPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Rock.PROPERTY_TRAINERS, modifier);
   }

   public TrainerSet getTrainers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Rock) this.getCurrentMatch()).getTrainers();
      }
      return null;
   }

}
