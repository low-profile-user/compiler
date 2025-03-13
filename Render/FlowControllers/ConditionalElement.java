package Render.FlowControllers;

import java.util.LinkedList;

import Render.AbstractRenderElement;
import Render.BlockElement;
import Render.Expressions.AbstractExpression;

public class ConditionalElement extends AbstractRenderElement {
  public BlockElement mainBlock, elseBlock; 
  public LinkedList<AbstractExpression> verificationExpression;
  
  public ConditionalElement(LinkedList<AbstractExpression> verificationExpression, BlockElement mainBlock, BlockElement elseBlock) {
    this.verificationExpression = verificationExpression;
    this.mainBlock = mainBlock;
    this.elseBlock = elseBlock;
  }

  public ConditionalElement(LinkedList<AbstractExpression> verificationExpression, BlockElement mainBlock) {
    this.verificationExpression = verificationExpression;
    this.mainBlock = mainBlock;
  }

  private String renderVerification() {
    String verificationRender = "";
    for(AbstractExpression verification : this.verificationExpression) {
      verificationRender += verification.render();
    }
    return verificationRender;
  }

  public String render() {
    String finalRender = this.renderVerification();
    if (this.elseBlock != null) {
      finalRender += this.mainBlock.render("goto " + this.elseBlock.getEndLabel() + "\r\n");
      finalRender += this.elseBlock.render();
    } else {
      finalRender += this.mainBlock.render();
    }
    return finalRender;
  }

}
