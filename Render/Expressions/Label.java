package Render.Expressions;


public class Label extends AbstractExpression {
  private String value;
  
  public Label( String value) {
    this.setType("int");
    this.value = value;
  }


  public String render() {
    return ""+this.value + ":\r\n" + this.getConversionByteCode();
  }
}
