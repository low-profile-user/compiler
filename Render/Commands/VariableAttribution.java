package Render.Commands;

import Render.BaseTypedElement;
import Render.AbstractRenderElement;

public class VariableAttribution extends AbstractRenderElement {
  static int qtyVariableInstances = 1;
  private int variableSerialID;
  private String referenceName;
  private BaseTypedElement variable = new BaseTypedElement();
  
  public VariableAttribution(String type, String referenceName) {
    this.variable.setType(type);
    this.variableSerialID = VariableAttribution.qtyVariableInstances;
    System.out.println(this.variable.getSlotSize());
    VariableAttribution.qtyVariableInstances += this.variable.getSlotSize();
    this.referenceName = referenceName;
  }

  public String getType() {
    return this.variable.getType();
  }

  public int getVariableSerialID() {
    return this.variableSerialID;
  }

  public VariableAttribution(String type, String referenceName, int serialID) {
    this.variableSerialID = serialID;
    this.referenceName = referenceName;
    this.variable.setType(type);
  }

  public String getReferenceName() {
    return this.referenceName;
  }

  public String render() {
    return  this.renderChildren() + this.variable.getTypePrefix() + "store " + this.variableSerialID + "\r\n";
  }
}
