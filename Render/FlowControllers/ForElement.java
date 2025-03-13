package Render.FlowControllers;

import java.util.LinkedList;

import Render.AbstractRenderElement;
import Render.BlockElement;
import Render.RenderElementInterface;
import Render.Expressions.AbstractExpression;

public class ForElement extends LoopElement {
  private LinkedList<RenderElementInterface> preContext, postContext;
  public ForElement(LinkedList<RenderElementInterface> preContext, LinkedList<RenderElementInterface> postContext, LinkedList<AbstractExpression> verificationExpression, BlockElement mainBlock) {
    super(verificationExpression, mainBlock);
    this.preContext = preContext;
    this.postContext = postContext;
  }

  @Override
  public String render() {
    String finalRender = "";
    finalRender += AbstractRenderElement.renderCollection(this.preContext);
    finalRender += super.render(AbstractRenderElement.renderCollection(postContext));
    return finalRender;
  }
}
