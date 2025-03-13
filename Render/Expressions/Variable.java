package Render.Expressions;

public class Variable extends  AbstractExpression {
  private int variableSerialID;
  
  public Variable(String type, int serialID) {
    this.variableSerialID = serialID;
    this.setType(type);
  }

  public String render() {
    return this.typePrefix + "load "+this.variableSerialID + "\r\n" + this.getConversionByteCode();
  }
}
