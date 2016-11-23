package de.uniks.pm.game.model.util;

import org.sdmlib.models.pattern.PatternObject;
import de.uniks.pm.game.model.Trap;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import de.uniks.pm.game.model.util.TrainerPO;
import de.uniks.pm.game.model.Trainer;
import de.uniks.pm.game.model.util.TrapPO;

public class TrapPO extends PatternObject<TrapPO, Trap>
{

    public TrapSet allMatches()
   {
      this.setDoAllMatches(true);
      
      TrapSet matches = new TrapSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Trap) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public TrapPO(){
      newInstance(null);
   }

   public TrapPO(Trap... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public TrapPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public TrapPO createSuccesRateCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Trap.PROPERTY_SUCCESRATE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TrapPO createSuccesRateCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Trap.PROPERTY_SUCCESRATE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TrapPO createSuccesRateAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Trap.PROPERTY_SUCCESRATE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getSuccesRate()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Trap) getCurrentMatch()).getSuccesRate();
      }
      return 0;
   }
   
   public TrapPO withSuccesRate(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Trap) getCurrentMatch()).setSuccesRate(value);
      }
      return this;
   }
   
   public TrainerPO createTrainerPO()
   {
      TrainerPO result = new TrainerPO(new Trainer[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Trap.PROPERTY_TRAINER, result);
      
      return result;
   }

   public TrainerPO createTrainerPO(String modifier)
   {
      TrainerPO result = new TrainerPO(new Trainer[]{});
      
      result.setModifier(modifier);
      super.hasLink(Trap.PROPERTY_TRAINER, result);
      
      return result;
   }

   public TrapPO createTrainerLink(TrainerPO tgt)
   {
      return hasLinkConstraint(tgt, Trap.PROPERTY_TRAINER);
   }

   public TrapPO createTrainerLink(TrainerPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Trap.PROPERTY_TRAINER, modifier);
   }

   public Trainer getTrainer()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Trap) this.getCurrentMatch()).getTrainer();
      }
      return null;
   }

}
