package de.uniks.pm.game.model.util;

import org.sdmlib.models.pattern.PatternObject;
import de.uniks.pm.game.model.Game;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import de.uniks.pm.game.model.util.TrainerPO;
import de.uniks.pm.game.model.Trainer;
import de.uniks.pm.game.model.util.GamePO;
import de.uniks.pm.game.model.util.TrainerSet;
import de.uniks.pm.game.model.util.DicePO;
import de.uniks.pm.game.model.Dice;
import de.uniks.pm.game.model.util.GroundPO;
import de.uniks.pm.game.model.Ground;
import de.uniks.pm.game.model.util.GroundSet;
import de.uniks.pm.game.model.util.ZombiePO;
import de.uniks.pm.game.model.Zombie;
import de.uniks.pm.game.model.util.ZombieSet;

public class GamePO extends PatternObject<GamePO, Game>
{

    public GameSet allMatches()
   {
      this.setDoAllMatches(true);
      
      GameSet matches = new GameSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Game) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public GamePO(){
      newInstance(null);
   }

   public GamePO(Game... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public GamePO(String modifier)
   {
      this.setModifier(modifier);
   }
   
   //==========================================================================
   
   public boolean checkEnd()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Game) getCurrentMatch()).checkEnd();
      }
      return false;
   }

   public GamePO createActionPointsCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Game.PROPERTY_ACTIONPOINTS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GamePO createActionPointsCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Game.PROPERTY_ACTIONPOINTS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GamePO createActionPointsAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Game.PROPERTY_ACTIONPOINTS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getActionPoints()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Game) getCurrentMatch()).getActionPoints();
      }
      return 0;
   }
   
   public GamePO withActionPoints(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Game) getCurrentMatch()).setActionPoints(value);
      }
      return this;
   }
   
   public TrainerPO createTrainersPO()
   {
      TrainerPO result = new TrainerPO(new Trainer[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Game.PROPERTY_TRAINERS, result);
      
      return result;
   }

   public TrainerPO createTrainersPO(String modifier)
   {
      TrainerPO result = new TrainerPO(new Trainer[]{});
      
      result.setModifier(modifier);
      super.hasLink(Game.PROPERTY_TRAINERS, result);
      
      return result;
   }

   public GamePO createTrainersLink(TrainerPO tgt)
   {
      return hasLinkConstraint(tgt, Game.PROPERTY_TRAINERS);
   }

   public GamePO createTrainersLink(TrainerPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Game.PROPERTY_TRAINERS, modifier);
   }

   public TrainerSet getTrainers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Game) this.getCurrentMatch()).getTrainers();
      }
      return null;
   }

   public TrainerPO createCurrentTrainerPO()
   {
      TrainerPO result = new TrainerPO(new Trainer[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Game.PROPERTY_CURRENTTRAINER, result);
      
      return result;
   }

   public TrainerPO createCurrentTrainerPO(String modifier)
   {
      TrainerPO result = new TrainerPO(new Trainer[]{});
      
      result.setModifier(modifier);
      super.hasLink(Game.PROPERTY_CURRENTTRAINER, result);
      
      return result;
   }

   public GamePO createCurrentTrainerLink(TrainerPO tgt)
   {
      return hasLinkConstraint(tgt, Game.PROPERTY_CURRENTTRAINER);
   }

   public GamePO createCurrentTrainerLink(TrainerPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Game.PROPERTY_CURRENTTRAINER, modifier);
   }

   public Trainer getCurrentTrainer()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Game) this.getCurrentMatch()).getCurrentTrainer();
      }
      return null;
   }

   public DicePO createDicePO()
   {
      DicePO result = new DicePO(new Dice[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Game.PROPERTY_DICE, result);
      
      return result;
   }

   public DicePO createDicePO(String modifier)
   {
      DicePO result = new DicePO(new Dice[]{});
      
      result.setModifier(modifier);
      super.hasLink(Game.PROPERTY_DICE, result);
      
      return result;
   }

   public GamePO createDiceLink(DicePO tgt)
   {
      return hasLinkConstraint(tgt, Game.PROPERTY_DICE);
   }

   public GamePO createDiceLink(DicePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Game.PROPERTY_DICE, modifier);
   }

   public Dice getDice()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Game) this.getCurrentMatch()).getDice();
      }
      return null;
   }

   public GroundPO createGroundsPO()
   {
      GroundPO result = new GroundPO(new Ground[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Game.PROPERTY_GROUNDS, result);
      
      return result;
   }

   public GroundPO createGroundsPO(String modifier)
   {
      GroundPO result = new GroundPO(new Ground[]{});
      
      result.setModifier(modifier);
      super.hasLink(Game.PROPERTY_GROUNDS, result);
      
      return result;
   }

   public GamePO createGroundsLink(GroundPO tgt)
   {
      return hasLinkConstraint(tgt, Game.PROPERTY_GROUNDS);
   }

   public GamePO createGroundsLink(GroundPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Game.PROPERTY_GROUNDS, modifier);
   }

   public GroundSet getGrounds()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Game) this.getCurrentMatch()).getGrounds();
      }
      return null;
   }

   public ZombiePO createZombiesPO()
   {
      ZombiePO result = new ZombiePO(new Zombie[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Game.PROPERTY_ZOMBIES, result);
      
      return result;
   }

   public ZombiePO createZombiesPO(String modifier)
   {
      ZombiePO result = new ZombiePO(new Zombie[]{});
      
      result.setModifier(modifier);
      super.hasLink(Game.PROPERTY_ZOMBIES, result);
      
      return result;
   }

   public GamePO createZombiesLink(ZombiePO tgt)
   {
      return hasLinkConstraint(tgt, Game.PROPERTY_ZOMBIES);
   }

   public GamePO createZombiesLink(ZombiePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Game.PROPERTY_ZOMBIES, modifier);
   }

   public ZombieSet getZombies()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Game) this.getCurrentMatch()).getZombies();
      }
      return null;
   }

}
