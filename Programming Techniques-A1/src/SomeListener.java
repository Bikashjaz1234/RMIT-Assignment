import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;


class SomeListener implements ActionListener
{
   private Robot robot;

   public SomeListener(Robot r)
   {
      this.robot = r;
   }

   public void actionPerformed(ActionEvent paramActionEvent)
   {
      if (paramActionEvent.getActionCommand().equals("SpeedUp"))
         robot.speedUp(2);
      else
         robot.slowDown(2);
      }
}

/*
 * Location: C:\Users\Craig\Documents\Programming
 * Techniques\PTAss1\PTAss1\classes\ Qualified Name: SomeListener JD-Core
 * Version: 0.6.2
 */