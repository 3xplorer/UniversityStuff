package de.uniks.pm.game.model.util;

import org.sdmlib.models.pattern.PatternObject;
import de.uniks.pm.game.model.Grass;
import de.uniks.pm.game.model.util.ZombiePO;
import de.uniks.pm.game.model.Zombie;
import de.uniks.pm.game.model.util.GrassPO;
import de.uniks.pm.game.model.util.ZombieSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import de.uniks.pm.game.model.util.GamePO;
import de.uniks.pm.game.model.Game;
import de.uniks.pm.game.model.util.TrainerPO;
import de.uniks.pm.game.model.Trainer;
import de.uniks.pm.game.model.util.TrainerSet;

public class GrassPO extends PatternObject<GrassPO, Grass>
{

    public GrassSet allMatches()
   {
      this.setDoAllMatches(true);
      
      GrassSet matches = new GrassSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Grass) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public GrassPO(){
      newInstance(null);
   }

   public GrassPO(Grass... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public GrassPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public ZombiePO createZombiesPO()
   {
      ZombiePO result = new ZombiePO(new Zombie[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Grass.PROPERTY_ZOMBIES, result);
      
      return result;
   }

   public ZombiePO createZombiesPO(String modifier)
   {
      ZombiePO result = new ZombiePO(new Zombie[]{});
      
      result.setModifier(modifier);
      super.hasLink(Grass.PROPERTY_ZOMBIES, result);
      
      return result;
   }

   public GrassPO createZombiesLink(ZombiePO tgt)
   {
      return hasLinkConstraint(tgt, Grass.PROPERTY_ZOMBIES);
   }

   public GrassPO createZombiesLink(ZombiePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Grass.PROPERTY_ZOMBIES, modifier);
   }

   public ZombieSet getZombies()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Grass) this.getCurrentMatch()).getZombies();
      }
      return null;
   }

   public GrassPO createXCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Grass.PROPERTY_X)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GrassPO createXCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Grass.PROPERTY_X)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GrassPO createXAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Grass.PROPERTY_X)
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
         return ((Grass) getCurrentMatch()).getX();
      }
      return 0;
   }
   
   public GrassPO withX(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Grass) getCurrentMatch()).setX(value);
      }
      return this;
   }
   
   public GrassPO createYCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Grass.PROPERTY_Y)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GrassPO createYCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Grass.PROPERTY_Y)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GrassPO createYAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Grass.PROPERTY_Y)
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
         return ((Grass) getCurrentMatch()).getY();
      }
      return 0;
   }
   
   public GrassPO withY(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Grass) getCurrentMatch()).setY(value);
      }
      return this;
   }
   
   public GamePO createGamePO()
   {
      GamePO result = new GamePO(new Game[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Grass.PROPERTY_GAME, result);
      
      return result;
   }

   public GamePO createGamePO(String modifier)
   {
      GamePO result = new GamePO(new Game[]{});
      
      result.setModifier(modifier);
      super.hasLink(Grass.PROPERTY_GAME, result);
      
      return result;
   }

   public GrassPO createGameLink(GamePO tgt)
   {
      return hasLinkConstraint(tgt, Grass.PROPERTY_GAME);
   }

   public GrassPO createGameLink(GamePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Grass.PROPERTY_GAME, modifier);
   }

   public Game getGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Grass) this.getCurrentMatch()).getGame();
      }
      return null;
   }

   public TrainerPO createTrainersPO()
   {
      TrainerPO result = new TrainerPO(new Trainer[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Grass.PROPERTY_TRAINERS, result);
      
      return result;
   }

   public TrainerPO createTrainersPO(String modifier)
   {
      TrainerPO result = new TrainerPO(new Trainer[]{});
      
      result.setModifier(modifier);
      super.hasLink(Grass.PROPERTY_TRAINERS, result);
      
      return result;
   }

   public GrassPO createTrainersLink(TrainerPO tgt)
   {
      return hasLinkConstraint(tgt, Grass.PROPERTY_TRAINERS);
   }

   public GrassPO createTrainersLink(TrainerPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Grass.PROPERTY_TRAINERS, modifier);
   }

   public TrainerSet getTrainers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Grass) this.getCurrentMatch()).getTrainers();
      }
      return null;
   }

}
