package Render.Commands;

import Render.AbstractRenderElement;
import Render.BaseTypedElement;

public class Output extends AbstractRenderElement {
  private String printStreamTypeMethod;

  public Output(int typeIndex) {
    this.setPrintStreamType(typeIndex);
  }

  public void setPrintStreamType(int typeIndex) {
    if (typeIndex < 0 || typeIndex > 4) {
      System.out.println("[ERROR] Output.setPrintStreamtype -> Invalid type.");
      System.exit(-1);
    }
    this.printStreamTypeMethod = BaseTypedElement.typeArrayByteCodeKey[typeIndex];
  }

  public String render() {
    String finalRender = "getstatic java/lang/System/out Ljava/io/PrintStream;\r\n";
    finalRender += this.renderChildren();
    finalRender += "invokevirtual java/io/PrintStream/println("+ this.printStreamTypeMethod +")V\r\n";
    return finalRender;
  }
}
