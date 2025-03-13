package Render.FlowControllers;

import java.util.LinkedList;

import Render.BlockElement;
import Render.Expressions.AbstractExpression;

public class DoWhileElement extends LoopElement {
  public DoWhileElement(LinkedList<AbstractExpression> verificationExpression, BlockElement mainBlock) {
    super(verificationExpression, mainBlock);
  }

  public String render(String insertBefore, String insertAfter) {
    String finalRender = this.mainBlock.render( insertBefore, insertAfter + this.renderVerification() + "goto " + this.mainBlock.getBeginLabel() + "\r\n");
    return finalRender;
  }
}
