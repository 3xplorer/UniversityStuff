package de.uniks.pm.game.model.util;

import org.sdmlib.models.pattern.PatternObject;
import de.uniks.pm.game.model.Trainer;
import de.uniks.pm.game.model.Ground;
import de.uniks.pm.game.model.Zombie;
import de.uniks.pm.game.model.Trap;
import de.uniks.pm.game.model.util.ZombiePO;
import de.uniks.pm.game.model.util.TrainerPO;
import de.uniks.pm.game.model.util.ZombieSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import de.uniks.pm.game.model.util.GamePO;
import de.uniks.pm.game.model.Game;
import de.uniks.pm.game.model.util.GroundPO;
import de.uniks.pm.game.model.util.TrapPO;
import de.uniks.pm.game.model.util.TrapSet;

public class TrainerPO extends PatternObject<TrainerPO, Trainer>
{

    public TrainerSet allMatches()
   {
      this.setDoAllMatches(true);
      
      TrainerSet matches = new TrainerSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Trainer) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public TrainerPO(){
      newInstance(null);
   }

   public TrainerPO(Trainer... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public TrainerPO(String modifier)
   {
      this.setModifier(modifier);
   }
   
   //==========================================================================
   
   public void movePlayer(Ground p0)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Trainer) getCurrentMatch()).movePlayer(p0);
      }
   }

   
   //==========================================================================
   
   public void attackZombie(Zombie p0, Zombie p1)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Trainer) getCurrentMatch()).attackZombie(p0, p1);
      }
   }

   
   //==========================================================================
   
   public void catchZombie(Zombie p0, Trap p1)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Trainer) getCurrentMatch()).catchZombie(p0, p1);
      }
   }

   public ZombiePO createZombiesPO()
   {
      ZombiePO result = new ZombiePO(new Zombie[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Trainer.PROPERTY_ZOMBIES, result);
      
      return result;
   }

   public ZombiePO createZombiesPO(String modifier)
   {
      ZombiePO result = new ZombiePO(new Zombie[]{});
      
      result.setModifier(modifier);
      super.hasLink(Trainer.PROPERTY_ZOMBIES, result);
      
      return result;
   }

   public TrainerPO createZombiesLink(ZombiePO tgt)
   {
      return hasLinkConstraint(tgt, Trainer.PROPERTY_ZOMBIES);
   }

   public TrainerPO createZombiesLink(ZombiePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Trainer.PROPERTY_ZOMBIES, modifier);
   }

   public ZombieSet getZombies()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Trainer) this.getCurrentMatch()).getZombies();
      }
      return null;
   }

   public TrainerPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Trainer.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TrainerPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Trainer.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TrainerPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Trainer.PROPERTY_NAME)
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
         return ((Trainer) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public TrainerPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Trainer) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public TrainerPO createColorCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Trainer.PROPERTY_COLOR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TrainerPO createColorCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Trainer.PROPERTY_COLOR)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TrainerPO createColorAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Trainer.PROPERTY_COLOR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getColor()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Trainer) getCurrentMatch()).getColor();
      }
      return null;
   }
   
   public TrainerPO withColor(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Trainer) getCurrentMatch()).setColor(value);
      }
      return this;
   }
   
   public TrainerPO createExperienceCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Trainer.PROPERTY_EXPERIENCE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TrainerPO createExperienceCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Trainer.PROPERTY_EXPERIENCE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TrainerPO createExperienceAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Trainer.PROPERTY_EXPERIENCE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getExperience()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Trainer) getCurrentMatch()).getExperience();
      }
      return 0;
   }
   
   public TrainerPO withExperience(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Trainer) getCurrentMatch()).setExperience(value);
      }
      return this;
   }
   
   public GamePO createGamePO()
   {
      GamePO result = new GamePO(new Game[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Trainer.PROPERTY_GAME, result);
      
      return result;
   }

   public GamePO createGamePO(String modifier)
   {
      GamePO result = new GamePO(new Game[]{});
      
      result.setModifier(modifier);
      super.hasLink(Trainer.PROPERTY_GAME, result);
      
      return result;
   }

   public TrainerPO createGameLink(GamePO tgt)
   {
      return hasLinkConstraint(tgt, Trainer.PROPERTY_GAME);
   }

   public TrainerPO createGameLink(GamePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Trainer.PROPERTY_GAME, modifier);
   }

   public Game getGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Trainer) this.getCurrentMatch()).getGame();
      }
      return null;
   }

   public GamePO createCurrentGamePO()
   {
      GamePO result = new GamePO(new Game[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Trainer.PROPERTY_CURRENTGAME, result);
      
      return result;
   }

   public GamePO createCurrentGamePO(String modifier)
   {
      GamePO result = new GamePO(new Game[]{});
      
      result.setModifier(modifier);
      super.hasLink(Trainer.PROPERTY_CURRENTGAME, result);
      
      return result;
   }

   public TrainerPO createCurrentGameLink(GamePO tgt)
   {
      return hasLinkConstraint(tgt, Trainer.PROPERTY_CURRENTGAME);
   }

   public TrainerPO createCurrentGameLink(GamePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Trainer.PROPERTY_CURRENTGAME, modifier);
   }

   public Game getCurrentGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Trainer) this.getCurrentMatch()).getCurrentGame();
      }
      return null;
   }

   public GroundPO createGroundPO()
   {
      GroundPO result = new GroundPO(new Ground[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Trainer.PROPERTY_GROUND, result);
      
      return result;
   }

   public GroundPO createGroundPO(String modifier)
   {
      GroundPO result = new GroundPO(new Ground[]{});
      
      result.setModifier(modifier);
      super.hasLink(Trainer.PROPERTY_GROUND, result);
      
      return result;
   }

   public TrainerPO createGroundLink(GroundPO tgt)
   {
      return hasLinkConstraint(tgt, Trainer.PROPERTY_GROUND);
   }

   public TrainerPO createGroundLink(GroundPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Trainer.PROPERTY_GROUND, modifier);
   }

   public Ground getGround()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Trainer) this.getCurrentMatch()).getGround();
      }
      return null;
   }

   public TrainerPO createNextPO()
   {
      TrainerPO result = new TrainerPO(new Trainer[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Trainer.PROPERTY_NEXT, result);
      
      return result;
   }

   public TrainerPO createNextPO(String modifier)
   {
      TrainerPO result = new TrainerPO(new Trainer[]{});
      
      result.setModifier(modifier);
      super.hasLink(Trainer.PROPERTY_NEXT, result);
      
      return result;
   }

   public TrainerPO createNextLink(TrainerPO tgt)
   {
      return hasLinkConstraint(tgt, Trainer.PROPERTY_NEXT);
   }

   public TrainerPO createNextLink(TrainerPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Trainer.PROPERTY_NEXT, modifier);
   }

   public Trainer getNext()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Trainer) this.getCurrentMatch()).getNext();
      }
      return null;
   }

   public TrainerPO createPrevPO()
   {
      TrainerPO result = new TrainerPO(new Trainer[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Trainer.PROPERTY_PREV, result);
      
      return result;
   }

   public TrainerPO createPrevPO(String modifier)
   {
      TrainerPO result = new TrainerPO(new Trainer[]{});
      
      result.setModifier(modifier);
      super.hasLink(Trainer.PROPERTY_PREV, result);
      
      return result;
   }

   public TrainerPO createPrevLink(TrainerPO tgt)
   {
      return hasLinkConstraint(tgt, Trainer.PROPERTY_PREV);
   }

   public TrainerPO createPrevLink(TrainerPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Trainer.PROPERTY_PREV, modifier);
   }

   public Trainer getPrev()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Trainer) this.getCurrentMatch()).getPrev();
      }
      return null;
   }

   public TrapPO createTrapsPO()
   {
      TrapPO result = new TrapPO(new Trap[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Trainer.PROPERTY_TRAPS, result);
      
      return result;
   }

   public TrapPO createTrapsPO(String modifier)
   {
      TrapPO result = new TrapPO(new Trap[]{});
      
      result.setModifier(modifier);
      super.hasLink(Trainer.PROPERTY_TRAPS, result);
      
      return result;
   }

   public TrainerPO createTrapsLink(TrapPO tgt)
   {
      return hasLinkConstraint(tgt, Trainer.PROPERTY_TRAPS);
   }

   public TrainerPO createTrapsLink(TrapPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Trainer.PROPERTY_TRAPS, modifier);
   }

   public TrapSet getTraps()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Trainer) this.getCurrentMatch()).getTraps();
      }
      return null;
   }

}
