package de.uniks.pm.game.model.util;

import org.sdmlib.models.pattern.PatternObject;
import de.uniks.pm.game.model.Dice;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import de.uniks.pm.game.model.util.GamePO;
import de.uniks.pm.game.model.Game;
import de.uniks.pm.game.model.util.DicePO;

public class DicePO extends PatternObject<DicePO, Dice>
{

    public DiceSet allMatches()
   {
      this.setDoAllMatches(true);
      
      DiceSet matches = new DiceSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Dice) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public DicePO(){
      newInstance(null);
   }

   public DicePO(Dice... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public DicePO(String modifier)
   {
      this.setModifier(modifier);
   }
   public DicePO createValueCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Dice.PROPERTY_VALUE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public DicePO createValueCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Dice.PROPERTY_VALUE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public DicePO createValueAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Dice.PROPERTY_VALUE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getValue()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Dice) getCurrentMatch()).getValue();
      }
      return 0;
   }
   
   public DicePO withValue(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Dice) getCurrentMatch()).setValue(value);
      }
      return this;
   }
   
   public GamePO createGamePO()
   {
      GamePO result = new GamePO(new Game[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Dice.PROPERTY_GAME, result);
      
      return result;
   }

   public GamePO createGamePO(String modifier)
   {
      GamePO result = new GamePO(new Game[]{});
      
      result.setModifier(modifier);
      super.hasLink(Dice.PROPERTY_GAME, result);
      
      return result;
   }

   public DicePO createGameLink(GamePO tgt)
   {
      return hasLinkConstraint(tgt, Dice.PROPERTY_GAME);
   }

   public DicePO createGameLink(GamePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Dice.PROPERTY_GAME, modifier);
   }

   public Game getGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Dice) this.getCurrentMatch()).getGame();
      }
      return null;
   }

}
