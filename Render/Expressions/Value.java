package Render.Expressions;


public class Value extends AbstractExpression {
  private String value;
  
  public Value(String type, String value) {
    this.setType(type);
    this.setValue(value);
  }

  public void setValue(String value) {
    switch (this.type) {
      case "double":
      case "float":
        if (!value.contains(".")) {
          this.value = value + ".0";
        } else {
          this.value = value;
        }
        break;
      case "int":
      case "long":
        if (!value.contains(".")) {
          this.value = value.split(".")[0];
        } else {
          this.value = value;
        }
      default:
        break;
    }
  }

  public String render() {
    return "ldc2_w "+this.value + "\r\n" + this.getConversionByteCode();
  }
}
