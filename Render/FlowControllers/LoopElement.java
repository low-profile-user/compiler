package Render.FlowControllers;

import java.util.LinkedList;

import Render.AbstractRenderElement;
import Render.BlockElement;
import Render.Expressions.AbstractExpression;

public class LoopElement extends AbstractRenderElement {
  public BlockElement mainBlock; 
  public LinkedList<AbstractExpression> verificationExpression;
  
 

  public LoopElement(LinkedList<AbstractExpression> verificationExpression, BlockElement mainBlock) {
    this.verificationExpression = verificationExpression;
    this.mainBlock = mainBlock;
  }

  protected String renderVerification() {
    String verificationRender = "";
    for(AbstractExpression verification : this.verificationExpression) {
      verificationRender += verification.render();
    }
    return verificationRender;
  }
  public String render() {
    return this.render("", "");
  }

  public String render(String insertAfter) {
    return this.render("", insertAfter);
  }

  public String render(String insertBefore, String insertAfter) {
   
    String finalRender = this.mainBlock.render( this.renderVerification() + insertBefore, insertAfter + "goto " + this.mainBlock.getBeginLabel() + "\r\n");
    
    return finalRender;
  }

}
