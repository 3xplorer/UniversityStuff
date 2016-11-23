/*
   Copyright (c) 2016 baeant
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package de.uniks.pm.game.model;

import de.uniks.pm.game.model.Zombie;
import de.uniks.pm.game.model.Ground;
import de.uniks.pm.game.model.Game;
import de.uniks.pm.game.model.Trainer;
   /**
    * 
    * @see <a href='../../../../../../../../src/main/java/de/uniks/pm/game/test/GenModelTest.java'>GenModelTest.java</a>
 */
   public  class Grass extends Ground
{

   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
      setGame(null);
      withoutTrainers(this.getTrainers().toArray(new Trainer[this.getTrainers().size()]));
      withoutZombies(this.getZombies().toArray(new Zombie[this.getZombies().size()]));
      firePropertyChange("REMOVE_YOU", this, null);
   }


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getX());
      result.append(" ").append(this.getY());
      return result.substring(1);
   }

}
