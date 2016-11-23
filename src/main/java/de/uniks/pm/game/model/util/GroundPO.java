package de.uniks.pm.game.model.util;

import org.sdmlib.models.pattern.PatternObject;
import de.uniks.pm.game.model.Ground;
import de.uniks.pm.game.model.util.ZombiePO;
import de.uniks.pm.game.model.Zombie;
import de.uniks.pm.game.model.util.GroundPO;
import de.uniks.pm.game.model.util.ZombieSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import de.uniks.pm.game.model.util.GamePO;
import de.uniks.pm.game.model.Game;
import de.uniks.pm.game.model.util.TrainerPO;
import de.uniks.pm.game.model.Trainer;
import de.uniks.pm.game.model.util.TrainerSet;

public class GroundPO extends PatternObject<GroundPO, Ground>
{

    public GroundSet allMatches()
   {
      this.setDoAllMatches(true);
      
      GroundSet matches = new GroundSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Ground) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public GroundPO(){
      newInstance(null);
   }

   public GroundPO(Ground... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public GroundPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public ZombiePO createZombiesPO()
   {
      ZombiePO result = new ZombiePO(new Zombie[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Ground.PROPERTY_ZOMBIES, result);
      
      return result;
   }

   public ZombiePO createZombiesPO(String modifier)
   {
      ZombiePO result = new ZombiePO(new Zombie[]{});
      
      result.setModifier(modifier);
      super.hasLink(Ground.PROPERTY_ZOMBIES, result);
      
      return result;
   }

   public GroundPO createZombiesLink(ZombiePO tgt)
   {
      return hasLinkConstraint(tgt, Ground.PROPERTY_ZOMBIES);
   }

   public GroundPO createZombiesLink(ZombiePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Ground.PROPERTY_ZOMBIES, modifier);
   }

   public ZombieSet getZombies()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Ground) this.getCurrentMatch()).getZombies();
      }
      return null;
   }

   public GroundPO createXCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Ground.PROPERTY_X)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GroundPO createXCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Ground.PROPERTY_X)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GroundPO createXAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Ground.PROPERTY_X)
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
         return ((Ground) getCurrentMatch()).getX();
      }
      return 0;
   }
   
   public GroundPO withX(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Ground) getCurrentMatch()).setX(value);
      }
      return this;
   }
   
   public GroundPO createYCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Ground.PROPERTY_Y)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GroundPO createYCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Ground.PROPERTY_Y)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GroundPO createYAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Ground.PROPERTY_Y)
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
         return ((Ground) getCurrentMatch()).getY();
      }
      return 0;
   }
   
   public GroundPO withY(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Ground) getCurrentMatch()).setY(value);
      }
      return this;
   }
   
   public GamePO createGamePO()
   {
      GamePO result = new GamePO(new Game[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Ground.PROPERTY_GAME, result);
      
      return result;
   }

   public GamePO createGamePO(String modifier)
   {
      GamePO result = new GamePO(new Game[]{});
      
      result.setModifier(modifier);
      super.hasLink(Ground.PROPERTY_GAME, result);
      
      return result;
   }

   public GroundPO createGameLink(GamePO tgt)
   {
      return hasLinkConstraint(tgt, Ground.PROPERTY_GAME);
   }

   public GroundPO createGameLink(GamePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Ground.PROPERTY_GAME, modifier);
   }

   public Game getGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Ground) this.getCurrentMatch()).getGame();
      }
      return null;
   }

   public TrainerPO createTrainersPO()
   {
      TrainerPO result = new TrainerPO(new Trainer[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Ground.PROPERTY_TRAINERS, result);
      
      return result;
   }

   public TrainerPO createTrainersPO(String modifier)
   {
      TrainerPO result = new TrainerPO(new Trainer[]{});
      
      result.setModifier(modifier);
      super.hasLink(Ground.PROPERTY_TRAINERS, result);
      
      return result;
   }

   public GroundPO createTrainersLink(TrainerPO tgt)
   {
      return hasLinkConstraint(tgt, Ground.PROPERTY_TRAINERS);
   }

   public GroundPO createTrainersLink(TrainerPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Ground.PROPERTY_TRAINERS, modifier);
   }

   public TrainerSet getTrainers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Ground) this.getCurrentMatch()).getTrainers();
      }
      return null;
   }

}
