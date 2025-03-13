package Render.Commands;

import Render.AbstractRenderElement;

public class Output extends AbstractRenderElement {

  public String render() {
    String finalRender = "getstatic java/lang/System/out Ljava/io/PrintStream;\r\n";
    finalRender += this.renderChildren();
    finalRender += "invokevirtual java/io/PrintStream/println(D)V\r\n";
    return finalRender;
  }
}
