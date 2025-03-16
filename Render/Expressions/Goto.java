package Render.Expressions;


public class Goto extends AbstractExpression {
  private String value;
  
  public Goto( String value) {
    this.setType("int");
    this.value = value;
  }


  public String render() {
    return "goto "+this.value + "\r\n" + this.getConversionByteCode();
  }
}
