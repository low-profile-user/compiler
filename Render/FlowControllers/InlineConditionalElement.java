package Render.FlowControllers;

import java.util.LinkedList;

import Render.BlockElement;
import Render.Expressions.AbstractExpression;
import Render.Expressions.Value;

public class InlineConditionalElement extends AbstractExpression {
  public BlockElement mainBlock, elseBlock; 
  public LinkedList<AbstractExpression> verificationExpression;
  
  public InlineConditionalElement(String type, LinkedList<AbstractExpression> verificationExpression, String mainType, BlockElement mainBlock, String elseType, BlockElement elseBlock) {
    this.setType(type);

    if(!mainType.equals(elseType)) {
      System.out.println("[ERROR] Expressão ternária precisa retornar um tipo só nos dois cenários.");
      System.exit(1);
    }
    
    if(!mainType.equals(type)) {
      System.out.println("ERROR] Tipo de retorno inválido para a expressão ternária.\r\n");
      System.exit(1);
    }
    this.verificationExpression = verificationExpression;
    this.mainBlock = mainBlock;
    this.elseBlock = elseBlock;
  }

  public InlineConditionalElement(LinkedList<AbstractExpression> verificationExpression) {
    this.setType("double");

    BlockElement mainBlock = new BlockElement();
    BlockElement elseBlock = new BlockElement();

    mainBlock.addChild(new Value("double", "1"));
    mainBlock.addChild(new Value("double", "0"));

    this.verificationExpression = verificationExpression;
    this.mainBlock = mainBlock;
    this.elseBlock = elseBlock;
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
