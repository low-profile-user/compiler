package Render;

public class BlockElement extends AbstractRenderElement {
  static int qtyBlockInstances = 0;
  private int blockSerialID;

  public static int getQtyBlockInstances() {
    return BlockElement.qtyBlockInstances;
  }

  public BlockElement() {
    BlockElement.qtyBlockInstances++;
    this.blockSerialID = BlockElement.qtyBlockInstances;
  }
  public String getBeginLabel() {
    return "BlockBegin" + this.blockSerialID;
  }

  public String getEndLabel() {
    return "BlockEnd" + this.blockSerialID ;
  }

  public String render() {
    return this.render("","");
  }
  
  public String render(String insertAfter) {
    return this.render("",insertAfter);
  }

  public String render(String insertBefore, String insertAfter) {
    String renderResult = "";
    
    renderResult += this.getBeginLabel()  +":\r\n" ;
    renderResult += insertBefore;
    renderResult += this.renderChildren();
    renderResult += insertAfter;
    renderResult += this.getEndLabel() + ":\r\n";

    return renderResult;
  }
}
